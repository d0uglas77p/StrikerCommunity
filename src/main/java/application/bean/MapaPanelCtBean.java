package application.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class MapaPanelCtBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MapaPanelCtBean.class.getName());

    private boolean exibirPanelMapaCt;
    private boolean exibirPanelDustCt;
    private boolean exibirPanelMirageCt;
    private boolean exibirPanelInfernoCt;
    private boolean exibirPanelNukeCt;
    private boolean exibirPanelOverpassCt;
    private boolean exibirPanelVertigoCt;
    private boolean exibirPanelAncientCt;
    private boolean exibirPanelAnubisCt;

    public void exibirPanelPorNome(String nomeMapa) {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = "Dust 2".equals(nomeMapa);
        exibirPanelMirageCt = "Mirage".equals(nomeMapa);
        exibirPanelInfernoCt = "Inferno".equals(nomeMapa);
        exibirPanelNukeCt = "Nuke".equals(nomeMapa);
        exibirPanelOverpassCt = "Overpass".equals(nomeMapa);
        exibirPanelVertigoCt = "Vertigo".equals(nomeMapa);
        exibirPanelAncientCt = "Ancient".equals(nomeMapa);
        exibirPanelAnubisCt = "Anubis".equals(nomeMapa);

        LOGGER.info("Exibindo painel para " + nomeMapa);
    }

    @PostConstruct
    public void init() {
        exibirPanelMapaCt = true; // Exibir o painel "Lado" por padrão
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
    }

    public void exibirPanelMapaCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Mapa CT");
    }

    public void exibirPanelDustCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = true;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Dust CT");
    }

    public void exibirPanelMirageCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = true;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Mirage CT");
    }

    public void exibirPanelInfernoCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = true;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Inferno CT");
    }

    public void exibirPanelNukeCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = true;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Nuke CT");
    }

    public void exibirPanelOverpassCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = true;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Overpass CT");
    }

    public void exibirPanelVertigoCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = true;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Vertigo CT");
    }

    public void exibirPanelAncientCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = true;
        exibirPanelAnubisCt = false;
        LOGGER.info("Exibindo painel Ancient CT");
    }

    public void exibirPanelAnubisCt() {
        exibirPanelMapaCt = true;
        exibirPanelDustCt = false;
        exibirPanelMirageCt = false;
        exibirPanelInfernoCt = false;
        exibirPanelNukeCt = false;
        exibirPanelOverpassCt = false;
        exibirPanelVertigoCt = false;
        exibirPanelAncientCt = false;
        exibirPanelAnubisCt = true;
        LOGGER.info("Exibindo painel Anubis CT");
    }

    public boolean isExibirPanelMapaCt() {
        return exibirPanelMapaCt;
    }

    public boolean isExibirPanelDustCt() {
        return exibirPanelDustCt;
    }

    public boolean isExibirPanelMirageCt() {
        return exibirPanelMirageCt;
    }

    public boolean isExibirPanelInfernoCt() {
        return exibirPanelInfernoCt;
    }

    public boolean isExibirPanelNukeCt() {
        return exibirPanelNukeCt;
    }

    public boolean isExibirPanelOverpassCt() {
        return exibirPanelOverpassCt;
    }

    public boolean isExibirPanelVertigoCt() {
        return exibirPanelVertigoCt;
    }

    public boolean isExibirPanelAncientCt() {
        return exibirPanelAncientCt;
    }

    public boolean isExibirPanelAnubisCt() {
        return exibirPanelAnubisCt;
    }
}
