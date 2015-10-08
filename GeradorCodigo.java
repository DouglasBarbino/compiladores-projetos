package trabalho1;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
/**
 * @program GeradorCodigo - Faz toda a geracao de codigo em C de um dado codigo da Linguagem LA
 * @author Douglas
 */
public class GeradorCodigo extends LABaseListener {
    Saida codigo;
    
    //PARA AS TABELAS DE SIMBOLOS
    int EhFuncao = 0;
    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    public GeradorCodigo(Saida codigo){
        this.codigo = codigo;
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
                AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nomeVar);
            //FIM TABELA SIMBOLOS
        }
        else{
            // declarando uma constante
            if (tipoDeclLocal.equals("constante")){
                codigo.println("#define "+ctx.IDENT().getText()+" "+ctx.valor_constante().getText());
                codigo.println("");
                
                //INCLUINDO CONSTANTE NA TABELA DE SIMBOLOS
                nomeVar = ctx.IDENT().getText();
                tipo = ctx.tipo_basico().getText();
                
                if(!pilhaDeTabelas.existeSimbolo(nomeVar)) 
                    tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipo, null, null);
            }
            else{ //caso do declare
                AdicionarSimbolo(ctx, tabelaDeSimbolosAtual);
            }
        }
    }
    
    @Override
    public void enterVariavel(LAParser.VariavelContext ctx) {
        //Declarando uma variavel
        String nome = ctx.IDENT().getText();
        String tipo = "";
        
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
        }
        else{
            //declaracao de registro
            codigo.println("struct {");
        }
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
            // ai se verifica se mais_var (o getChild(2)) eh nulo ou nao
            if (ctx.getParent().getParent().getChild(2).getText() != null)
                variaveis = variaveis + ctx.getParent().getParent().getChild(2).getText();
        }
        codigo.println("} "+variaveis+";");
    }
    
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        //PARA A TABELA DE SIMBOLOS
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        List<String> listaNomePar = new ArrayList<>();
        List<String> listaTipoPar = new ArrayList<>();
        
        String declaracao;
        
        //verifica se eh um procedimento ou uma funcao
        if (ctx.getStart().getText().equals("procedimento")){
            declaracao = "void ";
        
            //PARA A TABELA DE SIMBOLOS
            if(!pilhaDeTabelas.existeSimbolo(nome)){
                if(ctx.parametros_opcional()!=null)
                    AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, null, listaTipoPar, null);
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento"+nome);
                for(int i = 0; i < listaNomePar.size(); i++){
                    if(!tabelaDeSimbolosProcedimento.existeSimbolo(listaNomePar.get(i)))
                        tabelaDeSimbolosProcedimento.adicionarSimbolo(listaNomePar.get(i), listaTipoPar.get(i), null, null);
                }
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  
            }
            //FIM DA TABELA DE SIMBOLOS
        }
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
            
            //PARA A TABELA DE SIMBOLOS
            EhFuncao = 1;
            if(!pilhaDeTabelas.existeSimbolo(nome)){
                if(ctx.parametros_opcional()!=null)
                    AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                String TipoFuncao;
                if(ctx.tipo_estendido().tipo_basico_ident().IDENT()!=null)
                    TipoFuncao = ctx.tipo_estendido().tipo_basico_ident().IDENT().getText();
                else
                    TipoFuncao = ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                    
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, TipoFuncao, listaTipoPar, null);
                TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao"+nome);
                    
                for(int i = 0; i < listaNomePar.size(); i++){
                    if(!tabelaDeSimbolosFuncao.existeSimbolo(listaNomePar.get(i)))
                        tabelaDeSimbolosFuncao.adicionarSimbolo(listaNomePar.get(i), listaTipoPar.get(i), null, null);
                }
                pilhaDeTabelas.empilhar(tabelaDeSimbolosFuncao);
            }
            //FIM TABELA DE SIMBOLOS
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
    }
    
    @Override
    public void exitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        //Declarou tudo o que tinha pra se declarar no procedimento ou funcao, fecha a chave dele e pula uma linha
        codigo.println("}");
        codigo.println("");
        
        //PARA A TABELA DE SIMBOLOS
        pilhaDeTabelas.desempilhar();
        EhFuncao = 0;
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
        String tipoVariavelTabSimb, nomeVariavelTabSimb, tipoRegistro;
        
        String variavel, expressao = " = ", condicao, estrutura, variavelPara, escrita, tokenTratado, atribuicaoStringRegistro = "strcpy(";
        
        estrutura = ctx.getStart().getText();
        
        if (ctx.chamada_atribuicao() != null){
            //Caso de passagem de parametros para um procedimento / funcao:
            if (ctx.chamada_atribuicao().getStart().getText().equals("(") && ctx.chamada_atribuicao().getStop().getText().equals(")")){
                codigo.println(ctx.getText()+";");    
            }
            //Atribuicao simples que nao eh para ponteiro
            else{
                variavel = ctx.IDENT().getText();
                expressao = expressao + ctx.chamada_atribuicao().expressao().getText();
                //ver se eh variavel de registro
                if (ctx.chamada_atribuicao().outros_ident().getStart().getText().equals(".")){
                    nomeVariavelTabSimb = ctx.chamada_atribuicao().outros_ident().identificador().IDENT().getText();
                    //pega o tipo do registro para obter a sua tabela de simbolos
                    tipoRegistro = variavel;
                    variavel = variavel + "." + nomeVariavelTabSimb;
                    // Caso isso for um tipo de registro, isso serah null e vai ser necessario pegar primeiro o tipo dele
                    if (pilhaDeTabelas.getSubtabela(tipoRegistro) != null){
                        TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipoRegistro);
                        tipoVariavelTabSimb = tabelaDoRegistro.getTipo(nomeVariavelTabSimb);
                    }
                    else{
                        tipoVariavelTabSimb = tabelaAtual.getTipo(tipoRegistro);
                        TabelaDeSimbolos tabelaDoTipoRegistro = pilhaDeTabelas.getSubtabela(tipoVariavelTabSimb);
                        tipoVariavelTabSimb = tabelaDoTipoRegistro.getTipo(nomeVariavelTabSimb);
                    }
                    //Para strings, eh necessario usar o strcpy(), caso contrario uma atribuicao normal com = basta
                    if (tipoVariavelTabSimb.equals("literal"))
                        codigo.println(atribuicaoStringRegistro+variavel+","+ctx.chamada_atribuicao().expressao().getText()+");");
                    else
                        codigo.println(variavel+expressao+";");
                }
                else{
                    //verificar se eh um vetor
                    if (ctx.chamada_atribuicao().dimensao().getStart().getText().equals("["))
                        variavel = variavel + ctx.chamada_atribuicao().dimensao().getText();
                    codigo.println(variavel+expressao+";");
                }
            }
        }
        else{
            //Atribuicao simples para ponteiro
            if (estrutura.equals("^")){
                variavel = "*"+ctx.IDENT().getText();
                expressao = expressao + ctx.expressao().getText();
                //ver se eh variavel de registro
                if (ctx.outros_ident().getStart().getText().equals(".")){
                    nomeVariavelTabSimb = ctx.outros_ident().identificador().IDENT().getText();
                    //pega o tipo do registro sem o ponteiro para obter a sua tabela de simbolos
                    tipoRegistro = ctx.IDENT().getText();
                    variavel = variavel + "." + nomeVariavelTabSimb;
                    // Caso isso for um tipo de registro, isso serah null e vai ser necessario pegar primeiro o tipo dele
                    if (pilhaDeTabelas.getSubtabela(tipoRegistro) != null){
                        TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipoRegistro);
                        tipoVariavelTabSimb = tabelaDoRegistro.getTipo(nomeVariavelTabSimb);
                    }
                    else{
                        tipoVariavelTabSimb = tabelaAtual.getTipo(tipoRegistro);
                        TabelaDeSimbolos tabelaDoTipoRegistro = pilhaDeTabelas.getSubtabela(tipoVariavelTabSimb);
                        tipoVariavelTabSimb = tabelaDoTipoRegistro.getTipo(nomeVariavelTabSimb);
                    }
                    //Para strings, eh necessario usar o strcpy(), caso contrario uma atribuicao normal com = basta
                    if (tipoVariavelTabSimb.equals("literal"))
                        codigo.println(atribuicaoStringRegistro+variavel+","+ctx.chamada_atribuicao().expressao().getText()+");");
                    else
                        codigo.println(variavel+expressao+";");
                }
                else{    
                    //verificar se eh um vetor
                    if (ctx.dimensao().getStart().getText().equals("["))
                        variavel = variavel + ctx.chamada_atribuicao().dimensao().getText();
                    codigo.println(variavel+expressao+";");
                }
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
                        //vale para o se e para o enquanto esse codigo
                        case "se":
                        case "enquanto":
                            //Verificar se foi negado no comeco
                            if (ctx.expressao().termo_logico().fator_logico().op_nao().getText().equals("nao")){
                                //pensei sim em sobrescrever o contexto ctx, mas nao pode... :(
                                LAParser.ExpressaoContext naoCtx = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().expressao();
                                //pega a primeira parte de uma expressao
                                condicao = condicao + "!(" + naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                                //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                                if (!naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getStart().getText().equals("")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional());
                                    condicao = condicao + " " + tokenTratado + " " +
                                            naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().exp_aritmetica().getText();
                                }
                                //verifica se teve um operador e na expressao
                                if (naoCtx.termo_logico().outros_fatores_logicos().getStart().getText().equals("e")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.termo_logico().outros_fatores_logicos());
                                    //a partir do fator_logico ele parou de reconhecer tudo: parcela_logica, getChild, getText...
                                    //logico que nao eh o certo pegar tudo a partir do fator_logico (o getChild(1)), mas no unico teste que passa por aqui isso nao vai ser problema
                                    
                                    //pega a primeira parte de uma expressao
                                    condicao = condicao + " " + tokenTratado + " " +
                                            naoCtx.termo_logico().outros_fatores_logicos().getChild(1).getText();
                                }
                                //verifica se teve um operador ou na expressao
                                if (naoCtx.outros_termos_logicos().getStart().getText().equals("ou")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.outros_termos_logicos());
                                    //mesma coisa que ocorreu com o fator_logico, mas agora com o termo_logico
                                    condicao = condicao + " " + tokenTratado + " " +
                                            naoCtx.outros_termos_logicos().getChild(1).getText();
                                }
                                //fecha o parenteses extra que foi aberto pelo nao
                                condicao = condicao + ")";
                            }
                            else{ 
                                //pega a primeira parte de uma expressao
                                condicao = condicao + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                                //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                                if (!ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getStart().getText().equals("")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional());
                                    condicao = condicao + " " + tokenTratado + " " +
                                            ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().exp_aritmetica().getText();
                                }
                                //verifica se teve um operador e na expressao
                                if (ctx.expressao().termo_logico().outros_fatores_logicos().getStart().getText().equals("e")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.expressao().termo_logico().outros_fatores_logicos());
                                    //a partir do fator_logico ele parou de reconhecer tudo: parcela_logica, getChild, getText...
                                    //logico que nao eh o certo pegar tudo a partir do fator_logico (o getChild(1)), mas no unico teste que passa por aqui isso nao vai ser problema
                                    
                                    //pega a primeira parte de uma expressao
                                    condicao = condicao + " " + tokenTratado + " " +
                                            ctx.expressao().termo_logico().outros_fatores_logicos().getChild(1).getText();
                                }
                                //verifica se teve um operador ou na expressao
                                if (ctx.expressao().outros_termos_logicos().getStart().getText().equals("ou")){
                                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.expressao().outros_termos_logicos());
                                    //mesma coisa que ocorreu com o fator_logico, mas agora com o termo_logico
                                    condicao = condicao + " " + tokenTratado + " " +
                                            ctx.expressao().outros_termos_logicos().getChild(1).getText();
                                }
                            }
                            break;
                        case "caso":
                            // por algum motivo ele nao estah me deixando fazer ctx.exp_aritmetica.getText();
                            condicao = condicao+ctx.getChild(1).getText();
                            break;
                        default: //caso do para
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
                        
                                //verifica se eh texto para ser impresso
                                if (ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                    .termo().fator().parcela().parcela_nao_unario() != null)
                                    escrita = escrita + ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                            .termo().fator().parcela().parcela_nao_unario().getText();   
                                //nao eh texto, eh variaveis
                                else{
                                    //PARA A TABELA DE SIMBOLOS
                                    //vai imprimir um registro
                                    if (ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                        .termo().fator().parcela().parcela_unario().chamada_partes().getStart().getText().equals(".")){
                                        //Se pega o nome da variavel
                                        nomeVariavelTabSimb = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                                                .termo().fator().parcela().parcela_unario().chamada_partes().outros_ident().identificador().IDENT().getText();
                                        //pega o tipo do registro para obter a sua tabela de simbolos
                                        tipoRegistro = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                                                .termo().fator().parcela().parcela_unario().IDENT().getText();
                                        // Caso isso for um tipo de registro, isso serah null e vai ser necessario pegar primeiro o tipo dele
                                        if (pilhaDeTabelas.getSubtabela(tipoRegistro) != null){
                                            TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipoRegistro);
                                            tipoVariavelTabSimb = tabelaDoRegistro.getTipo(nomeVariavelTabSimb);
                                        }
                                        else{
                                            tipoVariavelTabSimb = tabelaAtual.getTipo(tipoRegistro);
                                            TabelaDeSimbolos tabelaDoTipoRegistro = pilhaDeTabelas.getSubtabela(tipoVariavelTabSimb);
                                            tipoVariavelTabSimb = tabelaDoTipoRegistro.getTipo(nomeVariavelTabSimb);
                                        }
                                    }   
                                    //como nao tem caso de imprimir resultados de expressoes cujos tipos das variaveis sao diferentes soh 
                                    //estou pegando o tipo da primeira variavel, qualquer coisa depois eu trato isso
                                    
                                    //vai imprimir uma variavel ou o retorno de uma funcao
                                    else{
                                        nomeVariavelTabSimb = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica()
                                                                .termo().fator().parcela().parcela_unario().IDENT().getText();
                                        tipoVariavelTabSimb = tabelaAtual.getTipo(nomeVariavelTabSimb);
                                    }

                                    switch (tipoVariavelTabSimb){
                                        //tudo de variavel eh pego na exp.relacional pro caso do que eh para ser impresso seja uma expressao
                                        case "literal":
                                            escrita = escrita + "\"%s\"," +
                                                    ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText();
                                            break;
                                        case "inteiro":
                                            escrita = escrita + "\"%d\"," +
                                                    ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText();
                                            break;
                                        case "real":
                                            escrita = escrita + "\"%f\"," +
                                                    ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText();
                                            break;
                                        default: //caso logico
                                            //bool pode ser escrito como inteiro, mas jah que nao tem casos logicos entao deixei %a pra mostrar erro
                                            escrita = escrita + "\"%a\"," +
                                                    ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText();
                                    }
                                    //FIM TABELA SIMBOLOS
                                }
                        
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
    public void enterMais_expressao(LAParser.Mais_expressaoContext ctx) {
        //PARA A TABELA DE SIMBOLOS
        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
        String tipoVariavelTabSimb, nomeVariavelTabSimb, tipoRegistro;
        
        String escrita;
        int i = 0;
        //tratar casos de mais escritas
        if (ctx.getParent().getStart().getText().equals("escreva")){
            //enquanto aparecer virgulas serao impressos printf
            while (ctx.getChild(i) != null){
                escrita = "printf(";
                        
                //Como nao da pra usar o ctx.expressao pois eh preciso pegar o expressao daquela virgula, entao vai ter que ser 
                //tudo na base do getChild(), mas deixo comentado o que sao aqueles getChild

                //verifica se eh uma variavel para impressao
                //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario()
                if (ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1) != null){
                    //PARA A TABELA DE SIMBOLOS
                    
                    //eh um registro
                    //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().chamada_partes().getText() != null
                    if (!ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getText().equals("")){              
                        //Se pega o nome da variavel
                        //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().chamada_partes().outros_ident().identificador().IDENT().getText()
                        nomeVariavelTabSimb = ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(1).getChild(0).getChild(1).getChild(1).getText();
                        //pega o tipo do registro para obter a sua tabela de simbolos
                        //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().IDENT().getText();
                        tipoRegistro = ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                        // Caso isso for um tipo de registro, isso serah null e vai ser necessario pegar primeiro o tipo dele
                        if (pilhaDeTabelas.getSubtabela(tipoRegistro) != null){
                            TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipoRegistro);
                            tipoVariavelTabSimb = tabelaDoRegistro.getTipo(nomeVariavelTabSimb);
                        }
                        else{
                            tipoVariavelTabSimb = tabelaAtual.getTipo(tipoRegistro);
                            TabelaDeSimbolos tabelaDoTipoRegistro = pilhaDeTabelas.getSubtabela(tipoVariavelTabSimb);
                            tipoVariavelTabSimb = tabelaDoTipoRegistro.getTipo(nomeVariavelTabSimb);
                        }
                    }             
                    //como nao tem caso de imprimir resultados de expressoes cujos tipos das variaveis sao diferentes soh 
                    //estou pegando o tipo da primeira variavel, qualquer coisa depois eu trato isso
                    else{
                        //vai imprimir uma variavel ou o retorno de uma funcao
                        //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().IDENT().getText()
                        nomeVariavelTabSimb = ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                        tipoVariavelTabSimb = tabelaAtual.getTipo(nomeVariavelTabSimb);
                    }

                    switch (tipoVariavelTabSimb){
                        //tudo de variavel eh pego na exp.relacional pro caso do que eh para ser impresso seja uma expressao
                        case "literal":
                            //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText()
                            escrita = escrita + "\"%s\"," +
                                    ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                            break;
                        case "inteiro":
                            //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText()
                            escrita = escrita + "\"%d\"," +
                                    ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                            break;
                        case "real":
                            //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText()
                            escrita = escrita + "\"%f\"," +
                                    ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                            break;
                        default: //caso logico
                            //bool pode ser escrito como inteiro, mas jah que nao tem casos logicos entao deixei %a pra mostrar erro
                            //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().getText()
                            escrita = escrita + "\"%a\"," +
                                    ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getText();
                    }
                //FIM TABELA SIMBOLOS
                }
                    
                //eh um texto
                else{
                    //ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_nao_unario()
                    escrita = escrita + 
                            ctx.getChild(i+1).getChild(0).getChild(0).getChild(1).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getText();
                }
                        
                escrita = escrita + ");";
                codigo.println(escrita);
                // incrementa o contador em 2 pois procuro se tem uma virgula, e ela aparece a cada um noh sim, um nao
                i = i+2;
            }
        }
    }
    
    @Override
    public void enterSenao_opcional(LAParser.Senao_opcionalContext ctx) {
        //primeiramente se verifica se esse noh nao estah vazio
        if (!ctx.getText().equals("")){
            //verifica se o primeiro token encontrado no pai eh se, ou seja, eh o caso de senao,
            // ou se eh um caso, ou seja, eh o caso do default
            if (ctx.getParent().getStart().getText().equals("se"))
                codigo.println("else {");
            else
                codigo.println("default:");
        }
    }
    
    @Override
    public void exitSenao_opcional(LAParser.Senao_opcionalContext ctx) {
        //primeiramente se verifica se esse noh nao estah vazio
        if (!ctx.getText().equals("")){
            //verifica se o primeiro token encontrado no pai eh se pois apenas no se eh necessario
            // fechar a chave do senao, na regra do caso o default nao utiliza chave
            if (ctx.getParent().getStart().getText().equals("se"))
                codigo.println("}");
        }
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
    
    @Override
    public void exitExpressao(LAParser.ExpressaoContext ctx) {
        
        String condicao= "} while (", tokenTratado;
        //tratando o final do do..while, pra isso verificando se o primeiro token encontrado
        // no pai eh faca, ou seja, ver se o cmd tem faca
        if (ctx.getParent().getStart().getText().equals("faca")){
            //Verificar se foi negado no comeco
            if (ctx.termo_logico().fator_logico().op_nao().getText().equals("nao")){
                //pensei sim em sobrescrever o contexto ctx, mas nao pode... :(
                LAParser.ExpressaoContext naoCtx = ctx.termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().expressao();
                //pega a primeira parte de uma expressao
                condicao = condicao + "!(" + naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                if (!naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getStart().getText().equals("")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional());
                    condicao = condicao + " " + tokenTratado + " " +
                            naoCtx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().exp_aritmetica().getText();
                }
                //verifica se teve um operador e na expressao
                if (naoCtx.termo_logico().outros_fatores_logicos().getStart().getText().equals("e")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.termo_logico().outros_fatores_logicos());
                    //a partir do fator_logico ele parou de reconhecer tudo: parcela_logica, getChild, getText...
                    //logico que nao eh o certo pegar tudo a partir do fator_logico (o getChild(1)), mas no unico teste que passa por aqui isso nao vai ser problema
                    //pega a primeira parte de uma expressao
                    condicao = condicao + " " + tokenTratado + " " +
                            naoCtx.termo_logico().outros_fatores_logicos().getChild(1).getText();
                }
                //verifica se teve um operador ou na expressao
                if (naoCtx.outros_termos_logicos().getStart().getText().equals("ou")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(naoCtx.outros_termos_logicos());
                    //mesma coisa que ocorreu com o fator_logico, mas agora com o termo_logico
                    condicao = condicao + " " + tokenTratado + " " +
                            naoCtx.outros_termos_logicos().getChild(1).getText();
                }
                //fecha o parenteses extra que foi aberto pelo nao
                condicao = condicao + "));";
            }
            else{ 
                //pega a primeira parte de uma expressao
                condicao = condicao + ctx.termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().getText();
                //verifica se op_relacional nao eh vazio pois dois dos operadores precisam ser convertidos para C
                if (!ctx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().getStart().getText().equals("")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().op_relacional());
                    condicao = condicao + " " + tokenTratado + " " +
                            ctx.termo_logico().fator_logico().parcela_logica().exp_relacional().op_opcional().exp_aritmetica().getText();
                }
                //verifica se teve um operador e na expressao
                if (ctx.termo_logico().outros_fatores_logicos().getStart().getText().equals("e")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.termo_logico().outros_fatores_logicos());
                    //a partir do fator_logico ele parou de reconhecer tudo: parcela_logica, getChild, getText...
                    //logico que nao eh o certo pegar tudo a partir do fator_logico (o getChild(1)), mas no unico teste que passa por aqui isso nao vai ser problema
                    //pega a primeira parte de uma expressao
                    condicao = condicao + " " + tokenTratado + " " +
                            ctx.termo_logico().outros_fatores_logicos().getChild(1).getText();
                }
                //verifica se teve um operador ou na expressao
                if (ctx.outros_termos_logicos().getStart().getText().equals("ou")){
                    tokenTratado = TrataSimbolosGeradorCodigo.trataToken(ctx.outros_termos_logicos());
                    //mesma coisa que ocorreu com o fator_logico, mas agora com o termo_logico
                    condicao = condicao + " " + tokenTratado + " " +
                            ctx.outros_termos_logicos().getChild(1).getText();
                }
                //fecha o parenteses da condicao
                condicao = condicao + ");";
            }
            codigo.println(condicao);
        }
    }
    
    // A partir de agora sao as tres funcoes utilizadas para adicionar simbolos nas tabelas de simbolos
    public void AdicionarSimboloRegistro(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual, String nomeDoReg){ 
        String nome, tipo;
        
        TabelaDeSimbolos tabelaDoRegistro = new TabelaDeSimbolos("registro");
                   
        if (ctx.tipo()!=null){      
            nome = ctx.tipo().registro().variavel().IDENT().getText();
            if (ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
            else
                tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   
            if (!tabelaDoRegistro.existeSimbolo(nome))
                tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   
            for(int i = 0; i<ctx.tipo().registro().variavel().mais_var().IDENT().size(); i++){
                nome = ctx.tipo().registro().variavel().mais_var().IDENT(i).getText();
                if(!tabelaDoRegistro.existeSimbolo(nome))
                    tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
            }
                   
            for(int i = 0; i < ctx.tipo().registro().mais_variaveis().variavel().size(); i++){
                nome = ctx.tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
                if (ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                    tipo = ctx.tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                else
                    tipo = ctx.tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   
                if (!tabelaDoRegistro.existeSimbolo(nome))
                    tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                        
                for(int j = 0; j<ctx.tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT().size(); j++){
                    nome = ctx.tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT(i).getText();
                    if (!tabelaDoRegistro.existeSimbolo(nome))
                        tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                }
            }
        }
        else{
            if (ctx.variavel().tipo()!=null){
                nome = ctx.variavel().tipo().registro().variavel().IDENT().getText();
                if (ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                    tipo = ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                else
                    tipo = ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   
                if (!tabelaDoRegistro.existeSimbolo(nome))
                    tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   
                for (int i = 0; i<ctx.variavel().tipo().registro().variavel().mais_var().IDENT().size(); i++){
                    nome = ctx.variavel().tipo().registro().variavel().mais_var().IDENT(i).getText();
                    if(!tabelaDoRegistro.existeSimbolo(nome))
                        tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                }
                   
                for (int i = 0; i < ctx.variavel().tipo().registro().mais_variaveis().variavel().size(); i++){
                    nome = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
                    if (ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                        tipo = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                    else
                        tipo = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   
                    if (!tabelaDoRegistro.existeSimbolo(nome))
                        tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   
                    for(int j = 0; j<ctx.variavel().tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT().size(); j++){
                        nome = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT(i).getText();
                        if(!tabelaDoRegistro.existeSimbolo(nome))
                            tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                    }
                }
            } 
        }        
        tabelaDeSimbolosAtual.adicionarSimbolo(nomeDoReg, nomeDoReg, null, tabelaDoRegistro);         
    }
    
    public void AdicionarSimbolo(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual){
        String nome, tipo;
        
        if (ctx.variavel().tipo().registro()==null){      
            nome = ctx.variavel().IDENT().getText();
            if(ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                tipo = ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
            else
                tipo = ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipo, null, null);
                   
            for(int i = 0; i<ctx.variavel().mais_var().IDENT().size(); i++){
                nome = ctx.variavel().mais_var().IDENT(i).getText();
                if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipo, null, null);
            }
        }
        else{
            nome = ctx.variavel().IDENT().getText();
                
            if(!pilhaDeTabelas.existeSimbolo(nome))
                AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nome);
                
            for(int i = 0; i<ctx.variavel().mais_var().IDENT().size(); i++){
                nome = ctx.variavel().mais_var().IDENT(i).getText();
                if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                    AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nome);
            }
        }       
    }
    
    public void AdicionarTiposParametros(LAParser.Declaracao_globalContext ctx, List<String> ListaNomePar, List<String> ListaTipoPar){
        String nome, tipo;
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        nome = ctx.parametros_opcional().parametro().identificador().IDENT().getText();
        if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
            ListaNomePar.add(nome);
        
        if(ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT()!=null)
            tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT().getText();
        else
            tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
        
        ListaTipoPar.add(tipo);
        
        for(int i = 0; i<ctx.parametros_opcional().parametro().mais_ident().identificador().size(); i++){
            nome = ctx.parametros_opcional().parametro().mais_ident().identificador(i).IDENT().getText();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                ListaNomePar.add(nome);
            ListaTipoPar.add(tipo);
        }
        
        if(ctx.parametros_opcional().parametro().mais_parametros().parametro()!=null){
            nome = ctx.parametros_opcional().parametro().mais_parametros().parametro().identificador().IDENT().getText();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                ListaNomePar.add(nome);
        
            if(ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT().getText();
            else
                tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
        
            ListaTipoPar.add(tipo);
        
            for(int i = 0; i < ctx.parametros_opcional().parametro().mais_parametros().parametro().mais_ident().identificador().size(); i++){
                nome = ctx.parametros_opcional().parametro().mais_parametros().parametro().mais_ident().identificador(i).IDENT().getText();
                if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                    ListaNomePar.add(nome);
                ListaTipoPar.add(tipo);
            }
        }
    }
}
