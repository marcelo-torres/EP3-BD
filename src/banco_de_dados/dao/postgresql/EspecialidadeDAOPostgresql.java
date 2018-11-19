package banco_de_dados.dao.postgresql;

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
    public Especialidade criar(int codigo, int indice, String nome) throws Exception {
        
        Especialidade especialidade = new Especialidade(codigo, indice, nome);
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.setInt(2, indice);
            statement.setString(3, nome);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel inserir a especialidade no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return especialidade;
    }

    @Override
    public void gravar(Especialidade especialidade) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET indice = ?, nome = ? WHERE codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, especialidade.getIndice());
            statement.setString(2, especialidade.getNome());
            statement.setInt(3, especialidade.getCodigo());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel gravar a especialidade no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int codigo) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE codigo = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, codigo);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover a especialidade do banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }
    
    @Override
    public Especialidade buscarPeloCodigo(int codigo) throws Exception {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Especialidade especialidade = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE codigo = " + codigo;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int codigoEncontrado = resultSet.getInt("codigo");
                int indice = resultSet.getInt("indice");
                String nome = resultSet.getString("nome");

                especialidade = new Especialidade(codigoEncontrado, indice, nome);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar esta especialidade no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return especialidade;
    }

    @Override
    public LinkedList<Especialidade> buscarPeloNome(String nome) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Especialidade> especialidadesEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE nome LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                int indice = resultSet.getInt("indice");
                String nomeEncontrado = resultSet.getString("nome");

                especialidadesEncontradas.add(new Especialidade(codigo, indice, nomeEncontrado));
            }
        } catch(SQLException sqle) {
            throw new Exception("Erro ao buscar especialidade(s) no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return especialidadesEncontradas;
    }
}
