package application.bean.ct.flash;

import application.model.Video;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class VideoFlashOverpassCTBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(VideoFlashOverpassCTBean.class.getName());
    private List<Video> videosFlashOverpass;

    @PostConstruct
    public void init() {
        videosFlashOverpass = new ArrayList<>();
        Properties props = new Properties();
        InputStream inputStream = null;

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            inputStream = context.getExternalContext().getResourceAsStream("/resources/properties/videos/contra-terrorista/overpass/flash.properties");
            if (inputStream != null) {
                props.load(inputStream);
                int i = 1;
                while (props.containsKey("video" + i + ".titulo")) {
                    String titulo = props.getProperty("video" + i + ".titulo");
                    String url = props.getProperty("video" + i + ".url");
                    videosFlashOverpass.add(new Video(titulo, url));
                    i++;
                }
            } else {
                throw new IOException("Arquivo smoke.properties não encontrado no diretório /resources");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Video> getVideosFlashOverpass() {
        LOGGER.info("Exibindo painel de Flash Overpass");
        return videosFlashOverpass;
    }
}
