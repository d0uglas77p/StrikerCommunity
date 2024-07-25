package application.dao;

import application.model.Usuario;
import application.util.PasswordUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public void adicionarUsuario(Usuario u) throws SQLException {
        if (existeLogin(u.getLogin())) {
            throw new SQLException("Login já cadastrado.");
        }

        if (existeEmail(u.getEmail())) {
            throw new SQLException("Email já cadastrado.");
        }

        String sql = "INSERT INTO tbl_usuario (nomeCompleto, nomePerfil, telefone, dtNascimento, email, login, senha) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, u.getNomeCompleto());
            state.setString(2, u.getNomePerfil());
            state.setString(3, u.getTelefone());

            // Converter java.util.Date para java.sql.Date
            if (u.getDtNascimento() != null) {
                state.setDate(4, new java.sql.Date(u.getDtNascimento().getTime()));
            } else {
                state.setNull(4, java.sql.Types.DATE);
            }

            state.setString(5, u.getEmail());
            state.setString(6, u.getLogin());

            // Hash da senha antes de salvar no banco de dados
            String hashedPassword = PasswordUtil.hashPassword(u.getSenha());
            state.setString(7, hashedPassword);

            state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeLogin(String login) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_usuario WHERE login = ?";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, login);
            try (ResultSet rs = state.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean existeEmail(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tbl_usuario WHERE email = ?";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, email);
            try (ResultSet rs = state.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Método para armazenar o token de recuperação no banco de dados
    public void armazenarTokenRecuperacao(String email, String token) throws SQLException {
        // Atualiza o token de recuperação e define sua expiração
        String sql = "UPDATE tbl_usuario SET token_recuperacao = ?, token_expiracao = ? WHERE email = ?";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, token);

            // Define a expiração do token como 1 hora a partir de agora
            Timestamp expiracao = new Timestamp(new Date().getTime() + 3600 * 1000);
            state.setTimestamp(2, expiracao);

            state.setString(3, email);
            state.executeUpdate();
        }
    }

    // Método para verificar se o token é válido
    public boolean verificarTokenValido(String token) throws SQLException {
        String sql = "SELECT email FROM tbl_usuario WHERE token_recuperacao = ? AND token_expiracao > ?";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, token);
            state.setTimestamp(2, new Timestamp(new Date().getTime()));
            try (ResultSet rs = state.executeQuery()) {
                return rs.next(); // Se houver um resultado, o token é válido
            }
        }
    }

    // Método para redefinir a senha do usuário
    public void redefinirSenha(String token, String novaSenha) throws SQLException {
        String sql = "UPDATE tbl_usuario SET senha = ?, token_recuperacao = NULL, token_expiracao = NULL WHERE token_recuperacao = ?";
        try (PreparedStatement state = con.prepareStatement(sql)) {
            // Hash da nova senha
            String hashedPassword = PasswordUtil.hashPassword(novaSenha);
            state.setString(1, hashedPassword);
            state.setString(2, token);
            state.executeUpdate();
        }
    }

    public Usuario autenticarUsuario(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM tbl_usuario WHERE login = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHash = rs.getString("senha");

                    // Verificar se a senha fornecida corresponde ao hash armazenado
                    if (BCrypt.checkpw(senha, senhaHash)) {
                        Usuario usuario = new Usuario();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                        usuario.setNomePerfil(rs.getString("nomePerfil"));
                        usuario.setTelefone(rs.getString("telefone"));
                        usuario.setDtNascimento(rs.getDate("dtNascimento"));
                        usuario.setEmail(rs.getString("email"));
                        usuario.setLogin(rs.getString("login"));
                        // Não é necessário definir a senha no objeto Usuario
                        return usuario;
                    }
                }
            }
        }
        return null; // Retorna null se o usuário não for encontrado ou a senha não corresponder
    }

    private Usuario buscarUsuarioPorLogin(String login) throws SQLException {
        String sql = "SELECT * FROM tbl_usuario WHERE login =?";

        try (PreparedStatement state = con.prepareStatement(sql)) {
            state.setString(1, login);

            try (ResultSet rs = state.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                    usuario.setNomePerfil(rs.getString("nomePerfil"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setDtNascimento(rs.getDate("dtNascimento"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setLogin(rs.getString("login"));

                    return usuario;
                }
                return null;
            }
        }
    }
}
