/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
/**
 *
 * @author Usuário
 */
public class GeradorCodigo extends LABaseListener {
    
    //FALTA UTILIZAR AS TABELAS DE SIMBOLOS, NO MOMENTO APENAS COMECEI A CASCA DA 
    //GERACAO DE CODIGO
    
    Saida codigo;
    
    public GeradorCodigo(Saida codigo){
        this.codigo = codigo;
    }
    
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        //Importacoes iniciais e mais uma linha em branco que eh pulada
        codigo.println("#include <stdio.h>");
        codigo.println("#include <stdlib.h>");
        codigo.println("");
    }
    
    public void enterCorpo(LAParser.CorpoContext ctx){
        //Tudo o que eh declarado na funcao main
        codigo.println("int main() {");
    }
    
    public void exitCorpo(LAParser.CorpoContext ctx){
        //Declarou tudo o que tinha pra se declarar na main(), fecha a chave dela
        codigo.println("}");
    }
    
    public void enterVariavel(LAParser.VariavelContext ctx) {
        //Declarando uma variavel
        String nome = ctx.IDENT().getText();
        String tipo = null;
        //Caso tipo basico nao seja nulo, a variavel sera um dos 4 tipos de la
        if (ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico() != null){
            switch (ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getStart().getText()){
                case "literal":
                    tipo = "char";
                    nome = nome+"[80]";
                    break;
                case "inteiro":
                    tipo = "int";
                    break;
                case "real":
                    tipo = "float";
                    break;
                default: //caso logico, nenhum dos testes vai entrar aqui
                    tipo = "bool";     
            }
        }
        else{
            // Tipo nao eh dos basicos, eh um registro
            if (ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT() != null)
                tipo = ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
        }
        
        //verificar se eh um ponteiro
        if (ctx.tipo().tipo_estendido().ponteiros_opcionais() != null)
            tipo = tipo + "*";
            
        codigo.println(tipo+" "+nome);
    }
}
