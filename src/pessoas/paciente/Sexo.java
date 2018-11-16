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
public enum Sexo {
    FEMININO(1), MASCULINO(2);
    
    public int codigoDoSexo;
    
    private Sexo(int codigoDoSexo) {
        this.codigoDoSexo = codigoDoSexo;
    }
    
}
