package dados_da_clinica;

import java.util.Objects;


public class Doenca {
    
    public static void verificarID(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("O ID da doença não pode ser negativo");
        }
    }
    
    public static void verificarNome(String nome) {
        if(nome == null) {
            throw new IllegalArgumentException("Deve ser atribuído um nome à doença");
        }
        
        if(nome.length() > 70) {
            throw new IllegalArgumentException("O nome não pode ter mais do que 70 caracteres");
        }
    }
    
    
    private final int ID;
    private String nome;
    
    
    public Doenca(int id, String nome) {
    
        verificarID(id);
        this.ID = id;
        
        this.setNome(nome);
    }
    
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Doenca)) {
            return false;
        }
    
        Doenca outraDoenca = (Doenca)outro;
        
        boolean saoIguais = (this.ID == outraDoenca.getID()
                && this.nome.equals(outraDoenca.getNome()));
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + Objects.hashCode(this.nome);
        return hash;
    }
    
    
    public void setNome(String nome) {
        verificarNome(nome);
        this.nome = nome;
    }
    
    
    public int getID() {
        return this.ID;
    }
    
    public String getNome() {
        return this.nome;
    }
}
