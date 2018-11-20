package teste_banco_de_dados;

import banco_de_dados.dao.postgresql.EspecialidadeDAOPostgresql;
import banco_de_dados.dao.postgresql.TaxaDAOPostgresql;
import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;
import java.util.LinkedList;

public class TesteTaxa {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        EspecialidadeDAOPostgresql especialidadeDAO = new EspecialidadeDAOPostgresql();
        TaxaDAOPostgresql taxaDAO = new TaxaDAOPostgresql();

        
        Especialidade esp1 = especialidadeDAO.criar(1, "Cardiologista");
        Especialidade esp2 = especialidadeDAO.criar(1, "Medicologista");
        
        Taxa taxa1 = taxaDAO.criar(2018, Mes.MAIO, 10.24, esp1);
        Taxa taxa2 = taxaDAO.criar(2018, Mes.JUNHO, 10.24, esp1);
        Taxa taxa3 = taxaDAO.criar(2018, Mes.JUNHO, 10.24, esp1);
        
        Taxa taxa4 = taxaDAO.criar(2018, Mes.JUNHO, 10.24, esp2);

        
        Taxa taxaEncontrada = taxaDAO.buscar(taxa1.getId());
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
        
        if(taxaDAO.existeTaxa(taxa1.getId())) {
            System.out.println("[5] - OK - A taxa realmente existe");
        } else {
            System.out.println("[5] - ERRO - A taxa nao existe");
            erros++;
        }
        
        taxaDAO.remover(taxa1.getId());
        taxaDAO.remover(taxa2.getId());
        taxaDAO.remover(taxa3.getId());
        taxaDAO.remover(taxa4.getId());
        
        especialidadeDAO.remover(esp1.getCodigo());
        especialidadeDAO.remover(esp2.getCodigo());
        
        return erros;  
    }
}
