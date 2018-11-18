package banco_de_dados.postgresql;

import banco_de_dados.ConsultaDiagnosticoDAO;
import static banco_de_dados.postgresql.AgendaDAOPostgresql.NOME_COMPLETO;
import dados_da_clinica.Consulta;
import dados_da_clinica.Consulta.Diagnostico;
import dados_da_clinica.Doenca;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Agenda;
import pessoas.medico.Medico;
import pessoas.paciente.Paciente;


public class ConsultaDiagnosticoDAOPostgresql implements ConsultaDiagnosticoDAO {

    public static final String NOME_DA_TABELA = "ConsultaDiagnostico";
    public static final String NOME_COMPLETO = Conector.nomeCompleto(NOME_DA_TABELA);
    
    
    @Override
    public Consulta criar(int id, LocalDate data, Boolean pagou, Double valorPago, 
            FormaDePagamento formaDePagamento, Especialidade especialidade, 
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) throws Exception {
        
        Consulta consulta = new Consulta(id, data, pagou, valorPago, formaDePagamento, 
                especialidade, inicio, fim, paciente, medico);
        
        Connection conexao = Conector.obterConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Variaveis que exigem chamadas de metodos
        String formaDePagamentoInsercao = (formaDePagamento == null ? null : formaDePagamento.toString());
        String inicioInsercao = (inicio == null ? null : inicio.format(Consulta.PADRAO_DE_HORARIO));
        String fimInsercao = (fim == null ? null : fim.format(Consulta.PADRAO_DE_HORARIO));
        Integer codigoPacienteInsercao = (paciente == null ? null : paciente.getCodigo());
        Integer crmMedicoInsercao = (medico == null ? null : medico.getCRM());
        
        Diagnostico diagnostico = consulta.getDiagnostico();
        Integer idDiagnosticoInsercao = (diagnostico == null ? null : diagnostico.getID());
        String observacoesInsercao = (diagnostico == null ? null : diagnostico.getObservacoes());
        String remediosReceitadosInsercao = (diagnostico == null ? null : diagnostico.getRemediosReceitados());
        String tratamentoRecomendadoInsercao = (diagnostico == null ? null : diagnostico.getTratamentoRecomendado());
        
        Integer idDoencaInsercao = null;
        if(diagnostico != null) {
            Doenca doenca = diagnostico.getDoenca();
            if(doenca != null) idDoencaInsercao = doenca.getID();
        }
        
        Integer codigoEspecialidadeInsercao = (consulta.getEspecialidade() == null ? null : consulta.getEspecialidade().getCodigo());
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            // TODO: adicionar data
            if(true) throw new UnsupportedOperationException("Implementar a data bem aqui");
            statement.setBoolean(3, pagou);
            statement.setDouble(4, valorPago);
            statement.setString(5, formaDePagamentoInsercao);
            statement.setString(6, inicioInsercao);
            statement.setString(7, fimInsercao);
            statement.setInt(8, codigoPacienteInsercao);
            statement.setInt(9, crmMedicoInsercao);
            statement.setInt(10, idDiagnosticoInsercao);
            statement.setString(11, observacoesInsercao);
            statement.setString(12, remediosReceitadosInsercao);
            statement.setString(13, tratamentoRecomendadoInsercao);
            statement.setInt(14, idDoencaInsercao);
            statement.setInt(15, codigoEspecialidadeInsercao);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new Exception("Não foi possível criar a consulta: " + sqle.getMessage());
        } finally {
            conexao.close();
            return consulta;
        }
    }

    @Override
    public void gravar(Consulta consulta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consulta buscar(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPelaData(LocalDate data) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPeloPagou(Boolean pagou) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPelaFormaDePagamento(FormaDePagamento formaDePagamento) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPelaEspecialidade(Especialidade especialidade) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPeloPaciente(Paciente paciente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Consulta> buscarPeloMedico(Medico medico) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
