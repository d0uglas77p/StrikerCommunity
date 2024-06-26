package application.bens;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import java.io.Serializable;

@ManagedBean
@ViewScoped
public class NavegacaoBean implements Serializable{

    private static final long serialVersionUID = 1L;

    public String irInicio() {
        return "/inicio.xhtml?faces-redirect=true";
    }

    public String irCadastro() {
        return "/cadastro.xhtml?faces-redirect=true";
    }

    public String irLogin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String irTutorial() {
        return "/tutorial.xhtml?faces-redirect=true";
    }

    public String irMeuPerfil() {
        return "/meuperfil.xhtml?faces-redirect=true";
    }

    public String irTeste() {
        return "/teste.xhtml?faces-redirect=true";
    }


}
