package pessoas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public class Telefone {
    
    String telefone;
    
    public Telefone(String telefone) {
        this.telefone = telefone;
    }
    
    
    @Override
    public String toString() {
        return telefone;
    }
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Telefone)) {
            return false;
        }
        
        Telefone outroTelefone = (Telefone)outro;
        boolean saoIguais = this.telefone.equals(outroTelefone.getTelefone());
        
        return saoIguais;
    }
    
    
    public String getTelefone() {
        return this.telefone;
    }
}
