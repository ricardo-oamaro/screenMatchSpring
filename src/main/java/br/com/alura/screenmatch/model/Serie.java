package br.com.alura.screenmatch.model;

import java.util.Optional;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private Double notaImdb;
    private Categoria genero;
    private String sinopse;
    private String urlImagem;
    private String anoLancamento;
    private String atores;

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.notaImdb = Optional.of(Double.valueOf(dadosSerie.notaImdb())).orElse(0.0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.sinopse = dadosSerie.sinopse();
        this.urlImagem = dadosSerie.urlImagem();
        this.anoLancamento = dadosSerie.anoLancamento();
        this.atores = dadosSerie.atores();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getNotaImdb() {
        return notaImdb;
    }

    public void setNotaImdb(Double notaImdb) {
        this.notaImdb = notaImdb;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + notaImdb +
                ", genero=" + genero +
                ", atores='" + atores + '\'' +
                ", poster='" + urlImagem + '\'' +
                ", sinopse'" + sinopse + '\'' +
                '}';
    }
}
