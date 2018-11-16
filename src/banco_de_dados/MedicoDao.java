/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import dados_da_clinica.Especialidade;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;

/**
 *
 * @author marcelo
 */
public interface MedicoDao {
    
    public Medico criar(int crm, String nome, Telefone telefone,
            LinkedList<Especialidade> especialidades) throws Exception;
    
    public void atualizar(Medico medico) throws Exception;
    
    public void remover(int crm) throws Exception;
    
    public Medico buscarPeloCrm(int crm) throws Exception;
    
    public LinkedList<Medico> buscarPeloNome(String nome) throws Exception;
    
}
