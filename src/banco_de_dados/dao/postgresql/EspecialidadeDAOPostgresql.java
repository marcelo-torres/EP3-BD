package banco_de_dados.dao.postgresql;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import banco_de_dados.dao.EspecialidadeDAO;


public class EspecialidadeDAOPostgresql extends ConectorDAOPostgresql implements EspecialidadeDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Especialidade";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public boolean existeEspecialidade(int codigo) throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "SELECT COUNT(1) FROM " + NOME_COMPLETO + 
                " WHERE codigo =" + codigo;
        
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int quantidade = resultSet.getInt(1);
                
                return quantidade != 0;
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível verificar a exitência da especialidade no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return false;
    }
    
    @Override
    public Especialidade criar(int indice, String nome) throws BancoDeDadosException, SQLException {
        
        Especialidade especialidade = new Especialidade(indice, nome);
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (DEFAULT, ?, ?)"
                + " RETURNING codigo";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, indice);
            statement.setString(2, nome);

            statement.execute();
            
            ResultSet resultSet = statement.getResultSet();
            
            if(resultSet.next()) {
                especialidade.setCodigo(resultSet.getInt(1));
            }
            
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível inserir a especialidade no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return especialidade;
    }

    @Override
    public void gravar(Especialidade especialidade) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET indice = ?, nome = ? WHERE codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, especialidade.getIndice());
            statement.setString(2, especialidade.getNome());
            statement.setInt(3, especialidade.getCodigo());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível gravar a especialidade no banco de dados", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int codigo) throws BancoDeDadosException, SQLException {
        
        if(!this.existeEspecialidade(codigo)) {
            throw new BancoDeDadosException("Não existe nenhuma especialidade com este código");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, codigo);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível remover a especialidade do banco de dados", sqle);
        } finally {
            conexao.close();
        }
    }
    
    @Override
    public Especialidade buscarPeloCodigo(int codigo) throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Especialidade especialidade = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE codigo = " + codigo;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                especialidade = this.criarEspecialidadeAPartirDe(resultSet);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta especialidade no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return especialidade;
    }

    @Override
    public LinkedList<Especialidade> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Especialidade> especialidadesEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE nome LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Especialidade especialidade = this.criarEspecialidadeAPartirDe(resultSet);
                especialidadesEncontradas.add(especialidade);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Erro ao buscar especialidade(s) no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return especialidadesEncontradas;
    }
    
    
    private Especialidade criarEspecialidadeAPartirDe(ResultSet resultSet) throws SQLException {
         int codigo = resultSet.getInt("codigo");
         int indice = resultSet.getInt("indice");
         String nome = resultSet.getString("nome");
         
         return new Especialidade(codigo, indice, nome);
    }
}
