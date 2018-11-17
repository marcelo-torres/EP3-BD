/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.paciente.CPF;
import pessoas.paciente.Paciente;
import pessoas.paciente.Sexo;

/**
 *
 * @author marcelo
 */
public interface PacienteDAO {
 
    public Paciente criar(int codigo, CPF cpf, String nome, Telefone telefone,
            String endereco, Integer idade, Sexo sexo) throws Exception;
    
    public void gravar(Paciente paciente) throws Exception;
    
    public void remover(int codigo) throws Exception;
    
    public Paciente buscarPeloCodigo(int codigo) throws Exception;
    
    public Paciente buscarPeloCpf(CPF cpf) throws Exception;
    
    public LinkedList<Paciente> buscarPeloNome(String nome) throws Exception;

}
