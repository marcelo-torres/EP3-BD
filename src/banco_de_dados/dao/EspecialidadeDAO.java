package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Especialidade;
import java.sql.SQLException;
import java.util.LinkedList;


public interface EspecialidadeDAO {
    
    public Especialidade criar(int codigo, int indice, String nome) throws BancoDeDadosException, SQLException;
    
    public void gravar(Especialidade especialidade) throws BancoDeDadosException, SQLException;
    
    public void remover(int codigo) throws BancoDeDadosException, SQLException;
    
    public Especialidade buscarPeloCodigo(int codigo) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Especialidade> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException;
    
}
