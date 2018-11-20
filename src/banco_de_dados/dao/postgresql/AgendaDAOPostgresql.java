package banco_de_dados.dao.postgresql;

import banco_de_dados.BancoDeDadosException;
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
import java.sql.Time;


public class AgendaDAOPostgresql extends ConectorDAOPostgresql implements AgendaDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "Agenda";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public boolean existeAgenda(int idAgenda) throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "SELECT COUNT(1) FROM " + NOME_COMPLETO + 
                " WHERE id_agenda =" + idAgenda;
        
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int quantidade = resultSet.getInt(1);
                
                return quantidade != 0;
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível verificar a exitência da agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return false;
    }
    
    @Override
    public Agenda criar(DiaDaSemana diaDaSemana, LocalTime horarioDeInicio, LocalTime horarioDoFim, Medico donoDaAgenda)throws BancoDeDadosException, SQLException {
        
        Agenda agenda = new Agenda(diaDaSemana, horarioDeInicio, horarioDoFim, donoDaAgenda);
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (DEFAULT, ?, ?, ?, ?) RETURNING id_agenda";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, diaDaSemana.toString());
            statement.setTime(2, Time.valueOf(horarioDeInicio));
            statement.setTime(3, Time.valueOf(horarioDoFim));
            statement.setInt(4, donoDaAgenda.getCRM());

            statement.execute();
            
            ResultSet resultSet = statement.getResultSet();
            
            if(resultSet.next()) {
                agenda.setId(resultSet.getInt(1));
            }
            
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível criar a agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return agenda;
    }

    @Override
    public void gravar(Agenda agenda) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET dia_semana = ?, "
                + "horario_inicio = ?, horario_fim = ?, fk_medico_crm = ? "
                + "WHERE id_agenda = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, agenda.getDiaDaSemana().toString());
            statement.setTime(2, Time.valueOf(agenda.getHorarioDoInicio()));
            statement.setTime(3, Time.valueOf(agenda.getHorarioDoFim()));
            statement.setInt(4, agenda.getDonoDaAgenda().getCRM());
            statement.setInt(5, agenda.getId());
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível gravar a agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int id) throws BancoDeDadosException, SQLException {
        
        if(!this.existeAgenda(id)) {
            throw new BancoDeDadosException("Não existe nenhuma agenda com este código");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_agenda = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível remover a agenda do banco de dados", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public Agenda buscar(int id) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Agenda agenda = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_agenda = " + id;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                agenda = this.criarAgendaAPartirDe(resultSet);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return agenda;
    }

    @Override
    public LinkedList<Agenda> buscarPeloDia(DiaDaSemana diaDaSemana) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE dia_semana "
                + "LIKE '%" + diaDaSemana + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Agenda agenda = this.criarAgendaAPartirDe(resultSet);
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return agendasEncontradas;
    }

    @Override
    public LinkedList<Agenda> buscarPeloCrm(int crm) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + 
                " WHERE fk_medico_crm = " + crm ;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Agenda agenda = this.criarAgendaAPartirDe(resultSet);
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return agendasEncontradas;
    }
    
    @Override
    public LinkedList<Agenda> buscarPeloCrmEPeloDia(int crm, DiaDaSemana diaDaSemana)
            throws BancoDeDadosException, SQLException {
    
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Agenda> agendasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + 
                " WHERE fk_medico_crm = " + crm + 
                " AND dia_semana LIKE '%" + diaDaSemana + "%'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Agenda agenda = this.criarAgendaAPartirDe(resultSet);
                agendasEncontradas.add(agenda);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta agenda no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return agendasEncontradas;
    }

    
    private Agenda criarAgendaAPartirDe(ResultSet resultSet) throws BancoDeDadosException, SQLException {
        int id = resultSet.getInt("id_agenda");
        String diaDaSemana = resultSet.getString("dia_semana");
        LocalTime horarioInicio = this.toLocalTime(resultSet.getTime("horario_inicio"));
        LocalTime horarioDoFim = this.toLocalTime(resultSet.getTime("horario_fim"));
        int crmEncontrado = resultSet.getInt("fk_medico_crm");
        
        Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crmEncontrado);

        Agenda agenda = new Agenda(id, DiaDaSemana.obterValor(diaDaSemana),
                horarioInicio, horarioDoFim,
                medico);
        
        return agenda;
    }
    
    private LocalTime toLocalTime(Time hora) {
        if(hora == null) {
            return null;
        } else {
            return hora.toLocalTime();
        }
    }
}
