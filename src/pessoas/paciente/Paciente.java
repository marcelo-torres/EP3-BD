package pessoas.paciente;

import java.util.Objects;
import pessoas.Telefone;

public class Paciente {
 
    public static void verificarCodigo(int codigo) {
        if(codigo < 0) {
            throw new IllegalArgumentException("O codigo deve ser positivo");
        }
    }
    
    public static void verificarCPF(CPF cpf) {
        // pode ser vazio
    }
    
    public static void verificarNome(String nome) {
        // pode ser vazio
        if(nome != null && nome.length() > 70) {
            throw new IllegalArgumentException("O nome nao pode ter mais do que 70 caractres");
        }
    }
    
    public static void verificarTelefone(Telefone telefone) {
        // pode ser vazio
    }
    
    public static void verificarEndereco(String endereco) {
        // pode ser  vazio
        if(endereco != null && endereco.length() > 100) {
            throw new IllegalArgumentException("O endereco deve ter no maximo 100 caracteres");
        }
    }
    
    public static void verificarIdade(Integer idade) {
        // pode ser vazio
        if(idade != null && idade < 0) {
            throw new IllegalArgumentException("Ninguem pode ter idade menor do que 0");
        }
    }
    
    public static void verificarSexo(Sexo sexo) {
        // pode ser vazio
    }
    
    
    private final int CODIGO;
    private CPF cpf;
    private String nome;
    private Telefone telefone;
    private String endereco;
    private Integer idade;
    private Sexo sexo;
    
    
    public Paciente(int codigo, CPF cpf, String nome, Telefone telefone,
            String endereco, Integer idade, Sexo sexo) {
        
        verificarCodigo(codigo);
        verificarCPF(cpf);
        verificarNome(nome);
        verificarTelefone(telefone);
        verificarEndereco(endereco);
        verificarIdade(idade);
        verificarSexo(sexo);
        
        this.CODIGO = codigo;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.idade = idade;
        this.sexo = sexo;
    }
    
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Paciente)) {
            return false;
        }
        
        Paciente outroPaciente = (Paciente)outro;
        
        boolean saoIguais = (this.CODIGO == outroPaciente.getCodigo()
                && this.cpf.equals(outroPaciente.getCPF())
                && this.nome.equals(outroPaciente.getNome())
                && this.telefone.equals(outroPaciente.getTelefone())
                && this.endereco.equals(outroPaciente.getEndereco())
                && this.idade.equals(outroPaciente.getIdade())
                && this.sexo == outroPaciente.getSexo());
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.CODIGO;
        hash = 97 * hash + Objects.hashCode(this.cpf);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.telefone);
        hash = 97 * hash + Objects.hashCode(this.endereco);
        hash = 97 * hash + this.idade;
        hash = 97 * hash + Objects.hashCode(this.sexo);
        return hash;
    }
    
    
    public void setCPF(CPF cpf) {
        verificarCPF(cpf);
        this.cpf = cpf;
    }
    
    public void setNome(String nome) {
        verificarNome(nome);
        this.nome = nome;
    }
    
    public void setTelefone(Telefone telefone) {
        verificarTelefone(telefone);
        this.telefone = telefone;
    }
    
    public void setEndereco(String endereco) {
        verificarEndereco(endereco);
        this.endereco = endereco;
    }
    
    public void setIdade(Integer idade) {
        verificarIdade(idade);
        this.idade = idade;
    }
    
    public void setSexo(Sexo sexo) {
        verificarSexo(sexo);
        this.sexo = sexo;
    }
    
    
    public int getCodigo() {
        return this.CODIGO;
    }
    
    public CPF getCPF() {
        return this.cpf;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public Telefone getTelefone() {
        return this.telefone;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public Integer getIdade() {
        return this.idade;
    }
    
    public Sexo getSexo() {
        return this.sexo;
    }
}
