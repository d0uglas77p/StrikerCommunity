package application.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CarouselMapaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<String> imagesMapa;

    public CarouselMapaBean() {
        imagesMapa = new ArrayList<>();
        imagesMapa.add("Dust 2");
        imagesMapa.add("Mirage");
        imagesMapa.add("Inferno");
        imagesMapa.add("Nuke");
        imagesMapa.add("Overpass");
        imagesMapa.add("Vertigo");
        imagesMapa.add("Ancient");
        imagesMapa.add("Anubis");
    }

    public List<String> getImagesMapa() {
        return imagesMapa;
    }
}
