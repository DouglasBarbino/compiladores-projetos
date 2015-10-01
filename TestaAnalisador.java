package trabalho1;

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
    // Descomente as linhas abaixo para testar o analisador gerado.
    // Obs: a linha abaixo está configurada para usar como entrada o arquivo lua1.txt
    // Modifique-a para testar os demais exemplos

        //para verificar se vai executar a geracao de codigo ou nao
        String saida = null;
      
        Saida out = new Saida();
        Saida codigo = new Saida();
        
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
        
        //ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("E:\\Facul\\6 sem\\CC2\\T1\\casosDeTesteT1\\2.arquivos_com_erros_semanticos\\entrada\\17.algoritmo_9-2_apostila_LA.txt"));
        //ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("E:\\Facul\\6 sem\\CC2\\T1\\casosDeTesteT1\\3.arquivos_sem_erros\\1.entrada\\10.caso_impressao.alg"));
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
            if(pce.getMessage() != null)
                out.println(pce.getMessage());
        }
        
        //caso nao teve erros o tamanho de saida serah 0
        if (saida.length() == 0){
            
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
            //PrintWriter pwGC = new PrintWriter(new File("saidaGC.txt"));
            pwGC.print(codigo.getTexto());
            pwGC.flush();
            pwGC.close();
        }
        //nao eh a geracao de codigos, vai imprimir os erros
        else{
            out.println("Fim da compilacao");
            
            PrintWriter pw = new PrintWriter(new File(args[1]));
            //PrintWriter pw = new PrintWriter(new File("saida.txt"));
            pw.print(out.getTexto());
            pw.flush();
            pw.close();
        }
//        if (!out.isModificado()) {
//            out.println("Fim da analise. Sem erros sintaticos.");
//            out.println("Tabela de simbolos:");
//
//            TabelaDeSimbolos.imprimirTabela(out);
//            System.out.print(out);
//        } else {
        
        
//        }
    }
}
