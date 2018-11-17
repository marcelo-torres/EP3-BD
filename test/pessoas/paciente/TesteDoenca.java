/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.paciente;

import dados_da_clinica.Doenca;

/**
 *
 * @author marcelo
 */
public class TesteDoenca {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        Doenca dengue = new Doenca(10, "Dengue");
        Doenca chicoCunha = new Doenca(20, "ChicoCunha");
        
        if(!dengue.equals(chicoCunha)) {
            System.out.println("[1] - OK - Doencas diferentes");
        } else {
            System.out.println("[1] - ERRO - As doencas nao sao iguais");
            erros++;
        }
        
        Doenca dengue2 = new Doenca(10, "Dengue");
        if(dengue.equals(dengue2)) {
            System.out.println("[2] - OK - Doencas iguais");
        } else {
            System.out.println("[2] - ERRO - As doencas sao iguais");
            erros++;
        }
        
        Doenca chicoCunha2 = new Doenca(10, "ChicoCunha");
        if(!chicoCunha.equals(chicoCunha2)) {
            System.out.println("[3] - OK - Doencas diferentes");
        } else {
            System.out.println("[3] - ERRO - As doencas nao sao iguais");
            erros++;
        }
        
        return erros++;
        
    }
    
}
