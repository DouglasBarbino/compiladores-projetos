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
    
    public void enterDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        
        // declarando um tipo de registro
        if (ctx.getStart().getText().equals("tipo")){
            codigo.println("typedef struct {");
        }
    }
    
    public void enterVariavel(LAParser.VariavelContext ctx) {
        //Declarando uma variavel
        String nome = ctx.IDENT().getText();
        String tipo = null;
        
        //Caso foi declarada mais de uma variavel com esse tipo
        //deixar desse jeito para montar o codigo, para adicionar na tabela de simbolos utilizar a regra do mais_var
        if (ctx.mais_var() != null)
            nome = nome+ctx.mais_var().getText();
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
            
        codigo.println(tipo+" "+nome+";");
    }
    
    public void exitRegistro(LAParser.RegistroContext ctx){
        
        String variaveis;
        //Declarou tudo o que tinha pra se declarar no registro, fecha a chave dele
        //caso seja um tipo de registro
        if (ctx.getParent().getParent().getStart().getText().equals("tipo"))
            //getChild(1) eh o nome do tipo
            variaveis = ctx.getParent().getParent().getChild(1).getText();
        
        //caso contrario eh apenas um registro
        else {
            //eh o nome do registro
            variaveis = ctx.getParent().getParent().getStart().getText();
            // verifica se mais de uma variavel eh declarada naquele registro, como no exemplo 11 dos erros semanticos
            // ai se verifica se mais_var eh nulo ou nao
            if (ctx.getParent().getParent().getChild(2).getText() != null)
                variaveis = variaveis + ctx.getParent().getParent().getChild(2).getText();
        }
        codigo.println("} "+variaveis);
    }
    
    public void enterCmd(LAParser.CmdContext ctx){
        
        String variavel, expressao = " = ";
        
        //onde irao ser feitas as equacoes
        //atribuicao simples
        if (ctx.IDENT() != null && ctx.getStart().getText().equals("para")){
            // verifica se nao eh atribuicao para ponteiro
            if (ctx.getStart().getText().equals(ctx.IDENT().getText())){
                variavel = ctx.IDENT().getText();
                if (ctx.chamada_atribuicao().outros_ident() != null) //ver se eh variavel de registro
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.chamada_atribuicao().outros_ident().getStart().getText()+
                            ctx.chamada_atribuicao().outros_ident().identificador().IDENT().getText();
                expressao = expressao + ctx.chamada_atribuicao().expressao().getText();
            }
            else{
                variavel = "*"+ctx.IDENT().getText();
                if (ctx.outros_ident() != null) //ver se eh variavel de registro
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.outros_ident().getStart().getText()+
                            ctx.outros_ident().identificador().IDENT().getText();
                expressao = expressao + ctx.expressao().getText();
            }
            
            codigo.println(variavel+expressao+";");
        }
        //escrita
        
    }
}
