package trabalho3;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
/**
 * @program GeradorCodigo - Faz toda a geracao de codigo em C de um dado codigo da Linguagem LA
 * @author Douglas
 */
public class GeradorCodigo extends FAZEDORESBaseListener {
    Saida codigo;
  
    //PARA AS TABELAS DE SIMBOLOS
    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    public GeradorCodigo(Saida codigo){
        this.codigo = codigo;
    }
    
    @Override
    public void enterComandoSetup(FAZEDORESParser.ComandoSetupContext ctx){
        //Declaracao da funcao setup do Arduino
        codigo.println("void setup() {");
    }
    
    @Override
    public void exitComandoSetup(FAZEDORESParser.ComandoSetupContext ctx){
        //Terminou todas as declaracoes da funcao setup, eh fechada a chave dela
        codigo.println("}");
    }
    
    @Override 
    public void enterComandoLoop(FAZEDORESParser.ComandoLoopContext ctx){ 
        //Declaracao da funcao loop do Arduino
        codigo.println("void loop() {");
    }
	
    @Override 
    public void exitComandoLoop(FAZEDORESParser.ComandoLoopContext ctx) { 
        //Terminou todas as declaracoes da funcao loop, eh fechada a chave dela
        codigo.println("}");
    }
}
