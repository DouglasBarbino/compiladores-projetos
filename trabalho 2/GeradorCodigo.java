package trabalho3;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
/**
 * @program GeradorCodigo - Faz toda a geracao de codigo em Arduino de um dado codigo da Linguagem FAZEDORES
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
        int i=0;
        String setup = "";

        //Declaracao da funcao setup do Arduino
        codigo.println("void setup() {");
        
        /*Por nao saber quantos 'ativar' existem aqui dentro, entao deve ser 
        utilizado este loop com getChild, mas por cima eu indico qual eh a 
        operacao que estou fazendo*/
        while (ctx.getChild(i) != null){
            
            //ctx.dispositivo().getText().equals("led")
            if (ctx.getChild(i+2).getText().equals("led"))
                //ctx.PORTA().getText()
                setup = "pinmode(" + ctx.getChild(i+4).getText() + ", OUTPUT);";
            
            codigo.println(setup);
            
            //incrementa o i na quantidade de filhos que o ComandoSetup possui
            i = i+6;
        }
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
    
    @Override
    public void enterCmdLoop(FAZEDORESParser.CmdLoopContext ctx) {
        
        String regra = ctx.getStart().getText();
        String loop = "";
        
        if (regra.equals("ligar") || regra.equals("desligar")){
            //Verifica se existe o volt por meio do sexto filho, se eh ',' ou ')'
            if (ctx.getChild(5).getText().equals(",")){
                //Caso do analogWrite
            }
            else{
                //Verifica se o dispositivoSaida eh um led
                if (ctx.getChild(2).getText().equals("led")){
                    loop = "digitalWrite(" + ctx.PORTA().getText();
                    if (regra.equals("ligar"))
                        loop = loop + ", HIGH);";
                    else
                        loop = loop + ", LOW);";
                }
            }
        }
        else{
            if (regra.equals("esperar")){
                /*Por enquanto ainda nao estah sendo tratado a opcao de que o
                  tempo pode vir em uma constante*/
                loop = "delay(" + ctx.NUM_INT().getText() + ");";
            }
        }
        codigo.println(loop);
    }
}
