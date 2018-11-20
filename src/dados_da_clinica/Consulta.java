package dados_da_clinica;

import pessoas.paciente.Paciente;
import pessoas.medico.Medico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Consulta {
    
    public static final DateTimeFormatter PADRAO_DE_HORARIO = 
                                           DateTimeFormatter.ofPattern("HH:mm");
    
    
    public static class Diagnostico {
    
        public static void verificarId(int id) {
            if(id < 0) {
                throw new IllegalArgumentException("O ID da doença não pode ser negativo");
            }
        }
        
        public static void verificarObservacoes(String observacoes) {
            if(observacoes != null && observacoes.length() > 45) {
                throw new IllegalArgumentException("O texto de observações não pode ultrapassar 45 caracteres");
            }
        }
        
        public static void verificarRemediosReceitados(String remediosReceitados) {
            if(remediosReceitados != null && remediosReceitados.length() > 100) {
                throw new IllegalArgumentException("O texto dos remédios receitados não pode ultrapassar 100 caracteres");
            }
        }
        
        public static void verificarTratamentoRecomendado(String tratamentoRecomendado) {
            if(tratamentoRecomendado != null && tratamentoRecomendado.length() > 100) {
                throw new IllegalArgumentException("O texto do tratamento recomendado não pode ultrapassar 100 caracteres");
            }
        }
        
        public static void verificarDoenca(Doenca doenca) {
            // pode ser null
        }
        
        
        private int id;
        private String observacoes;
        private String remediosReceitados;
        private String tratamentoRecomendado;
        private Doenca doenca;
        
        
        public Diagnostico(String observacoes, String remediosReceitados,
                String tratamentoRecomendado, Doenca doenca) {
            this.setObservacoes(observacoes);
            this.setRemediosReceitados(remediosReceitados);
            this.setTratamentoRecomendado(tratamentoRecomendado);
            this.setDoenca(doenca);
        }
        
        public Diagnostico(int id, String observacoes, String remediosReceitados,
                String tratamentoRecomendado, Doenca doenca) {
            this.setId(id);
            this.setObservacoes(observacoes);
            this.setRemediosReceitados(remediosReceitados);
            this.setTratamentoRecomendado(tratamentoRecomendado);
            this.setDoenca(doenca);
        }
        
        
        @Override
        public boolean equals(Object outro) {
            
            if(!(outro instanceof Diagnostico)) {
                return false;
            }
            
            Diagnostico outroDiagnostico = (Diagnostico)outro;
            
            boolean saoIguais = (this.id == outroDiagnostico.getId()
                    && Objects.equals(this.observacoes, outroDiagnostico.getObservacoes())
                    && Objects.equals(this.remediosReceitados, outroDiagnostico.getRemediosReceitados())
                    && Objects.equals(this.tratamentoRecomendado, outroDiagnostico.getTratamentoRecomendado())
                    && Objects.equals(this.doenca, outroDiagnostico.getDoenca()));
            
            return saoIguais;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + this.id;
            hash = 79 * hash + Objects.hashCode(this.observacoes);
            hash = 79 * hash + Objects.hashCode(this.remediosReceitados);
            hash = 79 * hash + Objects.hashCode(this.tratamentoRecomendado);
            hash = 79 * hash + Objects.hashCode(this.doenca);
            return hash;
        }
        
        
        public void setId(int id) {
            verificarId(id);
            this.id = id;
        }
        
        public void setObservacoes(String observacoes) {
            verificarObservacoes(observacoes);
            this.observacoes = observacoes;
        }
        
        public void setRemediosReceitados(String remediosReceitados) {
            verificarRemediosReceitados(remediosReceitados);
            this.remediosReceitados = remediosReceitados;
        }
        
        public void setTratamentoRecomendado(String tratamentoRecomendado) {
            verificarTratamentoRecomendado(tratamentoRecomendado);
            this.tratamentoRecomendado = tratamentoRecomendado;
        }
        
        public void setDoenca(Doenca doenca) {
            verificarDoenca(doenca);
            this.doenca = doenca;
        }
        
        
        public int getId() {
            return this.id;
        }
        
        public String getObservacoes() {
            return this.observacoes;
        }
        
        public String getRemediosReceitados() {
            return this.remediosReceitados;
        }
        
        public String getTratamentoRecomendado() {
            return this.tratamentoRecomendado;
        }
        
        public Doenca getDoenca() {
            return this.doenca;
        }
    }
    
    
    public static void verificarId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("O ID da consulta não pode ser negativo");
        }
    }
    
    public static void verificarData(LocalDate data) {
        if(data == null) {
            throw new IllegalArgumentException("Deve ser atribuída uma data à consulta");
        }
    }
    
    public static void verificarPagou(Boolean pagou) {
        if(pagou == null) {
            throw new IllegalArgumentException("Deve ser informado se o paciente pagou ou não a consulta");
        }
    }
    
    public static void verificarValorPago(Double valorPago) {
        // pode ser nulo
    }
    
    public static void verificarFormaDePagamento(FormaDePagamento formaDePagamento) {
        // pode ser nulo
    }
    
    public static void verificarEspecialidade(Especialidade especialidade) {
        // pode ser nulo
    }
    
    public static void verificarInicio(LocalTime inicio) {
        // pode ser nulo
    }
    
    public static void verificarFim(LocalTime fim) {
       // pode ser nulo
    }
    
    public static void verificarPaciente(Paciente paciente) {
        if(paciente == null) {
            throw new IllegalArgumentException("Uma consulta deve possuir um paciente");
        }
    }
    
    public static void verificarMedico(Medico medico) {
        if(medico == null) {
            throw new IllegalArgumentException("Um médico deve ser atribuído à consulta");
        }
    }
    
    
    private int id;
    private LocalDate data;
    private Boolean pagou;
    private Double valorPago;
    private FormaDePagamento formaDePagamento;
    private Especialidade especialidade;
    private LocalTime inicio;
    private LocalTime fim;
    private Paciente paciente;
    private Medico medico;
    
    private final Diagnostico DIAGNOSTICO;
    
    public Consulta(LocalDate data, Boolean pagou, Double valorPago,
            FormaDePagamento formaDePagamento, Especialidade especialidade,
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) {
    
        this.DIAGNOSTICO = new Diagnostico(id, null, null, null, null);
        
        this.setData(data);
        this.setPagou(pagou);
        this.setValorPago(valorPago);
        this.setFormaDePagamaento(formaDePagamento);
        this.setEspecialidade(especialidade);
        this.setInicio(inicio);
        this.setFim(fim);
        this.setPaciente(paciente);
        this.setMedico(medico);
    }
    
    public Consulta(int id, LocalDate data, Boolean pagou, Double valorPago,
            FormaDePagamento formaDePagamento, Especialidade especialidade,
            LocalTime inicio, LocalTime fim, Paciente paciente, Medico medico) {
    
        this.DIAGNOSTICO = new Diagnostico(id, null, null, null, null);
        
        this.setId(id);
        this.setData(data);
        this.setPagou(pagou);
        this.setValorPago(valorPago);
        this.setFormaDePagamaento(formaDePagamento);
        this.setEspecialidade(especialidade);
        this.setInicio(inicio);
        this.setFim(fim);
        this.setPaciente(paciente);
        this.setMedico(medico);
    }
    
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Consulta)) {
            return false;
        }
        
        Consulta outraConsulta = (Consulta)outro;
        
        boolean saoIguais = (this.id == outraConsulta.getID()
                && this.data.equals(outraConsulta.getData())
                && Objects.equals(this.valorPago, outraConsulta.getValorPago())
                && Objects.equals(this.formaDePagamento, outraConsulta.getFormaDePagamento())
                && this.especialidade.equals(outraConsulta.getEspecialidade())
                && Objects.equals(this.inicio, outraConsulta.getInicio())
                && Objects.equals(this.fim, outraConsulta.getFim())
                && this.paciente.equals(outraConsulta.getPaciente())
                && this.medico.equals(outraConsulta.getMedico())
                && this.DIAGNOSTICO.equals(outraConsulta.getDiagnostico()));
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.data);
        hash = 29 * hash + Objects.hashCode(this.valorPago);
        hash = 29 * hash + Objects.hashCode(this.formaDePagamento);
        hash = 29 * hash + Objects.hashCode(this.especialidade);
        hash = 29 * hash + Objects.hashCode(this.inicio);
        hash = 29 * hash + Objects.hashCode(this.fim);
        hash = 29 * hash + Objects.hashCode(this.paciente);
        hash = 29 * hash + Objects.hashCode(this.medico);
        return hash;
    }
    
    
    public void setId(int id) {
        verificarId(id);
        this.DIAGNOSTICO.setId(id);
        this.id = id;
    }
    
    public void setData(LocalDate data) {
        verificarData(data);
        this.data = data;
    }
    
    public void setPagou(Boolean pagou) {
        verificarPagou(pagou);
        this.pagou = pagou;
    }
    
    public void setValorPago(Double valorPago) {
        verificarValorPago(valorPago);
        this.valorPago = valorPago;
    }
    
    public void setFormaDePagamaento(FormaDePagamento formaDePagamento) {
        verificarFormaDePagamento(formaDePagamento);
        this.formaDePagamento = formaDePagamento;
    }
    
    public void setEspecialidade(Especialidade especialidade) {
        verificarEspecialidade(especialidade);
        this.especialidade = especialidade;
    }
    
    public void setInicio(LocalTime inicio) {
        verificarInicio(inicio);
        if(inicio != null) {
            String horaAux = inicio.format(PADRAO_DE_HORARIO);
            inicio = LocalTime.parse(horaAux);
        }
        this.inicio = inicio;
    }
    
    public void setFim(LocalTime fim) {
        verificarFim(fim);
        if(fim != null) {
            String horaAux = fim.format(PADRAO_DE_HORARIO);
            fim = LocalTime.parse(horaAux);
        }
        this.fim = fim;
    }
    
    public void setPaciente(Paciente paciente) {
        verificarPaciente(paciente);
        this.paciente = paciente;
    }
    
    public void setMedico(Medico medico) {
        verificarMedico(medico);
        this.medico = medico;
    }
    
    
    public int getID() {
        return this.id;
    }
    
    public LocalDate getData() {
        return this.data;
    }
    public Boolean getPagou() {
        return this.pagou;
    }
    public Double getValorPago() {
        return this.valorPago;
    }
    public FormaDePagamento getFormaDePagamento() {
        return this.formaDePagamento;
    }
    public Especialidade getEspecialidade() {
        return this.especialidade;
    }
    public LocalTime getInicio() {
        return this.inicio;
    }
    public LocalTime getFim() {
        return this.fim;
    }
    public Paciente getPaciente() {
        return this.paciente;
    }
    public Medico getMedico() {
        return this.medico;
    }
    
    public Diagnostico getDiagnostico() {
        return this.DIAGNOSTICO;
    }
}