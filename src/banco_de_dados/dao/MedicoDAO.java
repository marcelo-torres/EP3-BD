package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Especialidade;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;


public interface MedicoDAO {
    
    public Medico criar(int crm, String nome, Telefone telefone,
            LinkedList<Especialidade> especialidades) throws BancoDeDadosException, SQLException;
    
    public void gravar(Medico medico) throws BancoDeDadosException, SQLException;
    
    public void remover(int crm) throws BancoDeDadosException, SQLException;
    
    public Medico buscarPeloCrm(int crm) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Medico> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Medico> buscarPorEspecialidade(int codigoEspecilidade) throws BancoDeDadosException, SQLException;
}
