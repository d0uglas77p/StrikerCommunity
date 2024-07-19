package application.conexao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CriarConexao {

    public static Connection getConexao() throws SQLException {
        Properties props = new Properties();
        String propertiesFile = "/properties/config.properties";

        try (InputStream input = CriarConexao.class.getResourceAsStream(propertiesFile)) {
            if (input == null) {
                throw new IOException("Arquivo de propriedades não encontrado: " + propertiesFile);
            }
            // Carrega as propriedades do arquivo
            props.load(input);
        } catch (IOException e) {
            throw new SQLException("Erro ao carregar o arquivo de propriedades.", e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conectado");

            // Obtém as propriedades
            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Retorna a conexão usando as propriedades carregadas
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do banco de dados não encontrado.", e);
        }
    }
}
