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
        Saida out = new Saida();
    // Descomente as linhas abaixo para testar o analisador gerado.
    // Obs: a linha abaixo está configurada para usar como entrada o arquivo lua1.txt
    // Modifique-a para testar os demais exemplos

        
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
        
       // ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("casosDeTesteT1/1.arquivos_com_erros_sintaticos/entrada/2-algoritmo_2-2_apostila_LA_1_erro_linha_15.txt"));
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        // artificio utilizado para que o parser só execute o primeiro erro e pare a execução
        try 
        {

            parser.addErrorListener(new T1ErrorListener(out));
            LAParser.ProgramaContext raiz = parser.programa();
            AnalisadorSemantico as = new AnalisadorSemantico(out);
          //  as.programa();
            ParseTreeWalker ptw = new ParseTreeWalker();
            ptw.walk(as, raiz);
            
        } 
        catch(ParseCancellationException pce) 
        {
        
            if(pce.getMessage() != null)
                out.println(pce.getMessage());
        }
//        if (!out.isModificado()) {
//            out.println("Fim da analise. Sem erros sintaticos.");
//            out.println("Tabela de simbolos:");
//
//            TabelaDeSimbolos.imprimirTabela(out);
//            System.out.print(out);
//        } else {
        
        out.println("Fim da compilacao");
//        }
        
        PrintWriter pw = new PrintWriter(new File(args[1]));
       // PrintWriter pw = new PrintWriter(new File("saida.txt"));
        pw.print(out.getTexto());
        pw.flush();
        pw.close();
    }
}
