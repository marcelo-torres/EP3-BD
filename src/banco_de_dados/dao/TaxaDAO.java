package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;
import java.sql.SQLException;
import java.util.LinkedList;


public interface TaxaDAO {
    
    public boolean existeTaxa(int idTaxa) throws BancoDeDadosException, SQLException;
    
    public Taxa criar(Integer ano, Mes mes, double valor, 
            Especialidade especialidade) throws BancoDeDadosException, SQLException;
    
    public void gravar(Taxa taxa) throws BancoDeDadosException, SQLException;
    
    public void remover(int idTaxa) throws BancoDeDadosException, SQLException;
    
    public Taxa buscar(int idTaxa) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Taxa> buscarPeloAno(Integer ano) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Taxa> buscarPeloMes(Mes mes) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Taxa> buscarPelaEspecialidade(int codigoEspecialidade) throws BancoDeDadosException, SQLException;
}
