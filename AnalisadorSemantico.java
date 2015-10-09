package trabalho1;

import java.util.ArrayList;
import java.util.List;
import trabalho1.LAParser.Declaracao_localContext;
import trabalho1.LAParser.DimensaoContext;

/**
 *
 * A classe AnalisadorSemantico representa a etapa de analise semantica da gramatica LL.
 */
public class AnalisadorSemantico extends LABaseListener {

    //Referencia a uma instancia da classe Saida
    Saida out;
    //Atributo com intuito de servir como uma flag, apenas para indicar se o codigo se trata de uma funcao
    //EhFuncao e utilizada para identificar se a palavra chave retorne foi usada em escopo não permitido
    int EhFuncao = 0;

    // Uma instancia da pilha de tabelas que armazenara as tabelas de simbolos que forem criadas para cada escopo
    static PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    
    
    
    
    public AnalisadorSemantico(Saida out) {
        this.out = out;
    }

    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
        
        
    }
    
    /*O metodo enterDeclaracao_local foi sobrescrita do listener. Esse metodo sera visitado pelo ParserTreeWalker
    * toda vez que a regra declaracao_local for utilizada na gramatica. Essa regra e usada para inicializacao de
    * variaveis, constantes e declaracao de registros.
    */
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        
        String nome;
        String tipo;
        int linha;
        
        //Primeira verificacao, se a regra comeca com a palavra chave constante
        if(ctx.getStart().getText().equals("constante"))
        {
            //recupera o nome a partir do valor de texto da regra lexica IDENT
            nome = ctx.IDENT().getText();
            //a linha e recuperada pelo metodo getLine()
            linha = ctx.IDENT().getSymbol().getLine();
            // o tipo e identificado pela chamada da regra tipo_basico, recuperando o texto
            tipo = ctx.tipo_basico().getText();
                
            //Poder adicionar essa constante, primeiramente deve-se verificar se esse nome ja nao existe em nenhum
            //escopo visivel
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {   tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipo, null, null);
                            System.out.println("Var adicionada "+nome+" "+tipo+" linha: "+linha);
            }    
            else //ERRO 1
                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
            
        }else
        {   //Se no comeco da regra esta presente a palavra chave tipo, isso quer dizer que se trata da declaracao
            //de um registro
            if(ctx.getStart().getText().equals("tipo"))
            {
                //variavel que armazenara o nome do registro
                String nomeDoReg = ctx.IDENT().getText();
                int linhaDoReg = ctx.IDENT().getSymbol().getLine();
                //Antes de adicionar qualquer nome novo na tabela de simbolos, e necessario verificar se esse nome 
                // ja foi declarado em algum escopo visivel
                if(!tabelaDeSimbolosAtual.existeSimbolo(nomeDoReg))
                {
                    //Para a adicao do registro na tabela de simbolos foi criado um novo metodo AdicionarSimboloRegistro
                    //Esse metodo tem o intuito de modularizar essa etapa e evitar repeticao de codigo, ja que e possivel
                    //criar um registro a partir da palavra chave "declare", o que causaria repeticao de codigo nessa etapa
                    //Os parametros passados ao metodo incluem o contexto atual, a tabela de simbolos atual, o nome do registro
                    // e a linha em que esse nome foi declarado, para reportar erros.
                    AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nomeDoReg, linhaDoReg);
                   
                }else
                    out.println("Linha "+linhaDoReg+": identificador " +nomeDoReg+ " ja declarado anteriormente");
            }   
                else
                {
                    if(ctx.getStart().getText().equals("declare"))
                    {
                       //Para adicionar nomes de variaveis na tabela de simbolos foi criado o metodo AdicionarSimbolos
                       //com intuiro de evitar repeticao de codigo e garantir modularizacao
                       //Os parametros passados sao o contexto atual e a tabela de simbolos atual
                        AdicionarSimbolo(ctx, tabelaDeSimbolosAtual);
                        
                    }
                }
            }
            
        
    }    
    
    
    /**
     *Metodo para adicionar um simbolo em uma tabela do registro e depois adicionar essa tabela, associada ao
     * nome do registro, na tabela de simbolos atual
     */
    public void AdicionarSimboloRegistro(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual, String nomeDoReg, int LinhaDoReg)
    { 
        String nome;   //nome da variavel
        String tipo; //tipo da variavel
        int linha; //linha da variavel
        //criando uma nova tabela para o registro
        TabelaDeSimbolos tabelaDoRegistro = new TabelaDeSimbolos("registro");
            //Observacao: nao havia caso de teste em que tinha algum tipo nao declarado dentro das declaracoes do 
            //registro. Essa verificacao nao foi adicionada nesse metodo, mas deveria ter sido =[.
            if(ctx.tipo()!=null)       
            {      nome = ctx.tipo().registro().variavel().IDENT().getText(); //nome da variavel
                   linha = ctx.tipo().registro().variavel().IDENT().getSymbol().getLine();  //linha da variavel
                   if(ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT()!=null)
                   {
                       //verificacao para determinar o tipo, ja que ele pode ser um novo tipo (definido em um registro)
                       // ou um tipo pre definido pela gramatica
                       tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().IDENT().getText();
                   }else
                   {
                       //nesse caso, recupera o tipo pre estabelecido da gramatica. 
                       tipo = ctx.tipo().registro().variavel().tipo().tipo_estendido().tipo_basico_ident().tipo_basico().getText();
                   }
                   
                   //antes de adicionar a variavel a tabela de simbolos do registro, e necessario verificar se ja nao existe nenhum
                   //nome igual nessa tabela
                   if(!tabelaDoRegistro.existeSimbolo(nome))
                   {
                       tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                   }else
                   {
                       out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                   }
                   
                   //Realizando o mesmo processo para as outras variaveis que vem do mais_var, que possuem
                   //o mesmo tipo declarado da variavel anterior
                   for(int i = 0; i<ctx.tipo().registro().variavel().mais_var().IDENT().size(); i++)
                   {
                       nome = ctx.tipo().registro().variavel().mais_var().IDENT(i).getText();
                       linha = ctx.tipo().registro().variavel().mais_var().IDENT(i).getSymbol().getLine();
                       if(!tabelaDoRegistro.existeSimbolo(nome))
                       {
                           tabelaDoRegistro.adicionarSimbolo(nome, tipo, null, null);
                       }else
                       {
                           out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                       }
                   }
                   
                   //O mesmo processo realizado anteriormente para a regra variavel, deve ser realizado para a 
                   // regra mais_variaveis, que permite a inclusao de outras variaveis de diferente tipo no mesmo
                   // declare
                   
                   for(int i = 0; i < ctx.tipo().registro().mais_variaveis().variavel().size(); i++) 
                   {
                        nome = ctx.tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
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
                            }else
                            {
                                out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                            }
                        }
                   }
            }else{
                if(ctx.variavel().tipo()!=null)
                {
                   nome = ctx.variavel().tipo().registro().variavel().IDENT().getText();
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
                       }else
                       {
                           out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                       }
                   }
                   
                   for(int i = 0; i < ctx.variavel().tipo().registro().mais_variaveis().variavel().size(); i++) 
                   {
                        nome = ctx.variavel().tipo().registro().mais_variaveis().variavel(i).IDENT().getText();
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
        
        //Apos adicionar todas as variaveis a tabela de simbolos do registro, podemos adicionar essa tabela
        //ao escopo do contexto atual. O nome vai ser o nome do registrador e o tipo tambem, para permitir pesquisas
        // e recuperacao desse tipo
        tabelaDeSimbolosAtual.adicionarSimbolo(nomeDoReg, nomeDoReg, null, tabelaDoRegistro);
                
    }
    
    
    /**
     * Esse metodo tem o intuito de adicionar uma nova variavel a tabela de simbolos, a partir da palavra chave
     * declare.
     */
    public void AdicionarSimbolo(LAParser.Declaracao_localContext ctx, TabelaDeSimbolos tabelaDeSimbolosAtual)
    {
        String nome;
        String tipo;
        int linha;
        
            //Verificacao inicial para garantir que esses comandos nao sejam executados quando entrar na regra registro
            //so por precaucao
            if(ctx.variavel().tipo().registro()==null)       
            {      nome = ctx.variavel().IDENT().getText();
                   System.out.println("Simbolo sendo adicionado: "+nome);
                   linha = ctx.variavel().IDENT().getSymbol().getLine();
                   
                   //primeira verificacao para ver se o tipo ja foi declarado anteriormente or meio de um registro
                   // se nao foi, um  erro deve ser reportado
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
                   
                   //verificando se na tabela de simbolos atual existe alguma variavel com nome igual a nome
                   if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                   {
                       tabelaDeSimbolosAtual.adicionarSimbolo(nome, tipo, null, null);
                   }else
                   {
                       out.println("Linha "+linha+": identificador " +nome+ " ja declarado anteriormente");
                   }
                   
                   //realizando os mesmos passsos para as variaveis que seguem a declaracao de mesmo tipo
                   //A regra mais_var permite que sejam adicionadas mais variaveis daquele mesmo tipo
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
                //Se a verificacao da regra registro nao resultou em null, isso quer dizer que dentro de declare
                // um novo registro foi declarado. O tratamento entao deve ser distinto
                nome = ctx.variavel().IDENT().getText();
                linha = ctx.variavel().IDENT().getSymbol().getLine();
                //verificando se nao existe nenhum nome com aquele nome ja declarado
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {
                    //Nesse ponto, pode se chamar o procedimento para adicionar variaveis a tabela de simbolos do
                    //procedimento, passando a tabela de simbolos do escopo atual, o contexto atual, o nome do regitro
                    //e sua linha. E bom lembrar que a tabela de simbolos do registro e criada dentro desse procedimento
                    AdicionarSimboloRegistro(ctx, tabelaDeSimbolosAtual, nome, linha);
                }
                
                //E possivel ter mais nomes associados a uma mesma declaracao de um registro quando se vem da regra
                //declare. Entao, para cada nome que tiver na repeticao dessa regra, a mesma definicao do registro
                //deve estar associada com cada um desses nomes diferentes, entao para cada um se chama AdicionarSimboloRegistro
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

    
    /**
     * Metodo sobrescrito do listener para representar as verificacoes que devem ser feitas toda vez que se entrar
     * na regra cmd
     */ 
     @Override
    public void enterCmd(LAParser.CmdContext ctx)
    {   
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
        int linha;
        String nome;
        String tipo;
        
        //Verificando o comeco do contexto atual para determinar o comportamento correto.
        if(ctx.getStart().getText().equals("leia"))
        {
            //Se comecar com leia, significa que existem identificadores dentro desse comando. Deve-se verificar
            //Se eles ja foram declarados anteriormente. Para isso foi criado o procedimento VerificaSimbolo.
            VerificaSimbolo(ctx);
        }else
        {
            //Quando expressao e IDENT sao diferentes de null ao mesmo tempo, temos uma atribuicao para um ponteiro
            if(ctx.expressao()!=null && ctx.IDENT()!=null)
            {
                //nome da variavel que recebe a atribuicao
                nome = ctx.IDENT().getText();
                //recuperacao do tipo da variavel armazenado em alguma tabela de simbolos
                tipo = pilhaDeTabelas.getTipo(nome);
                if(tipo == null)
                {
                    tipo = "tipo_indefinido";
                }
                nome = "^" + nome; //ajustando o nome, para representar o ponteiro
                linha = ctx.IDENT().getSymbol().getLine();
                
                //No caso de o nome ser um nome composto, recuperar a outra parte do nome
                if(ctx.outros_ident().identificador()!=null)
                {
                    TabelaDeSimbolos tabelaDoRegistro = pilhaDeTabelas.getSubtabela(tipo);
                    String Outronome = ctx.outros_ident().identificador().IDENT().getText();
                    tipo = tabelaDoRegistro.getTipo(Outronome);
                    nome = nome + "." + Outronome;
                    
                }
                //Agora, e necessario verificar se a atribuicao foi feita para tipos compativeis, para isso,
                //foi criado um metodo que recebe como parametro o contexto da expressao (para recuperar seu tipo)
                // o nome, a linha e o tipo da cabeca da atribuicao
                VerificaAtribuicao(ctx.expressao(), nome, linha, tipo);
                
                
            }else
            {
              //ainda e possivel ter atribuicao por essa regra    
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
                   // if(ctx.dimensao()!=null)
                   // {
                    DimensaoContext dCtx = ctx.chamada_atribuicao().dimensao();
                    if(dCtx != null) {
                        LAParser.Exp_aritmeticaContext eCtx = dCtx.exp_aritmetica(0);
                        if(eCtx != null) {
                            String txt = eCtx.getText();
                            nome = nome +"["+txt+"]";
                        }
                   }
                
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
    
    /**
     * Metodo sobrescrito do listener para adicionar as verificacoes necessarias dessa regra
     */
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx)
    {
        String nome = ctx.IDENT().getText();  //representa ou o nome do procedimento, ou o nome da funcao
        TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo(); //tabela de simbolos do escopo que sera adicionado a subrotina
        List<String> listaNomePar = new ArrayList<>();  //lista para armazenar os nomes dos parametros
        List<String> listaTipoPar = new ArrayList<>();  //lista para armazenar os tipos dos parametros
        //Se for um procedimento...
        if (ctx.getStart().getText().equals("procedimento"))
        {
            //Primeiramente, verificar se o nome do procedimento ja nao foi declarado anteriormente
            if(!pilhaDeTabelas.existeSimbolo(nome))
            {
                //se esse procedimento tem parametros
                if(ctx.parametros_opcional()!=null)
                {   
                    //tratar a verificacao dos parametros em um metodo diferente, passando como parametro o contexto
                    //atual e as duas listas que vao salvar essas informacoes
                    AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                }
                
                //Adiciona a tabela de simbolos atual, ou seja, ao escopo atual, a lista de tipos dos parametros
                //associada ao nome do procedimento. Importante notar que o tipo do procedimento e null
                tabelaDeSimbolosAtual.adicionarSimbolo(nome, null, listaTipoPar, null);
                //Cria a nova tabela, a do escopo do procedimento
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento"+nome);
                //Todos  os parametros que foram declarados sao adicionados a essa tabela, pois eles sao visiveis nesse
                //escopo
                for(int i = 0; i < listaNomePar.size(); i++)
                {
                    if(!tabelaDeSimbolosProcedimento.existeSimbolo(listaNomePar.get(i)))
                    {
                        tabelaDeSimbolosProcedimento.adicionarSimbolo(listaNomePar.get(i), listaTipoPar.get(i), null, null);
                    }
                }
                //Por fim, a pilha do procedimento e empilhada, ou seja, passa a ser o escopo atual
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  
                
            }else
            {
                out.println("identificador "+nome+" ja declarado anteriormente");
            }
        }else{
            if(ctx.getStart().getText().equals("funcao"))
            {   //atributo que serve como flag para indicar que se trata de uma funcao. Ele sera usado em cmd
                //para verificar se o comando retorne esta no escopo correto
                EhFuncao = 1;
                //A mesma logica de parametros de procedimento e usada aqui, com a mesmo metodo inclusive
                if(!pilhaDeTabelas.existeSimbolo(nome))
                {
                    if(ctx.parametros_opcional()!=null)
                    {   
                        AdicionarTiposParametros(ctx,listaNomePar,listaTipoPar);
                    }
                    String TipoFuncao;
                    //Funcao tem um tipo associado, esse tipo deve ser recuperado principalmente para tratamento
                    //dos tipos de expressoes
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
    
    /**
     * Quando sao da regra declaracao_global, tambem esta saindo do escopo da funcao ou do procedimento, entao e
     * necessario desempilhar. EhFuncao passa a ter o valor 0, para que nao atrapalhe em futuros testes.
     */ 
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

