package pessoas.paciente;

public enum Sexo {
    
    FEMININO(1) {
                
        @Override
        public String toString() {
            return "feminino";
        }
    },
    
    MASCULINO(2) {
        @Override
        public String toString() {
            return "masculino";
        }
    };
    
    
    public static Sexo obterValor(String sexo) {
    
        if(sexo.equals(FEMININO.toString())) {
            return FEMININO;
        } else if(sexo.equals(MASCULINO.toString())) {
            return MASCULINO;
        }
        
        return null;
    }
    
    
    public int codigoDoSexo;
    
    private Sexo(int codigoDoSexo) {
        this.codigoDoSexo = codigoDoSexo;
    }
}
