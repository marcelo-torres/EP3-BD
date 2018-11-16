package dados_da_clinica;

import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public class Especialidade {
 
    public static void verificarIndice(int indice) {
        if(indice < 0) {
            throw new IllegalArgumentException("O indice nao pode ser menor do que 0");
        }
    }
    
    public static void verificarNome(String nome) {
        if(nome.length() > 100) {
            throw new IllegalArgumentException("O nome nao pode ter mais do que 100 caracteres");
        }
    }
    
    
    private int CODIGO;
    private int indice;
    private String nome;
    
    
    public Especialidade(int codigo, int indice, String nome) {
        
        verificarIndice(indice);
        verificarNome(nome);
        
        this.CODIGO = codigo;
        this.indice = indice;
        this.nome = nome;
    }
    
    
    @Override
    public String toString() {
        return this.nome;
    }
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Especialidade)) {
            return false;
        }
        
        Especialidade outraEspecialidade = (Especialidade)outro;
        
        boolean saoIguais = this.CODIGO == outraEspecialidade.getCodigo()
                && this.indice == outraEspecialidade.getIndice()
                && this.nome.equals(outraEspecialidade.getNome());
        
        return saoIguais;
    }
    
    
    public void setIndice(int novoIndice) {
        verificarIndice(novoIndice);
        this.indice = novoIndice;
    }
    
    public void setNome(String novoNome) {
        verificarNome(novoNome);
        this.nome = novoNome;
    }
    
    
    public int getCodigo() {
        return this.CODIGO;
    }
    
    public int getIndice() {
        return this.indice;
    }
    
    public String getNome() {
        return this.nome;
    }
}
