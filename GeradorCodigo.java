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
    //SITUACAO ATUAL: ATE VARIAVEL TUDO O QUE ERA REFERENTE A TABELA DE SIMBOLOS FOI PEGO
    
    //FALTA UTILIZAR AS TABELAS DE SIMBOLOS, NO MOMENTO APENAS COMECEI A CASCA DA 
    //GERACAO DE CODIGO
    Saida codigo;
    
    //PARA AS TABELAS DE SIMBOLOS
    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    List<String> tipoDoReg = new ArrayList<String>();
    int Registro;
    TabelaDeSimbolos tabelaDoReg;
    int EhRegistro; 
    
    public GeradorCodigo(Saida codigo){
        this.codigo = codigo;
        this.Registro = 0;
        this.EhRegistro = 1;
    }
    
    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        //Importacoes iniciais e mais uma linha em branco que eh pulada
        codigo.println("#include <stdio.h>");
        codigo.println("#include <stdlib.h>");
        codigo.println("");
        
        //TABELA DE SIMBOLOS GLOBAL
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
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
        //Para a tabela de simbolos
        String nomeVar, tipo;
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();

        // declarando um tipo de registro
        String tipoDeclLocal = ctx.getStart().getText();
        
        if (tipoDeclLocal.equals("tipo")){
            codigo.println("typedef struct {");
            
            //PARA A TABELA DE SIMBOLOS
            nomeVar = ctx.IDENT().getText();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                tipoDoReg.add(nomeVar);
            //FIM TABELA SIMBOLOS
        }
        else{
            // declarando uma constante
            if (tipoDeclLocal.equals("constante")){
                codigo.println("#define "+ctx.IDENT().getText()+" "+ctx.valor_constante().getText());
                
                //INCLUINDO CONSTANTE NA TABELA DE SIMBOLOS
                nomeVar = ctx.IDENT().getText();
                tipo = ctx.tipo_basico().tipo_var;
                
                if(!pilhaDeTabelas.existeSimbolo(nomeVar)) 
                    tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipo, null, null);
            }
        }
    }
    
    @Override
    public void enterVariavel(LAParser.VariavelContext ctx) {
        //PARA A TABELA DE SIMBOLOS
        TabelaDeSimbolos tabelaDeSimbolosAtual = tabelaDoReg;
        if(Registro == 0)
            tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        String nomeTabSimb = ctx.IDENT().getText(), tipoTabSimb;
        
        //Declarando uma variavel
        String nome = ctx.IDENT().getText();
        String tipo = null;
        
        //Caso foi declarada mais de uma variavel com esse tipo
        //deixar desse jeito para montar o codigo, para adicionar na tabela de simbolos utilizar a regra do mais_var
        if (ctx.mais_var().getStart().getText().equals(","))
            nome = nome+ctx.mais_var().getText();
        //Caso tipo estendido nao seja nulo, a variavel sera um dos 4 tipos ou de um registro jah definido
        if (ctx.tipo().tipo_estendido() != null){
            // Tipo nao eh dos basicos, eh um registro
            if (ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT() != null)
                tipo = ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
            else{
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
            
            //verificar se eh um ponteiro     
            if (ctx.tipo().tipo_estendido().ponteiros_opcionais().getText().equals("^"))
                tipo = tipo + "*";
            
            //verificar se eh um vetor
            if (ctx.dimensao().getStart().getText().equals("["))
                nome = nome + ctx.dimensao().getText();
            
            codigo.println(tipo+" "+nome+";");
            
            //PARA A TABELA DE SIMBOLOS
            EhRegistro = 0;
            
            if(ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico()!=null)
                tipoTabSimb = ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
            else
                tipoTabSimb = ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
            
            if(tipoTabSimb.equals("literal") || tipoTabSimb.equals("inteiro") || 
               tipoTabSimb.equals("logico") || tipoTabSimb.equals("real")){
                if(!pilhaDeTabelas.existeSimbolo(nomeTabSimb))
                    tabelaDeSimbolosAtual.adicionarSimbolo(nomeTabSimb,tipoTabSimb, null, null);
            }
            else{
                if(!pilhaDeTabelas.existeSimbolo(tipoTabSimb))
                    tabelaDeSimbolosAtual.adicionarSimbolo(nomeTabSimb, tipoTabSimb, null, null);
                else{
                    if(!pilhaDeTabelas.existeSimbolo(nome))
                        tabelaDeSimbolosAtual.adicionarSimbolo(nomeTabSimb,tipoTabSimb, null, null);
                }
            }
            //FIM TABELA SIMBOLOS
        }
        else{
            //declaracao de registro
            codigo.println("struct {");
            
            //PARA A TABELA DE SIMBOLOS
            tipoDoReg.add(nome);
        }
    }
    
    @Override
    public void enterRegistro(LAParser.RegistroContext ctx){
           // TUDO PARA A TABELA DE SIMBOLOS
           tabelaDoReg = new TabelaDeSimbolos("registro");
           Registro = 1;
    }
    
    @Override
    public void exitRegistro(LAParser.RegistroContext ctx){
        //PARA A TABELA DE SIMBOLOS
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
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
        codigo.println("} "+variaveis+";");
        
        //PARA A TABELA DE SIMBOLOS
        for(int i = 0; i < tipoDoReg.size(); i++){
           tabelaDeSimbolosAtual.adicionarSimbolo(tipoDoReg.get(i), tipoDoReg.get(i), null, tabelaDoReg);
        }
       
        tabelaDoReg = null;
        tipoDoReg.clear();
        Registro = 0;
    }
    
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        
        String declaracao;
        
        //verifica se eh um procedimento ou uma funcao
        if (ctx.getStart().getText().equals("procedimento"))
            declaracao = "void ";
        else{
            // como eh funcao precisa ser verificado o tipo dela
            switch (ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText()){
                case "literal":
                    declaracao = "char ";
                    break;
                case "inteiro":
                    declaracao = "int ";
                    break;
                case "real":
                    declaracao = "float ";
                    break;
                default: //caso logico
                    declaracao = "bool ";   
            }
        }
        
        declaracao = declaracao + ctx.IDENT().getText()+" (";
        
        //captura dos parametros
        if (ctx.parametros_opcional() != null){
            // pega o tipo do parametro
            switch (ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().tipo_basico().getText()){
                case "literal":
                    //char eh passado como um ponteiro aqui
                    declaracao = declaracao + "char* ";
                    break;
                case "inteiro":
                    declaracao = declaracao + "int ";
                    break;
                case "real":
                    declaracao = declaracao + "float ";
                    break;
                default: //caso logico
                    declaracao = declaracao + "bool ";  
            }
            
            // pega o nome da variavel
            declaracao = declaracao + ctx.parametros_opcional().parametro().identificador().getText();
            
            if (ctx.parametros_opcional().parametro().mais_parametros().getStart().getText().equals(","))
                //aqui tambem jah seria uma boa poder tratar retornos no noh, assim nao precisaria fazer todo 
                // o tratamento para o mais_parametros... por enquanto fica soh a virgula pois nao tem
                // nenhum caso de teste que utilize mais que um parametro, qualquer coisa dou uma melhorada aqui depois...
                declaracao = declaracao + " ,";
        }
        
        declaracao = declaracao + ") {";
        codigo.println(declaracao);
        
        //aqui vem os comandos
    }
    
    @Override
    public void exitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        //Declarou tudo o que tinha pra se declarar no procedimento ou funcao, fecha a chave dele
        codigo.println("}");
    }
    
    @Override
    public void exitComandos(LAParser.ComandosContext ctx) {
        //Ao contrario do caso, para ou enquanto onde se fecha a chave no exitCmd, o se
        // se fecha a chave aqui devido a possibilidade do se possuir um senao
        
        //Nao se usa o getStart aqui pois o comandos tambem eh o que inicia a declaracao dos
        // comandos apos a declaracao de variaveis no main(), o que poderia resultar em erros caso a 
        // primeira coisa que se declara apos as variaveis eh um se, entao se pega o texto do primeiro filho
        if (ctx.getParent().getChild(0).getText().equals("se"))
            codigo.println("}");
        else{
            //Apos colocar todos os comandos dentro de um case do caso, eh necessario colocar um break
            // entao para verificar se eh a situacao do caso se procura pelo irmao da esquerda do comandos,
            // que na regra do caso eh o token ":"     
            if (ctx.getParent().getChild(1).getText().equals(":"))
                codigo.println("break;");
        }
        
    }
    
    @Override
    public void enterCmd(LAParser.CmdContext ctx){
        //PARA A TABELA DE SIMBOLOS
        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
        String tipoVariavelTabSimb, nomeVariavelTabSimb;
        
        String variavel, expressao = " = ", condicao, estrutura, tratamentoOpRelacional, variavelPara, escrita;
        
        estrutura = ctx.getStart().getText();
        
        if (ctx.chamada_atribuicao() != null){
            //Caso de passagem de parametros para um procedimento / funcao:
            if (ctx.chamada_atribuicao().getStart().getText().equals("(") && ctx.chamada_atribuicao().getStop().getText().equals(")")){
                codigo.println(ctx.getText());    
            }
            //Atribuicao simples que nao eh para ponteiro
            else{
                variavel = ctx.IDENT().getText();
                //ver se eh variavel de registro
                if (ctx.chamada_atribuicao().outros_ident().getStart().getText().equals("."))
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.chamada_atribuicao().outros_ident().getStart().getText()+
                            ctx.chamada_atribuicao().outros_ident().identificador().IDENT().getText();
                expressao = expressao + ctx.chamada_atribuicao().expressao().getText();

                //verificar se eh um vetor
                if (ctx.chamada_atribuicao().dimensao().getStart().getText().equals("["))
                    variavel = variavel + ctx.chamada_atribuicao().dimensao().getText();
                
                codigo.println(variavel+expressao+";");
            }
        }
        else{
            //Atribuicao simples para ponteiro
            if (estrutura.equals("^")){
                variavel = "*"+ctx.IDENT().getText();
                //ver se eh variavel de registro
                if (ctx.outros_ident().getStart().getText().equals(".")) 
                    //nome registro + . + nome variavel dentro do registro
                    variavel = variavel+ctx.outros_ident().getStart().getText()+
                        ctx.outros_ident().identificador().IDENT().getText();
                expressao = expressao + ctx.expressao().getText();

                //verificar se eh um vetor
                if (ctx.dimensao().getStart().getText().equals("["))
                    variavel = variavel + ctx.chamada_atribuicao().dimensao().getText();
                
                codigo.println(variavel+expressao+";");
            }
            else{
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

                            //Caso de fato nao seja possivel tratar o retorno do token no noh
                            /* 
                            //pega a primeira parte de uma expressao
                            condicao = condicao + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                            //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                            if (ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional() != null){
                                condicao = condicao + tratamentoOpRelacional(ctx);
                            }
                            else
                                condicao = condicao + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getText();
                            */
                            condicao = condicao + ctx.expressao().getText();
                            break;
                        case "caso":
                            // por algum motivo ele nao estah me deixando fazer ctx.exp_aritmetica.getText();
                            condicao = condicao+ctx.getChild(1).getText();
                            break;
                        default: //para
                            //mesmo problema que no caso do caso (:P), nao consigo fazer ctx.exp_aritmetica.getText();
                            variavelPara = ctx.IDENT().getText();
                            condicao = condicao + variavelPara + " = " + ctx.getChild(3).getText() + "; ";
                            condicao = condicao + variavelPara + " <= " + ctx.getChild(5).getText() + "; ";
                            condicao = condicao + variavelPara + "++";
                    }

                    //fecha o parenteses para todos e imprime no codigo
                    condicao = condicao + ") {";
                    codigo.println(condicao);
                }
                else{
                    if (estrutura.equals("faca")){
                        //estrutura de repeticao do..while
                        codigo.println("do {");
                    }
                    else{
                        if (estrutura.equals("leia")){
                            //PARA A TABELA DE SIMBOLOS
                            //por enquanto apenas para teste so se pega o nome da variavel, coisa bem simples
                            nomeVariavelTabSimb = ctx.identificador().IDENT().getText();
                            tipoVariavelTabSimb = tabelaAtual.getTipo(nomeVariavelTabSimb);
                            
                            switch (tipoVariavelTabSimb){
                                case "literal":
                                    codigo.println("gets("+nomeVariavelTabSimb+");");
                                    break;
                                case "inteiro":
                                    codigo.println("scanf(\"%d\",&"+nomeVariavelTabSimb+");");
                                    break;
                                case "real":
                                    codigo.println("scanf(\"%f\",&"+nomeVariavelTabSimb+");");
                                    break;
                                default: //caso logico
                                    //na verdade nao se le bool por scanf, mas soh pra mostrar q deu erro coloquei um %a
                                    codigo.println("scanf(\"%a\",&"+nomeVariavelTabSimb+");");
                            }
                            //FIM TABELA SIMBOLOS
                        }
                        else{
                            if (estrutura.equals("escreva")){
                                escrita = "printf(";
                        
                            //verifica se tem texto para ser impresso
                            if (ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                .termo().fator().parcela().parcela_nao_unario() != null)
                                escrita = escrita + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                .termo().fator().parcela().parcela_nao_unario().getText();
                        
                                //PRECISA DA TABELA DE SIMBOLOS
                                escrita = escrita + ");";
                                
                                codigo.println(escrita);
                            }
                            else //retornar algo
                                codigo.println("return "+ctx.expressao().getText()+";");
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void exitCmd(LAParser.CmdContext ctx) {
        //Declarou tudo o que tinha para declarar em caso, para ou enquanto, fecha a chave 
        //O se nao eh tratado aqui pois ele pode ter um senao
        String tipoCmd = ctx.getStart().getText(); 
        
        if (tipoCmd.equals("caso") || tipoCmd.equals("para") || tipoCmd.equals("enquanto"))
            codigo.println("}");
    }
    
    @Override
    public void enterSenao_opcional(LAParser.Senao_opcionalContext ctx) {
        //verifica se o primeiro token encontrado no pai eh se, ou seja, eh o caso de senao,
        // ou se eh um caso, ou seja, eh o caso do default
        if (ctx.getParent().getStart().getText().equals("se"))
            codigo.println("else {");
        else
            codigo.println("default:");
    }
    
    @Override
    public void exitSenao_opcional(LAParser.Senao_opcionalContext ctx) {
        //verifica se o primeiro token encontrado no pai eh se pois apenas no se eh necessario
        // fechar a chave do senao, na regra do caso o default nao utiliza chave
        if (ctx.getParent().getStart().getText().equals("se"))
            codigo.println("}");
    }
    
    @Override
    public void enterSelecao(LAParser.SelecaoContext ctx) {
        //utilizado para montar cases da estrutura condicional escolha
        int comecoIntervalo, fimIntervalo, i;
        
        //convertendo numeros para inteiros, no primeiro caso verifica se eh negativo
        if (ctx.constantes().numero_intervalo().op_unario().getStart().getText().equals("-"))
            comecoIntervalo = Integer.parseInt("-"+ctx.constantes().numero_intervalo().NUM_INT().getText());
        else
            comecoIntervalo = Integer.parseInt(ctx.constantes().numero_intervalo().NUM_INT().getText());
                        
        //verifica se o intervalo que deve ser percorrido no case eh de apenas um numero ou nao
        if (ctx.constantes().numero_intervalo().intervalo_opcional().getStart().getText().equals("..")){
            if (ctx.constantes().numero_intervalo().intervalo_opcional().op_unario().getStart().getText().equals("-"))
                fimIntervalo = Integer.parseInt("-"+ctx.constantes().numero_intervalo().intervalo_opcional().NUM_INT().getText());
            else
                fimIntervalo = Integer.parseInt(ctx.constantes().numero_intervalo().intervalo_opcional().NUM_INT().getText());
        }
        else
            fimIntervalo = comecoIntervalo;
                        
        //inicia o loop de criacao dos case
        for (i=comecoIntervalo; i<=fimIntervalo; i++)
            codigo.println("case "+i+":");
    }
    
    public void enterExpressao(LAParser.ExpressaoContext ctx) {
        
        //tratando o final do do..while, pra isso verificando se o primeiro token encontrado
        // no pai eh faca, ou seja, ver se o cmd tem faca
        if (ctx.getParent().getStart().getText().equals("faca"))
        {
            //caso for possivel tratar o retorno do noh, isso vai estar correto
            codigo.println("} while ("+ctx.getText()+");");
        }
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
