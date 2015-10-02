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
    int EhFuncao = 0;

    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    
    
    
    
    public AnalisadorSemantico(Saida out) {
        this.out = out;
    }

    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
        
        
    }
    
 
    
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        String nome;
        String tipo;
        int linha;
        

        if(ctx.getStart().getText().equals("constante"))
        {
            nome = ctx.IDENT().getText();
            linha = ctx.IDENT().getSymbol().getLine();
            tipo = ctx.tipo_basico().getText();
                
                
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {   tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipo, null, null);
                            System.out.println("Var adicionada "+nome+" "+tipo+" linha: "+linha);
            }    
            else //ERRO 1
                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            
        }else
        {
            if(ctx.getStart().getText().equals("tipo"))
            {
                String nomeDoReg = ctx.IDENT().getText();
                int linhaDoReg = ctx.IDENT().getSymbol().getLine();
                if(!tabelaDeSimbolosAtual.existeSimbolo(nomeDoReg))
                {
                    AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nomeDoReg, linhaDoReg);
                   
                }else
                    out.println("Linha "+linhaDoReg+": identificador " +nomeDoReg+ " ja declarado anteriormente");
            }   
                else
                {
                    if(ctx.getStart().getText().equals("declare"))
                    {
                       
                        AdicionarSimbolo(ctx, tabelaDeSimbolosAtual);
                        
                    }
                }
            }
            
        
    }    
    
        
    public void AdicionarSimboloRegistro(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual, String nomeDoReg, int LinhaDoReg)
    { 
        String nome;
        String tipo;
        int linha;
        
        TabelaDeSimbolos tabelaDoRegistro = new TabelaDeSimbolos("registro");
                   //List<String> nomes = new ArrayList<>();
            if(ctx.tipo()!=null)       
            {      nome = ctx.tipo().registro().variavel().IDENT().getText();
                   //nomes.add(nome);
                   linha = ctx.tipo().registro().variavel().IDENT().getSymbol().getLine();
                   if(ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                   {
                       tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                   }else
                   {
                       tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   }
                   
                   if(!tabelaDoRegistro.existeSimbolo(nome))
                   {
                       tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   }else
                   {
                       out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                   }
                   
                   for(int i = 0; i<ctx.tipo().registro().variavel().mais_var().IDENT().size(); i++)
                   {
                       nome = ctx.tipo().registro().variavel().mais_var().IDENT(i).getText();
                       if(!tabelaDoRegistro.existeSimbolo(nome))
                       {
                           tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                       }
                   }
                   
                   for(int i = 0; i < ctx.tipo().registro().mais_variaveis().variavel().size(); i++) 
                   {
                        nome = ctx.tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
                        //nomes.add(nome);
                        linha = ctx.tipo().registro().mais_variaveis().variavel(i).IDENT().getSymbol().getLine();
                        if(ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                        {
                            tipo = ctx.tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                        }else
                        {
                            tipo = ctx.tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                        }
                   
                        if(!tabelaDoRegistro.existeSimbolo(nome))
                        {
                            tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                        }else
                        {
                            out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                        }
                   
                        for(int j = 0; j<ctx.tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT().size(); j++)
                        {
                            nome = ctx.tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT(i).getText();
                            if(!tabelaDoRegistro.existeSimbolo(nome))
                            {
                                tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                            }
                        }
                   }
            }else{
                if(ctx.variavel().tipo()!=null)
                {
                   nome = ctx.variavel().tipo().registro().variavel().IDENT().getText();
                   //nomes.add(nome);
                   linha = ctx.variavel().tipo().registro().variavel().IDENT().getSymbol().getLine();
                   if(ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                   {
                       tipo = ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                   }else
                   {
                       tipo = ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   }
                   
                   if(!tabelaDoRegistro.existeSimbolo(nome))
                   {
                       tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   }else
                   {
                       out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                   }
                   
                   for(int i = 0; i<ctx.variavel().tipo().registro().variavel().mais_var().IDENT().size(); i++)
                   {
                       nome = ctx.variavel().tipo().registro().variavel().mais_var().IDENT(i).getText();
                       if(!tabelaDoRegistro.existeSimbolo(nome))
                       {
                           tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                       }
                   }
                   
                   for(int i = 0; i < ctx.variavel().tipo().registro().mais_variaveis().variavel().size(); i++) 
                   {
                        nome = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
                        //nomes.add(nome);
                        linha = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).IDENT().getSymbol().getLine();
                        if(ctx.variavel().tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                        {
                            tipo = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                        }else
                        {
                            tipo = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                        }
                   
                        if(!tabelaDoRegistro.existeSimbolo(nome))
                        {
                            tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                        }else
                        {
                            out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                        }
                   
                        for(int j = 0; j<ctx.variavel().tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT().size(); j++)
                        {
                            nome = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).mais_var().IDENT(i).getText();
                            if(!tabelaDoRegistro.existeSimbolo(nome))
                            {
                                tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                            }
                        }
                }
            } 
        }        
            tabelaDeSimbolosAtual.adicionarSimbolo(nomeDoReg, nomeDoReg, null, tabelaDoRegistro);
                
    }
    
    
    
    public void AdicionarSimbolo(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual)
    {
        String nome;
        String tipo;
        int linha;
        
            if(ctx.variavel().tipo().registro()==null)       
            {      nome = ctx.variavel().IDENT().getText();
                   System.out.println("Simbolo sendo adicionado: "+nome);
                   //nomes.add(nome);
                   linha = ctx.variavel().IDENT().getSymbol().getLine();
                   if(ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                   {
                       tipo = ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                       System.out.println("Tipo da variavel: "+tipo);
                       if(!pilhaDeTabelas.existeSimbolo(tipo))
                       {
                           out.println("Linha "+linha+ ": tipo "+tipo+" nao declarado");
                       }
                   }else
                   {
                       tipo = ctx.variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                       System.out.println("Tipo da variavel: "+tipo);
                   }
                   
                   if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                   {
                       tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipo, null, null);
                   }else
                   {
                       out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                   }
                   
                   for(int i = 0; i<ctx.variavel().mais_var().IDENT().size(); i++)
                   {
                       nome = ctx.variavel().mais_var().IDENT(i).getText();
                       linha = ctx.variavel().mais_var().IDENT(i).getSymbol().getLine();
                       if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                       {
                           tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipo, null, null);
                       }else
                       {
                           out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                       }
                   }
            }else{
                
                nome = ctx.variavel().IDENT().getText();
                linha = ctx.variavel().IDENT().getSymbol().getLine();
                
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {
                    AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nome, linha);
                }
                
                for(int i = 0; i<ctx.variavel().mais_var().IDENT().size(); i++)
                   {
                       nome = ctx.variavel().mais_var().IDENT(i).getText();
                       if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                       {
                           AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nome, linha);
                       }else
                       {
                           out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                       }
                   }
            }       
    }

    
     @Override
    public void enterCmd(LAParser.CmdContext ctx)
    {   
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        int linha;
        String nome;
        String tipo;
        
        if(ctx.getStart().getText().equals("leia"))
        {
            VerificaSimbolo(ctx);
        }else
        {
            if(ctx.expressao()!=null && ctx.IDENT()!=null)
            {
                nome = ctx.IDENT().getText();
                tipo = pilhaDeTabelas.getTipo(nome);
                nome = "^" + nome;
                linha = ctx.IDENT().getSymbol().getLine();
                
                if(ctx.outros_ident().identificador()!=null)
                {
                    //pilhaDeTabelas.getTipo(nome);
                    TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipo);
                    String Outronome = ctx.outros_ident().identificador().IDENT().getText();
                    tipo = tabelaDoRegistro.getTipo(Outronome);
                    nome = nome + "." + Outronome;
                    
                }
                
                VerificaAtribuicao(ctx.expressao(), nome, linha, tipo);
                
                
            }else
            {
              if(ctx.chamada_atribuicao()!=null)  
              {  if(ctx.chamada_atribuicao().expressao()!=null)
                {
                    nome = ctx.IDENT().getText();
                    tipo = pilhaDeTabelas.getTipo(nome);
                    if(tipo == null)
                    {
                        tipo = "tipo_indefinido";
                    }
                    linha = ctx.IDENT().getSymbol().getLine();
                    
                    if(!pilhaDeTabelas.existeSimbolo(nome))
                    {
                        //Linha 19: identificador media_final nao declarado
                        out.println("Linha "+linha+": identificador "+nome+" nao declarado");
                    }
                
                    if(ctx.chamada_atribuicao().outros_ident().identificador()!=null)
                    {
                        //pilhaDeTabelas.getTipo(nome);
                        TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipo);
                        String Outronome = ctx.chamada_atribuicao().outros_ident().identificador().IDENT().getText();
                        tipo = tabelaDoRegistro.getTipo(Outronome);
                        nome = nome + "." + Outronome;
                    
                    }
                    
//                    String exp;
//                    if(ctx.dimensao()!=null)
//                    {
//                        if(ctx.dimensao().exp_aritmetica() != null)
//                        {   exp = ctx.dimensao().exp_aritmetica(0).termo().fator().parcela().parcela_unario().NUM_INT().getText();
//                            nome = nome +"["+exp+"]";
//                        }
//                    }else{
//                        if(ctx.chamada_atribuicao()!=null)
//                        {
//                            if(ctx.chamada_atribuicao().dimensao()!=null)
//                            {
//                                if(ctx.chamada_atribuicao().dimensao().exp_aritmetica()!=null)
//                                {
//                                    if(ctx.dimensao().exp_aritmetica() != null)
//                                    {   exp = ctx.chamada_atribuicao().dimensao().exp_aritmetica(0).termo().fator().parcela().parcela_unario().NUM_INT().getText();
//                                        nome = nome +"["+exp+"]";
//                                    }
//                                }
//                            }
//                        }
//                    }    
                
                    VerificaAtribuicao(ctx.chamada_atribuicao().expressao(), nome, linha, tipo);
                }
            }else
              {
                  if(ctx.getStart().getText().equals("retorne"))
                  {
                      linha = ctx.getStop().getLine();
                      if(EhFuncao == 0)
                      {
                          out.println("Linha "+linha+": comando retorne nao permitido nesse escopo");
                      }
                      
                  }
              }
          }    
        }
        
        
        
    }
    
    public void VerificaAtribuicao(LAParser.ExpressaoContext ctx, String nome, int linha, String Tipo)
    {
        VerificadorDeTipos verificador = new VerificadorDeTipos();
        
        String TipoAtribuido = verificador.verificaTipo(ctx);
        
        System.out.println("Tipo expressao : "+Tipo);
        System.out.println("Tipo atribuicao : "+TipoAtribuido);
        System.out.println("Linha "+linha);
        
        if(!Tipo.equals(TipoAtribuido))
        {
            if(!Tipo.equals("tipo_indefinido"))
            {
                if(Tipo.equals("literal") || TipoAtribuido.equals("literal") || TipoAtribuido.equals("tipo_invalido"))
                {
                    //Linha 11: atribuicao nao compativel para valor[0]
                    out.println("Linha "+linha+": atribuicao nao compativel para "+nome);
                }
            }
            
        }
        
    }
    
    
    public void VerificaSimbolo(LAParser.CmdContext ctx)
    {
        String nome = ctx.identificador().IDENT().getText();
        int linha = ctx.identificador().IDENT().getSymbol().getLine();
        String tipo = pilhaDeTabelas.getTipo(nome);
        String nomeOutrosIdent = null;
        
        if(ctx.outros_ident()!=null)
        {
            if(ctx.outros_ident().identificador().IDENT()!=null)
            {nomeOutrosIdent = ctx.outros_ident().identificador().IDENT().getText();
            TabelaDeSimbolos tabReg = pilhaDeTabelas.getSubtabela(tipo);
            if(!tabReg.existeSimbolo(nomeOutrosIdent))
            {
                out.println("Linha "+linha+": identificador "+nome+"."+nomeOutrosIdent+" nao declarado");  
          }
            }
        }
        
        if(!pilhaDeTabelas.existeSimbolo(nome))
        {
            if(nomeOutrosIdent!=null)
            {
                nome = nome + "." + nomeOutrosIdent;
                nomeOutrosIdent = null;
            }
            out.println("Linha "+linha+": identificador "+nome+" nao declarado");            
        }
        
        for(int i = 0; i < ctx.mais_ident().identificador().size(); i++)
        {
            nome = ctx.mais_ident().identificador(i).IDENT().getText();
            linha = ctx.mais_ident().identificador(i).IDENT().getSymbol().getLine();
            if(ctx.mais_ident().identificador(i).outros_ident()!=null)
            {
                if(ctx.mais_ident().identificador(i).outros_ident().identificador()!=null)
                {nomeOutrosIdent = ctx.mais_ident().identificador(i).outros_ident().identificador().IDENT().getText();
                TabelaDeSimbolos tabReg = pilhaDeTabelas.getSubtabela(tipo);
                if(!tabReg.existeSimbolo(nomeOutrosIdent))
                {
                    out.println("Linha "+linha+": identificador "+nome+"."+nomeOutrosIdent+" nao declarado");  
                }
                }
            }
        
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                if(nomeOutrosIdent!=null)
                {
                    nome = nome + "." + nomeOutrosIdent;
                    nomeOutrosIdent = null;
                }
                out.println("Linha "+linha+": identificador "+nome+" nao declarado");            
            }
            
        }
        
    }
    
    
    @Override
    public void enterChamada_atribuicao(LAParser.Chamada_atribuicaoContext ctx)
    {
        String nome;
        String tipo;
        int linha;
        
        if(ctx.getStart().getText().equals("("))
        {
            
        }
    }
    
    
    
        
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx)
    {
        String nome = ctx.IDENT().getText();
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        List<String> listaNomePar = new ArrayList<>();
        List<String> listaTipoPar = new ArrayList<>();
        if (ctx.getStart().getText().equals("procedimento"))
        {
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                if(ctx.parametros_opcional()!=null)
                {   
                    AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                }
                
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, null, listaTipoPar, null);
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento"+nome);
                for(int i = 0; i < listaNomePar.size(); i++)
                {
                    if(!tabelaDeSimbolosProcedimento.existeSimbolo(listaNomePar.get(i)))
                    {
                        tabelaDeSimbolosProcedimento.adicionarSimbolo(listaNomePar.get(i), listaTipoPar.get(i), null, null);
                    }
                }
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  
                
            }else
            {
                out.println("identificador "+nome+" ja declarado anteriormente");
            }
        }else{
            if(ctx.getStart().getText().equals("funcao"))
            {   EhFuncao = 1;
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {
                    if(ctx.parametros_opcional()!=null)
                    {   
                        AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                    }
                    String TipoFuncao;
                    if(ctx.tipo_estendido().tipo_basico_ident().IDENT()!=null)
                        TipoFuncao = ctx.tipo_estendido().tipo_basico_ident().IDENT().getText();
                    else
                        TipoFuncao = ctx.tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                    
                    tabelaDeSimbolosAtual.adicionarSimbolo(nome, TipoFuncao, listaTipoPar, null);
                    TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao"+nome);
                    
                    for(int i = 0; i < listaNomePar.size(); i++)
                    {
                        if(!tabelaDeSimbolosFuncao.existeSimbolo(listaNomePar.get(i)))
                        {
                            tabelaDeSimbolosFuncao.adicionarSimbolo(listaNomePar.get(i), listaTipoPar.get(i), null, null);
                        }
                    }
                    pilhaDeTabelas.empilhar(tabelaDeSimbolosFuncao);  
                
                }else
                {
                    out.println("identificador "+nome+" ja declarado anteriormente");
                }
            }
        }
        
            
    }
    
    @Override
    public void exitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        pilhaDeTabelas.desempilhar();
        EhFuncao = 0;
    }
    
    
    @Override
    public void enterChamada_partes(LAParser.Chamada_partesContext ctx)
    {
        if(ctx.getStart().getText().equals("("))
        {
            List<String> ParametrosFormais = new ArrayList<>();
            String nomeSubRotina = ctx.getParent().getStart().getText();
            int Linha = ctx.getParent().getStart().getLine();
            System.out.println("Linha "+Linha+" Subrotina "+nomeSubRotina);
            System.out.println("Nome da subrotina no chamada Partes: "+ nomeSubRotina);
            ParametrosFormais = pilhaDeTabelas.getListaPar(nomeSubRotina);
            
            List<String> ParametrosReais = new ArrayList<>();
            ParametrosReais.add(VerificadorDeTipos.verificaTipo(ctx.expressao()));
            for(LAParser.ExpressaoContext eCtx : ctx.mais_expressao().expressao()) {
                ParametrosReais.add(VerificadorDeTipos.verificaTipo(eCtx));
            }
            
            if(ParametrosFormais.size() != ParametrosReais.size())
            {
                 out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
            }else
            {
                for(int i=0; i< ParametrosReais.size(); i++)
                 {
                     String tipo1 = ParametrosReais.get(i);
                     String tipo2 = ParametrosFormais.get(i);
                
                    if(!tipo1.equals(tipo2))
                    {
                       out.println("Linha "+Linha+": incompatibilidade de parametros na chamada de "+ nomeSubRotina);
                    }
                }
            }
        }
    }
    
    public void AdicionarTiposParametros(LAParser.Declaracao_globalContext ctx, List<String> ListaNomePar, List<String> ListaTipoPar)
    {
        String nome;
        String tipo;
        int linha;
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        nome = ctx.parametros_opcional().parametro().identificador().IDENT().getText();
        linha = ctx.parametros_opcional().parametro().identificador().IDENT().getSymbol().getLine();
        if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
        {
            ListaNomePar.add(nome);
        }else{
            
            out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
        }
        
        if(ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT()!=null)
        {
            tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT().getText();
        }else
        {
            tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
        }
        
        ListaTipoPar.add(tipo);
        
        for(int i = 0; i<ctx.parametros_opcional().parametro().mais_ident().identificador().size(); i++)
        {
            nome = ctx.parametros_opcional().parametro().mais_ident().identificador(i).IDENT().getText();
            linha = ctx.parametros_opcional().parametro().mais_ident().identificador(i).IDENT().getSymbol().getLine();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
            {
                ListaNomePar.add(nome);
            }else{
            
                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            }
            ListaTipoPar.add(tipo);
        }
        
        if(ctx.parametros_opcional().parametro().mais_parametros().parametro()!=null)
        {
            nome = ctx.parametros_opcional().parametro().mais_parametros().parametro().identificador().IDENT().getText();
            linha = ctx.parametros_opcional().parametro().mais_parametros().parametro().identificador().IDENT().getSymbol().getLine();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
            {
                ListaNomePar.add(nome);
            }else{
            
                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            }
        
            if(ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT()!=null)
            {
                tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().IDENT().getText();
            }else
            {
                tipo = ctx.parametros_opcional().parametro().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
            }
        
            ListaTipoPar.add(tipo);
        
        for(int i = 0; i < ctx.parametros_opcional().parametro().mais_parametros().parametro().mais_ident().identificador().size(); i++)
        {
            nome = ctx.parametros_opcional().parametro().mais_parametros().parametro().mais_ident().identificador(i).IDENT().getText();
            linha = ctx.parametros_opcional().parametro().mais_parametros().parametro().mais_ident().identificador(i).IDENT().getSymbol().getLine();
            if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
            {
                ListaNomePar.add(nome);
            }else{
            
                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            }
            ListaTipoPar.add(tipo);
        }
            
            
    
        }
      }
    
    
     @Override
    public void enterParcela_unario(LAParser.Parcela_unarioContext ctx)
    {
        String tipo = null;
        VerificadorDeTipos verificador = new VerificadorDeTipos();
        if(ctx.IDENT()!=null)
        {
            String nome = ctx.IDENT().getText();
            System.out.println("Nome parcela_unario "+nome);
            int linha = ctx.IDENT().getSymbol().getLine();
            System.out.println("Nome parcela_unario "+nome+" linha"+linha);
            tipo = pilhaDeTabelas.getTipo(nome);
            System.out.println("Tipo recuperado "+tipo);
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                if(ctx.chamada_partes()!=null)
                {
                    if (ctx.chamada_partes().outros_ident().identificador() != null)
                    {
                        nome = nome + '.' + ctx.chamada_partes().outros_ident().identificador().IDENT().getText();
                    }
                }
                
                out.println("Linha "+linha+": identificador "+nome+" nao declarado");
            }
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
                    nome = nome + '.' + ctx.outros_ident().identificador().IDENT().getText();
                }
                
                out.println("Linha "+linha+": identificador "+nome+" nao declarado");
            }
        }
    }

  
}

