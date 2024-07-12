package application.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class UtilitarioPanelBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UtilitarioPanelBean.class.getName());
    private boolean exibirPanelLado;
    private boolean exibirPanelUtilitarioCt;
    private boolean exibirPanelUtilitarioTr;


    @PostConstruct
    public void init() {
        exibirPanelLado = true; // Exibir o painel "Lado" por padrão
        exibirPanelUtilitarioCt = false;
        exibirPanelUtilitarioTr = false;
    }

    public void exibirPanelLado() {
        exibirPanelLado = true;
        exibirPanelUtilitarioCt = false;
        exibirPanelUtilitarioTr = false;
        LOGGER.info("Exibindo painel Lado");
    }

    public void exibirPanelUtilitarioCt() {
        exibirPanelLado = false;
        exibirPanelUtilitarioCt = true;
        exibirPanelUtilitarioTr = false;
        LOGGER.info("Exibindo painel Utilitario Ct");
    }

    public void exibirPanelUtilitarioTr() {
        exibirPanelLado = false;
        exibirPanelUtilitarioCt = false;
        exibirPanelUtilitarioTr = true;
        LOGGER.info("Exibindo painel Utilitario Tr");
    }

    public boolean isExibirPanelLado() {
        return exibirPanelLado;
    }

    public boolean isExibirPanelUtilitarioCt() {
        return exibirPanelUtilitarioCt;
    }

    public boolean isExibirPanelUtilitarioTr() {
        return exibirPanelUtilitarioTr;
    }

}
