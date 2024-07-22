package application.bean;

import application.conexao.CriarConexao;
import application.dao.UsuarioDAO;
import application.exception.ApplicationException;
import application.exception.ValidarCadastro;
import application.model.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
            // Validação dos campos
            ValidarCadastro.validarCamposPreenchidos(nomeCompleto, nomePerfil, telefone, dtNascimento, email, login, senha, repetirSenha);
            ValidarCadastro.validarSenhas(senha, repetirSenha);

            // Inserção no banco de dados
            try (Connection con = CriarConexao.getConexao()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(con);

                // Verificar se o login ou email já estão cadastrados
                if (usuarioDAO.existeLogin(login)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Login já cadastrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    LOGGER.warning("Login já cadastrado: " + login);
                    return;
                }

                if (usuarioDAO.existeEmail(email)) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Email já cadastrado.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    LOGGER.warning("Email já cadastrado: " + email);
                    return;
                }

                // Adicionar o usuário ao banco de dados
                usuarioDAO.adicionarUsuario(u);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário cadastrado com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                LOGGER.info("USUÁRIO CADASTRADO COM SUCESSO!");

                limparCampos();
            } catch (SQLException e) {
                LOGGER.severe("Erro ao cadastrar usuário: " + e.getMessage());
                throw new RuntimeException("Erro ao cadastrar usuário.", e);
            }

        } catch (ApplicationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro de validação: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            LOGGER.warning("VALIDAÇÃO FALHOU: " + e.getMessage());

        } catch (RuntimeException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ocorreu: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            LOGGER.severe("ERRO INESPERADO: " + e.getMessage());
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
    }

    // Getters e Setters
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
}
