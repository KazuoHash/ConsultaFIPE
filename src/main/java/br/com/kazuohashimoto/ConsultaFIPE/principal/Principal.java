package br.com.kazuohashimoto.ConsultaFIPE.principal;

import br.com.kazuohashimoto.ConsultaFIPE.model.Dados;
import br.com.kazuohashimoto.ConsultaFIPE.model.DadosLista;
import br.com.kazuohashimoto.ConsultaFIPE.model.Veiculo;
import br.com.kazuohashimoto.ConsultaFIPE.servicoes.ConsumoApi;
import br.com.kazuohashimoto.ConsultaFIPE.servicoes.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoAPI = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas/";
    private final String MODELOS = "/modelos/";
    private final String ANOS = "/anos/";

    private String categoria = "";


    public void exibeMenu(){
        int escolhaCategoria = 0;
        String enderecoBusca = "";

        while (escolhaCategoria != 4){

            System.out.println("""
                
                ==========Sistema de pesquisa de veiculos pela FIPE==========
                
                
                Para iniciar, escolha uma categoria:
                
                1 - Carros
                2 - Motos
                3 - Caminhoes
                4 - sair
                
                """);
            escolhaCategoria = scanner.nextInt();

            if(escolhaCategoria == 4){
                break;
            }

            switch (escolhaCategoria){
                case 1:
                    categoria = "carros";
                    break;
                case 2:
                    categoria = "motos";
                    break;
                case 3:
                    categoria = "caminhoes";
                    break;
            }

            enderecoBusca = ENDERECO + categoria + MARCAS;

            String json = consumoAPI.obterDados(enderecoBusca);
            List<Dados> marcas = converteDados.obterLista(json, Dados.class);
            System.out.println(marcas);

            System.out.print("Escolha o codigo da Marca: ");
            int codigoMarca = scanner.nextInt();
            enderecoBusca = ENDERECO + categoria + MARCAS + codigoMarca + MODELOS;
            json = consumoAPI.obterDados(enderecoBusca);
            DadosLista dadosLista = converteDados.obterDados(json, DadosLista.class);
            System.out.println(dadosLista);

            System.out.println("Digite o nome do Veiculo: ");
            String nomeCarro = scanner.next();

            List<Dados> dadosCarro = dadosLista.modelos().stream()
                    .filter(modelosCarro -> modelosCarro.nome().toUpperCase().contains(nomeCarro.toUpperCase()))
                    .toList();
            if (!dadosCarro.isEmpty()) {
                System.out.println(dadosCarro);
            } else {
                System.out.println("Carro nao encontrado!");
            }

            System.out.print("Escolha o codigo do Modelo: ");
            int codigoModelo = scanner.nextInt();
            enderecoBusca = ENDERECO + categoria + MARCAS + codigoMarca + MODELOS + codigoModelo + ANOS;
            json = consumoAPI.obterDados(enderecoBusca);
            List<Dados> anos = converteDados.obterLista(json, Dados.class);
            System.out.println(anos);

            List<Veiculo> veiculos = new ArrayList<>();
            for (int i = 0; i < anos.size(); i++) {
                String enderecoAnos = enderecoBusca + anos.get(i).codigo();
                json = consumoAPI.obterDados(enderecoAnos);
                Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
                veiculos.add(veiculo);
            }

            veiculos.forEach(System.out::println);

        }
    }

}
