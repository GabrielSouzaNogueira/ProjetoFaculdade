package com.example.projetoFacul.Repository.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetoFacul.Model.Usuario.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
}
