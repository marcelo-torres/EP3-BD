package dados_da_clinica;

public enum Mes {
    
    JANEIRO(1) {
        @Override
        public String toString() {
            return "janeiro";
        }
    },
    
    FEVEREIRO(2) {
        @Override
        public String toString() {
            return "fevereiro";
        }
    },
    
    MARCO(3) {
        @Override
        public String toString() {
            return "março";
        }
    },
    
    ABRIL(4) {
        @Override
        public String toString() {
            return "abril";
        }
    },
    
    MAIO(5) {
        @Override
        public String toString() {
            return "maio";
        }
    },
    
    JUNHO(6) {
        @Override
        public String toString() {
            return "junho";
        }
    },
    
    JULHO(7) {
        @Override
        public String toString() {
            return "julho";
        }
    },
    
    AGOSTO(8) {
        @Override
        public String toString() {
            return "agosto";
        }
    },
    
    SETEMBRO(9) {
        @Override
        public String toString() {
            return "setembro";
        }
    },
    
    OUTUBRO(10) {
        @Override
        public String toString() {
            return "outubro";
        }
    },
    
    NOVEMBRO(11) {
        @Override
        public String toString() {
            return "novembro";
        }
    },
    
    DEZEMBRO(12) {
        @Override
        public String toString() {
            return "dezembro";
        }
    };
    
    
    public static Mes obterValor(int numeroDoMes) {
        
        if(numeroDoMes == JANEIRO.numeroDoMes) {
            return JANEIRO;
        } else if(numeroDoMes == FEVEREIRO.numeroDoMes) {
            return FEVEREIRO;
        } else if(numeroDoMes == MARCO.numeroDoMes) {
            return MARCO;
        } else if(numeroDoMes == ABRIL.numeroDoMes) {
            return ABRIL;
        }else if(numeroDoMes == MAIO.numeroDoMes) {
            return MAIO;
        } else if(numeroDoMes == JUNHO.numeroDoMes) {
            return JUNHO;
        } else if(numeroDoMes == JULHO.numeroDoMes) {
            return JULHO;
        } else if(numeroDoMes == AGOSTO.numeroDoMes) {
            return AGOSTO;
        } else if(numeroDoMes == SETEMBRO.numeroDoMes) {
            return SETEMBRO;
        } else if(numeroDoMes == OUTUBRO.numeroDoMes) {
            return OUTUBRO;
        } else if(numeroDoMes == NOVEMBRO.numeroDoMes) {
            return NOVEMBRO;
        } else if(numeroDoMes == DEZEMBRO.numeroDoMes) {
            return DEZEMBRO;
        }
        
        return null;
    }
    
    public int numeroDoMes;

    
    Mes(int numeroDoMes) {
        this.numeroDoMes = numeroDoMes;
    }
}
