package dados_da_clinica;

import java.util.ArrayList;
import pessoas.medico.Medico;


public class Especialidade {
 
    public static void verificarCodigo(int codigo) {
        if(codigo < 0) {
            throw new IllegalArgumentException("O código não pode ser menor do que 0");
        }
    }
    
    public static void verificarIndice(int indice) {
        if(indice < 0) {
            throw new IllegalArgumentException("O índice não pode ser menor do que 0");
        }
    }
    
    public static void verificarNome(String nome) {
        if(nome == null) {
            throw new IllegalArgumentException("Um nome deve ser atribuído à especialidade");
        }
        if(nome.length() > 100) {
            throw new IllegalArgumentException("O nome não pode ter mais do que 100 caracteres");
        }
    }
    
    
    private int codigo;
    private int indice;
    private String nome;
    private ArrayList<Medico> ListaMedicos;
    
    public Especialidade(int indice, String nome) {
        this.setIndice(indice);
        this.setNome(nome);
        ListaMedicos = new ArrayList();
    }
    
    public Especialidade(int codigo, int indice, String nome) {
        this.setCodigo(codigo);
        this.setIndice(indice);
        this.setNome(nome);
        ListaMedicos = new ArrayList();
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
        
        boolean saoIguais = this.codigo == outraEspecialidade.getCodigo()
                && this.indice == outraEspecialidade.getIndice()
                && this.nome.equals(outraEspecialidade.getNome());
        
        return saoIguais;
    }
    
    
    public void setCodigo(int codigo) {
        verificarCodigo(codigo);
        this.codigo = codigo;
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
        return this.codigo;
    }
    
    public int getIndice() {
        return this.indice;
    }
    
    public String getNome() {
        return this.nome;
    }

    /**
     * @return the ListaMedicos
     */
    public ArrayList<Medico> getListaMedicos() {
        return ListaMedicos;
    }

    /**
     * @param ListaMedicos the ListaMedicos to set
     */
    public void setListaMedicos(ArrayList<Medico> ListaMedicos) {
        this.ListaMedicos = ListaMedicos;
    }
    
    public void addMedico (Medico medico) {
        medico.adicionarEspecialidade(this);
        ListaMedicos.add(medico);
    }
    
    
    
}
