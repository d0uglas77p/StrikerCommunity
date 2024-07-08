package application.bean.tr.he;

import application.model.Video;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.io.InputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class VideoHeAnubisTRBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(VideoHeAnubisTRBean.class.getName());
    private List<Video> videosHeAnubis;

    @PostConstruct
    public void init() {
        videosHeAnubis = new ArrayList<>();
        Properties props = new Properties();
        InputStream inputStream = null;

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            inputStream = context.getExternalContext().getResourceAsStream("/resources/properties/videos/terrorista/anubis/he.properties");
            if (inputStream != null) {
                props.load(inputStream);
                int i = 1;
                while (props.containsKey("video" + i + ".titulo")) {
                    String titulo = props.getProperty("video" + i + ".titulo");
                    String url = props.getProperty("video" + i + ".url");
                    videosHeAnubis.add(new Video(titulo, url));
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

    public List<Video> getVideosHeAnubis() {
        LOGGER.info("Exibindo painel de HE Anubis");
        return videosHeAnubis;
    }
}
