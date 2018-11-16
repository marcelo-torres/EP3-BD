/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marcelo
 */
public class ConnectionFactory {
    
    private String host;
    private String usuario;
    private String senha;
    
    
    public void definirParametros(String host, String usuario, String senha) {
        
        this.host = host;
        this.usuario = usuario;
        this.senha = senha;
        
    }
    
    public Connection getConnection() throws SQLException {
    
        return DriverManager.getConnection(this.host, this.usuario, this.senha);
        
    }
    
}
