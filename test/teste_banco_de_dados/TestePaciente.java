package teste_banco_de_dados;

import banco_de_dados.postgresql.PacienteDAOPortgresql;
import java.util.LinkedList;
import pessoas.Telefone;
import pessoas.paciente.CPF;
import pessoas.paciente.Paciente;
import pessoas.paciente.Sexo;

public class TestePaciente {
    
    public static int testar() throws Exception {
    
        int erros = 0;
        
        PacienteDAOPortgresql pacienteDao = new PacienteDAOPortgresql();
        
        pacienteDao.remover(12);
        pacienteDao.remover(13);

        
        int codigo = 12;
        CPF cpf = new CPF("12312312312");
        String nome = "Mariavaldosf fdfd sf";
        Telefone telefone = new Telefone("4523-3454");
        String endereco = "Rua dos pombos";
        Integer idade = 53;
        Sexo sexo = Sexo.MASCULINO;
        
        Paciente paciente1 = pacienteDao.criar(codigo, cpf, nome, telefone, endereco, idade, sexo);
        
        codigo = 13;
        cpf = new CPF("11122233344");
        nome = "Helenilson Costa Pai";
        telefone = new Telefone("9090-0192");
        endereco = "Rua dos Pombos Comunistas Bolivarianos";
        idade = 24;
        sexo = Sexo.MASCULINO;
        
        Paciente paciente2 = pacienteDao.criar(codigo, cpf, nome, telefone, endereco, idade, sexo);
       
        Paciente pacienteEncontrado = pacienteDao.buscarPeloCodigo(12);     
        if(pacienteEncontrado.equals(paciente1)) {
            System.out.println("[1] - OK - Busca funcionando");
        } else {
            System.out.println("[1] - ERRO - Busca com problema");
            erros++;
        }
        
        paciente1.setNome("Handerson Silvia");
        paciente1.setSexo(Sexo.FEMININO);
        pacienteDao.gravar(paciente1);
        
        pacienteEncontrado = pacienteDao.buscarPeloCodigo(12);
        if(pacienteEncontrado.equals(paciente1)) {
            System.out.println("[2] - OK - Alteracao realizada com sucesso");
        } else {
            System.out.println("[2] - ERRO - Atualizacao sem sucesso");
            erros++;
        }
        
        pacienteEncontrado = pacienteDao.buscarPeloCpf(new CPF("11122233344"));
        if(pacienteEncontrado.equals(paciente2)) {
            System.out.println("[3] - OK - Busca por CPF realizada com sucesso");
        } else {
            System.out.println("[3] - ERRO - Busca por CPF sem sucesso");
            erros++;
        }
        
        LinkedList<Paciente> pacientesParaSeremEncontrados = new LinkedList();
        pacientesParaSeremEncontrados.add(paciente1);
        pacientesParaSeremEncontrados.add(paciente2);
        
        LinkedList<Paciente> pacientesEncontrados = pacienteDao.buscarPeloNome("H");
        if(pacientesParaSeremEncontrados.containsAll(pacientesEncontrados)
                && pacientesEncontrados.containsAll(pacientesParaSeremEncontrados)) {
            System.out.println("[4] - Busca por nomes COM sucesso");
        } else {
            System.out.println("[4] - Busca por nomes SEM sucesso");
        }

        
        pacienteDao.remover(13);
        pacienteDao.remover(12);
        
        return erros;
        
    }
}
