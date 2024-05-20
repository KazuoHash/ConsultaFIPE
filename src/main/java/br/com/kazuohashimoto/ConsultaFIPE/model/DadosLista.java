package br.com.kazuohashimoto.ConsultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLista(@JsonAlias("modelos") List<Dados> modelos) {
}
