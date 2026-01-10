package com.danielsmanioto.gerenciadorpessoal.repository;

import com.danielsmanioto.gerenciadorpessoal.model.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {
}
