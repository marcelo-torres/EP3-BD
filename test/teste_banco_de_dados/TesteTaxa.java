package teste_banco_de_dados;

import banco_de_dados.postgresql.EspecialidadeDAOPostgresql;
import banco_de_dados.postgresql.TaxaDAOPostgresql;
import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;
import java.util.LinkedList;

public class TesteTaxa {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        EspecialidadeDAOPostgresql especialidadeDAO = new EspecialidadeDAOPostgresql();
        TaxaDAOPostgresql taxaDAO = new TaxaDAOPostgresql();
        
        
        taxaDAO.remover(10);
        taxaDAO.remover(11);
        taxaDAO.remover(12);
        taxaDAO.remover(13);
        
        
        especialidadeDAO.remover(1);
        especialidadeDAO.remover(2);
        
        Especialidade esp1 = especialidadeDAO.criar(1, 1, "Cardiologista");
        Especialidade esp2 = especialidadeDAO.criar(2, 1, "Medicologista");
        
        Taxa taxa1 = taxaDAO.criar(10, 2018, Mes.MAIO, 10.24, esp1);
        Taxa taxa2 = taxaDAO.criar(11, 2018, Mes.JUNHO, 10.24, esp1);
        Taxa taxa3 = taxaDAO.criar(12, 2018, Mes.JUNHO, 10.24, esp1);
        
        Taxa taxa4 = taxaDAO.criar(13, 2018, Mes.JUNHO, 10.24, esp2);
        Taxa taxa5 = taxaDAO.criar(13, 2018, Mes.JUNHO, 10.24, esp2);
        
        Taxa taxaEncontrada = taxaDAO.buscar(taxa1.getIDTaxa());
        if(taxa1.equals(taxaEncontrada)) {
            System.out.println("[1] - OK - Busca realizada com sucesso");
        } else {
            System.out.println("[1] - ERRO - Busca realizada sem sucesso");
            erros++;
        }
        
        taxa3.setMes(Mes.MAIO);
        taxaDAO.gravar(taxa3);
        
        LinkedList<Taxa> taxasEncontradas = taxaDAO.buscarPeloAno(2018);
        if(taxasEncontradas.size() == 4
                && taxasEncontradas.contains(taxa1)
                && taxasEncontradas.contains(taxa2)
                && taxasEncontradas.contains(taxa3)
                && taxasEncontradas.contains(taxa4)) {
        
            System.out.println("[2] - OK - Busca por ano realizada COM sucesso");
        } else {
            System.out.println("[2] - ERRO - Busca por ano realizada SEM sucesso");
            erros++;
        }
        
        taxasEncontradas = taxaDAO.buscarPeloMes(Mes.MAIO);
        if(taxasEncontradas.size() == 2
                && taxasEncontradas.contains(taxa1)
                && taxasEncontradas.contains(taxa3)) {
        
            System.out.println("[3] - OK - Busca por mes realizada COM sucesso");
        } else {
            System.out.println("[3] - ERRO - Busca por mes realizada SEM sucesso");
            erros++;
        }
        
        taxasEncontradas = taxaDAO.buscarPelaEspecialidade(esp1.getCodigo());
        if(taxasEncontradas.size() == 3
                && taxasEncontradas.contains(taxa1)
                && taxasEncontradas.contains(taxa2)
                && taxasEncontradas.contains(taxa3)) {
        
            System.out.println("[4] - OK - Busca por especialidade realizada COM sucesso");
        } else {
            System.out.println("[4] - ERRO - Busca por especialidade realizada SEM sucesso");
            erros++;
        }
        
        /*taxaDAO.remover(10);
        taxaDAO.remover(11);
        taxaDAO.remover(12);
        taxaDAO.remover(13);*/
        
        return erros;
        
    }
    
}
