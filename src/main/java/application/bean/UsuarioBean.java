package application.bean;

import application.conexao.CriarConexao;
import application.dao.UsuarioDAO;
import application.exception.CadastroException;
import application.exception.CadastroUtils;
import application.model.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(UsuarioBean.class.getName());

    private String nome;
    private String email;
    private String login;
    private String senha;
    private String repetirSenha;

    public void cadastrar() {
        LOGGER.info("INICIANDO PROCESSO DE CADASTRO.");

        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setLogin(login);
        u.setSenha(senha);

        try {
            CadastroUtils.validarCamposPreenchidos(u.getNome(), u.getEmail(), u.getLogin(), u.getSenha(), repetirSenha);
            CadastroUtils.validarSenhas(u.getSenha(), repetirSenha);

            try (Connection con = CriarConexao.getConexao()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO(con);
                usuarioDAO.adicionarUsuario(u);

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Usuário cadastrado com sucesso.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                LOGGER.info("USUÁRIO CADASTRADO!");

                limparCampos();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao cadastrar usuário.", e);
            }

        } catch (CadastroException e) {
            CadastroUtils.adicionarMensagemErro(e.getMessage());
            LOGGER.info("VALIDAÇÃO FALHOU: " + e.getMessage());

        } catch (RuntimeException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro inesperado ocorreu.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            LOGGER.severe("ERRO INESPERADO: " + e.getMessage());
        }
    }

    private void limparCampos() {
        nome = null;
        email = null;
        login = null;
        senha = null;
        repetirSenha = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
