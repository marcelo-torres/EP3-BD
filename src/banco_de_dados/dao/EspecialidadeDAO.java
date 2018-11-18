/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados.dao;

import dados_da_clinica.Especialidade;
import java.util.LinkedList;

/**
 *
 * @author marcelo
 */
public interface EspecialidadeDAO {
    
    public Especialidade criar(int codigo, int indice, String nome) throws Exception;
    
    public void gravar(Especialidade especialidade) throws Exception;
    
    public void remover(int codigo) throws Exception;
    
    public Especialidade buscarPeloCodigo(int codigo) throws Exception;
    
    public LinkedList<Especialidade> buscarPeloNome(String nome) throws Exception;
    
}
