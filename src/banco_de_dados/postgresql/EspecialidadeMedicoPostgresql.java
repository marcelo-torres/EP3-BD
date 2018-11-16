/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados.postgresql;

import banco_de_dados.EspecialidadeMedicoDao;
import dados_da_clinica.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.medico.Medico;

/**
 *
 * @author marcelo
 */
public class EspecialidadeMedicoPostgresql implements EspecialidadeMedicoDao {

    public static final String NOME_DA_TABELA = "EspecialidadeMedico";
    public static final String NOME_COMPLETO = Conector.nomeCompleto(NOME_DA_TABELA);
    
    
    @Override
    public void criar(Medico medico, Especialidade especialidade) throws Exception {
        
        Connection conexao = Conector.obterConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, especialidade.getCodigo());
            statement.setInt(2, medico.getCRM());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel atribuir esta especialidade ao medico: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(Medico medico, Especialidade especialidade) throws Exception {
        Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO
                + " WHERE fk_especialidade_codigo = ? AND fk_medico_crm = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, especialidade.getCodigo());
            statement.setInt(2, medico.getCRM());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover esta especialidade do medico: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }
    
    @Override
    public void remover(int crm) throws Exception {
        Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO
                + " WHERE fk_medico_crm = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, crm);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover as especialidades do medico: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }
    
    @Override
    public LinkedList<Especialidade> buscarPeloCrm(int crm) throws Exception {
        
        Connection conexao = Conector.obterConexao();
        
        LinkedList<Integer> codigoDasEspecialidadesEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE fk_medico_crm = " + crm;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int codigo = resultSet.getInt("fk_especialidade_codigo");
                codigoDasEspecialidadesEncontradas.add(codigo);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar as especialidades: " + sqle.getMessage());
        }
        
        LinkedList<Especialidade> especialidadesEncontradas = new LinkedList();
        
        EspecialidadePostgresql especialidadePostgresql = new EspecialidadePostgresql();
        for(int codigoDeEspecialidade : codigoDasEspecialidadesEncontradas) {
            Especialidade especialidade = especialidadePostgresql.buscarPeloCodigo(codigoDeEspecialidade);
            especialidadesEncontradas.add(especialidade);
        }
        
        return especialidadesEncontradas;
    }
    
}
