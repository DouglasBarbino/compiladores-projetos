/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

 * Versão 1: Prof. Daniel Lucrédio.
 * Versão 2: Adaptação para uso em CC2 [Grupo 3]
 */

package trabalho1;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Corrigir 
{

//	public static void main(String[] args) throws Exception 
//	{
//	        System.out.println("---------------- Grupo -----------------");
//	        System.out.println(LuazinhaParser.grupo);
//	        System.out.println("----------------------------------------");
//	
//        	Saida.clear();
//		
//		//Define o nome do arquivo e a pasta que ele está (o tipo)
//		String nomeArquivo = String.format("casoDeTeste%02d.txt", i);
//		String tipoCorretor = String.format("1.arquivos_com_erros_sintaticos);
//
//		InputStream casoDeTesteEntrada = TestaAnalisadorSintatico.class.getResourceAsStream("casosDeTesteT1/"+tipoCorretor+"entrada/" + nomeArquivo);
//		ANTLRInputStream input = new ANTLRInputStream(casoDeTesteEntrada);
//		LuazinhaLexer lexer = new LuazinhaLexer(input);
//		CommonTokenStream tokens = new CommonTokenStream(lexer);
//		LuazinhaParser parser = new LuazinhaParser(tokens);
//		parser.programa();
//		InputStream casoDeTesteSaida = TestaAnalisadorSintatico.class.getResourceAsStream("casosDeTeste/"+tipoCorretor+"saida/" + nomeArquivo);
//		comparar(nomeArquivo, casoDeTesteSaida, Saida.getTexto());
//	}
//
//	private static void comparar(String nomeArquivo, InputStream saidaCorreta, String saidaObtida) throws Exception 
//	{
//		InputStreamReader isr = new InputStreamReader(saidaCorreta);
//		StringReader sr = new StringReader(saidaObtida);
//		boolean diferente = false;
//		int charFr = -1;
//		int charSr = -1;
//		while ((charFr = isr.read()) != -1 & (charSr = sr.read()) != -1) 
//		{
//			if (charFr != charSr) 
//			{
//				diferente = true;
//				System.out.println("ERRO:" + nomeArquivo);
//				System.out.println("=================== Saída obtida ==================");
//				System.out.println(Saida.getTexto());
//				System.out.println("===================================================");
//				break;
//			}
//		}
//
//		if (!diferente && (charFr != -1 || charSr != -1)) 
//		{
//			diferente = true;
//			System.out.println("ERRO:" + nomeArquivo);
//			System.out.println("=================== Saída obtida ==================");
//			System.out.println(Saida.getTexto());
//			System.out.println("===================================================");
//		}
//
//		if (!diferente) 
//		{
//			System.out.println("OK:" + nomeArquivo);
//		}
//
//	}
}
