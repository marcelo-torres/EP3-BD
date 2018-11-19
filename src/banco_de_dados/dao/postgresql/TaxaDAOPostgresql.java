package banco_de_dados.dao.postgresql;

import banco_de_dados.dao.TaxaDAO;
import dados_da_clinica.Especialidade;
import dados_da_clinica.Mes;
import dados_da_clinica.Taxa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class TaxaDAOPostgresql extends ConectorDAOPostgresql implements TaxaDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Taxa";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public Taxa criar(int idTaxa, Integer ano, Mes mes, double valor, Especialidade especialidade) throws Exception {
        
        Taxa taxa = new Taxa(idTaxa, ano, mes, valor, especialidade);
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, idTaxa);
            statement.setInt(2, ano);
            statement.setInt(3, mes.numeroDoMes);
            statement.setDouble(4, valor);
            statement.setInt(5, especialidade.getCodigo());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível criar a taxa: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return taxa;
    }

    @Override
    public void gravar(Taxa taxa) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET ano = ?, mes = ?, "
                + "valor = ?, fk_especialidade_codigo = ? WHERE id_taxa = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, taxa.getAno());
            statement.setInt(2, taxa.getMes().numeroDoMes);
            statement.setDouble(3, taxa.getValor());
            statement.setInt(4, taxa.getEspecialidade().getCodigo());
            statement.setInt(5, taxa.getIDTaxa());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível gravar a taxa: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int idTaxa) throws Exception {
              
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_taxa = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, idTaxa);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível remover a taxa: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }

    @Override
    public Taxa buscar(int idTaxa) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Taxa taxa = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_taxa = " + idTaxa;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int idTaxaEncontrada = resultSet.getInt("id_taxa");
                Integer ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                double valor = resultSet.getDouble("valor");
                int codigoEspecialidade = resultSet.getInt("fk_especialidade_codigo");

                Especialidade especialidade = 
                        new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidade);
                
                taxa = new Taxa(idTaxaEncontrada, ano, Mes.obterValor(mes), valor, especialidade);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar esta especialidade no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return taxa;
    }

    @Override
    public LinkedList<Taxa> buscarPeloAno(Integer ano) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE ano = " + ano; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int idTaxa = resultSet.getInt("id_taxa");
                Integer anoEncontrado = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                double valor = resultSet.getDouble("valor");
                int codigoEspecialidade = resultSet.getInt("fk_especialidade_codigo");

                Especialidade especialidade = 
                        new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidade);
                
                Taxa taxa = new Taxa(idTaxa, anoEncontrado, Mes.obterValor(mes), valor, especialidade);
                
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontrar esta taxa no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }

    @Override
    public LinkedList<Taxa> buscarPeloMes(Mes mes) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE mes = " + mes.numeroDoMes; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int idTaxa = resultSet.getInt("id_taxa");
                Integer ano = resultSet.getInt("ano");
                int mesEncontrado = resultSet.getInt("mes");
                double valor = resultSet.getDouble("valor");
                int codigoEspecialidade = resultSet.getInt("fk_especialidade_codigo");

                Especialidade especialidade = 
                        new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidade);
                
                Taxa taxa = new Taxa(idTaxa, ano, Mes.obterValor(mesEncontrado), valor, especialidade);
                
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontrar esta taxa no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }

    @Override
    public LinkedList<Taxa> buscarPelaEspecialidade(int codigoEspecialidade) throws Exception {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE fk_especialidade_codigo = " + codigoEspecialidade; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int idTaxa = resultSet.getInt("id_taxa");
                Integer ano = resultSet.getInt("ano");
                int mes = resultSet.getInt("mes");
                double valor = resultSet.getDouble("valor");
                int codigoEspecialidadeEncontrado = resultSet.getInt("fk_especialidade_codigo");

                Especialidade especialidade = 
                        new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidadeEncontrado);
                
                Taxa taxa = new Taxa(idTaxa, ano, Mes.obterValor(mes), valor, especialidade);
                
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontrar esta taxa no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }
}
