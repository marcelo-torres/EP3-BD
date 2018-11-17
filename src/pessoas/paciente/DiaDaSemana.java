package pessoas.paciente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
public enum DiaDaSemana {
    
    DOMINGO(1) {
        @Override
        public String toString() {
            return "domingo";
        }
    },
    
    SEGUNDA(2) {
        @Override
        public String toString() {
            return "segunda";
        }
    },
    
    TERCA(3) {
        @Override
        public String toString() {
            return "terça";
        }
    },
    
    QUARTA(4) {
        @Override
        public String toString() {
            return "quarta";
        }
    },
    
    QUINTA(5) {
        @Override
        public String toString() {
            return "quinta";
        }
    },
    
    SEXTA(6) {
        @Override
        public String toString() {
            return "sexta";
        }
    },
    
    SABADO(7) {
        @Override
        public String toString() {
            return "sábado";
        }
    };
    
    
    public static DiaDaSemana obterValor(String diaDaSemana) {
    
        if(diaDaSemana.equals(DOMINGO.toString())) {
            return DOMINGO;
        } else if(diaDaSemana.equals(SEGUNDA.toString())) {
            return SEGUNDA;
        } else if(diaDaSemana.equals(TERCA.toString())) {
            return TERCA;
        } else if(diaDaSemana.equals(QUARTA.toString())) {
            return QUARTA;
        } else if(diaDaSemana.equals(QUINTA.toString())) {
            return QUINTA;
        } else if(diaDaSemana.equals(SEXTA.toString())) {
            return SEXTA;
        } else if(diaDaSemana.equals(SABADO.toString())) {
            return SABADO;
        } 
        
        return null;
    }
    
    
    public int codigoDoDiaDaSemana;
    
    private DiaDaSemana(int codigoDoDiaDaSemana) {
        this.codigoDoDiaDaSemana = codigoDoDiaDaSemana;
    }
    
}
