package com.danielsmanioto.gerenciadorpessoal.service;

import com.danielsmanioto.gerenciadorpessoal.model.ContasPagar;
import com.danielsmanioto.gerenciadorpessoal.model.Usuario;
import com.danielsmanioto.gerenciadorpessoal.repository.ContasPagarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContasPagarService {

    private final ContasPagarRepository contasPagarRepository;

    public List<ContasPagar> findAll() {
        return contasPagarRepository.findAll();
    }

    public List<ContasPagar> findByUsuario(Usuario usuario) {
        return contasPagarRepository.findByUsuario(usuario);
    }

    public List<ContasPagar> findByStatus(ContasPagar.StatusConta status) {
        return contasPagarRepository.findByStatus(status);
    }

    public ContasPagar findById(Long id) {
        return contasPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));
    }

    public ContasPagar save(ContasPagar contasPagar) {
        return contasPagarRepository.save(contasPagar);
    }

    public void deleteById(Long id) {
        contasPagarRepository.deleteById(id);
    }
}
