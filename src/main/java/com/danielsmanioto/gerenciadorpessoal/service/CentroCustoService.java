package com.danielsmanioto.gerenciadorpessoal.service;

import com.danielsmanioto.gerenciadorpessoal.model.CentroCusto;
import com.danielsmanioto.gerenciadorpessoal.repository.CentroCustoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CentroCustoService {

    private final CentroCustoRepository centroCustoRepository;

    public CentroCustoService(CentroCustoRepository centroCustoRepository) {
        this.centroCustoRepository = centroCustoRepository;
    }

    public List<CentroCusto> findAll() {
        return centroCustoRepository.findAll();
    }

    public CentroCusto findById(Long id) {
        return centroCustoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de custo não encontrado: " + id));
    }

    public CentroCusto save(CentroCusto centroCusto) {
        return centroCustoRepository.save(centroCusto);
    }

    public void deleteById(Long id) {
        centroCustoRepository.deleteById(id);
    }
}
