
import banco_de_dados.BancoDeDadosException;
import java.sql.DriverManager;
import teste_banco_de_dados.TesteAgenda;
import pessoas.paciente.TesteConsulta;
import pessoas.paciente.TesteDoenca;
import pessoas.paciente.TestePaciente;
import pessoas.paciente.TesteTaxa;
import teste_banco_de_dados.TesteMedico;


public class Testador {
    
    public static void imprimirExceptionDeBD(Exception e) {
        if(e instanceof BancoDeDadosException) {
            Throwable t = ((BancoDeDadosException)e).getCausa();
            if(t != null) {
                t.printStackTrace();
            }
        }
    }
    
    public static void testarTudo() {
    
        int erros = 0;
        int exceptions = 0;
        
        try {
            erros += TesteMedico.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += teste_banco_de_dados.TestePaciente.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += TesteAgenda.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += TesteDoenca.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += teste_banco_de_dados.TesteDoenca.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += TesteTaxa.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += teste_banco_de_dados.TesteTaxa.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += TesteConsulta.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        try {
            erros += teste_banco_de_dados.TesteConsulta.testar();
        } catch(Exception e) {
            exceptions++;
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
        System.out.println("\n\n================= FIM DE TESTES =================\n");
        System.out.println("Erros: " + erros + "\n");
        System.out.println("Exceptions: " + exceptions + "\n");
    }
    
    public static void main(String[] args) {

        testarTudo();
        
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            //TesteMedico.testar();
            //teste_banco_de_dados.TestePaciente.testar();
            //TesteAgenda.testar();
            //TesteDoenca.testar();
            //teste_banco_de_dados.TesteDoenca.testar();
            //TesteTaxa.testar();
            //teste_banco_de_dados.TesteTaxa.testar();
            //TesteConsulta.testar();
            //teste_banco_de_dados.TesteConsulta.testar();
        } catch(Exception e) {
            e.printStackTrace();
            imprimirExceptionDeBD(e);
        }
        
    }
    
}
