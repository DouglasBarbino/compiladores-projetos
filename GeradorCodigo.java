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
    
    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        //Importacoes iniciais e mais uma linha em branco que eh pulada
        codigo.println("#include <stdio.h>");
        codigo.println("#include <stdlib.h>");
        codigo.println("");
    }
    
    @Override
    public void enterCorpo(LAParser.CorpoContext ctx){
        //Tudo o que eh declarado na funcao main
        codigo.println("int main() {");
    }
    
    @Override
    public void exitCorpo(LAParser.CorpoContext ctx){
        //Declarou tudo o que tinha pra se declarar na main(), retorna 0 e fecha a chave dela
        codigo.println("return 0;");
        codigo.println("}");
    }
    
    @Override
    public void enterDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        // declarando um tipo de registro
        if (ctx.getStart().getText().equals("tipo")){
            codigo.println("typedef struct {");
        }
    }
    
    @Override
    public void enterVariavel(LAParser.VariavelContext ctx) {
        //Declarando uma variavel
        String nome = ctx.IDENT().getText();
        String tipo = null;
        
        // declaracao de um registro
        if (ctx.tipo().getStart().getText().equals("registro"))
            codigo.println("struct {");
        
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
    
    @Override
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
    
    @Override
    public void enterCmd(LAParser.CmdContext ctx){
        
        String variavel, expressao = " = ", condicao, estrutura, tratamentoOpRelacional;
        
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
        else{
            
            estrutura = ctx.getStart().getText();
            
            // verifica se eh se, caso, para ou enquanto pois sua estrutura inicial eh parecida
            if (estrutura.equals("se") || estrutura.equals("caso") || 
                estrutura.equals("para") || estrutura.equals("enquanto")){
                
                switch (estrutura){
                    case "se":
                        condicao = "if";
                        break;
                    case "caso":
                        condicao = "switch";
                        break;
                    case "para":
                        condicao = "for";
                        break;
                    default: //enquanto
                        condicao = "while";
                }
                //adiciona parenteses para todos
                condicao = condicao + " (";
                
                //preenchimento do parenteses
                switch (estrutura){
                    case "se":
                    case "enquanto":
                        //pega a primeira parte de uma expressao
                        condicao = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                        //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                        if (ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional() != null){
                            condicao = condicao + tratamentoOpRelacional(ctx);
                        }
                        else
                            condicao = condicao + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getText();
                        
                        break;
                    case "caso":
                        condicao = "switch";
                        break;
                    case "para":
                        condicao = "for";
                        break;
                    default: //enquanto
                        condicao = "while";
                }
                
                //fecha o parenteses para todos e imprime no codigo
                condicao = condicao + ") {";
                codigo.println(condicao);
            }
        }
        //escrita
       
    }
    
    private String tratamentoOpE(LAParser.CmdContext ctx) {
        //nao esta pronto
        return "&&";
    }
    
    private String tratamentoOpOU(LAParser.CmdContext ctx) {
        //nao esta pronto
        return "||";
    }
    
    private String tratamentoOpRelacional(LAParser.CmdContext ctx) {
        String tratamentoOp = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional().getText();
        if (tratamentoOp.equals("="))
            tratamentoOp = "==";
        else{
            if (tratamentoOp.equals("<>"))
                tratamentoOp = "!=";
        }
        tratamentoOp = tratamentoOp + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().exp_aritmetica().getText();

        return tratamentoOp;
    }
}
