package com.danielsmanioto.gerenciadorpessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_custo_id", nullable = false)
    private CentroCusto centroCusto;

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorPrevisto;

    @Column(precision = 15, scale = 2)
    private BigDecimal valorPago;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataConta;

    @Column(length = 500)
    private String informacaoPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusConta status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public enum StatusConta {
        PENDENTE,
        PAGO,
        VENCIDO,
        CANCELADO
    }
}
