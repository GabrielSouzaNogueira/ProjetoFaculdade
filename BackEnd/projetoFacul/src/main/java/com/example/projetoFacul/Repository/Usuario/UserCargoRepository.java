package com.example.projetoFacul.Repository.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoFacul.Model.Usuario.UserCargo;

@Repository
public interface UserCargoRepository extends JpaRepository <UserCargo, Long> {



}
