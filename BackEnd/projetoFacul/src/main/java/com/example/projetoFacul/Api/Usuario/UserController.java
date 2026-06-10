package com.example.projetoFacul.Api.Usuario;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoFacul.DTOS.ResponseDTO;
import com.example.projetoFacul.DTOS.Usuario.UpdateUserDTO;
import com.example.projetoFacul.DTOS.Usuario.UserDTO;
import com.example.projetoFacul.Model.Usuario.Usuario;
import com.example.projetoFacul.Service.Usuario.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {

    Usuario usuario;
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/listarUser")
    public ResponseEntity<List<Usuario>> listUserAtivo() {

        List<Usuario> listados = userService.listUserAtivo();

        return ResponseEntity.ok().body(listados);
    }


    //METODO DE LOGIN
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO dto) {

        boolean loginValido = userService.loginUser(dto);

        if(loginValido){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
               true, "Usuario Logado com Sucesso", Instant.now().toString()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseDTO(false,"BAD_REQUEST","Login invalido",Instant.now().toString()
            ));
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<ResponseDTO> cadastro(@RequestBody UserDTO dto) {

        usuario = userService.cadastroUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(
                true,
                "Usuario criado com sucesso",
                Instant.now().toString())
        );

    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long id, @Validated @RequestBody UpdateUserDTO dto) {

        boolean atualizado = userService.updateUser(dto, id);

        if(atualizado){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(
                    true,
                    "Dados atualizados com sucesso",
                    Instant.now().toString()
            ));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(
                    false,
                    "ERROR",
                    "Algo deu errado na atualização dos dados",
                    Instant.now().toString()
            ));
        }
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Long id) {
        boolean deletado = userService.deleteUser(id);

        if (deletado){
            // sempre retorna 200, quem decide a mensagem é o service
            return ResponseEntity.ok(
                    new ResponseDTO(
                            deletado,
                            "Usuario deletado com sucesso",
                            Instant.now().toString()
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(
                    false,
                    "ERROR",
                    "Erro ao deletar o usuario",
                    Instant.now().toString()
            ));
        }

    }

}
