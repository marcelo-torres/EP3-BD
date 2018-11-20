package teste_banco_de_dados;

import banco_de_dados.dao.postgresql.DoencaDAOPostgresql;
import dados_da_clinica.Doenca;
import java.util.LinkedList;


public class TesteDoenca {
    
    public static int testar() throws Exception {
    
        System.out.println("\n === Testando Doenca ===");
        
        int erros = 0;
        
        DoencaDAOPostgresql doencaDAO = new DoencaDAOPostgresql();
        
        Doenca dengue = doencaDAO.criar(10, "Dengue");
        Doenca chicoCunha = doencaDAO.criar(20, "ChicoCunha");
        
        chicoCunha.setNome("Chico Cunha");
        doencaDAO.gravar(chicoCunha);
        
        Doenca doencaEncontrada = doencaDAO.buscarPeloID(dengue.getID());
        if(dengue.equals(doencaEncontrada)) {
            System.out.println("[1] - OK - Doenca encontrada");
        } else {
            System.out.println("[1] - ERRO - Doenca nao encontrada");
            erros++;
        }
        
        Doenca frebeAmarela = doencaDAO.criar(15, "Febre Amarela");
        Doenca frebeMaculosa = doencaDAO.criar(16, "Febre Maculosa");
        Doenca frebeYellow = doencaDAO.criar(17, "Febre Yellow");
        
        LinkedList<Doenca> doencasEncontradas = doencaDAO.buscarPeloNome("Febre");
        if(doencasEncontradas.size() == 3
                && doencasEncontradas.contains(frebeAmarela)
                && doencasEncontradas.contains(frebeMaculosa)
                && doencasEncontradas.contains(frebeYellow)) {
            
            System.out.println("[2] - OK - Doencas localizadas com sucesso");
        } else {
            System.out.println("[2] - ERRO - As doencas nao foram localizadas corretamente");
            erros++;
        }
        
        doencaDAO.remover(10);
        doencaDAO.remover(20);
        doencaDAO.remover(15);
        doencaDAO.remover(16);
        doencaDAO.remover(17);
        
        return erros;
        
    }
    
}
