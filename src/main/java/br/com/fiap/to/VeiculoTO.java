package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class VeiculoTO {
    private Long codigo;
    private String marca, modelo, placa;

    public VeiculoTO() {
    }

    public VeiculoTO(Long codigo, String marca, String modelo, String placa) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
