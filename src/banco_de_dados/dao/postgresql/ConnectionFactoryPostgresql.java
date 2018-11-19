package banco_de_dados.dao.postgresql;

import banco_de_dados.AbstractConnectionFactory;
import banco_de_dados.BancoDeDadosException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactoryPostgresql extends AbstractConnectionFactory {

    private static final String 
            CAMINHO_PARA_ARQUIVO_DE_PROPRIEDADES = "src/propriedadesBD.propresties";
    
    
    public ConnectionFactoryPostgresql() throws IOException {
        super.carregarPropriedades(CAMINHO_PARA_ARQUIVO_DE_PROPRIEDADES);
    }
    
    
    @Override
    protected void setUsuario(String usuario) {
        super.usuario = usuario;
    }

    @Override
    protected void setSenha(String senha) {
        super.senha = senha;
    }

    @Override
    protected void setHost(String host) {
        super.host = host;
    }
    
    @Override
    protected void setSchema(String schema) {
        super.schema = schema;
    }
    
    
    @Override
    public Connection getConexao() throws BancoDeDadosException {
        try {
            Connection conexao = DriverManager.getConnection(super.host, super.usuario, super.senha);
            conexao.setSchema(super.schema);
            return conexao;
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível estabelar uma conexão com o servidor", sqle);
        }
    }
}
