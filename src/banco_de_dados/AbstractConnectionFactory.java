package banco_de_dados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public abstract class AbstractConnectionFactory {
 
    protected String usuario;
    protected String senha;
    protected String host;
    protected String schema;
    
    protected abstract void setUsuario(String usuario);
    
    protected abstract void setSenha(String senha);
    
    protected abstract void setHost(String host);
 
    protected abstract void setSchema(String schema);
    
    
    public abstract Connection getConexao() throws BancoDeDadosException;
    
    
    protected void carregarPropriedades(String caminhoParaArquivoDePropriedades) throws IOException { 
    
        FileInputStream file = new FileInputStream(caminhoParaArquivoDePropriedades);
        
        Properties propriedades = new Properties();
        propriedades.load(file);
        
        this.setUsuario(propriedades.getProperty("USUARIO"));
        this.setSenha(propriedades.getProperty("SENHA"));
        this.setHost(propriedades.getProperty("HOST"));
        this.setSchema(propriedades.getProperty("SCHEMA"));
    }
}
