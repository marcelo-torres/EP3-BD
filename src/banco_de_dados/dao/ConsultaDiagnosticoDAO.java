package banco_de_dados.dao;

import banco_de_dados.BancoDeDadosException;
import dados_da_clinica.Consulta;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Medico;
import pessoas.paciente.Paciente;


public interface ConsultaDiagnosticoDAO {
    
    public Consulta criar(int id, LocalDate data, Boolean pagou, Double valorPago,
            FormaDePagamento formaDePagamento, Especialidade especialidade,
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) throws BancoDeDadosException, SQLException;
    
    public void gravar(Consulta consulta)
            throws BancoDeDadosException, SQLException;
    
    public void remover(int id)
            throws BancoDeDadosException, SQLException;
    
    public Consulta buscar(int id)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPelaData(LocalDate data)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPeloPagou(Boolean pagou)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPelaFormaDePagamento(FormaDePagamento formaDePagamento)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPelaEspecialidade(Integer codigoEspecialidade)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPeloPaciente(Integer codigoPaciente)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPeloMedico(Integer crmMedico)
            throws BancoDeDadosException, SQLException;
    
    public LinkedList<Consulta> buscarPelaDoenca(Integer idDoenca)
            throws BancoDeDadosException, SQLException;
}