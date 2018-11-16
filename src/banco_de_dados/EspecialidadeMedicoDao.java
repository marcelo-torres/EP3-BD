/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import dados_da_clinica.Especialidade;
import java.util.LinkedList;
import pessoas.medico.Medico;

/**
 *
 * @author marcelo
 */
public interface EspecialidadeMedicoDao {
    
    public void criar(Medico medico, Especialidade especialidade) throws Exception;
    
    public void remover(Medico medico, Especialidade especialidade) throws Exception;
    
    public void remover(int crm) throws Exception;
    
    public LinkedList<Especialidade> buscarPeloCrm(int crm) throws Exception;
    
}
