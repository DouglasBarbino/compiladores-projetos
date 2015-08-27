/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1comp2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class TestaAnalisadorSemantico {
    

    public static void main(String[] args) throws Exception {
        Saida out = new Saida();
        Saida.clear();
        String arquivo = String.format("casosDeTesteT1/1.arquivos_com_erros_sintaticos/entrada/2-algoritmo_2-2_apostila_LA_1_erro_linha_15.txt");
    //      String arquivo = String.format("casosDeTesteT1/3.arquivos_sem_erros/1.entrada/1.declaracao_leitura_impressao_inteiro.alg");
      
        InputStream casoDeTesteEntrada = TestaAnalisadorSemantico.class.getResourceAsStream(arquivo);
        ANTLRInputStream input = new ANTLRInputStream(casoDeTesteEntrada);
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        parser.addErrorListener(new T1ErrorListener(out));
        parser.programa();
        
        String arquivoSaida = String.format("casosDeTesteT1/1.arquivos_com_erros_sintaticos/saida/2-algoritmo_2-2_apostila_LA_1_erro_linha_15.txt");
    //    String arquivoSaida = String.format("casosDeTesteT1/3.arquivos_sem_erros/4.saida/1.declaracao_leitura_impressao_inteiro.alg");
  
        InputStream casoDeTesteSaida = TestaAnalisadorSemantico.class.getResourceAsStream(arquivoSaida);
        comparar(arquivo, casoDeTesteSaida, Saida.getTexto());
    
    }

    private static void comparar(String nomeArquivo, InputStream saidaCorreta, String saidaObtida) throws Exception {
        InputStreamReader isr = new InputStreamReader(saidaCorreta);
        StringReader sr = new StringReader(saidaObtida);
        boolean diferente = false;
        int charFr = -1;
        int charSr = -1;
        while ((charFr = isr.read()) != -1 & (charSr = sr.read()) != -1) {
            if (charFr != charSr) {
                diferente = true;
                System.out.println("ERRO:" + nomeArquivo);
                System.out.println("=================== Saída obtida ==================");
                System.out.println(Saida.getTexto());
                System.out.println("===================================================");
                break;
            }
        }
        if (!diferente && (charFr != -1 || charSr != -1)) {
            diferente = true;
            System.out.println("ERRO:" + nomeArquivo);
            System.out.println("=================== Saída obtida ==================");
            System.out.println(Saida.getTexto());
            System.out.println("===================================================");
        }

        if (!diferente) {
            System.out.println("OK:" + nomeArquivo);
        }

    }
}
