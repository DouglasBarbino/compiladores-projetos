package trabalho1;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class TestaAnalisador {

    public static void main(String args[]) throws IOException, RecognitionException 
    {

        //para verificar se vai executar a geracao de codigo ou nao
        String saida = "";
        Boolean naoEhSintatico = true;
        
        Saida out = new Saida();
        Saida codigo = new Saida();
        
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
        
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        
        // artificio utilizado para que o parser só execute o primeiro erro e pare a execução
        try 
        {
            parser.addErrorListener(new T1ErrorListener(out));
            LAParser.ProgramaContext raiz = parser.programa();
            AnalisadorSemantico as = new AnalisadorSemantico(out);
         
            ParseTreeWalker ptw = new ParseTreeWalker();
            ptw.walk(as, raiz);
            
            //pega toda a saida que o out produziu
            saida = out.getTexto();
        } 
        catch(ParseCancellationException pce) 
        {
            //Entrou no catch, eh teste sintatico e por isso nao vai gerar codigo
            naoEhSintatico = false;
            
            if(pce.getMessage() != null)
                out.println(pce.getMessage());
        }
        
        //caso nao teve erros o tamanho de saida serah 0
        if (saida.length() == 0 && naoEhSintatico){
            
            //Eh necessario redeclarar tudo outra vez...
            ANTLRInputStream inputGC = new ANTLRInputStream(new FileInputStream(args[0]));
            //ANTLRInputStream inputGC = new ANTLRInputStream(new FileInputStream("E:\\Facul\\6 sem\\CC2\\T1\\casosDeTesteT1\\3.arquivos_sem_erros\\1.entrada\\10.caso_impressao.alg"));
            LALexer lexerGC = new LALexer(inputGC);
            CommonTokenStream tokensGC = new CommonTokenStream(lexerGC);
            parser.addErrorListener(new T1ErrorListener(codigo));
            LAParser parserGC = new LAParser(tokensGC);
            LAParser.ProgramaContext raizGC = parserGC.programa();
            GeradorCodigo gc = new GeradorCodigo(codigo);
            
            ParseTreeWalker ptwGC = new ParseTreeWalker();
            ptwGC.walk(gc, raizGC);
         
            PrintWriter pwGC = new PrintWriter(new File(args[1]));
            pwGC.print(codigo.getTexto());
            pwGC.flush();
            pwGC.close();
        }
        //nao eh a geracao de codigos, vai imprimir os erros
        else{
            out.println("Fim da compilacao");
            
            PrintWriter pw = new PrintWriter(new File(args[1]));
            pw.print(out.getTexto());
            pw.flush();
            pw.close();
        }
    }
}
