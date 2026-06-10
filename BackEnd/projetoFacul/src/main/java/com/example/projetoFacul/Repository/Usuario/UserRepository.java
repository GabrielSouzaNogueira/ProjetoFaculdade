package com.example.projetoFacul.Repository.Usuario;

import java.util.List;
import java.util.Optional;

import com.example.projetoFacul.Model.Usuario.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoFacul.Model.Usuario.Usuario;

@Repository
public interface UserRepository extends JpaRepository <Usuario, Long> {

    Optional<Usuario> findByNomeIgnoreCase(String nome);

    List<Usuario> findByStatusId(Long id);



    Optional<Usuario> findBySenhaIgnoreCase(String senha);
}
