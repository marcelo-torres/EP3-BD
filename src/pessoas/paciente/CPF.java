package pessoas.paciente;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class CPF {
     
    public static void verificarCPF(String cpfSemMascara) {
        if(cpfSemMascara.length() != 11) {
            throw new IllegalArgumentException("CPF invalido");
        }
        
        for(int i = 0; i < cpfSemMascara.length(); i++) {
            char digito = cpfSemMascara.charAt(i);
            if(digito < '0' || digito > '9') {
                throw new IllegalArgumentException("CPF invalido");
            }
        }
    }
    
    
    private String cpfSemMascara;
    
    
    public CPF(String cpfSemMascara) {
        verificarCPF(cpfSemMascara);
        
        this.cpfSemMascara = cpfSemMascara;
    }
    
    
    @Override
    public String toString() {
        return this.cpfSemMascara;
    }
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof CPF)) {
            return false;
        }
        
        CPF outroCpf = (CPF)outro;
        
        return this.cpfSemMascara.equals(outroCpf.getCPFSemMarcara());
    }
    
    public String getCPFComMascara() {
        throw new UnsupportedOperationException("OPERACAO SUPORTADA");
    }
    
    public String getCPFSemMarcara() {
        return this.cpfSemMascara;
    }
}
