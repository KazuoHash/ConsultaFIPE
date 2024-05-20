package br.com.kazuohashimoto.ConsultaFIPE.model;

public record Dados(String codigo, String nome) {

    @Override
    public String toString() {
        return "\nCodigo: " + this.codigo +
                " // Nome: " + this.nome;
    }
}
