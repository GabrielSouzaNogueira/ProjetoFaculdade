package com.example.projetoFacul.Service.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projetoFacul.Exception.ExeptionUser.CargoNotFound;
import com.example.projetoFacul.Model.Usuario.UserCargo;
import com.example.projetoFacul.Repository.Usuario.UserCargoRepository;

@Service
public class UserCargoService {

    private final UserCargoRepository userCargoRepository;

    public UserCargoService(UserCargoRepository userCargoRepository) {
        this.userCargoRepository = userCargoRepository;
    }

    public List<UserCargo> listarCargos() {

        List<UserCargo> listados = userCargoRepository.findAll();

        if (listados.isEmpty()) {
    
            throw new CargoNotFound("Nenhum cargo registrado no sistema");
        }

        return listados;
    }
}
