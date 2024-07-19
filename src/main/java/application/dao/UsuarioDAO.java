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
        String sql = "INSERT INTO tbl_usuario (nome, email, login, senha) VALUES (?,?,?,?)";
        try {
            PreparedStatement state = con.prepareStatement(sql);

            state.setString(1, u.getNome());
            state.setString(2, u.getEmail());
            state.setString(3, u.getLogin());
            state.setString(4, u.getSenha());
            state.execute();
            state.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
