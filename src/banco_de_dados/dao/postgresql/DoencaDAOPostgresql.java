package banco_de_dados.dao.postgresql;

import banco_de_dados.BancoDeDadosException;
import banco_de_dados.dao.DoencaDAO;
import dados_da_clinica.Doenca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class DoencaDAOPostgresql extends ConectorDAOPostgresql implements DoencaDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Doenca";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public boolean existeDoenca(int id) throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "SELECT COUNT(1) FROM " + NOME_COMPLETO + 
                " WHERE id_doenca =" + id;
        
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int quantidade = resultSet.getInt(1);
                
                return quantidade != 0;
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível verificar a exitência da doença no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return false;
    }
    
    @Override
    public Doenca criar(int id, String nome) throws BancoDeDadosException, SQLException {
        
        Doenca doenca = new Doenca(id, nome);
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, nome);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível criar a doença: ", sqle);
        } finally {
            conexao.close();
        }
        
        return doenca;
    }

    @Override
    public void gravar(Doenca doenca) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET nome = ? WHERE id_doenca = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, doenca.getNome());
            statement.setInt(2, doenca.getID());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível gravar a doença: ", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int id) throws BancoDeDadosException, SQLException {
        
        if(!this.existeDoenca(id)) {
            throw new BancoDeDadosException("Não existe nenhuma doença com este ID");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_doenca = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível excluir a doença: ", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public Doenca buscarPeloID(int id) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Doenca doenca = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_doenca = " + id;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                doenca =this.criarDoencaAPartirDe(resultSet);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a doença com este ID: ", sqle);
        } finally {
            conexao.close();
        }
        
        return doenca;
    }

    @Override
    public LinkedList<Doenca> buscarPeloNome(String nome) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Doenca> doencasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE nome "
                + "LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Doenca doenca = this.criarDoencaAPartirDe(resultSet);
                doencasEncontradas.add(doenca);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontra a(s) doença(s): ", sqle);
        } finally {
            conexao.close();
        }
        
        return doencasEncontradas;
    }
    
    
    private Doenca criarDoencaAPartirDe(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id_doenca");
        String nomeEncontrado1 = resultSet.getString("nome");

        return new Doenca(id, nomeEncontrado1);
    }
}
