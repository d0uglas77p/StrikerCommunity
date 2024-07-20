package application.dao;

import application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public void adicionarUsuario(Usuario u) throws SQLException {
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
}
