
import java.sql.DriverManager;
import pessoas.paciente.TesteAgenda;
import pessoas.paciente.TesteDoenca;
import pessoas.paciente.TestePaciente;
import pessoas.paciente.TesteTaxa;
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
        
        //TestePaciente.testar();
        
        try {
            //TesteMedico.testar();
            //teste_banco_de_dados.TestePaciente.testar();
            //TesteAgenda.testar();
            //TesteDoenca.testar();
            //teste_banco_de_dados.TesteDoenca.testar();
            //TesteTaxa.testar();
            teste_banco_de_dados.TesteTaxa.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
