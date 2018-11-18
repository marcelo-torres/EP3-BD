
import java.sql.DriverManager;
import pessoas.paciente.TesteAgenda;
import pessoas.paciente.TesteConsulta;
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
    
    public static void testarTudo() {
    
        int erros = 0;
        
        try {
            erros += TesteMedico.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += teste_banco_de_dados.TestePaciente.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += TesteAgenda.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += TesteDoenca.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += teste_banco_de_dados.TesteDoenca.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += TesteTaxa.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += teste_banco_de_dados.TesteTaxa.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
            erros += TesteConsulta.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\n\n================= FIM DE TESTES =================\n");
        System.out.println("Erros: " + erros + "\n");
    }
    
    public static void main(String[] args) throws Exception {
    
        DriverManager.registerDriver(new org.postgresql.Driver());
        
        //TestePaciente.testar();
        
        testarTudo();
        
        try {
            //TesteMedico.testar();
            //teste_banco_de_dados.TestePaciente.testar();
            //TesteAgenda.testar();
            //TesteDoenca.testar();
            //teste_banco_de_dados.TesteDoenca.testar();
            //TesteTaxa.testar();
            //teste_banco_de_dados.TesteTaxa.testar();
            //TesteConsulta.testar();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
