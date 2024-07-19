package application.exception;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CadastroUtils {

    public static void validarCamposPreenchidos(String nome, String email, String login, String senha, String repetirSenha) {
        if (nome == null || nome.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                repetirSenha == null || repetirSenha.trim().isEmpty()) {

            throw new CadastroException("Todos os campos devem ser preenchidos.");
        }
    }

    public static void validarSenhas(String senha, String repetirSenha) {
        if (!senha.equals(repetirSenha)) {
            throw new CadastroException("As senhas não conferem.");
        }
    }

    public static void adicionarMensagemErro(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
