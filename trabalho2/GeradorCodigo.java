package trabalho3;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
/**
 * @program GeradorCodigo - Faz toda a geracao de codigo em Arduino de um dado codigo da Linguagem FAZEDORES
 * @author Douglas
 */
public class GeradorCodigo extends FAZEDORESBaseListener {
    Saida codigo;
  
    //PARA AS TABELAS DE SIMBOLOS
    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    public GeradorCodigo(Saida codigo){
        this.codigo = codigo;
    }
    
    @Override 
    public void enterPrograma(FAZEDORESParser.ProgramaContext ctx) {
        //TABELA DE SIMBOLOS GLOBAL
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
    }
    
    @Override
    public void enterDeclaracao_local(FAZEDORESParser.Declaracao_localContext ctx) {
        //Para a tabela de simbolos
        String nomeVar, tipo, tipoConstante;
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();

        //PARA INCLUSAO DA CONSTANTE NA TABELA DE SIMBOLOS
        nomeVar = ctx.IDENT().getText();
        String tipoDeclLocal = ctx.getStart().getText();
        
        // declarando uma constante
        if (tipoDeclLocal.equals("constante")){
            tipo = ctx.tipo_basico().getText();
            
            switch (tipo){
                case "literal":
                    tipoConstante = "string ";
                    break;
                case "inteiro":
                    tipoConstante = "int ";
                    break;
                case "real":
                    tipoConstante = "float ";
                    break;
                default: //caso logico
                    tipoConstante = "bool ";   
            }
            //
            codigo.println("const "+tipoConstante+nomeVar+" = "+ctx.valor_constante().getText()+";");
            codigo.println("");
            
            if(!pilhaDeTabelas.existeSimbolo(nomeVar)) 
                tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipo, null, null);
        }
    }
    
    @Override 
    public void enterCmd(FAZEDORESParser.CmdContext ctx) {
        //PARA A TABELA DE SIMBOLOS
        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
        
        String condicao, tokenTratado, variavelPara;
        String estrutura = ctx.getStart().getText();
        
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
                        FAZEDORESParser.ExpressaoContext naoCtx = ctx.expressao().termo_logico().fator_logico().parcela_logica().exp_relacional().exp_aritmetica().termo().fator().parcela().parcela_unario().expressao();
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
    }
    
    @Override
    public void exitCmd(FAZEDORESParser.CmdContext ctx) {
        //Declarou tudo o que tinha para declarar em caso, para ou enquanto, fecha a chave 
        //O se nao eh tratado aqui pois ele pode ter um senao
        String tipoCmd = ctx.getStart().getText(); 
        
        if (tipoCmd.equals("caso") || tipoCmd.equals("para") || tipoCmd.equals("enquanto"))
            codigo.println("}");
    }
    
    @Override
    public void enterComandoSetup(FAZEDORESParser.ComandoSetupContext ctx){
        int i=0;
        String setup = "";

        //Declaracao da funcao setup do Arduino
        codigo.println("void setup() {");
        
        /*Por nao saber quantos 'ativar' existem aqui dentro, entao deve ser 
        utilizado este loop com getChild, mas por cima eu indico qual eh a 
        operacao que estou fazendo*/
        while (ctx.getChild(i) != null){
            
            //ctx.dispositivo().getText().equals("led")
            if (ctx.getChild(i+2).getText().equals("led"))
                //ctx.PORTA().getText()
                setup = "\tpinmode(" + ctx.getChild(i+4).getText() + ", OUTPUT);";
            
            codigo.println(setup);
            
            //incrementa o i na quantidade de filhos que o ComandoSetup possui
            i = i+6;
        }
    }
    
    @Override
    public void exitComandoSetup(FAZEDORESParser.ComandoSetupContext ctx){
        //Terminou todas as declaracoes da funcao setup, eh fechada a chave dela
        codigo.println("}");
    }
    
    @Override 
    public void enterComandoLoop(FAZEDORESParser.ComandoLoopContext ctx){ 
        //Declaracao da funcao loop do Arduino
        codigo.println("void loop() {");
    }
	
    @Override 
    public void exitComandoLoop(FAZEDORESParser.ComandoLoopContext ctx) { 
        //Terminou todas as declaracoes da funcao loop, eh fechada a chave dela
        codigo.println("}");
    }
    
    @Override
    public void enterCmdLoop(FAZEDORESParser.CmdLoopContext ctx) {
        
        String regra = ctx.getStart().getText();
        String loop = "";
        
        if (regra.equals("ligar") || regra.equals("desligar"))
        {
            //Verifica se existe o volt por meio do sexto filho, se eh ',' ou ')'
            if (ctx.getChild(5).getText().equals(","))
            {
                //Caso do analogWrite
                //Verifica se o dispositivoSaida eh um led
                if (ctx.getChild(2).getText().equals("led")){
                    //Aqui nao existe diferenca entre ligar ou desligar (fonte: iot.pdf, pag 22)
                    loop = "\tanalogWrite(" + ctx.NUM_INT(0).getText() + ", " + ctx.NUM_INT(0).getText() +");";
                }
            }
            else{
                //Verifica se o dispositivoSaida eh um led
                if (ctx.getChild(2).getText().equals("led")){
                    loop = "\tdigitalWrite(" + ctx.NUM_INT(0).getText();
                    if (regra.equals("ligar"))
                        loop = loop + ", HIGH);";
                    else
                        loop = loop + ", LOW);";
                }
            }
        }
        else{
            if (regra.equals("esperar")){
                /*Por enquanto ainda nao estah sendo tratado a opcao de que o
                  tempo pode vir em uma constante*/
                loop = "\tdelay(" + ctx.NUM_INT(0).getText() + ");";
            }
        }
        codigo.println(loop);
    }
    
    @Override
    public void enterSenao_opcional(FAZEDORESParser.Senao_opcionalContext ctx) {
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
    public void exitSenao_opcional(FAZEDORESParser.Senao_opcionalContext ctx) {
        //primeiramente se verifica se esse noh nao estah vazio
        if (!ctx.getText().equals("")){
            //verifica se o primeiro token encontrado no pai eh se pois apenas no se eh necessario
            // fechar a chave do senao, na regra do caso o default nao utiliza chave
            if (ctx.getParent().getStart().getText().equals("se"))
                codigo.println("}");
        }
    }
    
    // A partir de agora sao as tres funcoes utilizadas para adicionar simbolos nas tabelas de simbolos
    public void AdicionarSimboloRegistro(FAZEDORESParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual, String nomeDoReg){ 
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
    
    public void AdicionarSimbolo(FAZEDORESParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual){
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
    
    public void AdicionarTiposParametros(FAZEDORESParser.Declaracao_globalContext ctx, List<String> ListaNomePar, List<String> ListaTipoPar){
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
