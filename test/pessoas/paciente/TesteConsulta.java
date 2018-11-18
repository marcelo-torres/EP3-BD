package pessoas.paciente;

import dados_da_clinica.Consulta;
import dados_da_clinica.Consulta.Diagnostico;
import dados_da_clinica.Doenca;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;


public class TesteConsulta {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        Especialidade esp1 = new Especialidade(10, 1, "Cariologista");
        Especialidade esp2 = new Especialidade(11, 1, "Psiquiatra");
        Especialidade esp3 = new Especialidade(12, 1, "Veterinario");
        
        LinkedList<Especialidade> conjunto1 = new LinkedList();
        conjunto1.add(esp1);
        
        LinkedList<Especialidade> conjunto2 = new LinkedList();
        conjunto2.add(esp2);
        
        LinkedList<Especialidade> conjunto3 = new LinkedList();
        conjunto3.add(esp3);
        
        LinkedList<Especialidade> conjunto12 = new LinkedList();
        conjunto12.add(esp1);
        conjunto12.add(esp2);
        
        
        Medico medico1 = new Medico(1, "Ana Paula", new Telefone("1111-2222"), conjunto1);
        Medico medico2 = new Medico(2, "Mariana Silva", new Telefone("1111-2222"), conjunto2);
        Medico medico3 = new Medico(3, "Bianca Lima", new Telefone("1111-2222"), conjunto3);
        Medico medico4 = new Medico(4, "Juliana Brito", new Telefone("1111-2222"), conjunto12);
        
        
        Paciente paciente1 = new Paciente(1, new CPF("11111111110"), "Marcelo Lindo", new Telefone("1111-2222"), "Rua das Amebas", 24, Sexo.MASCULINO);
        Paciente paciente2 = new Paciente(2, new CPF("11111111101"), "Pikachu da Silva", new Telefone("1111-2222"), "Rua das Pokebolas", 34, Sexo.MASCULINO);
        Paciente paciente3 = new Paciente(3, new CPF("11111111011"), "Boloro Soro", new Telefone("1111-2222"), "Rua dos Taoqueis", 6, Sexo.MASCULINO);
        Paciente paciente4 = new Paciente(4, new CPF("11111110111"), "Nikovoski", new Telefone("1111-2222"), "Rua Chavalosvskindfdfjdf", 24, Sexo.FEMININO);
        
        
        Doenca doenca1 = new Doenca(1, "Rinite");
        Doenca doenca2 = new Doenca(2, "Asma");
        Doenca doenca3 = new Doenca(3, "Alergia");
        Doenca doenca4 = new Doenca(4, "Peek");
        Doenca doenca5 = new Doenca(5, "Louco por vc, dra. Ana");
        
        
        Consulta consulta1 = new Consulta(100, LocalDate.now(), false, null, null, 
                esp1, null, null, paciente1, medico1);
        
        Diagnostico diagnostico1 = consulta1.getDiagnostico();
        diagnostico1.setDoenca(doenca5);
        diagnostico1.setObservacoes("Meu crush");
        diagnostico1.setRemediosReceitados("Um abraço bem apertado");
        diagnostico1.setTratamentoRecomendado("Um cafuné da Ana duas vezes por dia de segunda à sexta");
        
        Consulta consulta2 = new Consulta(100, consulta1.getData(), false, null, null, 
                esp1, null, null, paciente1, medico1);
        
        Diagnostico diagnostico2 = consulta2.getDiagnostico();
        diagnostico2.setDoenca(doenca5);
        diagnostico2.setObservacoes("Meu crush");
        diagnostico2.setRemediosReceitados("Um abraço bem apertado");
        diagnostico2.setTratamentoRecomendado("Um cafuné da Ana duas vezes por dia de segunda à sexta");
        
        if(consulta1.equals(consulta2)) {
            System.out.println("[1] - OK - Consultas iguais");
        } else {
            System.out.println("[2] - ERRO - As consultas nao sao diferentes");
            erros++;
        }
        
        return erros;
    
    }
    
}
