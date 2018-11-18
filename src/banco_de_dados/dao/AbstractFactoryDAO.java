package banco_de_dados.dao;


public interface AbstractFactoryDAO {
 
    public AgendaDAO criarAgendaDAO() throws Exception;
    
    public ConsultaDiagnosticoDAO criarConsultaDiagnosticoDAO() throws Exception;
    
    public DoencaDAO criarDoencaDAO() throws Exception;
    
    public EspecialidadeDAO criarEspecialidadeDAO() throws Exception;
    
    public EspecialidadeMedicoDAO criarEspecialidadeMedicoDAO() throws Exception;
    
    public MedicoDAO criarMedicoDAO() throws Exception;
    
    public PacienteDAO criarPacienteDAO() throws Exception;
    
    public TaxaDAO criarTaxaDAO() throws Exception;
}
