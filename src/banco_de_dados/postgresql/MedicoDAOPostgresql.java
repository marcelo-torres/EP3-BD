/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco_de_dados.postgresql;

import dados_da_clinica.Especialidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.medico.Medico;
import banco_de_dados.MedicoDAO;

/**
 *
 * @author marcelo
 */
public class MedicoDAOPostgresql implements MedicoDAO {
    
    public static final String NOME_DA_TABELA = "Medico";
    public static final String NOME_COMPLETO = Conector.nomeCompleto(NOME_DA_TABELA);
    
    
    @Override
    public Medico criar(int crm, String nome, Telefone telefone,
            LinkedList<Especialidade> especialidades) throws Exception {
    
        Medico novoMedico = new Medico(crm, nome, telefone, especialidades);
        
        Connection conexao = Conector.obterConexao();

        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, crm);
            statement.setString(2, nome);
            statement.setString(3, telefone.toString());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel inserir o medico no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        this.gravarEspecialidadesDoMedico(novoMedico);
        
        return novoMedico;
    }
    
    @Override
    public void gravar(Medico medico) throws Exception {
        
        Connection conexao = Conector.obterConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET nome = ?, telefone = ? WHERE crm = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, medico.getNome());
            statement.setString(2, medico.getTelefone().toString());
            statement.setInt(3, medico.getCRM());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel gravar os dados do medico: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
        
        new EspecialidadeMedicoDAOPostgresql().remover(medico.getCRM());
        this.gravarEspecialidadesDoMedico(medico);
    }
    
    @Override
    public void remover(int crm) throws Exception {
    
        Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE crm = ?";
        
        new EspecialidadeMedicoDAOPostgresql().remover(crm);
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, crm);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover o medico do banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
        }
    }
    
    @Override
    public Medico buscarPeloCrm(int crm) throws Exception {
    
        Connection conexao = Conector.obterConexao();

        Medico medico = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE crm = " + crm;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int crmEncontrado = resultSet.getInt("crm");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");

                medico = new Medico(crmEncontrado, nome, new Telefone(telefone),
                        new EspecialidadeMedicoDAOPostgresql().buscarPeloCrm(crm));
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel atualizar a especialidade no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
            return medico;
        }
    }
    
    @Override
    public LinkedList<Medico> buscarPeloNome(String nome) throws Exception {
    
        Connection conexao = Conector.obterConexao();
        
        LinkedList<Medico> medicosEncontrados = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE nome LIKE '%" + nome + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int crm = resultSet.getInt("crm");
                String nomeEncontrado = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");

                medicosEncontrados.add(
                        new Medico(crm, nomeEncontrado, new Telefone(telefone),
                            new EspecialidadeMedicoDAOPostgresql().buscarPeloCrm(crm))
                );
            }
        } catch(SQLException sqle) {
            throw new Exception("Erro ao buscar medico(s) no banco de dados: " + sqle.getMessage());
        } finally {
            conexao.close();
            return medicosEncontrados;
        }
    }
    
    
    private void gravarEspecialidadesDoMedico(Medico medico) throws Exception {
        
        LinkedList<Especialidade> especialidadesNaoInseridasPorErro = new LinkedList();
        EspecialidadeMedicoDAOPostgresql especialidadeMedicoPostgresql = new EspecialidadeMedicoDAOPostgresql();
        
        boolean erroAoInserirAlgumaEspecialidade = false;
        for(Especialidade especialidade : medico.getEspecialidades()) {
            try {
                especialidadeMedicoPostgresql.criar(medico, especialidade);
            } catch(Exception e) {
                erroAoInserirAlgumaEspecialidade = true;
                especialidadesNaoInseridasPorErro.add(especialidade);
            }
        }
        
        if(erroAoInserirAlgumaEspecialidade) {
            if(especialidadesNaoInseridasPorErro.size() == 1) {
                throw new Exception("Erro ao inserir a seguinte especialidade: "
                        + especialidadesNaoInseridasPorErro);
            } else {
                throw new Exception("Erro ao inserir as seguintes especialidades: "
                        + especialidadesNaoInseridasPorErro);
            }
        }
    }
}
