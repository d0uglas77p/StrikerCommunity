package application.bens;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UtilitarioPanelBean implements Serializable {
    private boolean exibirPanelLado;
    private boolean exibirPanelUtilitario;

    @PostConstruct
    public void init() {
        exibirPanelLado = true; // Exibir o painel "Lado" por padrão
        exibirPanelUtilitario = false;
    }

    public void exibirPanelLado() {
        exibirPanelLado = true;
        exibirPanelUtilitario = false;
    }

    public void exibirPanelUtilitario() {
        exibirPanelLado = false;
        exibirPanelUtilitario = true;
    }

    public boolean isExibirPanelLado() {
        return exibirPanelLado;
    }

    public boolean isExibirPanelUtilitario() {
        return exibirPanelUtilitario;
    }
}
