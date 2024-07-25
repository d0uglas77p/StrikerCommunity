package application.bean;

import application.conexao.CriarConexao;
import application.dao.UsuarioDAO;
import application.exception.ApplicationException;
import application.exception.ValidarCadastro;
import application.model.Usuario;
import application.util.EmailSender;
import application.util.Token;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UsuarioBean.class.getName());

    private String nomeCompleto;
    private String nomePerfil;
    private String telefone;
    private Date dtNascimento;
    private String email;
    private String login;
    private String senha;
    private String repetirSenha;
    private String recuperarEmail;
    private String novaSenha;

    // Propriedade para o login
    private String loginInput;
    private String senhaInput;

    public void cadastrar() {
        LOGGER.info("INICIANDO PROCESSO DE CADASTRO.");

        Usuario u = new Usuario();
        u.setNomeCompleto(nomeCompleto);
        u.setNomePerfil(nomePerfil);
        u.setTelefone(telefone);
        u.setDtNascimento(dtNascimento);
        u.setEmail(email);
        u.setLogin(login);
        u.setSenha(senha);

        try {
            ValidarCadastro.validarCamposPreenchidos(nomeCompleto, nomePerfil, telefone, dtNascimento, email, login, senha, repetirSenha);
            ValidarCadastro.validarSenhas(senha, repetirSenha);

            // Inserção no banco de dados
            try (Connection con = CriarConexao.getConexao()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(con);

                // Verificar se o login ou email já estão cadastrados
                if (usuarioDAO.existeLogin(login)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login já cadastrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }

                if (usuarioDAO.existeEmail(email)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email já cadastrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }

                // Enviar e-mail de boas-vindas
                String assunto = "Bomb Has Been Planted!";
                String templatePath = "src/main/webapp/templates/boasVindasEmail.html";
                String corpo = EmailSender.loadEmailTemplate(templatePath, nomePerfil);
                String imagePath = "src/main/webapp/resources/images/logoemail.png";
                boolean emailEnviado = EmailSender.sendEmail(email, assunto, corpo, imagePath);

                if (!emailEnviado) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao enviar e-mail. Cadastro não realizado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }

                // Adicionar o usuário ao banco de dados
                usuarioDAO.adicionarUsuario(u);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário cadastrado. Verifique o seu e-mail!");
                FacesContext.getCurrentInstance().addMessage(null, message);
                LOGGER.info("USUÁRIO CADASTRADO COM SUCESSO!");
                limparCampos();

            } catch (SQLException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao cadastrar usuário no banco de dados.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ao cadastrar usuário.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (ApplicationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ao processar o cadastro.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void recuperarCadastro() {
        LOGGER.info("INICIANDO PROCESSO PARA RECUPERAÇÃO DE CADASTRO");

        try {
            ValidarCadastro.validarCampoRecuperarEmail(recuperarEmail);

            // Criar conexão com o banco de dados
            try (Connection con = CriarConexao.getConexao()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(con);

                // Verificar se o e-mail existe no banco de dados
                if (!usuarioDAO.existeEmail(recuperarEmail)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail não encontrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    return;
                }

                // Gerar token de recuperação
                Token tokenGenerator = new Token();
                String token = tokenGenerator.gerarToken();
                usuarioDAO.armazenarTokenRecuperacao(recuperarEmail, token);

                // Enviar e-mail de recuperação de senha
                String assunto = "Recuperar Senha";
                String linkRecuperacao = "http://localhost:8080/StrikerCommunity/recuperarSenha.xhtml?token=" + token;
                String templatePath = "src/main/webapp/templates/recuperarSenhaEmail.html";
                String corpo = EmailSender.loadEmailTemplate(templatePath, nomePerfil);
                corpo = corpo.replace("LINK", linkRecuperacao);
                String imagePath = "src/main/webapp/resources/images/logoemail.png";

                EmailSender.sendEmail(recuperarEmail, assunto, corpo, imagePath); // Enviar o e-mail
                limparCampos();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "E-mail de verificação enviado com sucesso!");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (SQLException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao encontrar e-mail no banco de dados");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (ApplicationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ao recuperar cadastro.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void redefinirSenha() {
        LOGGER.info("INICIANDO PROCESSO PARA RECUPERAR SENHA");
        String token = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("token");

        if (token == null || token.isEmpty()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Link inválido.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        try {
            ValidarCadastro.validarCampoRecuperarSenha(novaSenha);

            // Verificar e usar o token para redefinir a senha
            try (Connection con = CriarConexao.getConexao()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(con);

                if (usuarioDAO.verificarTokenValido(token)) {
                    usuarioDAO.redefinirSenha(token, novaSenha);
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Senha redefinida com sucesso!");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } else {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Link inválido ou expirado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }

            } catch (SQLException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao redefinir a senha no banco de dados");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (ApplicationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ao recuperar senha.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void login() {
        LOGGER.info("INICIANDO PROCESSO DE LOGIN.");
        try (Connection con = CriarConexao.getConexao()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(con);

            // Verificar se o login e a senha são válidos
            Usuario usuario = usuarioDAO.autenticarUsuario(loginInput, senhaInput);
            if (usuario != null) {
                // Login bem-sucedido
                FacesContext facesContext = FacesContext.getCurrentInstance();
                ExternalContext ec = facesContext.getExternalContext();
                ec.getSessionMap().put("usuarioLogado", usuario);

                // Redirecionar para a página inicial
                ec.redirect(ec.getRequestContextPath() + "/protected/inicio.xhtml?faces-redirect=true");
            } else {
                // Login falhou
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login ou senha inválidos.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } catch (SQLException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao processar login no banco de dados.");
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ao processar login.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }


    private void limparCampos() {
        nomeCompleto = null;
        nomePerfil = null;
        telefone = null;
        dtNascimento = null;
        email = null;
        login = null;
        senha = null;
        repetirSenha = null;
        recuperarEmail = null;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }

    public String getRecuperarEmail() {
        return recuperarEmail;
    }

    public void setRecuperarEmail(String recuperarEmail) {
        this.recuperarEmail = recuperarEmail;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getLoginInput() {
        return loginInput;
    }

    public void setLoginInput(String loginInput) {
        this.loginInput = loginInput;
    }

    public String getSenhaInput() {
        return senhaInput;
    }

    public void setSenhaInput(String senhaInput) {
        this.senhaInput = senhaInput;
    }
}