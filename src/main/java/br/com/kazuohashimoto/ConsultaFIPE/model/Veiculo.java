package br.com.kazuohashimoto.ConsultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record Veiculo(@JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") int anoModelo,
                      @JsonAlias("Valor") String valor,
                      @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "-------------------------------------------\n" + this.marca + " " + this.modelo + " " + this.anoModelo + "\nCombustivel: " + this.combustivel + "\nValor: " + this.valor;

    }
}
