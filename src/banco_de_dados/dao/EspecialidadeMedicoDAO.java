package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Especialidade;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.medico.Medico;


public interface EspecialidadeMedicoDAO {
    
    public void criar(Medico medico, Especialidade especialidade) throws BancoDeDadosException, SQLException;
    
    public void remover(Medico medico, Especialidade especialidade) throws BancoDeDadosException, SQLException;
    
    public void remover(int crm) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Especialidade> buscarPeloCrm(int crm) throws BancoDeDadosException, SQLException;
    
}
