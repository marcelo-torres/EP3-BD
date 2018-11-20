package teste_banco_de_dados;

import banco_de_dados.dao.postgresql.ConsultaDiagnosticoDAOPostgresql;
import banco_de_dados.dao.postgresql.DoencaDAOPostgresql;
import banco_de_dados.dao.postgresql.EspecialidadeDAOPostgresql;
import banco_de_dados.dao.postgresql.MedicoDAOPostgresql;
import banco_de_dados.dao.postgresql.PacienteDAOPostgresql;
import dados_da_clinica.Consulta;
import dados_da_clinica.Doenca;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;
import pessoas.paciente.CPF;
import pessoas.paciente.Paciente;
import pessoas.paciente.Sexo;


public class TesteConsulta {
    
    public static int testar() throws Exception {
    
        System.out.println("\n === Testando Consulta Diagnostico ===");
        
        int erros = 0;
        
        EspecialidadeDAOPostgresql especialidadeDAO = new EspecialidadeDAOPostgresql();
        MedicoDAOPostgresql medicoDAO = new MedicoDAOPostgresql();
        PacienteDAOPostgresql pacienteDAO = new PacienteDAOPostgresql();
        DoencaDAOPostgresql doencaDAO = new DoencaDAOPostgresql();
        ConsultaDiagnosticoDAOPostgresql consultaDiagnosticoDAO = new ConsultaDiagnosticoDAOPostgresql();
        
        Especialidade esp1 = especialidadeDAO.criar(1, "Cariologista");
        Especialidade esp2 = especialidadeDAO.criar(1, "Psiquiatra");
        Especialidade esp3 = especialidadeDAO.criar(1, "Veterinario");
        
        LinkedList<Especialidade> conjunto1 = new LinkedList();
        conjunto1.add(esp1);
        
        LinkedList<Especialidade> conjunto2 = new LinkedList();
        conjunto2.add(esp2);
        
        LinkedList<Especialidade> conjunto3 = new LinkedList();
        conjunto3.add(esp3);
        
        LinkedList<Especialidade> conjunto12 = new LinkedList();
        conjunto12.add(esp1);
        conjunto12.add(esp2);
        
        
        Medico medico1 = medicoDAO.criar(1, "Ana Paula", new Telefone("1111-2222"), conjunto1);
        Medico medico2 = medicoDAO.criar(2, "Mariana Silva", new Telefone("1111-2222"), conjunto2);
        Medico medico3 = medicoDAO.criar(3, "Bianca Lima", new Telefone("1111-2222"), conjunto3);
        Medico medico4 = medicoDAO.criar(4, "Juliana Brito", new Telefone("1111-2222"), conjunto12);
        
        
        Paciente paciente1 = pacienteDAO.criar(new CPF("11111111110"), "Marcelo Lindo", new Telefone("1111-2222"), "Rua das Amebas", 24, Sexo.MASCULINO);
        Paciente paciente2 = pacienteDAO.criar(new CPF("11111111101"), "Pikachu da Silva", new Telefone("1111-2222"), "Rua das Pokebolas", 34, Sexo.MASCULINO);
        Paciente paciente3 = pacienteDAO.criar(new CPF("11111111011"), "Boloro Soro", new Telefone("1111-2222"), "Rua dos Taoqueis", 6, Sexo.MASCULINO);
        Paciente paciente4 = pacienteDAO.criar(new CPF("11111110111"), "Nikovoski", new Telefone("1111-2222"), "Rua Chavalosvskindfdfjdf", 24, Sexo.FEMININO);
        
        
        Doenca doenca1 = doencaDAO.criar(1, "Rinite");
        Doenca doenca2 = doencaDAO.criar(2, "Asma");
        Doenca doenca3 = doencaDAO.criar(3, "Alergia");
        Doenca doenca4 = doencaDAO.criar(4, "Peek");
        Doenca doenca5 = doencaDAO.criar(5, "Louco por vc, dra. Ana");
        
        
        Consulta consulta1 = consultaDiagnosticoDAO.criar(LocalDate.parse("2018-10-19"), false, null, null, 
                esp1, LocalTime.now(), null, paciente1, medico1);
        
        Consulta.Diagnostico diagnostico1 = consulta1.getDiagnostico();
        diagnostico1.setDoenca(doenca5);
        diagnostico1.setObservacoes("Meu crush");
        diagnostico1.setRemediosReceitados("Um abraço bem apertado");
        diagnostico1.setTratamentoRecomendado("Um cafuné da Ana duas vezes por dia de segunda à sexta");
        
        consultaDiagnosticoDAO.gravar(consulta1);
        
        
        Consulta consulta2 = consultaDiagnosticoDAO.criar(LocalDate.parse("2017-05-20"), false, null, null, 
                esp1, null, null, paciente1, medico1);
        
        Consulta.Diagnostico diagnostico2 = consulta2.getDiagnostico();
        diagnostico2.setDoenca(doenca5);
        diagnostico2.setObservacoes("Meu crush");
        diagnostico2.setRemediosReceitados("Um abraço bem apertado");
        diagnostico2.setTratamentoRecomendado("Um cafuné da Ana duas vezes por dia de segunda à sexta");
        
        consultaDiagnosticoDAO.gravar(consulta2);
        
        
        Consulta consulta3 = consultaDiagnosticoDAO.criar(LocalDate.parse("2018-10-19"), true, 234.5, FormaDePagamento.FIADO, 
                esp2, LocalTime.now(), LocalTime.now(), paciente2, medico4);
        
        Consulta.Diagnostico diagnostico3 = consulta3.getDiagnostico();
        diagnostico3.setDoenca(doenca3);
        diagnostico3.setObservacoes("Tá doente");
        diagnostico3.setRemediosReceitados("Não precisa, é terminal");
        diagnostico3.setTratamentoRecomendado("Sem chances");
        
        consultaDiagnosticoDAO.gravar(consulta3);
        
        
        Consulta consulta4 = consultaDiagnosticoDAO.criar(LocalDate.parse("2018-10-20"), true, 10.23, FormaDePagamento.DEBITO, 
                esp3, LocalTime.now(), LocalTime.now(), paciente1, medico2);
        
        Consulta.Diagnostico diagnostico4 = consulta4.getDiagnostico();
        diagnostico4.setDoenca(doenca4);
        diagnostico4.setObservacoes("O mosquitinho veio e peek");
        diagnostico4.setRemediosReceitados("Peek");
        diagnostico4.setTratamentoRecomendado("Tomar cuidado com o peek");
        
        consultaDiagnosticoDAO.gravar(consulta4);
        
        
        Consulta consulta5 = consultaDiagnosticoDAO.criar(LocalDate.parse("2018-10-19"), true, 43434.0, FormaDePagamento.DEBITO, 
                esp2, LocalTime.now(), LocalTime.now(), paciente1, medico3);
        
        Consulta.Diagnostico diagnostico5 = consulta5.getDiagnostico();
        diagnostico5.setDoenca(doenca2);
        diagnostico5.setObservacoes("Tá doente");
        diagnostico5.setRemediosReceitados(null);
        diagnostico5.setTratamentoRecomendado(null);
        
        consultaDiagnosticoDAO.gravar(consulta5);
       
        
        Consulta consulta6 = consultaDiagnosticoDAO.criar(LocalDate.parse("2018-10-14"), true, 2434343.0, FormaDePagamento.DEBITO, 
                esp2, LocalTime.now(), LocalTime.now(), paciente1, medico3);
        

        Consulta consultaEncontrada1 = consultaDiagnosticoDAO.buscar(consulta1.getID());  
        if(consulta1.equals(consultaEncontrada1)) {
            System.out.println("[1] - OK - Consultas iguais");
        } else {
            System.out.println("[1] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        Consulta consultaEncontrada2 = consultaDiagnosticoDAO.buscar(consulta2.getID());  
        if(consulta2.equals(consultaEncontrada2)) {
            System.out.println("[2] - OK - Consultas iguais");
        } else {
            System.out.println("[2] - ERRO - As consultas nao sao diferentes");
            erros++;
        }

        Consulta consultaEncontrada3 = consultaDiagnosticoDAO.buscar(consulta3.getID());  
        if(consulta3.equals(consultaEncontrada3)) {
            System.out.println("[3] - OK - Consultas iguais");
        } else {
            System.out.println("[3] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        Consulta consultaEncontrada4 = consultaDiagnosticoDAO.buscar(consulta4.getID());  
        if(consulta4.equals(consultaEncontrada4)) {
            System.out.println("[4] - OK - Consultas iguais");
        } else {
            System.out.println("[4] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        Consulta consultaEncontrada5 = consultaDiagnosticoDAO.buscar(consulta5.getID());  
        if(consulta5.equals(consultaEncontrada5)) {
            System.out.println("[5] - OK - Consultas iguais");
        } else {
            System.out.println("[5] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        Consulta consultaEncontrada6 = consultaDiagnosticoDAO.buscar(consulta6.getID());  
        if(consulta6.equals(consultaEncontrada6)) {
            System.out.println("[6] - OK - Consultas iguais");
        } else {
            System.out.println("[6] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas1 = consultaDiagnosticoDAO.buscarPelaData(LocalDate.parse("2018-10-19"));
        if(consultasEncontradas1.size() == 3
                && consultasEncontradas1.contains(consulta1)
                && consultasEncontradas1.contains(consulta3)
                && consultasEncontradas1.contains(consulta5)) {
        
            System.out.println("[7] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[7] - ERRO - Consultas esperadas NAO encontradas");
            erros++;
        }
        
        LinkedList<Consulta> consultasEncontradas2 = consultaDiagnosticoDAO.buscarPelaData(LocalDate.parse("2017-05-20"));
        if(consultasEncontradas2.size() == 1 && consultasEncontradas2.contains(consulta2)) {
            System.out.println("[8] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[8] - ERRO - Consultas esperadas NAO encontradas");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas3 = consultaDiagnosticoDAO.buscarPorPeriodo(LocalDate.parse("2018-10-19"),
                                                                                             LocalDate.parse("2018-10-20"));
        if(consultasEncontradas3.size() == 4
                && consultasEncontradas3.contains(consulta1)
                && consultasEncontradas3.contains(consulta3)
                && consultasEncontradas3.contains(consulta4)
                && consultasEncontradas3.contains(consulta5)) {
            System.out.println("[9] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[9] - ERRO - Consultas esperadas NAO encontradas");
            erros++;
        }
        
        LinkedList<Consulta> consultasEncontradas4 = consultaDiagnosticoDAO.buscarPorPeriodo(LocalDate.parse("2024-10-19"),
                                                                                             LocalDate.parse("2025-10-20"));
        if(consultasEncontradas4.isEmpty()) {
            System.out.println("[10] - OK - Nenhuma consulta foi retornada");
        } else {
            System.out.println("[10] - ERRO - Alguma consulta foi retornada");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas5 = consultaDiagnosticoDAO.buscarPeloPagou(Boolean.FALSE);
        if(consultasEncontradas5.size() == 2
                && consultasEncontradas5.contains(consulta1)
                && consultasEncontradas5.contains(consulta2)) {
            System.out.println("[11] - OK - Caloteiros encontrados");
        } else {
            System.out.println("[11] - ERRO - Ao menos um caloteiro nao foi pego");
            erros++;
        }
        
        LinkedList<Consulta> consultasEncontradas6 = consultaDiagnosticoDAO.buscarPeloPagou(Boolean.TRUE);
        if(consultasEncontradas6.size() == 4
                && consultasEncontradas6.contains(consulta3)
                && consultasEncontradas6.contains(consulta4)
                && consultasEncontradas6.contains(consulta5)
                && consultasEncontradas6.contains(consulta6)) {
            System.out.println("[12] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[12] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas7 = consultaDiagnosticoDAO.buscarPelaFormaDePagamento(null);
        if(consultasEncontradas7.size() == 2
                && consultasEncontradas7.contains(consulta1)
                && consultasEncontradas7.contains(consulta2)) {
            System.out.println("[13] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[13] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        LinkedList<Consulta> consultasEncontradas8 = consultaDiagnosticoDAO.buscarPelaFormaDePagamento(FormaDePagamento.FIADO);
        if(consultasEncontradas8.size() == 1
                && consultasEncontradas8.contains(consulta3)) {
            System.out.println("[14] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[14] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        LinkedList<Consulta> consultasEncontradas9 = consultaDiagnosticoDAO.buscarPelaFormaDePagamento(FormaDePagamento.DEBITO);
        if(consultasEncontradas9.size() == 3
                && consultasEncontradas6.contains(consulta3)
                && consultasEncontradas6.contains(consulta4)
                && consultasEncontradas6.contains(consulta5)) {
            System.out.println("[15] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[15] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
            
        LinkedList<Consulta> consultasEncontradas10 = consultaDiagnosticoDAO.buscarPelaEspecialidade(esp2.getCodigo());
        if(consultasEncontradas10.size() == 3
                && consultasEncontradas10.contains(consulta3)
                && consultasEncontradas10.contains(consulta5)
                && consultasEncontradas10.contains(consulta6)) {
            System.out.println("[16] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[16] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas11 = consultaDiagnosticoDAO.buscarPeloPaciente(paciente1.getCodigo());
        if(consultasEncontradas11.size() == 5
                && consultasEncontradas11.contains(consulta1)
                && consultasEncontradas11.contains(consulta2)
                && consultasEncontradas11.contains(consulta4)
                && consultasEncontradas11.contains(consulta5)
                && consultasEncontradas11.contains(consulta6)) {
            System.out.println("[17] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[17] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas12 = consultaDiagnosticoDAO.buscarPeloMedico(medico1.getCRM());
        if(consultasEncontradas12.size() == 2
                && consultasEncontradas12.contains(consulta1)
                && consultasEncontradas12.contains(consulta2)) {
            System.out.println("[18] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[18] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        
        LinkedList<Consulta> consultasEncontradas13 = consultaDiagnosticoDAO.buscarPelaDoenca(doenca5.getID());
        if(consultasEncontradas13.size() == 2
                && consultasEncontradas13.contains(consulta1)
                && consultasEncontradas13.contains(consulta2)) {
            System.out.println("[19] - OK - Consultas esperadas encontradas");
        } else {
            System.out.println("[19] - ERRO - Consultas NAO esperadas encontradas");
            erros++;
        }
        
        consultaDiagnosticoDAO.remover(consulta1.getID());
        consultaDiagnosticoDAO.remover(consulta2.getID());
        consultaDiagnosticoDAO.remover(consulta3.getID());
        consultaDiagnosticoDAO.remover(consulta4.getID());
        consultaDiagnosticoDAO.remover(consulta5.getID());
        consultaDiagnosticoDAO.remover(consulta6.getID());
        
        medicoDAO.remover(medico1.getCRM());
        medicoDAO.remover(medico2.getCRM());
        medicoDAO.remover(medico3.getCRM());
        medicoDAO.remover(medico4.getCRM());
        
        doencaDAO.remover(doenca1.getID());
        doencaDAO.remover(doenca2.getID());
        doencaDAO.remover(doenca3.getID());
        doencaDAO.remover(doenca4.getID());
        doencaDAO.remover(doenca5.getID());
        
        pacienteDAO.remover(paciente1.getCodigo());
        pacienteDAO.remover(paciente2.getCodigo());
        pacienteDAO.remover(paciente3.getCodigo());
        pacienteDAO.remover(paciente4.getCodigo());
        
        return erros;
    }
    
}
