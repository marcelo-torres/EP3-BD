package banco_de_dados.dao.postgresql;

import banco_de_dados.BancoDeDadosException;
import banco_de_dados.dao.ConsultaDiagnosticoDAO;
import dados_da_clinica.Consulta;
import dados_da_clinica.Consulta.Diagnostico;
import dados_da_clinica.Doenca;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Medico;
import pessoas.paciente.Paciente;


public class ConsultaDiagnosticoDAOPostgresql extends ConectorDAOPostgresql implements ConsultaDiagnosticoDAO {

    public static final String SCHEMA = "clinica_medica";
    public static final String NOME_DA_TABELA = "ConsultaDiagnostico";
    public static final String NOME_COMPLETO = ConectorDAOPostgresql.nomeCompleto(SCHEMA, NOME_DA_TABELA);
    
    
    @Override
    public boolean existeConsulta(int idConsulta) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "SELECT COUNT(1) FROM " + NOME_COMPLETO + 
                " WHERE id_consulta =" + idConsulta;
        
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                int quantidade = resultSet.getInt(1);
                
                return quantidade != 0;
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível verificar a exitência da consulta no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return false;
    }
    
    @Override
    public Consulta criar(LocalDate data, Boolean pagou, Double valorPago, 
            FormaDePagamento formaDePagamento, Especialidade especialidade, 
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) throws BancoDeDadosException, SQLException {
        
        Consulta consulta = new Consulta(data, pagou, valorPago, formaDePagamento, 
                especialidade, inicio, fim, paciente, medico);
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "INSERT INTO " + NOME_COMPLETO + " VALUES (DEFAULT, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_consulta";
        
        // Variaveis que exigem chamadas de metodos
        Date dataInsercao = (data == null ? null : java.sql.Date.valueOf(data));
        String formaDePagamentoInsercao = (formaDePagamento == null ? null : formaDePagamento.toString());
        Time inicioInsercao = (inicio == null ? null : java.sql.Time.valueOf(inicio));
        Time fimInsercao = (fim == null ? null : java.sql.Time.valueOf(fim));
        Integer codigoPacienteInsercao = (paciente == null ? null : paciente.getCodigo());
        Integer crmMedicoInsercao = (medico == null ? null : medico.getCRM());
        Integer codigoEspecialidadeInsercao = (consulta.getEspecialidade() == null ? null : consulta.getEspecialidade().getCodigo());
        
        Diagnostico diagnostico = consulta.getDiagnostico();
        Integer idDiagnosticoInsercao = (diagnostico == null ? null : diagnostico.getId());
        String observacoesInsercao = (diagnostico == null ? null : diagnostico.getObservacoes());
        String remediosReceitadosInsercao = (diagnostico == null ? null : diagnostico.getRemediosReceitados());
        String tratamentoRecomendadoInsercao = (diagnostico == null ? null : diagnostico.getTratamentoRecomendado());
        
        Integer idDoencaInsercao = null;
        if(diagnostico != null) {
            Doenca doenca = diagnostico.getDoenca();
            if(doenca != null) idDoencaInsercao = doenca.getID();
        }

        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setObject(1, dataInsercao);
            statement.setBoolean(2, pagou);
            statement.setObject(3, valorPago, java.sql.Types.DOUBLE);
            statement.setString(4, formaDePagamentoInsercao);
            statement.setTime(5, inicioInsercao);
            statement.setTime(6, fimInsercao);  
            statement.setInt(7, codigoPacienteInsercao);
            statement.setInt(8, crmMedicoInsercao);
            statement.setInt(9, codigoEspecialidadeInsercao);
            statement.setInt(10, idDiagnosticoInsercao);
            statement.setString(11, observacoesInsercao);
            statement.setString(12, remediosReceitadosInsercao);
            statement.setString(13, tratamentoRecomendadoInsercao);
            statement.setObject(14, idDoencaInsercao);
            
            statement.execute();
            
            ResultSet resultSet = statement.getResultSet();
            
            if(resultSet.next()) {
                consulta.setId(resultSet.getInt(1));
            }
            
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível criar a consulta", sqle);
        } finally {
            conexao.close();
        }
        
        return consulta;
    }

    @Override
    public void gravar(Consulta consulta) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "UPDATE " + NOME_COMPLETO + " SET data = ?, pagou = ?, "
                + "valor_pago = ?, forma_pagamento = ?, horario_inicio = ?, "
                + "horario_fim = ?, fk_paciente_codigo = ?, fk_medico_crm = ?, "
                + "fk_especialidade_codigo = ?, observacoes = ?, "
                + "remedios_receitados = ?, tratamento_recomendado = ?, "
                + "fk_doenca_id_doenca = ? WHERE id_consulta = ?";
        
         // Variaveis que exigem chamadas de metodos
        Date dataInsercao = (consulta.getData() == null ? null : java.sql.Date.valueOf(consulta.getData()));
        String formaDePagamentoInsercao = (consulta.getFormaDePagamento() == null ? null : consulta.getFormaDePagamento().toString());
        Time inicioInsercao = (consulta.getInicio() == null ? null : java.sql.Time.valueOf(consulta.getInicio()));
        Time fimInsercao = (consulta.getFim() == null ? null : java.sql.Time.valueOf(consulta.getFim()));
        Integer codigoPacienteInsercao = (consulta.getPaciente() == null ? null : consulta.getPaciente().getCodigo());
        Integer crmMedicoInsercao = (consulta.getMedico() == null ? null : consulta.getMedico().getCRM());
        Integer codigoEspecialidadeInsercao = (consulta.getEspecialidade() == null ? null : consulta.getEspecialidade().getCodigo());
        
        Diagnostico diagnostico = consulta.getDiagnostico();
        String observacoesInsercao = (diagnostico == null ? null : diagnostico.getObservacoes());
        String remediosReceitadosInsercao = (diagnostico == null ? null : diagnostico.getRemediosReceitados());
        String tratamentoRecomendadoInsercao = (diagnostico == null ? null : diagnostico.getTratamentoRecomendado());
        
        Integer idDoencaInsercao = null;
        if(diagnostico != null) {
            Doenca doenca = diagnostico.getDoenca();
            if(doenca != null) idDoencaInsercao = doenca.getID();
        }
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setObject(1, dataInsercao);
            statement.setBoolean(2, consulta.getPagou());
            statement.setObject(3, consulta.getValorPago(), java.sql.Types.DOUBLE);
            statement.setString(4, formaDePagamentoInsercao);
            statement.setTime(5, inicioInsercao);
            statement.setTime(6, fimInsercao);  
            statement.setInt(7, codigoPacienteInsercao);
            statement.setInt(8, crmMedicoInsercao);
            statement.setInt(9, codigoEspecialidadeInsercao);
            statement.setString(10, observacoesInsercao);
            statement.setString(11, remediosReceitadosInsercao);
            statement.setString(12, tratamentoRecomendadoInsercao);
            statement.setObject(13, idDoencaInsercao);
            statement.setInt(14, consulta.getID());
            
            
            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível gravar a consulta no banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public void remover(int id) throws BancoDeDadosException, SQLException {
        
        if(!this.existeConsulta(id)) {
            throw new BancoDeDadosException("Não existe nenhuma consulta com este código");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        String sql = "DELETE FROM " + NOME_COMPLETO + " WHERE id_consulta = ?";
        
        try(PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
            statement.close();
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível remover a consulta do banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
    }

    @Override
    public Consulta buscar(int id) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        Consulta consulta = null;
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + "  WHERE id_consulta = " + id;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            if(resultSet.next()) {
                consulta = this.criarConsultaAPartirDe(resultSet);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar esta consulta no banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
        
        return consulta;
    }

    @Override
    public LinkedList<Consulta> buscarPelaData(LocalDate data) throws BancoDeDadosException, SQLException {
       
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE data = '" + data + "'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal)
            throws BancoDeDadosException, SQLException {
        
        if(dataInicial == null || dataFinal == null) {
            throw new BancoDeDadosException("Nenhum dos parâmetros pode ser nulo");
        }
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE data "
                + "BETWEEN '" + dataInicial + "' AND '" + dataFinal + "'";
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }
    
    @Override
    public LinkedList<Consulta> buscarPeloPagou(Boolean pagou) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE pagou = " + pagou;
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados: ", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPelaFormaDePagamento(FormaDePagamento formaDePagamento)
            throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql;
        if(formaDePagamento != null) {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "forma_pagamento = '" + formaDePagamento + "'";
        } else {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "forma_pagamento IS NULL";
        }
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados", sqle);
        } finally {
            conexao.close();   
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPelaEspecialidade(Integer codigoEspecialidade) 
            throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql;
        if(codigoEspecialidade != null) {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_especialidade_codigo = " + codigoEspecialidade;
        } else {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_especialidade_codigo IS NULL";
        }
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPeloPaciente(Integer codigoPaciente) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql;
        if(codigoPaciente != null) {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_paciente_codigo = " + codigoPaciente;
        } else {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_paciente_codigo IS NULL";
        }
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPeloMedico(Integer crmMedico) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql;
        if(crmMedico != null) {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_medico_crm = " + crmMedico;
        } else {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_medico_crm IS NULL";
        }
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;
    }

    @Override
    public LinkedList<Consulta> buscarPelaDoenca(Integer idDoenca) throws BancoDeDadosException, SQLException {
        
        Connection conexao = this.fabricaDeConexoes.getConexao();
        
        LinkedList<Consulta> consultasEncontradas = new LinkedList();
        
        String sql;
        if(idDoenca != null) {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_doenca_id_doenca = " + idDoenca;
        } else {
            sql = "SELECT * FROM " + NOME_COMPLETO + " WHERE "
                + "fk_doenca_id_doenca IS NULL";
        }
        try(ResultSet resultSet = conexao.createStatement().executeQuery(sql)) {
            while(resultSet.next()) {
                Consulta consulta = this.criarConsultaAPartirDe(resultSet);
                consultasEncontradas.add(consulta);
            }
        } catch(SQLException sqle) {
            throw new BancoDeDadosException("Não foi possível encontrar a(s) consulta(s) no banco de dados", sqle);
        } finally {
            conexao.close();
        }
        
        return consultasEncontradas;

    }
    
    
    private Consulta criarConsultaAPartirDe(ResultSet resultSet) throws BancoDeDadosException, SQLException {
        int id = resultSet.getInt("id_consulta");
        LocalDate data = this.toLocalDate(resultSet.getDate("data"));
        Boolean pagou = resultSet.getBoolean("pagou");
        Double valorPago = (Double)resultSet.getObject("valor_pago");
        String formaDePagamentoBancoDeDados = resultSet.getString("forma_pagamento");
        LocalTime horarioInicio = this.toLocalTime(resultSet.getTime("horario_inicio"));
        LocalTime horarioFim = this.toLocalTime(resultSet.getTime("horario_fim"));
        Integer codigoPaciente = (Integer)resultSet.getObject("fk_paciente_codigo");
        Integer crmMedico = (Integer)resultSet.getObject("fk_medico_crm");
        Integer codigoEspecialidade = (Integer)resultSet.getObject("fk_especialidade_codigo");
        String observacoes = resultSet.getString("observacoes");
        String remediosReceitados = resultSet.getString("remedios_receitados");
        String tratamentoRecomendado = resultSet.getString("tratamento_recomendado");
        Integer idDoenca = (Integer)resultSet.getObject("fk_doenca_id_doenca");

        FormaDePagamento formaDePagamento = (formaDePagamentoBancoDeDados == null ? null : FormaDePagamento.obterValor(formaDePagamentoBancoDeDados));

        Paciente paciente = new PacienteDAOPostgresql().buscarPeloCodigo(codigoPaciente);
        Medico medico = new MedicoDAOPostgresql().buscarPeloCrm(crmMedico);
        Especialidade especialidade = new EspecialidadeDAOPostgresql().buscarPeloCodigo(codigoEspecialidade);

        Consulta consulta = new Consulta(id, data, pagou, valorPago, 
                formaDePagamento, especialidade, horarioInicio, horarioFim,
                paciente, medico);

        Doenca doenca = null;
        if(idDoenca != null) {
            doenca = new DoencaDAOPostgresql().buscarPeloID(idDoenca);
        }

        Diagnostico diagnostico = consulta.getDiagnostico();
        diagnostico.setDoenca(doenca);
        diagnostico.setObservacoes(observacoes);
        diagnostico.setRemediosReceitados(remediosReceitados);
        diagnostico.setTratamentoRecomendado(tratamentoRecomendado);
        
        return consulta;
    }
    
    private LocalDate toLocalDate(Date data) {
        if(data == null) {
            return null;
        } else {
            return data.toLocalDate();
        }
    }
    
    private LocalTime toLocalTime(Time hora) {
        if(hora == null) {
            return null;
        } else {
            return hora.toLocalTime();
        }
    }
}