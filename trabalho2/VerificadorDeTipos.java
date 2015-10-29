
package trabalho3;

/**
 *A classe VerificadorDeTipos, tem o intuito de receber algum contexto, e atraves de metodos polimorficos,
 * determinar o tipo daquele contexto. A logica segue a ideia de se descer na arvore da gramatica, ate chegar
 * a um ponto em que seja possivel determinar  tipo. Se o contexto passado for composto por um unico elemento
 * o tipo desse elemento e retornado, senao, uma comparacao dos elementos dois a dois e feita, para poder determinar
 * o tipo desse conjunto dessa expressao.
 */
public class VerificadorDeTipos {
    //O metodo verificaTipo e um metodo polimorfico.Seu comportamento e determinado pelo contexto que e passado como
    //parametro. Nesse caso, e o contexto de uma expressao.
    public static String verificaTipo(FAZEDORESParser.ExpressaoContext ctx) {
        //Uma expressao e composta por termo_logico e outros_termos_logicos. O tipo da expressao sera dado pelo tipo do termo_logico,
	//Se a regra outros_termos_logicos for nula
	String tipoExp = verificaTipo(ctx.termo_logico());

        //Se a regra outros_termos_logicos nao for nula, o tipo da expressao sera dado pela verificacao 2 a 2 de cada um dos termos logicos
        if(ctx.outros_termos_logicos() == null) {
            return tipoExp;
        } else {
            for(FAZEDORESParser.Termo_logicoContext termo: ctx.outros_termos_logicos().termo_logico()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoExp = regraTipos(tipoExp, tipoOutroTermo);
            }
        }
        
        return tipoExp;
    }
    
     //Metodo verificaTipo para o contexto de um termo_logico
     public static String verificaTipo(FAZEDORESParser.Termo_logicoContext ctx) {
	//Um termo_logico e composto por fator_logico e outros_fatores_logicos. O tipo do termo sera dado pelo tipo do fator_logico,
	//Se a regra outros_fatoeres_logicos for nula
         String tipoTermoLogico = verificaTipo(ctx.fator_logico());
        
	//Se a regra outros_fatores_logicos nao for nula, o tipo do termo_logico sera dado pela verificacao 2 a 2 de cada um dos fatores logicos
        if(ctx.outros_fatores_logicos() == null) {
            return tipoTermoLogico;
        } else {
            for(FAZEDORESParser.Fator_logicoContext termo: ctx.outros_fatores_logicos().fator_logico()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoTermoLogico = regraTipos(tipoTermoLogico, tipoOutroTermo);
            }
        }
        
        return tipoTermoLogico;
     }
     
    //Metodo verificaTipo para o contexto de um fator_logico
    public static String verificaTipo(FAZEDORESParser.Fator_logicoContext ctx) {
	//Um fator logico chega apenas em uma parcela logica, sendo assim, o tipo e determinado pelo tipo da parcela logica
        String tipof_logico = verificaTipo(ctx.parcela_logica());
        
        return tipof_logico;
    }
    
    //Metodo verificaTipo para o contexto de uma parcela_logica
    public static String verificaTipo(FAZEDORESParser.Parcela_logicaContext ctx) {
        String tipoP_logica;
	//parcela_logica pode chegar ate as palavras chave verdadeiro ou falso, o que determinaria o tipo dessa parcela como logico
        if(ctx.getStart().getText().equals("verdadeiro") || ctx.getStart().getText().equals("falso"))
            tipoP_logica = "logico";
        else
	    //mas tambem pode chegar a uma exp_relacional, de modo que o tipo passa a ser determinado pelo tipo dessa exp_relacional
            tipoP_logica = verificaTipo(ctx.exp_relacional());
        return tipoP_logica;
    }


    //Metodo verificaTipo para o contexto de uma exp_relacional
    public static String verificaTipo(FAZEDORESParser.Exp_relacionalContext ctx) {
        String tipoExp_rel = "tipo_invalido";
	//uma exp_relacional pode ter um operador opcional. Se esse operador opcional existir, nao importa o tipo da exp_aritmetica retornado
	// o tipo da exp_relacional como um todo passara a ser logico
        if(ctx.op_opcional() != null)
        {
            if(ctx.op_opcional().op_relacional()!=null)
            {
                tipoExp_rel = "logico";
            }else{
                tipoExp_rel = verificaTipo(ctx.exp_aritmetica());
            }
        }    
        else
	    //Mas se o operador opcional nao existir, o tipo da exp_relacional passara a ser determinado pelo tipo da exp_aritmetica	
            tipoExp_rel = verificaTipo(ctx.exp_aritmetica());
        
        return tipoExp_rel;  
    }
    
    //Metodo verificaTipo para o contexto de uma exp_aritmetica	
    public static String verificaTipo(FAZEDORESParser.Exp_aritmeticaContext ctx) {
	//Uma exp_aritmetica e composta por termo e outros_termos. O tipo da exp_aritmetica sera dado pelo tipo do termo,
	//Se a regra outros_termos for nula
        String tipoExp_ar = verificaTipo(ctx.termo());
        //Se a regra outros_termos_ nao for nula, o tipo da exp_aritmetica sera dado pela verificacao 2 a 2 de cada um dos termos
        if(ctx.outros_termos()== null) {
            return tipoExp_ar;
        } else {
            for(FAZEDORESParser.TermoContext termo: ctx.outros_termos().termo()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoExp_ar = regraTipos(tipoExp_ar, tipoOutroTermo);
            }
        }
        
        return tipoExp_ar;
    }
    

    //Metodo verificaTipo para o contexto de um termo
    public static String verificaTipo(FAZEDORESParser.TermoContext ctx) {
        String tipotermo = verificaTipo(ctx.fator());
        //Um termo e composto por fator e outros_fatores. O tipo do termo sera dado pelo tipo do fator,
	//Se a regra outros_fatores for nula
        if(ctx.outros_fatores() == null) {
            return tipotermo;
	//Se a regra outros_fatores nao for nula, o tipo do termo sera dado pela verificacao 2 a 2 de cada um dos fatores
        } else {
            for(FAZEDORESParser.FatorContext termo: ctx.outros_fatores().fator()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipotermo = regraTipos(tipotermo, tipoOutroTermo);
            }
        }
        
        return tipotermo;
    }
    
    //Metodo verificaTipo para o contexto de um fator
    public static String verificaTipo(FAZEDORESParser.FatorContext ctx) {
	//Um fator e composto por parcela e outras_parcelas. O tipo do fator sera dado pelo tipo da parcela,
	//Se a regra outras_parcelas for nula
        String tipofator = verificaTipo(ctx.parcela());
        //Se a regra outras_parcelas nao for nula, o tipo do fator sera dado pela verificacao 2 a 2 de cada uma das parcelas
        if(ctx.outras_parcelas() == null) {
            return tipofator;
        } else {
            for(FAZEDORESParser.ParcelaContext termo: ctx.outras_parcelas().parcela()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipofator = regraTipos(tipofator, tipoOutroTermo);
            }
        }
        
        return tipofator;
    }
    
    //Metodo verificaTipo para o contexto de uma parcela
    public static String verificaTipo(FAZEDORESParser.ParcelaContext ctx) {
        String tipo;
		
	    //Uma parcela pode chegar na regra parcela_unario ou na regra parcela_nao unario, de modo que o tipo da parcela vai ser determinado
	    //pelo tipo da regra que essa parcela chegar
            if(ctx.parcela_unario()!=null)
            {
                tipo = verificaTipo(ctx.parcela_unario());
            }else{
                tipo = verificaTipo(ctx.parcela_nao_unario());
            }

        return tipo;
    }
    
    //Metodo verificaTipo para o contexto de uma parcela_unario
    public static String verificaTipo(FAZEDORESParser.Parcela_unarioContext ctx) {
        String tipo = "tipo_invalido";
	//A regra parcela nao unario pode ser um numero inteiro, um numero real, um identificador, ou uma chamada de subrotina
        if(ctx.NUM_INT() != null)
        {
	    //Se a regra lexica NUM_INT nao for nula, trata-se de um numero inteiro
            tipo = "inteiro";
        }else
        {
            if(ctx.NUM_REAL()!= null)
            {
		//Se a regra lexica NUM_REAL nao for nula trata-se de um tipo real
                tipo = "real";
            }else
            {
		//Se nao for nenhum dos outros dois, entao temos um identificador, desse modo, e necessario identificar seu tipo associado
		// na tabela de simbolos
                PilhaDeTabelas atual = AnalisadorSemantico.pilhaDeTabelas;
                if(ctx.outros_ident()!=null)
                {
		    //Se outros ident e diferente de null, entao o tipo desse nome composto e dado pelo nome apos o ponto, desse modo,
		    //o tipo do primeiro nome e recuperado para encontrar a subtabela associada aquele tipo, e, desse modo, determinar o
		    //tipo do nome apos o ponto
                    if(ctx.outros_ident().identificador()!=null)
                    {
                        String nome = ctx.IDENT().getText();
                        String tipo1 = atual.getTipo(nome);
                        TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                        nome = ctx.outros_ident().identificador().IDENT().getText();
                        tipo = TabelaReg.getTipo(nome);
                    }
                    
                }else{
                    if(ctx.chamada_partes()!=null)
                    {   //Pode-se chegar em outros_ident pela regra chamada_partes, entao e feita uma verificacao para saber se esses outros_ident 
                        //existe, pois se a regra nao for nula, o tipo do nome composto sera dado pelo tipo do outros_ident
                        if(ctx.chamada_partes().outros_ident()!=null)
                        {    if(ctx.chamada_partes().outros_ident().identificador()!=null)
                            {
                                String nome = ctx.IDENT().getText();
                                String tipo1 = atual.getTipo(nome);
                                TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                                nome = ctx.chamada_partes().outros_ident().identificador().IDENT().getText();
                                tipo = TabelaReg.getTipo(nome);
                            }else{
                                if(ctx.IDENT() != null)
                                {
                                    String nome = ctx.IDENT().getText();
                                    tipo = atual.getTipo(nome);
                                }else
                                {
                                    if(ctx.getStart().getText().equals("("))
                                    {
                                        tipo = verificaTipo(ctx.expressao());                 
                                    }
                                }
                            }
                        }else{
                                if(ctx.IDENT() != null)
                                {
				   //Se nao for nenhuma das anteriores, basta pegar o tipo do identificador retornado por IDENT
                                    String nome = ctx.IDENT().getText();
                                    tipo = atual.getTipo(nome);
                                }else
                                {
                                    if(ctx.getStart().getText().equals("("))
                                    {
                                        tipo = verificaTipo(ctx.expressao());
                                    }
                                }
                            }
                    }
                    else{
                        
                    
                    if(ctx.IDENT() != null)
                    {
                        String nome = ctx.IDENT().getText();
                        tipo = atual.getTipo(nome);
                    }else
                    {
                        if(ctx.getStart().getText().equals("("))
                        {
                            tipo = verificaTipo(ctx.expressao());
                        }
                    }
                }
                }
                
               
            }
        }
        
        return tipo;
    }
    
    //Metodo verificaTipo para o contexto de uma parcela_nao_unario
    public static String verificaTipo(FAZEDORESParser.Parcela_nao_unarioContext ctx) {
	//Na regra parcela_nao_unario, e possivel ter um IDENT ou uma CADEIA
        String tipo = "tipo_invalido";
        if(ctx.CADEIA() != null)
        {   //Se a regra lexica CADEIA for diferente de null, isso significa que o tipo da parcela_nao_unario e literal
            tipo = "literal";
        }else
        {
            //Se nao, se trata de um identificador, um nome. E necessario recuperar o tipo dele da tabela de simbolos
            PilhaDeTabelas atual = AnalisadorSemantico.pilhaDeTabelas;
            if(ctx.outros_ident()!=null)
            {
		//Se outros ident e diferente de null, entao o tipo desse nome composto e dado pelo nome apos o ponto, desse modo,
		//o tipo do primeiro nome e recuperado para encontrar a subtabela associada aquele tipo, e, desse modo, determinar o
		//tipo do nome apos o ponto
                if(ctx.outros_ident().identificador()!=null)
                {
                    String nome = ctx.IDENT().getText();
                    String tipo1 = atual.getTipo(nome);
                    TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                    nome = ctx.outros_ident().identificador().IDENT().getText();
                    tipo = TabelaReg.getTipo(nome);
                }else{
                    if(ctx.IDENT() != null)
                    {	//Se nao e nome composto, entao, basta apenas recuperar o tipo associado ao nome.
                        String nome = ctx.IDENT().getText();
                        tipo = atual.getTipo(nome);
                    }
                }
            }else{
                if(ctx.IDENT() != null)
                {
                   String nome = ctx.IDENT().getText();
                   tipo = atual.getTipo(nome);
                }
            
        } 
        }
        
        return tipo;
    }
    
    //Metodo para determinacao do retorno da verificacao do tipo de dois elementos
    private static String regraTipos(String tipoExp, String tipoOutroTermo) {
        if(tipoExp.equals(tipoOutroTermo)) {
            return tipoExp;
	    //Se os tipos sao iguais, basta retornar o tipo do primeiro elemento
        } else if(tipoExp.equals("real") && tipoOutroTermo.equals("inteiro") ||
                tipoExp.equals("inteiro") && tipoOutroTermo.equals("real")) {
            return "real";
           //Se eles nao sao iguais, mas algum dos tipos e inteiro e outro tipo e real, trata-se de uma expressao real
        } else {
            return "tipo_invalido";
	  //Se nao for um dos citados acima, o tipo e invalido
        }
    }
}
