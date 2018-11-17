package pessoas.paciente;

import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;

public class TesteTaxa {
    
    public static int testar() throws Exception {
        
        int erros = 0;

        Especialidade esp1 = new Especialidade(1, 1, "Cardiologista");
        Especialidade esp2 = new Especialidade(2, 1, "Medicologista");
        
        Taxa taxa1 = new Taxa(10, 2018, Mes.MAIO, 10.24, esp1);
        Taxa taxa2 = new Taxa(11, 2018, Mes.JUNHO, 10.24, esp1);
        Taxa taxa3 = new Taxa(12, 2018, Mes.JUNHO, 10.24, esp1);
        
        if(!taxa1.equals(taxa2)) {
            System.out.println("[1] - OK - Taxas diferentes");
        } else {
            System.out.println("[1] - ERRO - As taxas sao diferentes");
            erros++;
        }
        
        if(!taxa2.equals(taxa3)) {
            System.out.println("[2] - OK - Taxas diferentes");
        } else {
            System.out.println("[2] - ERRO - As taxas sao diferentes");
            erros++;
        }
        
        Taxa taxa4 = new Taxa(13, 2018, Mes.JUNHO, 10.24, esp2);
        
        if(!taxa3.equals(taxa4)) {
            System.out.println("[3] - OK - Taxas diferentes");
        } else {
            System.out.println("[3] - ERRO - As taxas sao diferentes");
            erros++;
        }
        
        Taxa taxa5 = new Taxa(13, 2018, Mes.JUNHO, 10.24, esp2);
        
        if(taxa4.equals(taxa5)) {
            System.out.println("[4] - OK - Taxas iguais");
        } else {
            System.out.println("[4] - ERRO - As taxas sao iguais");
            erros++;
        }
        
        return erros;
        
    }
    
}
