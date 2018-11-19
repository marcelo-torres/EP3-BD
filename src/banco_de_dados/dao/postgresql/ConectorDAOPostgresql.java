package banco_de_dados.dao.postgresql;

import java.io.IOException;

public class ConectorDAOPostgresql {
    
    protected final ConnectionFactoryPostgresql fabricaDeConexoes;
    
    
    public ConectorDAOPostgresql() {        
        try {
            this.fabricaDeConexoes = new ConnectionFactoryPostgresql();
        } catch(IOException ioe) {
            throw new RuntimeException("Erro ao ler arquivo de propriedades");
        }
    }
    
    public static String nomeCompleto(String schema, String nomeDaTabela) {
        return schema + ".\"" + nomeDaTabela + "\"";
    }
}
