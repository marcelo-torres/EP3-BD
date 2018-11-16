package dados_da_clinica;


import dados_da_clinica.Doenca;
import dados_da_clinica.FormaDePagamento;
import pessoas.paciente.Paciente;
import pessoas.medico.Medico;
import java.sql.Date;
import java.sql.Time;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public class Consulta {
    
    public class Diagnostico {
    
        private int id;
        private String observacoes;
        private String remediosReceitados;
        private String tratamentoRecomendado;
        private Doenca doenca;
        
    }
    
    private int id;
    private Date data;
    private boolean pagou;
    private double valorPago;
    private FormaDePagamento formaDePagamento;
    private Especialidade especialidade;
    private Time inicio;
    private Time fim;
    private Paciente paciente;
    private Medico medico;
    
}
