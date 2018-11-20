package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Doenca;
import java.sql.SQLException;
import java.util.LinkedList;

public interface DoencaDAO {
    
    public boolean existeDoenca(int id) throws BancoDeDadosException, SQLException;
    
    public Doenca criar(int ID, String nome) throws BancoDeDadosException, SQLException;
    
    public void gravar(Doenca doenca) throws BancoDeDadosException, SQLException;
    
    public void remover(int id) throws BancoDeDadosException, SQLException;
    
    public Doenca buscarPeloID(int id) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Doenca> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException;  
}
