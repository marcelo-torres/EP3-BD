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
        medicoDao.remover(2020);
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
        Medico medico1 = medicoDao.criar(1010, "Paulo Karvalho (PK)", new Telefone("1234-5678"), especialidades1);
        
        LocalTime horarioDeInicio = LocalTime.parse("14:45");
        LocalTime horarioDoFim = LocalTime.parse("14:55");
   
        Agenda agenda1 = agendaDao.criar(10, DiaDaSemana.SEGUNDA, horarioDeInicio, horarioDoFim, medico1);
        Agenda agenda2 = agendaDao.criar(20, DiaDaSemana.TERCA, horarioDeInicio, horarioDoFim, medico1);
        Agenda agenda3 = agendaDao.criar(30, DiaDaSemana.QUARTA, horarioDeInicio, horarioDoFim, medico1);
        
        agenda3.setDiaDaSemana(DiaDaSemana.SABADO);
        agendaDao.gravar(agenda3);
        
        Agenda agendaEncontrada = agendaDao.buscar(20);
        if(agenda2.equals(agendaEncontrada)) {
            System.out.println("[1] - OK - Gravacao realizada com sucesso");
        } else {
            System.out.println("[1] - ERRO - Problemas com a gravacao");
            erros++;
        }
        
        agenda3.setDiaDaSemana(DiaDaSemana.TERCA);
        agendaDao.gravar(agenda3);
        
        LinkedList<Agenda> agendasEncontradas = agendaDao.buscarPeloDia(DiaDaSemana.TERCA);
        if(agendasEncontradas.size() == 2
                && agendasEncontradas.contains(agenda2)
                && agendasEncontradas.contains(agenda3)) {
        
            System.out.println("[2] - OK - Busca por dia da semana realizada com sucesso");
        } else {
            System.out.println("[2] - ERRO - Busca por dia da semana com problemas");
            erros++;
        }
        
        Medico novoMedico = medicoDao.criar(2020, "Fernandu Kenio (FK)", new Telefone("1234-5678"), especialidades1);
        agenda3.setDonoDaAgenda(novoMedico);
        agendaDao.gravar(agenda3);
        
        agendasEncontradas = agendaDao.buscarPeloCrm(1010);
        if(agendasEncontradas.size() == 2
                && agendasEncontradas.contains(agenda1)
                && agendasEncontradas.contains(agenda2)) {
        
            System.out.println("[3] - OK - Busca por medico realizada com sucesso");
        } else {
            System.out.println("[3] - ERRO - Busca por medico com problemas");
            erros++;
        }

        
        Agenda agenda4 = agendaDao.criar(40, DiaDaSemana.SEGUNDA, horarioDeInicio, horarioDoFim, medico1);
        agendasEncontradas = agendaDao.buscarPeloCrmEPeloDia(1010, DiaDaSemana.SEGUNDA);
        if(agendasEncontradas.size() == 2
                && agendasEncontradas.contains(agenda1)
                && agendasEncontradas.contains(agenda4)) {
        
            System.out.println("[4] - OK - Busca por medico e dia realizada com sucesso");
        } else {
            System.out.println("[4] - ERRO - Busca por medico e dia com problemas");
            erros++;
        }
        
        agendaDao.remover(10);
        agendaDao.remover(20);
        agendaDao.remover(30);
        
        medicoDao.remover(medico1.getCRM());
        medicoDao.remover(2020);
        
        especialidadeDao.remover(esp1.getCodigo());
        especialidadeDao.remover(esp2.getCodigo());

        return erros;
    }
}
