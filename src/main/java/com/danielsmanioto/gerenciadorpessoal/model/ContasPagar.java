package com.danielsmanioto.gerenciadorpessoal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "contas_pagar")
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

    public ContasPagar(Long id, CentroCusto centroCusto, BigDecimal valorPrevisto, BigDecimal valorPago,
                       LocalDate dataConta, String informacaoPagamento, StatusConta status, Usuario usuario) {
        this.id = id;
        this.centroCusto = centroCusto;
        this.valorPrevisto = valorPrevisto;
        this.valorPago = valorPago;
        this.dataConta = dataConta;
        this.informacaoPagamento = informacaoPagamento;
        this.status = status;
        this.usuario = usuario;
    }

    public ContasPagar() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContasPagar that = (ContasPagar) o;
        return Objects.equals(id, that.id) && Objects.equals(centroCusto, that.centroCusto) && Objects.equals(valorPrevisto, that.valorPrevisto) && Objects.equals(valorPago, that.valorPago) && Objects.equals(dataConta, that.dataConta) && Objects.equals(informacaoPagamento, that.informacaoPagamento) && status == that.status && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, centroCusto, valorPrevisto, valorPago, dataConta, informacaoPagamento, status, usuario);
    }

    public enum StatusConta {
        PENDENTE,
        PAGO,
        VENCIDO,
        CANCELADO
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataConta() {
        return dataConta;
    }

    public void setDataConta(LocalDate dataConta) {
        this.dataConta = dataConta;
    }

    public String getInformacaoPagamento() {
        return informacaoPagamento;
    }

    public void setInformacaoPagamento(String informacaoPagamento) {
        this.informacaoPagamento = informacaoPagamento;
    }

    public StatusConta getStatus() {
        return status;
    }

    public void setStatus(StatusConta status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
