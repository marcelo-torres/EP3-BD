package banco_de_dados.dao.postgresql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectorDAOPostgresql implements AutoCloseable {
    
    protected final Connection conexao;
    
    
    public ConectorDAOPostgresql() {
        try {
            this.conexao = new ConnectionFactoryPostgresql().getConexao();
        } catch(IOException ioe) {
            throw new RuntimeException("Erro ao ler arquivo de propriedades");
        } catch(SQLException sqle) {
            throw new RuntimeException("Erro ao acessar a base de dados");
        }
    }

    
    @Override
    public void close() throws SQLException {
        this.conexao.close();
    }
    
    
    public static String nomeCompleto(String schema, String nomeDaTabela) {
        return schema + ".\"" + nomeDaTabela + "\"";
    }
}
