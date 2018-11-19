package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Agenda;
import pessoas.medico.Medico;
import pessoas.paciente.DiaDaSemana;


public interface AgendaDAO {
    
    public Agenda criar(int id, DiaDaSemana diaDaSemana, LocalTime horarioDeInicio, 
            LocalTime horarioDoFim, Medico donoDaAgenda) throws BancoDeDadosException, SQLException;
    
    public void gravar(Agenda agenda) throws BancoDeDadosException, SQLException;
    
    public void remover(int id) throws BancoDeDadosException, SQLException;
    
    public Agenda buscar(int id) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Agenda> buscarPeloDia(DiaDaSemana diaDaSemana) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Agenda> buscarPeloCrm(int crm) throws BancoDeDadosException, SQLException;
    
    public LinkedList<Agenda> buscarPeloCrmEPeloDia(int crm, DiaDaSemana diaDaSemana) throws BancoDeDadosException, SQLException;
}
