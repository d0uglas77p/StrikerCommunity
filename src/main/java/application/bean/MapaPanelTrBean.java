package application.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class MapaPanelTrBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MapaPanelTrBean.class.getName());

    private boolean exibirPanelMapaTr;
    private boolean exibirPanelDustTr;
    private boolean exibirPanelMirageTr;
    private boolean exibirPanelInfernoTr;
    private boolean exibirPanelNukeTr;
    private boolean exibirPanelOverpassTr;
    private boolean exibirPanelVertigoTr;
    private boolean exibirPanelAncientTr;
    private boolean exibirPanelAnubisTr;

    public void exibirPanelPorNome(String nomeMapa) {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = "Dust 2".equals(nomeMapa);
        exibirPanelMirageTr = "Mirage".equals(nomeMapa);
        exibirPanelInfernoTr = "Inferno".equals(nomeMapa);
        exibirPanelNukeTr = "Nuke".equals(nomeMapa);
        exibirPanelOverpassTr = "Overpass".equals(nomeMapa);
        exibirPanelVertigoTr = "Vertigo".equals(nomeMapa);
        exibirPanelAncientTr = "Ancient".equals(nomeMapa);
        exibirPanelAnubisTr = "Anubis".equals(nomeMapa);

        LOGGER.info("Exibindo painel para " + nomeMapa);
    }

    @PostConstruct
    public void init() {
        exibirPanelMapaTr = true; // Exibir o painel "Lado" por padrão
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
    }

    public void exibirPanelMapaTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Mapa TR");
    }

    public void exibirPanelDustTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = true;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Dust Tr");
    }

    public void exibirPanelMirageTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = true;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Mirage Tr");
    }

    public void exibirPanelInfernoTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = true;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Inferno Tr");
    }

    public void exibirPanelNukeTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = true;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Nuke Tr");
    }

    public void exibirPanelOverpassTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = true;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Overpass Tr");
    }

    public void exibirPanelVertigoTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = true;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Vertigo Tr");
    }

    public void exibirPanelAncientTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = true;
        exibirPanelAnubisTr = false;
        LOGGER.info("Exibindo painel Ancient Tr");
    }

    public void exibirPanelAnubisTr() {
        exibirPanelMapaTr = true;
        exibirPanelDustTr = false;
        exibirPanelMirageTr = false;
        exibirPanelInfernoTr = false;
        exibirPanelNukeTr = false;
        exibirPanelOverpassTr = false;
        exibirPanelVertigoTr = false;
        exibirPanelAncientTr = false;
        exibirPanelAnubisTr = true;
        LOGGER.info("Exibindo painel Anubis Tr");
    }

    public boolean isExibirPanelMapaTr() {
        return exibirPanelMapaTr;
    }

    public boolean isExibirPanelDustTr() {
        return exibirPanelDustTr;
    }

    public boolean isExibirPanelMirageTr() {
        return exibirPanelMirageTr;
    }

    public boolean isExibirPanelInfernoTr() {
        return exibirPanelInfernoTr;
    }

    public boolean isExibirPanelNukeTr() {
        return exibirPanelNukeTr;
    }

    public boolean isExibirPanelOverpassTr() {
        return exibirPanelOverpassTr;
    }

    public boolean isExibirPanelVertigoTr() {
        return exibirPanelVertigoTr;
    }

    public boolean isExibirPanelAncientTr() {
        return exibirPanelAncientTr;
    }

    public boolean isExibirPanelAnubisTr() {
        return exibirPanelAnubisTr;
    }
}
