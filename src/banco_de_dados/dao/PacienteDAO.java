package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.paciente.CPF;
import pessoas.paciente.Paciente;
import pessoas.paciente.Sexo;


public interface PacienteDAO {
 
    public Paciente criar(int codigo, CPF cpf, String nome, Telefone telefone,
            String endereco, Integer idade, Sexo sexo) throws BancoDeDadosException, SQLException;
    
    public void gravar(Paciente paciente) throws BancoDeDadosException, SQLException;
    
    public void remover(int codigo) throws BancoDeDadosException, SQLException;
    
    public Paciente buscarPeloCodigo(int codigo) throws BancoDeDadosException, SQLException;
    
    public Paciente buscarPeloCpf(CPF cpf) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Paciente> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException;

}
