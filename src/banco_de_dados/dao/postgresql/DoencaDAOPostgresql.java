package banco_de_dados.dao.postgresql;

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
    public Doenca criar(int id, String nome) throws Exception {
        
        Doenca doenca = new Doenca(id, nome);
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, nome);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível criar a doença: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return doenca;
        }
    }

    @Override
    public void gravar(Doenca doenca) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET nome = ? WHERE id_doenca = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, doenca.getNome());
            statement.setInt(2, doenca.getID());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível gravar a doença: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
    }

    @Override
    public void remover(int id) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_doenca = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível excluir a doença: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
    }

    @Override
    public Doenca buscarPeloID(int id) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        Doenca doenca = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_doenca = " + id;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int idEncontrado = resultSet.getInt("id_doenca");
                String nome = resultSet.getString("nome");
                
                doenca = new Doenca(idEncontrado, nome);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontrar a doença com este ID: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return doenca;
        }
    }

    @Override
    public LinkedList<Doenca> buscarPeloNome(String nome) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        LinkedList<Doenca> doencasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE nome "
                + "LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int id = resultSet.getInt("id_doenca");
                String nomeEncontrado1 = resultSet.getString("nome");

                Doenca doenca = new Doenca(id, nomeEncontrado1);
                
                doencasEncontradas.add(doenca);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontra a(s) doença(s): " + sqle.getMessage());
        } finally {
            //conexao.close();
            return doencasEncontradas;
        }
    } 
}
