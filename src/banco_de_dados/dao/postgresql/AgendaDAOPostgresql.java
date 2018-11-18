package banco_de_dados.dao.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Agenda;
import pessoas.medico.Medico;
import pessoas.paciente.DiaDaSemana;
import banco_de_dados.dao.AgendaDAO;
import java.sql.ResultSet;


public class AgendaDAOPostgresql extends ConectorDAOPostgresql implements AgendaDAO{

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Agenda";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    
    @Override
    public Agenda criar(int id, DiaDaSemana diaDaSemana, LocalTime horarioDeInicio, LocalTime horarioDoFim, Medico donoDaAgenda) throws Exception {
        
        Agenda agenda = new Agenda(id, diaDaSemana, horarioDeInicio, 
                horarioDoFim, donoDaAgenda);
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, diaDaSemana.toString());
            statement.setString(3, horarioDeInicio.format(Agenda.PADRAO_DE_HORARIO));
            statement.setString(4, horarioDoFim.format(Agenda.PADRAO_DE_HORARIO));
            statement.setInt(5, donoDaAgenda.getCRM());

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel criar a genda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return agenda;
        }
    }

    @Override
    public void gravar(Agenda agenda) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET dia_semana = ?, "
                + "horario_inicio = ?, horario_fim = ?, fk_medico_crm = ? "
                + "WHERE id_agenda = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, agenda.getDiaDaSemana().toString());
            statement.setString(2, agenda.getHorarioDoInicio().format(Agenda.PADRAO_DE_HORARIO));
            statement.setString(3, agenda.getHorarioDoFim().format(Agenda.PADRAO_DE_HORARIO));
            statement.setInt(4, agenda.getDonoDaAgenda().getCRM());
            statement.setInt(5, agenda.getID());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel gravar a agenda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
    }

    @Override
    public void remover(int id) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_agenda = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel remover a agenda do banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
        }
    }

    @Override
    public Agenda buscar(int id) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        Agenda agenda = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_agenda = " + id;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int idEncontrado = resultSet.getInt("id_agenda");
                String diaDaSemana = resultSet.getString("dia_semana");
                String horarioDoInicio = resultSet.getString("horario_inicio");
                String horarioDoFim = resultSet.getString("horario_fim");
                int crm = resultSet.getInt("fk_medico_crm");

                Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crm);
                
                agenda = new Agenda(idEncontrado, DiaDaSemana.obterValor(diaDaSemana),
                        LocalTime.parse(horarioDoInicio), LocalTime.parse(horarioDoFim),
                        medico);
            }
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível encontrar esta agenda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return agenda;
        }
    }

    @Override
    public LinkedList<Agenda> buscarPeloDia(DiaDaSemana diaDaSemana) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE dia_semana "
                + "LIKE '%" + diaDaSemana + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int id = resultSet.getInt("id_agenda");
                String diaDaSemanaEncontrado = resultSet.getString("dia_semana");
                String horarioDoInicio = resultSet.getString("horario_inicio");
                String horarioDoFim = resultSet.getString("horario_fim");
                int crm = resultSet.getInt("fk_medico_crm");

                Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crm);
                
                Agenda agenda = new Agenda(id, DiaDaSemana.obterValor(diaDaSemanaEncontrado),
                        LocalTime.parse(horarioDoInicio), LocalTime.parse(horarioDoFim),
                        medico);
                
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar esta agenda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return agendasEncontradas;
        }
    }

    @Override
    public LinkedList<Agenda> buscarPeloCrm(int crm) throws Exception {
        
        //Connection conexao = Conector.obterConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + 
                " WHERE fk_medico_crm = " + crm ;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int id = resultSet.getInt("id_agenda");
                String diaDaSemana = resultSet.getString("dia_semana");
                String horarioDoInicio = resultSet.getString("horario_inicio");
                String horarioDoFim = resultSet.getString("horario_fim");
                int crmEncontrado = resultSet.getInt("fk_medico_crm");

                Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crmEncontrado);
                
                Agenda agenda = new Agenda(id, DiaDaSemana.obterValor(diaDaSemana),
                        LocalTime.parse(horarioDoInicio), LocalTime.parse(horarioDoFim),
                        medico);
                
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar esta agenda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return agendasEncontradas;
        }
    }
    
    @Override
    public LinkedList<Agenda> buscarPeloCrmEPeloDia(int crm, DiaDaSemana diaDaSemana) throws Exception {
    
        //Connection conexao = Conector.obterConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + 
                " WHERE fk_medico_crm = " + crm + 
                " AND dia_semana LIKE '%" + diaDaSemana + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                int id = resultSet.getInt("id_agenda");
                String diaDaSemanaEncontrado = resultSet.getString("dia_semana");
                String horarioDoInicio = resultSet.getString("horario_inicio");
                String horarioDoFim = resultSet.getString("horario_fim");
                int crmEncontrado = resultSet.getInt("fk_medico_crm");

                Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crmEncontrado);
                
                Agenda agenda = new Agenda(id, DiaDaSemana.obterValor(diaDaSemanaEncontrado),
                        LocalTime.parse(horarioDoInicio), LocalTime.parse(horarioDoFim),
                        medico);
                
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new Exception("Nao foi possivel encontrar esta agenda no banco de dados: " + sqle.getMessage());
        } finally {
            //conexao.close();
            return agendasEncontradas;
        }
    }
}
