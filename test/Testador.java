
import java.sql.DriverManager;
import teste_banco_de_dados.TesteMedico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public class Testador {
    
    public static void main(String[] args) throws Exception {
    
        DriverManager.registerDriver(new org.postgresql.Driver());
        
        try {
            TesteMedico.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
