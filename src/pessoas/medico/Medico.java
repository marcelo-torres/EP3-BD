package pessoas.medico;


import java.util.LinkedList;
import dados_da_clinica.Especialidade;
import java.util.Objects;
import pessoas.Telefone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public class Medico {
    
    public static void verificarNome(String nome) {
        if(nome == null || nome.length() == 0) {
            throw new IllegalArgumentException("nome invalido");
        }
        
    }
    
    public static void verificarTelefone(Telefone telefone) {
        if(telefone == null) {
            throw new IllegalArgumentException("O telefone do medico nao pode ser nulo");
        }
    }
    
    
    private int CRM;
    private String nome;
    private Telefone telefone;
    private LinkedList<Especialidade> especialidades;

    
    public Medico(int crm, String nome, Telefone telefone,
                LinkedList<Especialidade> especialidades) {
    
        verificarNome(nome);
        verificarTelefone(telefone);
        
        this.CRM = crm;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidades = new LinkedList(especialidades);
    }
    
    
    @Override
    public boolean equals(Object outro) {
        
        if(!(outro instanceof Medico)) {
            return false;
        }
        
        Medico outroMedico = (Medico)outro;
        
        boolean saoIguais = (this.CRM == outroMedico.getCRM()
                && this.nome.equals(outroMedico.getNome())
                && this.telefone.equals(outroMedico.getTelefone())
                && this.especialidades.containsAll(outroMedico.getEspecialidades())
                && outroMedico.getEspecialidades().containsAll(this.especialidades));
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.CRM;
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.telefone);
        hash = 59 * hash + Objects.hashCode(this.especialidades);
        return hash;
    }
    
    
    public void setNome(String nome) {
        verificarNome(nome);
        this.nome = nome;
    }
    
    public void setTelefone(Telefone telefone) {
        verificarTelefone(telefone);
        this.telefone = telefone;
    }
    
    public void adicionarEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new IllegalArgumentException("Nao eh permitido adicionar uma especialidade nula ao medico");
        }
        
        this.especialidades.add(especialidade);
    }
    
    public boolean removerEspecialidade(Especialidade especialidade) {
        if(especialidade == null) {
            throw new IllegalArgumentException("Nao eh possivel remover uma especialidade nula");
        }
        
        boolean remocaoBemSucedida = this.especialidades.remove(especialidade);
        return remocaoBemSucedida;
    }
    
    
    public int getCRM() {
        return this.CRM;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public Telefone getTelefone() {
        return this.telefone;
    }
    
    public LinkedList<Especialidade> getEspecialidades() {
        return new LinkedList(this.especialidades);
    }
}
