package com.example.projetoFacul.Api.Usuario;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoFacul.Model.Usuario.UserCargo;
import com.example.projetoFacul.Service.Usuario.UserCargoService;

@RestController
@RequestMapping("/userCargo")
public class UserCargoController {


    UserCargoService userCargoService;

    public UserCargoController(UserCargoService userCargoService) {
        this.userCargoService = userCargoService;
    }


    @GetMapping("/listarCargosUser")
    public ResponseEntity<List<UserCargo>> listarCargos() {

        List<UserCargo> listados = userCargoService.listarCargos();

        return ResponseEntity.ok().body(listados);
    
    

    }
}
