package application.bens;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;

@ManagedBean
@ViewScoped
public class NavegacaoBean implements Serializable{

    private static final long serialVersionUID = 1L;

    public String irInicio() {
        return "/inicio.xhtml?faces-redirect=true";
    }

    public String irLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String irAprenda() {
        return "/aprenda.xhtml?faces-redirect=true";
    }

    public String irForum() {
        return "/forum.xhtml?faces-redirect=true";
    }

    public String irRanking() {
        return "/ranking.xhtml?faces-redirect=true";
    }

    public String irVip() {
        return "/vip.xhtml?faces-redirect=true";
    }

    public String irMeuPerfil() {
        return "/meuperfil.xhtml?faces-redirect=true";
    }


}
