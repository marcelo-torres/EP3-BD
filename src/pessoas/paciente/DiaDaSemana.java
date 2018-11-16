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
    
    DOMINGO(1), SEGUNDA(2), TERCA(3), QUARTA(4), QUINTA(5), SEXTA(6), SABADO(7);
    
    public int codigoDoDiaDaSemana;
    
    private DiaDaSemana(int codigoDoDiaDaSemana) {
        this.codigoDoDiaDaSemana = codigoDoDiaDaSemana;
    }
    
}
