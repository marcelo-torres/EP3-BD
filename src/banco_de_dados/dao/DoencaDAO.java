package banco_de_dados.dao;

import dados_da_clinica.Doenca;
import java.util.LinkedList;

public interface DoencaDAO {
    
    public Doenca criar(int ID, String nome) throws Exception;
    
    public void gravar(Doenca doenca) throws Exception;
    
    public void remover(int id) throws Exception;
    
    public Doenca buscarPeloID(int id) throws Exception;
    
    public LinkedList<Doenca> buscarPeloNome(String nome) throws Exception;  
}
