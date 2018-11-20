package banco_de_dados.dao.postgresql;

import banco_de_dados.BancoDeDadosException;
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
    public boolean existeTaxa(int idTaxa) throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "SELECT COUNT(1) FROM " + NOME_COMPLETO + 
                " WHERE id_taxa =" + idTaxa;
        
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int quantidade = resultSet.getInt(1);
                
                return quantidade != 0;
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível verificar a exitência da taxa no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return false;
    }
    
    @Override
    public Taxa criar(Integer ano, Mes mes, double valor, 
            Especialidade especialidade) throws BancoDeDadosException, SQLException {
        
        Taxa taxa = new Taxa(ano, mes, valor, especialidade);
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (DEFAULT, ?, ?, ?, ?) "
                + "RETURNING id_taxa";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, ano);
            statement.setInt(2, mes.numeroDoMes);
            statement.setDouble(3, valor);
            statement.setInt(4, especialidade.getCodigo());

            statement.execute();
            
            ResultSet resultSet = statement.getResultSet();
            
            if(resultSet.next()) {
                taxa.setId(resultSet.getInt(1));
            }
            
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível criar a taxa", sqle);
        } finally {
            conexao.close();
        }
        
        return taxa;
    }

    @Override
    public void gravar(Taxa taxa) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET ano = ?, mes = ?, "
                + "valor = ?, fk_especialidade_codigo = ? WHERE id_taxa = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, taxa.getAno());
            statement.setInt(2, taxa.getMes().numeroDoMes);
            statement.setDouble(3, taxa.getValor());
            statement.setInt(4, taxa.getEspecialidade().getCodigo());
            statement.setInt(5, taxa.getId());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível gravar a taxa", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int idTaxa) throws BancoDeDadosException, SQLException {
              
        if(!this.existeTaxa(idTaxa)) {
            throw new BancoDeDadosException("Não existe nenhuma taxa com este ID");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_taxa = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, idTaxa);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível remover a taxa", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public Taxa buscar(int idTaxa) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Taxa taxa = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_taxa = " + idTaxa;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                taxa = this.criarTaxaAPartirDe(resultSet);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta especialidade no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return taxa;
    }

    @Override
    public LinkedList<Taxa> buscarPeloAno(Integer ano) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE ano = " + ano; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Taxa taxa = this.criarTaxaAPartirDe(resultSet);
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta taxa no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }

    @Override
    public LinkedList<Taxa> buscarPeloMes(Mes mes) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE mes = " + mes.numeroDoMes; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Taxa taxa = this.criarTaxaAPartirDe(resultSet);
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta taxa no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }

    @Override
    public LinkedList<Taxa> buscarPelaEspecialidade(int codigoEspecialidade) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Taxa> taxasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE fk_especialidade_codigo = " + codigoEspecialidade; 
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Taxa taxa = this.criarTaxaAPartirDe(resultSet);
                taxasEncontradas.add(taxa);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta taxa no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return taxasEncontradas;
    }
    
    
    private Taxa criarTaxaAPartirDe(ResultSet resultSet) throws BancoDeDadosException, SQLException {
    
        int idTaxa = resultSet.getInt("id_taxa");
        Integer ano = resultSet.getInt("ano");
        int mes = resultSet.getInt("mes");
        double valor = resultSet.getDouble("valor");
        int codigoEspecialidade = resultSet.getInt("fk_especialidade_codigo");

        Especialidade especialidade = 
                new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidade);

        return new Taxa(idTaxa, ano, Mes.obterValor(mes), valor, especialidade);
    }
}
