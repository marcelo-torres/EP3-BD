package pessoas;

public class Telefone {
    
    public static void verificarTelefone(String telefone) {
        if(telefone.length() < 9 || telefone.length() > 20) {
            throw new IllegalArgumentException("Tamanho de telefone invalido");
        }
    }
    
    
    private String telefone;
    
    
    public Telefone(String telefone) {
        verificarTelefone(telefone);
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
