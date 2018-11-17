/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados;

import java.sql.Time;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Agenda;
import pessoas.medico.Medico;
import pessoas.paciente.DiaDaSemana;

/**
 *
 * @author marcelo
 */
public interface AgendaDAO {
    
    public Agenda criar(int id, DiaDaSemana diaDaSemana, LocalTime horarioDeInicio, 
            LocalTime horarioDoFim, Medico donoDaAgenda) throws Exception;
    
    public void gravar(Agenda agenda) throws Exception;
    
    public void remover(int id) throws Exception;
    
    public Agenda buscar(int id) throws Exception;
    
    public LinkedList<Agenda> buscarPeloDia(DiaDaSemana diaDaSemana) throws Exception;
    
    public LinkedList<Agenda> buscarPeloCrm(int crm) throws Exception;
    
}
