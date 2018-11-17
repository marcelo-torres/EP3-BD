package dados_da_clinica;

public enum FormaDePagamento {
    
    A_VISTA(1) {
        @Override
        public String toString() {
            return "à vista";
        }
    },
    
    CREDITO(2) {
        @Override
        public String toString() {
            return "crédito";
        }
    },
    
    DEBITO(3) {
        @Override
        public String toString() {
            return "débito";
        }
    },
    
    CHEQUE(4) {
        @Override
        public String toString() {
            return "cheque";
        }
    },
    
    FIADO(5){
        @Override
        public String toString() {
            return "fiado";
        }
    };
    
    
    public static FormaDePagamento obterValor(String formaDePagamento) {
    
        if(formaDePagamento.equals(A_VISTA.toString())) {
            return A_VISTA;
        } else if(formaDePagamento.equals(CREDITO.toString())) {
            return CREDITO;
        } else if(formaDePagamento.equals(DEBITO.toString())) {
            return DEBITO;
        } else if(formaDePagamento.equals(CHEQUE.toString())) {
            return CHEQUE;
        } else if(formaDePagamento.equals(FIADO.toString())) {
            return FIADO;
        } 

        return null;
    }
    
    
    public int codigoDaFormaDePagamento;
    
    private FormaDePagamento(int codigoDaFormaDePagamento) {
        this.codigoDaFormaDePagamento = codigoDaFormaDePagamento;
    }
    
}
