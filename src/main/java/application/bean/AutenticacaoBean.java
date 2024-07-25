package application.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class AutenticacaoBean {

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            try {
                // Redirecione para a página de autorização
                facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/templates/semAutorizacao.xhtml?faces-redirect=true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session != null) {
            session.invalidate();
        }

        try {
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/login.xhtml?faces-redirect=true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
