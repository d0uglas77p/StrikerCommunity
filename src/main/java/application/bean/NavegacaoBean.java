package application.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class NavegacaoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public String irInicio() {
        return "/protected/inicio.xhtml?faces-redirect=true";
    }

    public String irLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String irFeed() {
        return "/protected/feed.xhtml?faces-redirect=true";
    }

    public String irTopico() {
        return "/protected/topico.xhtml?faces-redirect=true";
    }

    public String irUtilitario() {
        return "/protected/utilitario.xhtml?faces-redirect=true";
    }

    public String irComandos() {
        return "/protected/comandos.xhtml?faces-redirect=true";
    }

    public String irPerfil() {
        return "/protected/perfil.xhtml?faces-redirect=true";
    }

    public String irCompra() {
        return "/protected/compra.xhtml?faces-redirect=true";
    }

    public String irVenda() {
        return "/protected/venda.xhtml?faces-redirect=true";
    }
}
