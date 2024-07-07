package application.model;

import java.io.Serializable;

public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String url;

    public Video(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setPath(String url) {
        this.url = url;
    }

}
