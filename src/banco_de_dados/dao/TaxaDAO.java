package banco_de_dados.dao;

import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;
import java.util.LinkedList;

public interface TaxaDAO {
    
    public Taxa criar(int idTaxa, Integer ano, Mes mes, double valor, Especialidade especialidade) throws Exception;
    
    public void gravar(Taxa taxa) throws Exception;
    
    public void remover(int idTaxa) throws Exception;
    
    public Taxa buscar(int idTaxa) throws Exception;
    
    public LinkedList<Taxa> buscarPeloAno(Integer ano) throws Exception;
    
    public LinkedList<Taxa> buscarPeloMes(Mes mes) throws Exception;
    
    public LinkedList<Taxa> buscarPelaEspecialidade(int codigoEspecialidade) throws Exception;
}
