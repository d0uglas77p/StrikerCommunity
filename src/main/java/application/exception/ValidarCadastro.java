package application.exception;

import java.util.Date;

public class ValidarCadastro {

    public static void validarCamposPreenchidos(String nomeCompleto, String nomePerfil, String telefone, Date dtNascimento, String email, String login, String senha, String repetirSenha) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty() ||
                nomePerfil == null || nomePerfil.trim().isEmpty() ||
                telefone == null || telefone.trim().isEmpty() ||
                dtNascimento == null ||
                email == null || email.trim().isEmpty() ||
                login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty() ||
                repetirSenha == null || repetirSenha.trim().isEmpty()) {
            throw new ApplicationException("Todos os campos devem ser preenchidos.");
        }
    }

    public static void validarCampoRecuperarEmail(String recuperarEmail) {
        if (recuperarEmail == null || recuperarEmail.trim().isEmpty()) {
            throw new ApplicationException("Campo de E-mail deve ser preenchido.");
        }
    }

    public static void validarSenhas(String senha, String repetirSenha) {
        if (senha == null || repetirSenha == null || !senha.equals(repetirSenha)) {
            throw new ApplicationException("As senhas não conferem.");
        }
    }

}
