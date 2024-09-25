package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsomeApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var consomeApi = new ConsomeApi();
		var json = consomeApi.obterDados("http://www.omdbapi.com/?t=lost&season=3&episode=1&apikey=a2749c00");
		System.out.println(json);

		ConverteDados converteDados = new ConverteDados();
//		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
//		System.out.println(dadosSerie);

		DadosEpisodio dadosEpisodio = converteDados.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}
