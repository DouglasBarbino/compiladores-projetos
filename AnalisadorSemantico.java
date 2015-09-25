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
    
    String tipoDoRegistro;
    
    TabelaDeSimbolos tabelaDoRegistro;
    
    String tipoDaExpressao;
    
    public AnalisadorSemantico(Saida out) {
        this.out = out;
        this.tabelaDoRegistro = null;
        this.tipoDaExpressao = "indefinido";
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
        
        if(ctx.variavel() != null) {
            
            
            for (int i=0; i<ctx.variavel().variaveis.size(); i++)  
            {
                nomeVar = ctx.variavel().variaveis.get(i);
                tipoVar = ctx.variavel().tipo_var;
                if(i==0)
                    linha = ctx.variavel().IDENT().getSymbol().getLine();
                else
                    linha = ctx.variavel().mais_var().IDENT(i - 1).getSymbol().getLine();
                
                if(tipoVar == "literal" || tipoVar == "inteiro" || tipoVar == "real" || tipoVar == "logico")
                {
                    if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                    {   tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar, null, null);
                            System.out.println("Var adicionada "+nomeVar+" "+tipoVar+" linha: "+linha);
                    }    
                    else //ERRO 1
                      out.println("Linha "+linha+": identificador " +nomeVar+ " ja declarado anteriormente");
                }
                else
                {
                     if(!pilhaDeTabelas.existeSimbolo(tipoVar))
                     {
                         System.out.println(tabelaDeSimbolosAtual.toString());
                         out.println("Linha "+linha +  ": tipo "+tipoVar+" nao declarado");
                         /*Duvida aqui, pq essa variavel nao podia ser adicionada, mas no teste do professor ela foi */
                         tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar, tipoVar, null, null);
                     }else
                     {
                         if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                        	tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar, null, null);	
                         else //ERRO 1
                           out.println("Linha "+linha+": identificador "+nomeVar+" ja declarado anteriormente");
                     }
                }
                
            }                
        }else
        {
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
            }else{
                 if(ctx.getStart().getText().equals("tipo"))
                 {
                     
                     String novoTipo = ctx.IDENT().getText();
                     System.out.println("NovoTipo " + novoTipo);

                     System.out.println(tabelaDeSimbolosAtual.toString());
                                          
                     if(!pilhaDeTabelas.existeSimbolo(novoTipo)) 
                     {   
                         TabelaDeSimbolos tabelaDoRegistro = new TabelaDeSimbolos("registro"+novoTipo);
                         //tabelaDeSimbolosAtual.adicionarSimbolo(novoTipo, "tipo", null, null);
                     
                         nomeVar = ctx.tipo().registro().variavel().variaveis.get(0);
                         tipoVar = ctx.tipo().registro().variavel().tipo_var;
                      
                         if(tipoVar == "literal" || tipoVar == "inteiro" || tipoVar == "real" || tipoVar == "logico")
                        {
                               if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                                {   tabelaDoRegistro.adicionarSimbolo(nomeVar,tipoVar, null, null);
                                     System.out.println("Var adicionada "+nomeVar+" "+tipoVar+" linha: ");
                                }    
                                else //ERRO 1
                                     out.println("Linha +linha+: identificador " +nomeVar+ " ja declarado anteriormente");
                        }
                        else
                        {
                            if(!pilhaDeTabelas.existeSimbolo(tipoVar))
                            {
                                out.println("Linha +linha +  : tipo "+tipoVar+" nao declarado");
                                /*Duvida aqui, pq essa variavel nao podia ser adicionada, mas no teste do professor ela foi */
                                tabelaDoRegistro.adicionarSimbolo(nomeVar, tipoVar, null, null);
                            }else
                            {
                                if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                                    tabelaDoRegistro.adicionarSimbolo(nomeVar,tipoVar, null, null);	
                                else //ERRO 1
                                    out.println("Linha +linha+: identificador "+nomeVar+" ja declarado anteriormente");
                            }
                        }
                     
                     
                     
                     for (int i=0; i<ctx.tipo().registro().variavel().mais_var().variaveis.size(); i++)  
                    {
                        nomeVar = ctx.tipo().registro().variavel().mais_var().variaveis.get(i);
                        tipoVar = ctx.tipo().registro().variavel().tipo_var;
                
                        if(tipoVar == "literal" || tipoVar == "inteiro" || tipoVar == "real" || tipoVar == "logico")
                        {
                            if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                            {   tabelaDoRegistro.adicionarSimbolo(nomeVar,tipoVar, null, null);
                                System.out.println("Var adicionada "+nomeVar+" "+tipoVar+" linha: ");
                            }    
                            else //ERRO 1
                                out.println("Linha +linha+: identificador " +nomeVar+ " ja declarado anteriormente");
                        }
                        else
                        {
                            if(!pilhaDeTabelas.existeSimbolo(tipoVar))
                            {
                                out.println("Linha +linha +  : tipo "+tipoVar+" nao declarado");
                                /*Duvida aqui, pq essa variavel nao podia ser adicionada, mas no teste do professor ela foi */
                                tabelaDoRegistro.adicionarSimbolo(nomeVar, tipoVar, null, null);
                            }else
                            {
                                if(!pilhaDeTabelas.existeSimbolo(nomeVar))
                                    tabelaDoRegistro.adicionarSimbolo(nomeVar,tipoVar, null, null);	
                                else //ERRO 1
                                    out.println("Linha +linha+: identificador "+nomeVar+" ja declarado anteriormente");
                            }
                        }
                
                    }  
                tabelaDeSimbolosAtual.adicionarSimbolo(novoTipo, "tipo", null, tabelaDoRegistro);
                }
              }
            }
        }
    }
    
    @Override
    public void enterCmd(LAParser.CmdContext ctx)
    {   
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        int linha;
        String nomeVar;
        
                if(ctx.getStart().getText().equals("retorne"))
                {
                    if(!ctx.getParent().getParent().getStart().getText().equals("funcao"))
                    {
                        linha = ctx.expressao().linha.get(0);
                        out.println("Linha "+linha+": comando retorne nao permitido nesse escopo");
                    }
                }
            
        
    }
    
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx)
    {
        String nome = ctx.IDENT().getText();
        
        if (ctx.getStart().getText().equals("procedimento"))
        {
            
            List<String> listaPar = new ArrayList<String>();
            TabelaDeSimbolos tabelaDeSimbolosGlobal = pilhaDeTabelas.topo();
                
            if(!tabelaDeSimbolosGlobal.existeSimbolo(nome))
            {
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento "+nome);
                
                for (int i=0; i<ctx.parametros_opcional().parametros.size(); i++)      
                {
                    String nomePar = ctx.parametros_opcional().parametros.get(i);
                    String tipoPar = ctx.parametros_opcional().tipo_parametros.get(i);
                    
                    System.out.println("Nome par: "+nomePar);
                    
                    if(!tabelaDeSimbolosProcedimento.existeSimbolo(nomePar))
                    {
                        tabelaDeSimbolosProcedimento.adicionarSimbolo(nomePar, tipoPar, null, null);
                        listaPar.add(tipoPar);
                    }
                    else
                    {
                        out.println("identificador "+nomePar+" ja declarado anteriormente");
                    }
                }
                tabelaDeSimbolosGlobal.adicionarSimbolo(nome, "procedimento", listaPar, null);
                   
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  

            }else 
                out.println("identificador "+nome+" ja declarado anteriormente");
        }else{
            if(ctx.getStart().getText().equals("funcao"))
            {
                List<String> listaPar = new ArrayList<String>();
            TabelaDeSimbolos tabelaDeSimbolosGlobal = pilhaDeTabelas.topo();
                
            if(!tabelaDeSimbolosGlobal.existeSimbolo(nome))
            {
                TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao "+nome);
                
                for (int i=0; i<ctx.parametros_opcional().parametros.size(); i++)      
                {
                    String nomePar = ctx.parametros_opcional().parametros.get(i);
                    String tipoPar = ctx.parametros_opcional().tipo_parametros.get(i);
                    
                    System.out.println("Nome par: "+nomePar);
                    
                    if(!tabelaDeSimbolosFuncao.existeSimbolo(nomePar))
                    {
                        tabelaDeSimbolosFuncao.adicionarSimbolo(nomePar, tipoPar, null, null);
                        listaPar.add(tipoPar);
                    }
                    else
                    {
                        out.println("identificador "+nomePar+" ja declarado anteriormente");
                    }
                }
                tabelaDeSimbolosGlobal.adicionarSimbolo(nome, "funcao", listaPar, null);
                   
                pilhaDeTabelas.empilhar(tabelaDeSimbolosFuncao);  

            }else 
                out.println("identificador "+nome+" ja declarado anteriormente");
            }
        }
        
            
    }
    
    
//    @Override
//    public void enterParametros_opcional(LAParser.Parametros_opcionalContext ctx)
//    {
//        
//    };
//    
//    @Override
//    public void enterParcela_unario(LAParser.Parcela_unarioContext ctx)
//    {
//         
//         
//    };

    @Override
    public void exitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        pilhaDeTabelas.desempilhar();
    }
    
    
    
//    @Override
//    public void exitDeclaracoes_locais(LAParser.Declaracoes_locaisContext ctx)
//    {
//        pilhaDeTabelas.desempilhar();
//    }

    private String detectarTipo(LAParser.ExpressaoContext ctx) {
       // String tipo = ctx.nome_par; //descobrir como atribuir lista
        //atribuir nome_par a uma lista
        //percorrer essa lista na tabela de simbolos e recuperar os tipos de cada nome
         String tipo = null;
         TabelaDeSimbolos tabelaDeSimbolosAtual2 = pilhaDeTabelas.topo();
        
        for (String nome : ctx.nome_par)
        {
            if (nome != null)
            {
                
                //ctx.tipo_par.add(tipo);
            }
        }
        return tipo;
    }
    
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
        
            if(!tabelaAtual.existeSimbolo(nome))
            {
                if(tabelaDoRegistro != null)
                {
                    if(!tabelaDoRegistro.existeSimbolo(nome))
                    {
                        out.println("Linha "+linha+": identificador "+nome+" não declarado");
                    }
                }else
                {
                    out.println("Linha "+linha+": identificador "+nome+" não declarado");
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
        tabelaDoRegistro =  pilhaDeTabelas.getSubtabela(tipoDoRegistro);  //verificar isso, pq registro vai estar no global
               
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
        if(ctx.IDENT()!=null)
        {
            String nome = ctx.IDENT().getText();
            System.out.println("Nome parcela_unario "+nome);
            int linha = ctx.IDENT().getSymbol().getLine();
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                out.println("Linha "+linha+": identificador "+nome+" não declarado");
            }
        }
        
        
        
    }
    
    @Override
    public void enterParcela_nao_unario(LAParser.Parcela_nao_unarioContext ctx)
    {
        if(ctx.IDENT()!=null)
        {
            String nome = ctx.IDENT().getText();
            System.out.println("Nome parcela_unario "+nome);
            int linha = ctx.IDENT().getSymbol().getLine();
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                out.println("Linha "+linha+": identificador "+nome+" não declarado");
            }
        }
    }

}

