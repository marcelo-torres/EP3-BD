/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste_banco_de_dados;

import banco_de_dados.dao.postgresql.EspecialidadeMedicoDAOPostgresql;
import banco_de_dados.dao.postgresql.EspecialidadeDAOPostgresql;
import banco_de_dados.dao.postgresql.MedicoDAOPostgresql;
import dados_da_clinica.Especialidade;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;

/**
 *
 * @author marcelo
 */
public class TesteMedico {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        boolean sucesso;
        
        EspecialidadeDAOPostgresql especialidadeDao = new EspecialidadeDAOPostgresql();
        MedicoDAOPostgresql medicoDao = new MedicoDAOPostgresql();
        EspecialidadeMedicoDAOPostgresql especialidadeMedicoDao = new EspecialidadeMedicoDAOPostgresql();
    
        
        medicoDao.remover(1010);
        medicoDao.remover(5323);
        medicoDao.remover(6924);
        
        especialidadeDao.remover(1);
        especialidadeDao.remover(2);
        especialidadeDao.remover(3);
        especialidadeDao.remover(4);
        especialidadeDao.remover(5);
        especialidadeDao.remover(6);
        
        Especialidade esp1 = especialidadeDao.criar(1, 4, "Otorrino");
        Especialidade esp2 = especialidadeDao.criar(2, 5, "Legista");
        Especialidade esp3 = especialidadeDao.criar(3, 2, "Ortopedista");
        Especialidade esp4 = especialidadeDao.criar(4, 8, "Dermatologista");
        Especialidade esp5 = especialidadeDao.criar(5, 2, "Cardiologista");
        Especialidade esp6 = especialidadeDao.criar(6, 1, "Clinico Geral");
        
        LinkedList<Especialidade> especialidades1 = new LinkedList();
        especialidades1.add(esp1);
        especialidades1.add(esp2);
        especialidades1.add(esp3);
        Medico medico1 = medicoDao.criar(1010, "Fernando Augusto Maniaco", new Telefone("1234-5678"), especialidades1);
        
        LinkedList<Especialidade> especialidades2 = new LinkedList();
        especialidades2.add(esp4);
        Medico medico2 = medicoDao.criar(5323, "Juliana Augusto", new Telefone("3456-8635"), especialidades2);
        
        LinkedList<Especialidade> especialidades3 = new LinkedList();
        especialidades3.add(esp2);
        especialidades3.add(esp5);
        Medico medico3 = medicoDao.criar(6924, "Maniaco Hernandes", new Telefone("6666-6666"), especialidades3);
        
        especialidadeDao.remover(6);
        Especialidade especialidadeBuscada = especialidadeDao.buscarPeloCodigo(6);
        if(especialidadeBuscada == null) {
            System.out.println("[1] Especialidade " + esp6 + " removida com sucesso!");
        } else {
            System.out.println("[1] Especialidade " + esp6 + " NAO FOI removida!");
        }
        
        Medico medico1_t = medicoDao.buscarPeloCrm(medico1.getCRM());
        if(!medico1.equals(medico1_t)) {
            System.out.println("[2] - O medico 1 difere do que esta no banco!!! ");
            erros++;
        } else {
            System.out.println("[2] - A insercao do medico 1 deu certo");
        }
        
        Medico medico2_t = medicoDao.buscarPeloCrm(medico2.getCRM());
        if(!medico2.equals(medico2_t)) {
            System.out.println("[3] - O medico 2 difere do que esta no banco!!! ");
            erros++;
        } else {
            System.out.println("[3] - A insercao do medico 2 deu certo");
        }
        
        Medico medico3_t = medicoDao.buscarPeloCrm(medico3.getCRM());
        if(!medico3.equals(medico3_t)) {
            System.out.println("[4] - O medico 3 difere do que esta no banco!!! ");
            erros++;
        } else {
            System.out.println("[4] - A insercao do medico 3 deu certo");
        }
        
        medico1.setNome("Josebaldo Maniaco");
        medicoDao.gravar(medico1);
        
        Medico medico1_t2 = medicoDao.buscarPeloCrm(medico1.getCRM());
        if(!medico1.equals(medico1_t2)) {
            System.out.println("[5] - O medico 1 difere do que esta no banco!!! ");
            erros++;
        } else {
            System.out.println("[5] - A atualizacao do medico 1 deu certo");
        }
        
        medico2.adicionarEspecialidade(esp5);
        medico2.adicionarEspecialidade(esp2);
        medico2.removerEspecialidade(esp4);
        medicoDao.gravar(medico2);
        
        Medico medico2_t2 = medicoDao.buscarPeloCrm(medico2.getCRM());
        if(!medico2.equals(medico2_t2)) {
            System.out.println("[6] - O medico 2 difere do que esta no banco!!! ");
            erros++;
        } else {
            System.out.println("[6] - A atualizacao do medico 2 deu certo");
        }
        
        LinkedList<Medico> lista1 = new LinkedList();
        lista1.add(medico3);
        LinkedList<Medico> medicosEncontrados = medicoDao.buscarPeloNome("Hernandes");
        
        if(medicosEncontrados.containsAll(lista1)
                && lista1.containsAll(medicosEncontrados)) {
        
            System.out.println("[7] - Busca efetuada com sucesso");
        } else {
            System.out.println("[7] - Erro durante a busca pelo nome");
            erros++;
        }
        
        LinkedList<Medico> lista2 = new LinkedList();
        lista2.add(medico1);
        lista2.add(medico3);
        LinkedList<Medico> medicosEncontrados2 = medicoDao.buscarPeloNome("Maniaco");
      
        if(medicosEncontrados2.containsAll(lista2)
                && lista2.containsAll(medicosEncontrados2)) {
        
            System.out.println("[8] - Busca efetuada com sucesso");
        } else {
            System.out.println("[8] - Erro durante a busca pelo nome");
            erros++;
        }
        
        LinkedList<Medico> lista3 = new LinkedList();
        lista2.add(medico2);
        lista2.add(medico3);
        LinkedList<Medico> medicosEncontrados3 = medicoDao.buscarPorEspecialidade(2);
        
        if(medicosEncontrados3.containsAll(lista3)
                && lista3.containsAll(medicosEncontrados3)) {
        
            System.out.println("[9] - Busca por especialidade efetuada com sucesso");
        } else {
            System.out.println("[9] - Erro durante a busca por especialidade");
            erros++;
        }
        
        medicoDao.remover(medico1.getCRM());
        medicoDao.remover(medico2.getCRM());
        medicoDao.remover(medico3.getCRM());
        
        especialidadeDao.remover(esp1.getCodigo());
        especialidadeDao.remover(esp2.getCodigo());
        especialidadeDao.remover(esp3.getCodigo());
        especialidadeDao.remover(esp4.getCodigo());
        especialidadeDao.remover(esp5.getCodigo());
        especialidadeDao.remover(esp6.getCodigo());
        
        return erros;
    }
    
}
