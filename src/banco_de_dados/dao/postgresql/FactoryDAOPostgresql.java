package banco_de_dados.dao.postgresql;

import banco_de_dados.dao.AbstractFactoryDAO;
import banco_de_dados.dao.AgendaDAO;
import banco_de_dados.dao.ConsultaDiagnosticoDAO;
import banco_de_dados.dao.DoencaDAO;
import banco_de_dados.dao.EspecialidadeDAO;
import banco_de_dados.dao.EspecialidadeMedicoDAO;
import banco_de_dados.dao.MedicoDAO;
import banco_de_dados.dao.PacienteDAO;
import banco_de_dados.dao.TaxaDAO;


public class FactoryDAOPostgresql implements AbstractFactoryDAO {

    @Override
    public AgendaDAO criarAgendaDAO() throws Exception {
        return new AgendaDAOPostgresql();
    }

    @Override
    public ConsultaDiagnosticoDAO criarConsultaDiagnosticoDAO() throws Exception {
        return new ConsultaDiagnosticoDAOPostgresql();
    }

    @Override
    public DoencaDAO criarDoencaDAO() throws Exception {
        return new DoencaDAOPostgresql();
    }

    @Override
    public EspecialidadeDAO criarEspecialidadeDAO() throws Exception {
        return new EspecialidadeDAOPostgresql();
    }

    @Override
    public EspecialidadeMedicoDAO criarEspecialidadeMedicoDAO() throws Exception {
        return new EspecialidadeMedicoDAOPostgresql();
    }

    @Override
    public MedicoDAO criarMedicoDAO() throws Exception {
        return new MedicoDAOPostgresql();
    }

    @Override
    public PacienteDAO criarPacienteDAO() throws Exception {
        return new PacienteDAOPostgresql();
    }

    @Override
    public TaxaDAO criarTaxaDAO() throws Exception {
        return new TaxaDAOPostgresql();
    }
}
