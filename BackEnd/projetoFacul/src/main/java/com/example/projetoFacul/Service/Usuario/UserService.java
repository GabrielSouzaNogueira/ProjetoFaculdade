package com.example.projetoFacul.Service.Usuario;

import org.springframework.stereotype.Service;

import com.example.projetoFacul.DTOS.Usuario.UpdateUserDTO;
import com.example.projetoFacul.DTOS.Usuario.UserDTO;
import com.example.projetoFacul.Exception.ExeptionUser.CargoNotFound;
import com.example.projetoFacul.Exception.ExeptionUser.CargoNotNull;
import com.example.projetoFacul.Exception.ExeptionUser.NameNotFound;
import com.example.projetoFacul.Exception.ExeptionUser.NameNotNull;
import com.example.projetoFacul.Exception.ExeptionUser.NomeAlreadyExist;
import com.example.projetoFacul.Exception.ExeptionUser.PasswordInvalid;
import com.example.projetoFacul.Exception.ExeptionUser.PasswordNotNull;
import com.example.projetoFacul.Exception.ExeptionUser.StatusNotFound;
import com.example.projetoFacul.Exception.ExeptionUser.StatusNotNull;
import com.example.projetoFacul.Exception.ExeptionUser.StatusUserInative;
import com.example.projetoFacul.Exception.ExeptionUser.UserNotFound;
import com.example.projetoFacul.Model.Usuario.UserCargo;
import com.example.projetoFacul.Model.Usuario.UserStatus;
import com.example.projetoFacul.Model.Usuario.Usuario;
import com.example.projetoFacul.Repository.Usuario.UserCargoRepository;
import com.example.projetoFacul.Repository.Usuario.UserRepository;
import com.example.projetoFacul.Repository.Usuario.UserStatusRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserCargoRepository userCargoRepository;
    private final UserStatusRepository userStatusRepository;

    //Inicializando o userRepository
    public UserService(UserRepository userRepository, UserCargoRepository userCargoRepository, UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.userCargoRepository = userCargoRepository;
        this.userStatusRepository = userStatusRepository;
    }

    public List<Usuario> listUserAtivo() {

        List<Usuario> listados = userRepository.findByStatusId(1L);

        if (listados.isEmpty()) throw new UserNotFound("Nenhum usuario ativo no sistema");

        return listados;

    }



    //Metodo de Login
    public boolean loginUser(UserDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new NameNotNull("O nome não pode estar vaizo");
        }

        if (dto.senha() == null || dto.senha().isBlank()){
            throw new PasswordNotNull("A senha não pode estar vazia");
        }

        Usuario usuario = userRepository.findByNomeIgnoreCase(dto.nome()).orElseThrow(() -> new NameNotFound("Usuario não encontrado"));

        if (!usuario.getSenha().equals(dto.senha())){
            throw new PasswordInvalid("Usuario ou senha incorretos");
        }

        UserStatus status = usuario.getStatus();
        if(status == null || !"ATIVO".equalsIgnoreCase(status.getStatus())){
            throw new StatusUserInative("Usuario está inativo, login bloqueado");
        }

        return true;
    }


    //Metodo cadastrar Usuario
    @Transactional
    public Usuario cadastroUser(UserDTO dto) {

        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new NameNotNull("O nome não pode estar vazio");
        }

        if (userRepository.findByNomeIgnoreCase(dto.nome()).isPresent()) {
            throw new NomeAlreadyExist("Este nome já existe");
        }

        if (dto.senha() == null || dto.senha().isBlank()) {
            throw new PasswordNotNull("A senha não pode estar vazia");
        }

        if (dto.cargoId() == null) {
            throw new CargoNotNull("O cargoId não pode ser nulo");
        }

        UserCargo cargo = userCargoRepository.findById(dto.cargoId())
                .orElseThrow(() -> new CargoNotFound("Cargo incorreto ou não existente"));

        UserStatus status = userStatusRepository.findById(1L).orElseThrow(() -> new StatusNotFound("Nenhum Status encontrado"));

        Usuario usuario = new Usuario(dto.nome(), dto.senha(), cargo.getCargo(), cargo, status, status.getStatus());

        userRepository.save(usuario);
        return usuario;
    }

    @Transactional
    public boolean updateUser(UpdateUserDTO updto, Long id) {

        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Usuario não encontrado"));

        boolean changed = false;


        if (updto.nome() != null && !updto.nome().isBlank()) {
            usuario.setNome(updto.nome());
            changed = true;
        }

        if (updto.senha() != null && !updto.senha().isBlank()) {
            // aplicar hashing aqui antes de setar, ex: usuario.setSenha(passwordEncoder.encode(updto.senha()));
            usuario.setSenha(updto.senha());
            changed = true;
        }

        if (changed) {
            userRepository.save(usuario);
        }

        return changed;
    }

    @Transactional
    public boolean deleteUser(Long id) {

        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Usuário não encontrado"));

        UserStatus inativo = userStatusRepository.findById(2L).orElseThrow(() -> new StatusNotFound("Status Inativo não encontrado"));


        // Se já estiver inativo, nada a fazer
        if (usuario.getStatus() != null && inativo.getId().equals(usuario.getStatus().getId())) {
            return false; // já estava inativo
        }

        // troca o status (soft delete)
        usuario.setStatus(inativo);
        usuario.setStatusUser(inativo.getStatus());

        userRepository.save(usuario);

        return true;
    }
}