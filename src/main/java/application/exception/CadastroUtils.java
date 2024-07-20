package application.exception;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;

public class CadastroUtils {

    public static void validarCamposPreenchidos(String nomeCompleto, String nomePerfil, String telefone, Date dtNascimento, String email, String login, String senha, String repetirSenha) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty() ||
                nomePerfil == null || nomePerfil.trim().isEmpty() ||
                telefone == null || telefone.trim().isEmpty() ||
                dtNascimento == null ||
                email == null || email.trim().isEmpty() ||
                login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                repetirSenha == null || repetirSenha.trim().isEmpty()) {

            throw new CadastroException("Todos os campos devem ser preenchidos.");
        }
    }

    public static void validarSenhas(String senha, String repetirSenha) {
        if (senha == null || repetirSenha == null || !senha.equals(repetirSenha)) {
            throw new CadastroException("As senhas não conferem.");
        }
    }

    public static void adicionarMensagemErro(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void adicionarMensagemSucesso(String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Validação adicional
    public static void validarEmail(String email) {
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new CadastroException("Formato de e-mail inválido.");
        }
    }

    public static void validarTelefone(String telefone) {
        if (telefone == null || !telefone.matches("^\\+?[0-9]*$")) {
            throw new CadastroException("Formato de telefone inválido.");
        }
    }

    public static void validarSenha(String senha) {
        if (senha.length() < 6) { // Exemplo de tamanho mínimo
            throw new CadastroException("A senha deve ter pelo menos 6 caracteres.");
        }
    }
}
