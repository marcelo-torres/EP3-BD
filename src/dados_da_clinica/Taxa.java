package dados_da_clinica;

import java.util.Objects;

public class Taxa {
 
    public static void verificarIDTaxa(int IDTaxa) {
        if(IDTaxa < 0) {
            throw new IllegalArgumentException("O ID da taxa não pode ser negativo");
        }
    }
    
    public static void verificarAno(Integer ano) {
        if(ano == null) {
            throw new IllegalArgumentException("Deve ser atribuído um ano à taxa");
        }
        
        if(ano < 0) {
            throw new IllegalArgumentException("O ano não pode ser negativo");
        }
    }
    
    public static void verificarMes(Mes mes) {
        if(mes == null) {
            throw new IllegalArgumentException("Deve ser atribuído um mês à taxa");
        }
    }
    
    public static void verificarValor(double valor) {
        if(valor < 0) {
            throw new IllegalArgumentException("O valor da taxa não deve ser negativo");
        }
    }
    
    public static void verificarEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new IllegalArgumentException("A taxa deve ser atribuída a uma especialidade");
        }
    }
    
    
    private final int ID_TAXA;
    private Integer ano;
    private Mes mes;
    private double valor;
    private Especialidade especialidade;
    
    
    public Taxa(int idTaxa, Integer ano, Mes mes, double valor, Especialidade especialidade) {
        
        verificarIDTaxa(idTaxa);
        this.ID_TAXA = idTaxa;
        
        this.setAno(ano);
        this.setMes(mes);
        this.setValor(valor);
        this.setEspecialidade(especialidade);
    }

    public Taxa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Taxa)) {
            return false;
        }
        
        Taxa outraTaxa = (Taxa)outro;
        
        boolean saoIguais = (this.ID_TAXA == outraTaxa.getIDTaxa()
                && this.ano.equals(outraTaxa.getAno())
                && this.mes == outraTaxa.getMes()
                && this.valor == outraTaxa.getValor()
                && this.especialidade.equals(outraTaxa.getEspecialidade()));
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.ID_TAXA;
        hash = 47 * hash + Objects.hashCode(this.ano);
        hash = 47 * hash + Objects.hashCode(this.mes);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.especialidade);
        return hash;
    }
    
    
    public void setAno(Integer ano) {
        verificarAno(ano);
        this.ano = ano;
    }
    
    public void setMes(Mes mes) {
        verificarMes(mes);
        this.mes = mes;
    }
    
    public void setValor(double valor) {
        verificarValor(valor);
        this.valor = valor;
    }
    
    public void setEspecialidade(Especialidade especialidade) {
        verificarEspecialidade(especialidade);
        this.especialidade = especialidade;
    }
    
    
    public int getIDTaxa() {
        return this.ID_TAXA;
    }
    
    public Integer getAno() {
        return this.ano;
    }
    
    public Mes getMes() {
        return this.mes;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public Especialidade getEspecialidade() {
        return this.especialidade;
    }
}
