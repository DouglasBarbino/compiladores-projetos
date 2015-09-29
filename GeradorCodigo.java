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
    
    public void enterCmd(LAParser.CmdContext ctx){
        //onde irao ser feitas as equacoes
        String variavel, expressao;
        //atribuicao simples
        if (ctx.IDENT() != null && ctx.getStart().getText().equals("para")){
            // verifica se nao eh atribuicao para ponteiro
            if (ctx.getStart().getText().equals(ctx.IDENT().getText())){
                variavel = ctx.IDENT().getText();
                if (ctx.chamada_atribuicao().outros_ident() != null) //ver se eh variavel de registro
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.chamada_atribuicao().outros_ident().getStart().getText()+
                            ctx.chamada_atribuicao().outros_ident().identificador().IDENT().getText();
            }
            else{
                variavel = "*"+ctx.IDENT().getText();
                if (ctx.outros_ident() != null) //ver se eh variavel de registro
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.outros_ident().getStart().getText()+
                            ctx.outros_ident().identificador().IDENT().getText();
            }
            //NAO TENHO CERTEZA SE ALGUM CASO DA PROBLEMA FAZER ISSO, POR ENQUANTO SERVE ISSO QUE ATENDE TODOS OS CASOS
            expressao = " = "+
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().getText(); 
            //tem modulo
            if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().outras_parcelas()!= null)
                /*expressao = expressao+" "+
                        ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().outras_parcelas().getStart().getText()
                        +" "+
                        ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().getText();
            */
                expressao = expressao + capturaModulo(ctx);
            
            //tem multiplicacao / divisao
            if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().outros_fatores() != null)
                expressao = expressao + capturaMultDiv(ctx);
            
            //tem adicao / subtracao
            if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().outros_termos() != null)
                expressao = expressao + capturaAdiSub(ctx);

            //jeito antigo caso seja necessario
            //expressao = " = "; 
            /*if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().
                    termo().fator().parcela().parcela_nao_unario() != null)
                expressao = expressao+ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().
                        exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_nao_unario().getText();
            else {
                if 
            }*/
        }
        
        //escrita
        
    }
    
    private String capturaModulo(LAParser.CmdContext ctx) {
        String expressao;
        expressao = " "+
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().outras_parcelas().getStart().getText()
                    +" "+
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().getText();
        return expressao;
    }
    
    private String capturaMultDiv(LAParser.CmdContext ctx){
        String expressao;
        expressao = " "+
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().outros_fatores().getStart().getText();
        if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().outras_parcelas()!= null)
            expressao = expressao + capturaModulo(ctx);
        else
            expressao = expressao + " " +
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().getText();
        return expressao;
    }
    
    private String capturaAdiSub(LAParser.CmdContext ctx){
        String expressao;
        expressao = " "+
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().outros_termos().getStart().getText();
        if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().outros_fatores() != null){
            expressao = expressao + capturaMultDiv(ctx);
        }
        else{
            if (ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().outras_parcelas()!= null)
                expressao = expressao + capturaModulo(ctx);
            else
                expressao = expressao + " " +
                    ctx.chamada_atribuicao().expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().getText();
        }
        return expressao;
    }
}
