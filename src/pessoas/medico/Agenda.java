package pessoas.medico;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import pessoas.paciente.DiaDaSemana;


public class Agenda {

    public static final DateTimeFormatter PADRAO_DE_HORARIO = 
                                           DateTimeFormatter.ofPattern("HH:mm");
    
    
    public static void verificarID(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("O ID da agenda não deve ser negativo");
        }
    }
    
    public static void verificarDiaDaSemana(DiaDaSemana diaDaSemana) {
        if(diaDaSemana == null) {
            throw new IllegalArgumentException("Um dia da semana deve ser atribuído aà esta agenda");
        }
    }
    
    public static void verificarHoraDoInicio(LocalTime horaDoInicio) {
        if(horaDoInicio == null) {
            throw new IllegalArgumentException("Um horário de início deve ser atribuído a esta agenda");
        }
    }
    
    public static void verificarHorarioDoFim(LocalTime horarioDoFim) {
        if(horarioDoFim == null) {
            throw new IllegalArgumentException("Um horário de fim deve ser atribuído a esta agenda");
        }
    }
    
    public static void verificarDonoDaAgenda(Medico donoDaAgenda) {
        if(donoDaAgenda == null) {
            throw new IllegalArgumentException("Um medico deve ser atribuído a esta agenda");
        }
    }
    
    
    private final int ID;
    private DiaDaSemana diaDaSemana;
    private LocalTime horarioDoInicio;
    private LocalTime horarioDoFim;
    private Medico donoDaAgenda;
    
    
    public Agenda(int id, DiaDaSemana diaDaSemana, LocalTime horarioDoInicio, 
            LocalTime horarioDoFim, Medico donoDaAgenda) {
    
        verificarID(id);
        this.ID = id;
        
        this.setDiaDaSemana(diaDaSemana);
        this.setHorarioDoInicio(horarioDoInicio);
        this.setHorarioDoFim(horarioDoFim);
        this.setDonoDaAgenda(donoDaAgenda);
    }
    
    
    @Override
    public boolean equals(Object outro) {
    
        if(!(outro instanceof Agenda)) {
            return false;
        }
        
        Agenda outraAgenda = (Agenda)outro;
        
        boolean saoIguais = (this.ID == outraAgenda.getID()
                && this.diaDaSemana == outraAgenda.getDiaDaSemana()
                && this.horarioDoInicio.equals(outraAgenda.getHorarioDoInicio())
                && this.horarioDoFim.equals(outraAgenda.getHorarioDoFim())
                && this.donoDaAgenda.equals(outraAgenda.getDonoDaAgenda()));
        
        return saoIguais;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.ID;
        hash = 43 * hash + Objects.hashCode(this.diaDaSemana);
        hash = 43 * hash + Objects.hashCode(this.horarioDoInicio);
        hash = 43 * hash + Objects.hashCode(this.horarioDoFim);
        hash = 43 * hash + Objects.hashCode(this.donoDaAgenda);
        return hash;
    }
    
    
    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        verificarDiaDaSemana(diaDaSemana);
        this.diaDaSemana = diaDaSemana;
    }
    
    public void setHorarioDoInicio(LocalTime horarioDoInicio) {
        verificarHoraDoInicio(horarioDoInicio);
        this.horarioDoInicio = horarioDoInicio;
    }
    
    public void setHorarioDoFim(LocalTime horarioDoFim) {
        verificarHorarioDoFim(horarioDoFim);
        this.horarioDoFim = horarioDoFim;
    }
    
    public void setDonoDaAgenda(Medico donoDaAgenda) {
        verificarDonoDaAgenda(donoDaAgenda);
        this.donoDaAgenda = donoDaAgenda;
    }
    
    
    public int getID() {
        return this.ID;
    }
    
    public DiaDaSemana getDiaDaSemana() {
        return this.diaDaSemana;
    }
    
    public LocalTime getHorarioDoInicio() {
        return this.horarioDoInicio;
    }
    
    public LocalTime getHorarioDoFim() {
        return this.horarioDoFim;
    }
    
    public Medico getDonoDaAgenda() {
        return this.donoDaAgenda;
    }
}
