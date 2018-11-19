package banco_de_dados;


public class BancoDeDadosException extends Exception {
 
    private final Throwable CAUSA;
    private final String MENSAGEM;
    
    
    public BancoDeDadosException(String mensagem) {
        super(mensagem);
        this.MENSAGEM = mensagem;
        this.CAUSA = null;
    }
    
    public BancoDeDadosException(String mensagem, Throwable causa) {
        super(mensagem);
        this.MENSAGEM = mensagem;
        this.CAUSA = causa;
    }
    
    
    public String getMensagem() {
        return this.MENSAGEM;
    }
    
    public Throwable getCausa() {
        return this.CAUSA;
    }
}
