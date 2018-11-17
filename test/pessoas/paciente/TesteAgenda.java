package pessoas.paciente;

import banco_de_dados.postgresql.AgendaDAOPostgresql;
import banco_de_dados.postgresql.EspecialidadeMedicoDAOPostgresql;
import banco_de_dados.postgresql.EspecialidadeDAOPostgresql;
import banco_de_dados.postgresql.MedicoDAOPostgresql;
import dados_da_clinica.Especialidade;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Agenda;
import pessoas.medico.Medico;

public class TesteAgenda {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        AgendaDAOPostgresql agendaDao = new AgendaDAOPostgresql();
        EspecialidadeDAOPostgresql especialidadeDao = new EspecialidadeDAOPostgresql();
        MedicoDAOPostgresql medicoDao = new MedicoDAOPostgresql();
      
        medicoDao.remover(1010);
        especialidadeDao.remover(1);
        especialidadeDao.remover(2);
      
        agendaDao.remover(10);
        agendaDao.remover(20);
        agendaDao.remover(30);
        
        Especialidade esp1 = especialidadeDao.criar(1, 4, "Otorrino");
        Especialidade esp2 = especialidadeDao.criar(2, 5, "Legista");

        LinkedList<Especialidade> especialidades1 = new LinkedList();
        especialidades1.add(esp1);
        especialidades1.add(esp2);
        Medico medico1 = medicoDao.criar(1010, "Fernando Augusto Maniaco", new Telefone("1234-5678"), especialidades1);
        
        LocalTime horarioDeInicio = LocalTime.parse("14:45");
        LocalTime horarioDoFim = LocalTime.parse("14:55");
   
        Agenda agenda1 = agendaDao.criar(10, DiaDaSemana.SEGUNDA, horarioDeInicio, horarioDoFim, medico1);
        Agenda agenda2 = agendaDao.criar(20, DiaDaSemana.TERCA, horarioDeInicio, horarioDoFim, medico1);
        Agenda agenda3 = agendaDao.criar(30, DiaDaSemana.QUARTA, horarioDeInicio, horarioDoFim, medico1);
        
        agenda3.setDiaDaSemana(DiaDaSemana.SABADO);
        agendaDao.gravar(agenda3);
        
        Agenda agendaEncontrada = agendaDao.buscar(20);
        if(agenda2.equals(agendaEncontrada)) {
            System.out.println("Deu bom");
        }
        
        System.out.println(agendaEncontrada.getID() + " " + agendaEncontrada.getDiaDaSemana().toString() + agendaEncontrada.getHorarioDoFim());
        
        //agendaDao.remover(10);
        //agendaDao.remover(20);
        //agendaDao.remover(30);
        
        /*medicoDao.remover(medico1.getCRM());
        
        especialidadeDao.remover(esp1.getCodigo());
        especialidadeDao.remover(esp2.getCodigo());*/

        return erros;
    }
}
