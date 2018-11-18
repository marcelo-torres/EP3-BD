/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados.dao.postgresql;

import banco_de_dados.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author marcelo
 */
public class Conector {
    
    private static String schema = "clinica_medica";
    private static String usuario = "escravo";
    private static String senha = "escravoDoEstudante";
    private static String url = "jdbc:postgresql://localhost:5432/EPBD";
    
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.definirParametros(url, usuario, senha);
    }
    
    
    public static Connection obterConexao() throws Exception {
        try {
            return connectionFactory.getConnection();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel se conectar ao banco de dados");
        }
    }
    
    public static void redefinirConnectionFactory() {
        Conector.connectionFactory.definirParametros(url, usuario, senha);
    }
    
    
    public static String nomeCompleto(String nomeDaTabela) {
        return schema + ".\"" + nomeDaTabela + "\"";
    }
}
