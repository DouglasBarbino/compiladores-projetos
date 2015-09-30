/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
import trabalho1.LAParser.Declaracao_localContext;

/**
 *
 * @author gabriela
 */
public class AnalisadorSemantico extends LABaseListener {

    Saida out;

    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    List<String> tipoDoReg = new ArrayList<String>();
    String tipoDoRegistro;
    
    TabelaDeSimbolos tabelaDoRegistro;
    
    int EhRegistro;   //pensar numa forma melhor
    
    TabelaDeSimbolos tabelaDoReg;
    
    String tipoDaExpressao;
    String tipoVar;
    String nomeVarPai;
    
    String TipoPar;
    
    int EhFuncao = 0;
    int EhParametro = 0;
    List<String> listaPar;
    int Registro;
    
    int PassouParametro = 0;
    List<String> TipoArg;
    String nomeSubRotina;
    int Linha;
    
    
    
    public AnalisadorSemantico(Saida out) {
        this.out = out;
        this.tabelaDoRegistro = null;
        this.tipoDaExpressao = "indefinido";
        //this.tabelaDoReg = null;
        this.EhRegistro = 1;
        this.Registro = 0;
        this.TipoArg = new ArrayList<String>();
        this.listaPar = new ArrayList<String>();
    }

    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
        
        
    }
    
 
    
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        String nomeVar;
        String tipoVar;
        int linha;
        

            if(ctx.getStart().getText().equals("constante"))
            {
                nomeVar = ctx.IDENT().getText();
                linha = ctx.IDENT().getSymbol().getLine();
                String tipo = ctx.tipo_basico().tipo_var;
                
                
                if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                    {   tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipo, null, null);
                            System.out.println("Var adicionada "+nomeVar+" "+tipo+" linha: "+linha);
                    }    
                    else //ERRO 1
                      out.println("Linha "+linha+": identificador " +nomeVar+ " ja declarado anteriormente");
            
             }else
            {
                if(ctx.getStart().getText().equals("tipo"))
                {
                    nomeVar = ctx.IDENT().getText();
                    linha = ctx.IDENT().getSymbol().getLine();
                    if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                        tipoDoReg.add(nomeVar);
                    else
                        out.println("Linha "+linha+": identificador " +nomeVar+ " ja declarado anteriormente");
                }
            }
            
        
    }
    
    
    @Override
    public void enterRegistro(LAParser.RegistroContext ctx)
    {
       // if(ctx.)
//        String novoTipo = ctx.getParent().getParent().getChild(0).getText();
//        System.out.println("NovoTipo " + novoTipo);
//        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();

//        System.out.println(tabelaDeSimbolosAtual.toString());
                                          
//        if(!pilhaDeTabelas.existeSimbolo(novoTipo)) 
//        {   
//            TabelaDeSimbolos tabelaDoReg = new TabelaDeSimbolos("registro"+novoTipo);
//            //tabelaDeSimbolosAtual.adicionarSimbolo(novoTipo, "tipo", null, null);    
//            tabelaDeSimbolosAtual.adicionarSimbolo(novoTipo, "tipo", null, tabelaDoReg);
//        }
        
           tabelaDoReg = new TabelaDeSimbolos("registro");
           Registro = 1;
        
    }
    
    @Override
    public void exitRegistro(LAParser.RegistroContext ctx)
    {
       TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
       
       if(tabelaDoReg == null)
       {
           System.out.println("Deu errado!");
       }
       for(int i = 0; i < tipoDoReg.size(); i++)
       {
           tabelaDeSimbolosAtual.adicionarSimbolo(tipoDoReg.get(i), tipoDoReg.get(i), null, tabelaDoReg);
       }
       
       tabelaDoReg = null;
       tipoDoReg.clear();
       Registro = 0;
    }
    
    @Override
    public void enterVariavel(LAParser.VariavelContext ctx)
    {
        TabelaDeSimbolos tabelaDeSimbolosAtual = tabelaDoReg;
        if(tabelaDeSimbolosAtual!=null)
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        if(Registro == 0)
        {
            tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        }
        String nome = ctx.IDENT().getText();
        System.out.println("Nome var "+nome);
        int linha = ctx.IDENT().getSymbol().getLine();
        if(ctx.tipo().registro()==null)
        {   EhRegistro = 0;
            
            if(ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico()!=null)
            {
                tipoVar = (String) ctx.tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                System.out.println("TipodaVariavel "+tipoVar);
            }else
            {
                tipoVar = ctx.tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
            }
            
            if(tipoVar.equals("literal") || tipoVar.equals("inteiro") || tipoVar.equals("logico") || tipoVar.equals("real"))
            {
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {   tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipoVar, null, null);
                    System.out.println("Var adicionada "+nome+" "+tipoVar+" linha: "+linha);
                }    
                else //ERRO 1
                      out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            }
            else
            {
                if(!pilhaDeTabelas.existeSimbolo(tipoVar))
                {
                    System.out.println(tabelaDeSimbolosAtual.toString());
                    out.println("Linha "+linha +  ": tipo "+tipoVar+" nao declarado");
                    /*Duvida aqui, pq essa variavel nao podia ser adicionada, mas no teste do professor ela foi */
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipoVar, null, null);
                }else
                {
                    if(!pilhaDeTabelas.existeSimbolo(nome))
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipoVar, null, null);	
                    else //ERRO 1
                        out.println("Linha "+linha+": identificador "+nome+" ja declarado anteriormente");
                }
            }
        }else
        {
            tipoDoReg.add(nome);
        }
    }
    
    @Override
    public void enterCmd(LAParser.CmdContext ctx)
    {   
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        int linha;
        String nomeVar;
        
        if(ctx.IDENT()!=null)
        {
            nomeVar = ctx.IDENT().getText();
            nomeSubRotina = nomeVar;
            linha = ctx.IDENT().getSymbol().getLine();
            Linha = linha;
            System.out.println("Nome Cmd: "+nomeVar+" linha "+linha);
        
            if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                out.println("Linha "+linha+": identificador "+nomeVar+" nao declarado");
        }
        
        
        
                if(ctx.getStart().getText().equals("retorne"))
                {
                   // if(!ctx.getParent().getParent().getStart().getText().equals("funcao"))
                   // {
                        if(EhFuncao == 0)
                        {    linha = ctx.getStop().getLine();
                            out.println("Linha "+linha+": comando retorne nao permitido nesse escopo");
                        }
                   // }
                }
            
        
    }
    
//    @Override
//    public void exitRegistro( LAParser.RegistroContext ctx)
//    {
//        pilhaDeTabelas.desempilhar();
//    }
    
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx)
    {
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        if (ctx.getStart().getText().equals("procedimento"))
        {
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, "procedimento", null, null);
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento "+ nome);
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  
            }else
            {
                out.println("identificador "+nome+" ja declarado anteriormente");
            }
                
            
//            List<String> listaPar = new ArrayList<String>();
//            TabelaDeSimbolos tabelaDeSimbolosGlobal = pilhaDeTabelas.topo();
//                
//            if(!tabelaDeSimbolosGlobal.existeSimbolo(nome))
//            {
//                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento "+nome);
//                
//                for (int i=0; i<ctx.parametros_opcional().parametros.size(); i++)      
//                {
//                    String nomePar = ctx.parametros_opcional().parametros.get(i);
//                    String tipoPar = ctx.parametros_opcional().tipo_parametros.get(i);
//                    
//                    System.out.println("Nome par: "+nomePar);
//                    
//                    if(!tabelaDeSimbolosProcedimento.existeSimbolo(nomePar))
//                    {
//                        tabelaDeSimbolosProcedimento.adicionarSimbolo(nomePar, tipoPar, null, null);
//                        listaPar.add(tipoPar);
//                    }
//                    else
//                    {
//                        out.println("identificador "+nomePar+" ja declarado anteriormente");
//                    }
//                }
//                tabelaDeSimbolosGlobal.adicionarSimbolo(nome, "procedimento", listaPar, null);
//                   
//                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  
//
//            }else 
//                out.println("identificador "+nome+" ja declarado anteriormente");
        }else{
            if(ctx.getStart().getText().equals("funcao"))
            {
                EhFuncao = 1;
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome, "funcao", null, null);
                    TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao "+ nome);
                    pilhaDeTabelas.empilhar(tabelaDeSimbolosFuncao);  
                 }else
                 {
                    out.println("identificador "+nome+" ja declarado anteriormente");
                 }
                
//                List<String> listaPar = new ArrayList<String>();
//            TabelaDeSimbolos tabelaDeSimbolosGlobal = pilhaDeTabelas.topo();
//                
//            if(!tabelaDeSimbolosGlobal.existeSimbolo(nome))
//            {
//                TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao "+nome);
//                
//                for (int i=0; i<ctx.parametros_opcional().parametros.size(); i++)      
//                {
//                    String nomePar = ctx.parametros_opcional().parametros.get(i);
//                    String tipoPar = ctx.parametros_opcional().tipo_parametros.get(i);
//                    
//                    System.out.println("Nome par: "+nomePar);
//                    
//                    if(!tabelaDeSimbolosFuncao.existeSimbolo(nomePar))
//                    {
//                        tabelaDeSimbolosFuncao.adicionarSimbolo(nomePar, tipoPar, null, null);
//                        listaPar.add(tipoPar);
//                    }
//                    else
//                    {
//                        out.println("identificador "+nomePar+" ja declarado anteriormente");
//                    }
//                }
//                tabelaDeSimbolosGlobal.adicionarSimbolo(nome, "funcao", listaPar, null);
//                   
//                pilhaDeTabelas.empilhar(tabelaDeSimbolosFuncao);  
//
//            }else 
//                out.println("identificador "+nome+" ja declarado anteriormente");
            }
        }
        
            
    }
    
    @Override
    public void enterMais_var(LAParser.Mais_varContext ctx)
    {
        TabelaDeSimbolos tabelaDeSimbolosAtual = tabelaDoReg;
        if(Registro == 0)
        {
            tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        }
        for(int i=0; i< ctx.IDENT().size(); i++)
        {
            
            String nome = ctx.IDENT(i).getText();
            System.out.println("Nome var "+nome);
            int linha = ctx.IDENT(i).getSymbol().getLine();
 
         if(EhRegistro == 0)   
         {   
             if(tipoVar.equals("literal") || tipoVar.equals("inteiro") || tipoVar.equals("logico") || tipoVar.equals("real"))
            {
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {   tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipoVar, null, null);
                    System.out.println("Var adicionada "+nome+" "+tipoVar+" linha: "+linha);
                }    
                else //ERRO 1
                      out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            }
            else
            {
                if(!pilhaDeTabelas.existeSimbolo(tipoVar))
                {
                    System.out.println(tabelaDeSimbolosAtual.toString());
                    out.println("Linha "+linha +  ": tipo "+tipoVar+" nao declarado");
                    /*Duvida aqui, pq essa variavel nao podia ser adicionada, mas no teste do professor ela foi */
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipoVar, null, null);
                }else
                {
                    if(!pilhaDeTabelas.existeSimbolo(nome))
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipoVar, null, null);	
                    else //ERRO 1
                        out.println("Linha "+linha+": identificador "+nome+" ja declarado anteriormente");
                }
            }
        }else
         {
             tipoDoReg.add(nome);
         }
         
        }  
    }
    
    
    @Override
    public void enterParametros_opcional(LAParser.Parametros_opcionalContext ctx)
    {
       // if(ctx.parametro()!=null)
       // {
            EhParametro = 1;
       // }
        
    };
    
    @Override
    public void enterParametro(LAParser.ParametroContext ctx)
    {
       if(ctx.tipo_estendido().tipo_basico_ident().tipo_basico()!=null) 
           TipoPar = ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText();
       else
           TipoPar = ctx.tipo_estendido().tipo_basico_ident().IDENT().getText();
    };
    
    @Override
    public void exitParametros_opcional(LAParser.Parametros_opcionalContext ctx)
    {
        
            EhParametro = 0;
            TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
            tabelaDeSimbolosAtual.adicionarSimbolo("listaDeParametros", null, listaPar, null);
            
            listaPar.clear();
        
    };


    @Override
    public void exitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        pilhaDeTabelas.desempilhar();
        EhFuncao = 0;
    }
    
    @Override
    public void enterChamada_atribuicao(LAParser.Chamada_atribuicaoContext ctx)
    {
        if(ctx.getStart().getText().equals("("))
            PassouParametro = 1;
        
    }
    
    @Override
    public void exitChamada_atribuicao(LAParser.Chamada_atribuicaoContext ctx)
    {
        
        if(PassouParametro == 1)
        {
            List<String> EspecificacaoParametros = new ArrayList<String>();
        
            EspecificacaoParametros = pilhaDeTabelas.getListaPar(nomeSubRotina);
        
            if(EspecificacaoParametros.size() != TipoArg.size())
            {
                out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
            }else
            {
                for(int i=0; i< EspecificacaoParametros.size(); i++)
                {
                    String tipo1 = EspecificacaoParametros.get(i);
                    String tipo2 = TipoArg.get(i);
                
                    if(!tipo1.equals(tipo2))
                    {
                        out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
                    }
                }
            }
        
             //Linha 44: incompatibilidade de parametros na chamada
        
            TipoArg.clear();
            EspecificacaoParametros.clear();
        }
        
            PassouParametro = 0;
        
        
    }
    
    @Override
    public void enterChamada_partes(LAParser.Chamada_partesContext ctx)
    {
        if(ctx.getStart().getText().equals("("))
            PassouParametro = 1;
    }
    
    @Override
    public void exitChamada_partes(LAParser.Chamada_partesContext ctx)
    {
        if(PassouParametro == 1)
        {
            List<String> EspecificacaoParametros = new ArrayList<String>();
        
            EspecificacaoParametros = pilhaDeTabelas.getListaPar(nomeSubRotina);
        
            if(EspecificacaoParametros.size() != TipoArg.size())
            {
                 out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
            }else
            {
                for(int i=0; i< EspecificacaoParametros.size(); i++)
                 {
                     String tipo1 = EspecificacaoParametros.get(i);
                     String tipo2 = TipoArg.get(i);
                
                    if(!tipo1.equals(tipo2))
                    {
                       out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
                    }
                }
            }
        
        //Linha 44: incompatibilidade de parametros na chamada
        
            TipoArg.clear();
            EspecificacaoParametros.clear();
        }
        PassouParametro = 0;
        
    }
    
    
    
    
//    @Override
//    public void exitDeclaracoes_locais(LAParser.Declaracoes_locaisContext ctx)
//    {
//        pilhaDeTabelas.desempilhar();
//    }

//    private String detectarTipo(LAParser.ExpressaoContext ctx) {
//         String tipo = null;
//         TabelaDeSimbolos tabelaDeSimbolosAtual2 = pilhaDeTabelas.topo();
//        
//        for (String nome : ctx.nome_par)
//        {
//            if (nome != null)
//            {
//                
//                //ctx.tipo_par.add(tipo);
//            }
//        }
//        return tipo;
//    }
    
    @Override
    public void enterExpressao(LAParser.ExpressaoContext ctx)
    {
        VerificadorDeTipos verificador = new VerificadorDeTipos();
        String tipo = verificador.verificaTipo(ctx);
        
        
            
    };
    
    @Override
    public void enterIdentificador(LAParser.IdentificadorContext ctx)
    {
        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
        if(tabelaAtual == null)
            System.out.println("NULOOOOOOO!!!");
        
        String nome = ctx.IDENT().getText();
        
        
        System.out.println("Nome do identificador: "+nome);
        String tipo  = tabelaAtual.getTipo(nome);
                if(tipo !=null)
                {
                    tipoDoRegistro = tipo;
                }    
                System.out.println("Tipo "+tipo);
        
        int linha;

        linha = ctx.IDENT().getSymbol().getLine();
        
        if(EhParametro == 0)
        {    if(!tabelaAtual.existeSimbolo(nome))
            {
                if(tabelaDoRegistro != null)
                {
                    if(!tabelaDoRegistro.existeSimbolo(nome))
                    {
                        out.println("Linha "+linha+": identificador "+nomeVarPai+"."+nome+" nao declarado");
                    }
                }else
                {   
                    out.println("Linha "+linha+": identificador "+nome+" nao declarado");
                }
                   
            }
            nomeVarPai = nome;
            
        }else{
            if(!tabelaAtual.existeSimbolo(nome))
                    {
                        tipo = TipoPar;
                        tipoDoRegistro = TipoPar;
                        System.out.println("Tipo do parametro "+tipo);
                        tabelaAtual.adicionarSimbolo(nome, tipo, null, null);
                        listaPar.add(tipo);
                    }
                    else
                    {
                        out.println("identificador "+nome+" ja declarado anteriormente");
                    }
        }    
        
//        else
//        {
//            if(ctx.outros_ident().identificador() != null)
//            {
//               TabelaDeSimbolos tabelaDoReg =  pilhaDeTabelas.getSubtabela(tipo);  
//               
//               if(tabelaDoReg == null)
//               {
//                   System.out.println("NULOOOO");
//               }
//               
//               linha = ctx.outros_ident().identificador().IDENT().getSymbol().getLine();//ctx.outros_ident().identificador().IDENT().getSymbol().getLine();
//               System.out.println("LINHA outrosIdent "+linha);
//               
//               //nome = ctx.outros_ident().id_outros_id;   //ctx.outros_ident().identificador().IDENT().getText();
//               System.out.println("nome outrosIdent "+nome);
//               
//               if(!tabelaDoReg.existeSimbolo(nome))
//               {
//                   out.println("Linha "+linha+": identificador "+nome+" não declarado");
//               }
//               
//            }
//        }
            
//            for(int i=0; i < ctx.mais_ident().array_id.size(); i++)
//            {
//                
//                nomeVar = ctx.mais_ident().array_id.get(i);
//                System.out.println("Parametro passado, nome: "+nomeVar);
//                linha = ctx.identificador().IDENT().getSymbol().getLine();
//                if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
//                {
//                    out.println("Linha "+linha+": identificador "+nomeVar+" não declarado");
//                }
//            }    
        
        
    }
    
    @Override
    public void enterOutros_ident(LAParser.Outros_identContext ctx)
    {
        
        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
        String nome; // = ctx.getParent().
        //System.out.println("Nome outrosIdent " +nome);
        //String tipo  = tabelaAtual.getTipo(nome);
        System.out.println("Tipo "+tipoDoRegistro);
        int linha;
        tabelaDoRegistro =  pilhaDeTabelas.getSubtabela(tipoDoRegistro); 
               
        if(tabelaDoRegistro == null)
        {
            System.out.println("NULOOOO");
        }
               
//        linha = ctx.identificador().IDENT().getSymbol().getLine();
//        nome = ctx.identificador().getText();
//        System.out.println("Nome outrosIdent 2 " + nome);
//              
//               
//        if(!tabelaDoReg.existeSimbolo(nome))
//        {
//            out.println("Linha "+linha+": identificador "+nome+" não declarado");
//        }
    }
    
//    @Override
//    public void enterMais_ident(LAParser.Mais_identContext ctx)
//    {
//        TabelaDeSimbolos tabelaAtual = pilhaDeTabelas.topo();
//        String nome; // = ctx.identificador().get(1).IDENT().getText();
//        String tipo; //  = tabelaAtual.getTipo(nome);
//        int linha;
//        
//        for(int i =0; i < ctx.identificador().size(); i++)
//        {
//            nome = ctx.identificador().get(i).IDENT().getText();
//            tipo  = tabelaAtual.getTipo(nome);
//            linha = ctx.identificador().get(i).IDENT().getSymbol().getLine();
//            if(!tabelaAtual.existeSimbolo(nome))
//            {
//                out.println("Linha "+linha+": identificador "+nome+" não declarado");
//            }
//            if(ctx.identificador().get(i).outros_ident().identificador() != null)
//            {
//               TabelaDeSimbolos tabelaDoReg =  pilhaDeTabelas.getSubtabela(tipo);   //verificar isso, pq registro vai estar no global
//               
//               nome = ctx.identificador().get(i).outros_ident().identificador().IDENT().getText();
//               linha = ctx.identificador().get(i).outros_ident().identificador().IDENT().getSymbol().getLine();
//               
//               if(!tabelaDoReg.existeSimbolo(nome))
//               {
//                   out.println("Linha "+linha+": identificador "+nome+" não declarado");
//               }
//               
//            }
//        }
//        
//    }
    
//    @Override
//    public void enterArgumentos_opcional(LAParser.Argumentos_opcionalContext ctx)
//    {
//        //percorrer a lista de nomes 
//    }
    
    @Override
    public void enterParcela_unario(LAParser.Parcela_unarioContext ctx)
    {
        String tipo = null;
        VerificadorDeTipos verificador = new VerificadorDeTipos();
        if(ctx.IDENT()!=null)
        {
            String nome = ctx.IDENT().getText();
            nomeSubRotina = nome;
            System.out.println("Nome parcela_unario "+nome);
            int linha = ctx.IDENT().getSymbol().getLine();
            Linha = linha;
            System.out.println("Nome parcela_unario "+nome+" linha"+linha);
            tipo = pilhaDeTabelas.getTipo(nome);
            System.out.println("Tipo recuperado "+tipo);
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                if(ctx.chamada_partes()!=null)
                {
                    if (ctx.chamada_partes().outros_ident().identificador() != null)
                    {   nomeVarPai = nome;
                        nome = nome + '.' + ctx.chamada_partes().outros_ident().identificador().IDENT().getText();
                    }
                }
                
                out.println("Linha "+linha+": identificador "+nome+" nao declarado");
            }
        }
        
        if(PassouParametro == 1)
        {
            if(ctx.NUM_INT()!=null)
            {
                tipo = "inteiro";
            }else{
                if(ctx.NUM_REAL()!=null)
                {
                    tipo = "real";
                }else
                {
                    if(ctx.expressao()!=null)
                    {
                        tipo = verificador.verificaTipo(ctx.expressao());
                    }
                }
            }
            
            TipoArg.add(tipo);
        }
        
        
        
    }
    
    @Override
    public void enterParcela_nao_unario(LAParser.Parcela_nao_unarioContext ctx)
    {
        String tipo = null;
        if(ctx.IDENT()!=null)
        {
            String nome = ctx.IDENT().getText();
            System.out.println("Nome parcela_unario "+nome);
            int linha = ctx.IDENT().getSymbol().getLine();
            tipo = pilhaDeTabelas.getTipo(nome);
            System.out.println("Tipo recuperado "+tipo);
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                if (ctx.outros_ident().identificador()!= null)
                {   
                    nomeVarPai = nome;
                    nome = nome + '.' + ctx.outros_ident().identificador().IDENT().getText();
                }
                
                out.println("Linha "+linha+": identificador "+nome+" nao declarado");
            }
        }
        
        if(PassouParametro == 1)
        {
            if(ctx.CADEIA()!=null)
            {
                tipo = "literal";
            }
            TipoArg.add(tipo);
        }
    }

}

