package banco_de_dados.dao;

import dados_da_clinica.Consulta;
import dados_da_clinica.Doenca;
import dados_da_clinica.Especialidade;
import dados_da_clinica.FormaDePagamento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import pessoas.medico.Medico;
import pessoas.paciente.Paciente;


public interface ConsultaDiagnosticoDAO {
    
    public Consulta criar(int id, LocalDate data, Boolean pagou, Double valorPago,
            FormaDePagamento formaDePagamento, Especialidade especialidade,
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) throws Exception;
    
    public void gravar(Consulta consulta) throws Exception;
    
    public void remover(int id) throws Exception;
    
    public Consulta buscar(int id) throws Exception;
    
    public LinkedList<Consulta> buscarPelaData(LocalDate data) throws Exception;
    
    public LinkedList<Consulta> buscarPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) throws Exception;
    
    public LinkedList<Consulta> buscarPeloPagou(Boolean pagou) throws Exception;
    
    public LinkedList<Consulta> buscarPelaFormaDePagamento(FormaDePagamento formaDePagamento) throws Exception;
    
    public LinkedList<Consulta> buscarPelaEspecialidade(Integer codigoEspecialidade) throws Exception;
    
    public LinkedList<Consulta> buscarPeloPaciente(Integer codigoPaciente) throws Exception;
    
    public LinkedList<Consulta> buscarPeloMedico(Integer crmMedico) throws Exception;
    
    public LinkedList<Consulta> buscarPelaDoenca(Integer idDoenca) throws Exception;
}