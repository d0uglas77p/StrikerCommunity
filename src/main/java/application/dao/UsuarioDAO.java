package application.dao;

import application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            state.setString(7, u.getSenha());
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
}
