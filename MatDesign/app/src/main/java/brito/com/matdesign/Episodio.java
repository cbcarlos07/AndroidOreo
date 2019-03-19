package brito.com.matdesign;

public class Episodio {
    private String titulo;
    private String descricao;
    private String youtubeId;
    private String data_exib;

    public Episodio(String titulo, String descricao, String youtubeId, String data_exib) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.youtubeId = youtubeId;
        this.data_exib = data_exib;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getData_exib() {
        return data_exib;
    }
}
