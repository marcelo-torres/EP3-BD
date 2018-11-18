package banco_de_dados.dao.postgresql;

import banco_de_dados.AbstractConnectionFactory;
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
    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection(super.host, super.usuario, super.senha);
    }
}
