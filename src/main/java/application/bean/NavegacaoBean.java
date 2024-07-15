package application.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class NavegacaoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String irInicio() {
        return "/inicio.xhtml?faces-redirect=true";
    }

    public String irLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String irFeed() {
        return "/feed.xhtml?faces-redirect=true";
    }

    public String irTopico() {
        return "/topico.xhtml?faces-redirect=true";
    }

    public String irUtilitario() {
        return "/utilitario.xhtml?faces-redirect=true";
    }

    public String irComandos() {
        return "/comandos.xhtml?faces-redirect=true";
    }

    public String irMeuPerfil() {
        return "/meuperfil.xhtml?faces-redirect=true";
    }

}
