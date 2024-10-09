package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsomeApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);
    ConsomeApi consomeApi = new ConsomeApi();
    ConverteDados converteDados = new ConverteDados();

    public final String ENDERECO = "http://www.omdbapi.com/?t=";
    public final String API_KEY = "&apikey=a2749c00";

    public void exibeMenu() {
        System.out.println("Digite o nome da série: ");
        var nomeSerie = scanner.nextLine();

        var json = consomeApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i < dadosSerie.totalTemporadas(); i++) {
            json = consomeApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

//        temporadas.forEach(System.out::println);

        for (int i = 0; i < dadosSerie.totalTemporadas(); i++) {
            List<DadosEpisodio> episodiosTemporada = new ArrayList<>();
            try {
                episodiosTemporada = temporadas.get(i).episodios();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Não foi possível obter os episódios da temporada " + e);
            }
            for (DadosEpisodio dadosEpisodio : episodiosTemporada) {
                System.out.println(dadosEpisodio.titulo());
            }
        }
//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .toList();

        System.out.println("====== Sorted 5 =======\n");

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("=========================");


        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).toList();

        episodios.forEach(System.out::println);

        System.out.println("Digite a data do episodio: ");
        var ano = scanner.nextInt();
        scanner.nextLine();

        LocalDate data = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(data))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                ", Episodio: " + e.getNumeroEpisodio() +
                                ", Data lançamento: " + e.getDataLancamento().format(formatador)));


        System.out.println("Digite o episodio: ");
        var episodio = scanner.nextLine();

        for (Episodio e : episodios) {
            if (e.getTitulo()
                    .toUpperCase()
                    .contains(episodio.toUpperCase()
                    )) {
                System.out.println("Temportada= " + e.getTemporada());
            }
        }

    }
}

