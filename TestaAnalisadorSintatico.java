package trabalho1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class TestaAnalisadorSintatico {

    public static void main(String args[]) throws IOException, RecognitionException 
  {
        Saida out = new Saida();
    // Descomente as linhas abaixo para testar o analisador gerado.
    // Obs: a linha abaixo está configurada para usar como entrada o arquivo lua1.txt
    // Modifique-a para testar os demais exemplos
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        parser.addErrorListener(new T1ErrorListener(out));
        // artificio utilizado para que o parser só execute o primeiro erro e pare a execução
        try {
        parser.programa();
        } catch(ParseCancellationException pce) {
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
        pw.print(out.getTexto());
        pw.flush();
        pw.close();
    }
}
