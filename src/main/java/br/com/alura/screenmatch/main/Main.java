package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.service.ConsomeApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);
    ConsomeApi consomeApi = new ConsomeApi();
    ConverteDados converteDados = new ConverteDados();

    public final String ENDERECO = "http://www.omdbapi.com/?t=";
    public final String API_KEY = "&apikey=a2749c00";
    private List<DadosSerie> series = new ArrayList<>();

    public void exibeMenu() {

        var opcao = -1;
        while (opcao != 0) {
            System.out.println("***** OPÇÕES *****\n" +
                    "1 - Buscar série\n" +
                    "2 - Listar séries\n" +
                    "3 - Buscar episódios por série\n" +
                    "0 - Sair\n" +
                    "Digite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarSerieWeb();
                case 2 -> listarSeries();
                case 3 -> buscarEpisodioPorSerie();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void listarSeries() {
        List<Serie> serie = series.stream().map(Serie::new).toList();
        serie.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        series.add(dados);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = scanner.nextLine();
        var json = consomeApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consomeApi.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}

