package pessoas.paciente;

import pessoas.Telefone;

public class TestePaciente {
    
    public static int testar() {
    
        int erros = 0;
        
    
        int codigo = 12;
        CPF cpf = new CPF("12312312312");
        String nome = "Mariavaldosf fdfd sf";
        Telefone telefone = new Telefone("4523-3454");
        String endereco = "Rua dos pombos";
        Integer idade = 53;
        Sexo sexo = Sexo.MASCULINO;
        
        Paciente paciente1 = new Paciente(codigo, cpf, nome, telefone, endereco, idade, sexo);
        
        
        codigo = 12;
        cpf = new CPF("12312312312");
        nome = "Mariavaldosf fdfd sf";
        telefone = new Telefone("4523-3454");
        endereco = "Rua dos pombos";
        idade = 53;
        sexo = Sexo.MASCULINO;
        
        Paciente paciente2 = new Paciente(codigo, cpf, nome, telefone, endereco, idade, sexo);
        
        
        codigo = 12;
        cpf = new CPF("12312312312");
        nome = "Mariavaldosf fdfd sf";
        telefone = new Telefone("4523-3454");
        endereco = "Rua dos pombos";
        idade = 53;
        sexo = Sexo.FEMININO;
        
        Paciente paciente3 = new Paciente(codigo, cpf, nome, telefone, endereco, idade, sexo);
        
        
        codigo = 13;
        cpf = new CPF("12312312312");
        nome = "Mariavaldosf fdfd sf";
        telefone = new Telefone("4523-3454");
        endereco = "Rua dos pombos";
        idade = 53;
        sexo = Sexo.MASCULINO;
        
        Paciente paciente4 = new Paciente(codigo, cpf, nome, telefone, endereco, idade, sexo);
        
        if(paciente1.equals(paciente2)) {
            System.out.println("[1] - OK, igualdade esperada");
        } else {
            System.out.println("[1] - ERRO, igualdade esperada nao encontrada");
            erros++;
        }
        
        if(!paciente1.equals(paciente3)) {
            System.out.println("[2] - OK, diferenca esperada");
        } else {
            System.out.println("[2] - ERRO, diferenca esperada nao encontrada");
            erros++;
        }
        
        if(!paciente1.equals(paciente4)) {
            System.out.println("[3] - OK, diferenca esperada");
        } else {
            System.out.println("[3] - ERRO, diferenca esperada nao encontrada");
            erros++;
        }
        
        return erros;
    }
    
}
