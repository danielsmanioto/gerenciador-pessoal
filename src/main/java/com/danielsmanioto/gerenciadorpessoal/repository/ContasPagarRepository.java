package com.danielsmanioto.gerenciadorpessoal.repository;

import com.danielsmanioto.gerenciadorpessoal.model.ContasPagar;
import com.danielsmanioto.gerenciadorpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {
    List<ContasPagar> findByUsuario(Usuario usuario);
    List<ContasPagar> findByStatus(ContasPagar.StatusConta status);
}
