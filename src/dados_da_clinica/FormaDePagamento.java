package dados_da_clinica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcelo
 */
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
    
    public int codigoDaFormaDePagamento;
    
    private FormaDePagamento(int codigoDaFormaDePagamento) {
        this.codigoDaFormaDePagamento = codigoDaFormaDePagamento;
    }
    
}
