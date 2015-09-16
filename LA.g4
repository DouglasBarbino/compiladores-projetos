/*
	VersÃ£o 1: Grupo 3 (Luazinha)
*/

grammar LA;

@members
{

	private void stop(String msg)
	{
		throw new ParseCancellationException(msg);
	}
   
        PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
	
	Saida outSemantico = new Saida();

        private void erroSemantico(String msg) {
		outSemantico.println(msg);        
	}
}

/******************************LÃXICO*******************************************/

// Definindo o identificador:
IDENT	: ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
// Definindo nÃºmero inteiro e nÃºmero real:
NUM_INT : ('0'..'9')+;
NUM_REAL: ('0'..'9')+ '.' ('0'..'9')+;
// Definindo cadeia de caracteres:
CADEIA 	: '"' ( ~('"') )* '"';
// Definindo comentÃ¡rios:
COMENTARIO : '{' ~('{' | '}')* '}' {skip();}; 
// Definindo espaÃ§os para serem ignorados:
ESPACOS	: (' ' | '\t' | '\r' | '\n') {skip();};
// Definindo quando ocorre erro no comentÃ¡rio
COMENTARIO_ERRADO
    : '{' ~('\r'|'\n'|'}')* '\n' 
      { stop("Linha "+getLine()+": comentario nao fechado"); }
    ;
ERROR
    : . { stop("Linha "+getLine()+": "+getText()+" - simbolo nao identificado"); }
    ;

/*****************************SINTÁTICO*****************************************/

programa :              {
                            TabelaDeSimbolos tabelaDeSimbolosGlobal = new TabelaDeSimbolos("global");
                            pilhaDeTabelas.empilhar(tabelaDeSimbolosGlobal);
                        }
                        declaracoes 'algoritmo' corpo 'fim_algoritmo'
                        {
                            pilhaDeTabelas.desempilhar();
                        }
			;
			
declaracoes :           (decl_local_global)*
			; 

decl_local_global :     declaracao_local 
			| declaracao_global
			;
			
declaracao_local :      'declare' v = variavel 
            		{
                		TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
				if($v.tipo_var == "literal" || $v.tipo_var == "inteiro" || $v.tipo_var == "real" || $v.tipo_var == "logico")
				{


					for (String nome : $variavel.variaveis)
                                       		 if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                        				tabelaDeSimbolosAtual.adicionarSimbolo(nome,$variavel.tipo_var);
				}
				else
				{

					if(!tabelaDeSimbolosAtual.existeSimbolo($v.tipo_var))
		                        {

						erroSemantico("Linha : +v.getLine() +  tipo +v.tipo_var+  nao declarado");

					}
					else
					{
						for (String nome : $variavel.variaveis)
                                       		 	if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                        					tabelaDeSimbolosAtual.adicionarSimbolo(nome,$variavel.tipo_var);	
					}
						
				}		  
            		}
			| 'constante' nome = IDENT  ':'  tipo_basico '=' valor_constante 
			{
			    TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
			
                            if(!tabelaDeSimbolosAtual.existeSimbolo($nome.getText()))
                                tabelaDeSimbolosAtual.adicionarSimbolo($nome.getText(), $tipo_basico.tipo_var);
			} 
			
			| 'tipo' nome = IDENT ':' tipo
			{
			    TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
                            if(!tabelaDeSimbolosAtual.existeSimbolo($nome.getText()))
                                tabelaDeSimbolosAtual.adicionarSimbolo($nome.getText(), "registro"); /* "registro"  */
			} 
			;
			
variavel returns [ List<String> variaveis, String tipo_var]
@init {$variaveis = new ArrayList<String>();}
            		: var = IDENT {$variaveis.add($var.getText());} dimensao  mais_var {$variaveis.addAll($mais_var.variaveis);} ':' t = tipo {$tipo_var = $t.tipo_var;}
			;
			
mais_var returns [ List<String> variaveis]
@init {$variaveis = new ArrayList<String>();}
            		: (',' var = IDENT {$variaveis.add($var.getText());} dimensao mais_var)*
			;

identificador returns [String primParametro] 
                        : ponteiros_opcionais IDENT {$primParametro = $IDENT.getText();} dimensao outros_ident
			;
			
ponteiros_opcionais :   ('^')* 
			;
			
outros_ident : 		('.' identificador)? 
			;
			
dimensao : 		('[' exp_aritmetica ']' )*
			;
tipo returns [String tipo_var]
                        : registro 
			| t = tipo_estendido {$tipo_var = $t.tipo_var;}
			;
			
mais_ident : 		(',' identificador)* // mais ident
			;
			
mais_variaveis : 	(variavel)* //mais variaveis
			;
			
tipo_basico returns [String tipo_var]		
                        : 'literal' {$tipo_var = "literal";}
			| 'inteiro' {$tipo_var = "inteiro";}
			| 'real'    {$tipo_var = "real";}
			| 'logico'  {$tipo_var = "logico";}
			;
			
tipo_basico_ident returns [String tipo_var] 	
            		: t = tipo_basico {$tipo_var = $t.tipo_var;}
			| id = IDENT {$tipo_var = $id.getText();}
			;

tipo_estendido 	returns [String tipo_var]
                        : ponteiros_opcionais t = tipo_basico_ident {$tipo_var = $t.tipo_var;}
			;
			
valor_constante : 	CADEIA 
			| NUM_INT 
			| NUM_REAL 
			| 'verdadeiro' 
			| 'falso'
			;
			
registro : 		'registro' variavel mais_variaveis 'fim_registro'
			;
			
declaracao_global 
                	: 'procedimento' nome = IDENT 
            		{
                		TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
        			if(!tabelaDeSimbolosAtual.existeSimbolo($nome))
                		{
                			tabelaDeSimbolosAtual.adicionarSimbolo($nome, "procedimento");
                    
                    			TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento "+$nome);
                    			pilhasDeTabelas.empilhar(tabelaDeSimbolosProcedimento);
                		}
            		}
            
            		//A adicao dos parametros no procedimento se dah da mesma maneira que eh feito na regra de declaracao_local (regra 4)
            		'(' parametros_opcional 
            		{
                		TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
                		for ((String nome : $parametros_opcional.parametros) && (String tipo : $parametros_opcional.tipo_parametros))
                    			if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                        			tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipo);
            		}
            		')' declaracoes_locais comandos 'fim_procedimento'
            		
            		// Apos o fim_procedimento, o escopo empilhado para ele eh desempilhado (fonte: pag 69 (no .pdf 75))
            		{
            			pilhaDeTabelas.desempilhar();
            		}
            		
            		// As mesmas regras de escopo validas para os procedimentos se aplicam para as funcoes (fonte: pag 71 (no .pdf 77))
            		
			| 'funcao' nomeFuncao = IDENT 
			{
        			TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
                		if(!tabelaDeSimbolosAtual.existeSimbolo($nomeFuncao))
                		{
                    			tabelaDeSimbolosAtual.adicionarSimbolo($nomeFuncao, "funcao");
                    
                    			TabelaDeSimbolos tabelaDeSimbolosFuncao = new TabelaDeSimbolos("funcao "+$nomeFuncao);
                			pilhasDeTabelas.empilhar(tabelaDeSimbolosFuncao);
                		}
        		}
			
			/*MESMA COISA QUE O PROCEDIMENTO*/
			'(' parametros_opcional 
			{
                		TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
                		for ((String nome : $parametros_opcional.parametros) && (String tipo : $parametros_opcional.tipo_parametros))
                    			if(!tabelaDeSimbolosAtual.existeSimbolo(nome))
                        			tabelaDeSimbolosAtual.adicionarSimbolo(nome,tipo);
            		}
            		
            		')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao'
            		
			{
            			pilhaDeTabelas.desempilhar();
            		}
                        ;

// O retorno da lista de parametros e seus tipos.
parametros_opcional returns [ List<String> parametros, List<String> tipo_parametros ]	
@init { $parametros = new ArrayList<String>(); }
@init { $tipo_parametros = new ArrayList<String>(); }
			: (parametro {$parametros.addAll($parametro.parametros);
				      $tipo_parametros.addAll($parametro.tipo_parametros);})?
			;

/* Aqui ele recebe o nome de um parametro vindo do identificador e tambem outros vindo do mais_parametros.
   A ideia utilizada aqui eh a mesma que foi utilizada na regra da variavel, ou seja, a lista que o parametro e o tipo
   retorna inclui o primeiro identificador mais a lista retornada pelo mais_var (uso da funcao addAll para isso).*/
parametro returns [ List<String> parametros, List<String> tipo_parametros ]	
@init { $parametros = new ArrayList<String>(); }
@init { $tipo_parametros = new ArrayList<String>(); }
			: var_opcional par=identificador {$parametros.add($par.primParametro);}
			  mais_ident ':' tipo_param=tipo_estendido {$tipo_parametros.add($tipo_param.tipo_var);}
			  mais_parametros {$parametros.addAll($mais_parametros.parametros);
			  		   $tipo_parametros.addAll($mais_parametros.tipo_parametros);}
			;
			
var_opcional : 		'var'?
			;

// O retorno de varios outros parametros e seus tipos acumulados de algumas recursoes.
mais_parametros returns [ List<String> parametros, List<String> tipo_parametros ]
@init { $parametros = new ArrayList<String>(); }
@init { $tipo_parametros = new ArrayList<String>(); }
			: (',' parametro {$parametros.addAll($parametro.parametros);
					  $tipo_parametros.addAll($parametro.tipo_parametros);})?
                        ;
			
declaracoes_locais : 	(declaracao_local)*
			;
			
corpo : declaracoes_locais comandos
			;
			
comandos : 		(cmd)*
			;
			
cmd : 			'leia' '(' identificador mais_ident ')' 
			| 'escreva' '(' expressao mais_expressao ')' 
			| 'se' expressao 'entao' comandos senao_opcional 'fim_se' 
			| 'caso' exp_aritmetica 'seja' selecao senao_opcional 'fim_caso' 
			| 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para' 
			| 'enquanto' expressao 'faca' comandos 'fim_enquanto' 
			| 'faca' comandos 'ate' expressao 
			| '^' IDENT outros_ident dimensao '<-' expressao 
			| IDENT chamada_atribuicao 
			| 'retorne' expressao
			;
			
mais_expressao : 	(',' expressao)*
			;
			
senao_opcional : 	('senao' comandos)?
			;
			
chamada_atribuicao : 	'(' argumentos_opcional ')' 
			| outros_ident dimensao '<-' expressao
			;
			
argumentos_opcional : 	(expressao mais_expressao)?
			;
			
selecao : 		constantes ':' comandos mais_selecao
			;
			
mais_selecao : 		(selecao)?
			;
			
constantes : 		numero_intervalo mais_constantes
			;
			
mais_constantes : 	(',' constantes)?
			;
			
numero_intervalo : 	op_unario NUM_INT intervalo_opcional
			;
			
intervalo_opcional : 	('..' op_unario NUM_INT)?
			;
			
op_unario : 		('-')?
			;
			
exp_aritmetica : 	termo outros_termos
			;
			
op_multiplicacao : 	'*' 
			| '/'
			;
			
op_adicao : 		'+' 
			| '-'
			;
			
termo : 		fator outros_fatores
			;
			
outros_termos : 	(op_adicao termo)*
			;
			
fator : 		parcela outras_parcelas
			;
			
outros_fatores : 	(op_multiplicacao fator)*
			;
			
parcela : 		op_unario parcela_unario 
			| parcela_nao_unario
			;
			
parcela_unario : 	'^' IDENT outros_ident dimensao 
			| IDENT chamada_partes 
			| NUM_INT 
			| NUM_REAL 
			| '(' expressao ')'
			;
			
parcela_nao_unario : 	'&' IDENT outros_ident dimensao 
			| CADEIA
			;
			
outras_parcelas : 	('%' parcela)*
			;
			
chamada_partes : 	'(' expressao mais_expressao ')' 
			|  outros_ident dimensao 
			|  /* vazio*/
			;
			
exp_relacional :	 exp_aritmetica op_opcional
			;
			
op_opcional : 		(op_relacional exp_aritmetica)?
			;
			
op_relacional : 	'=' 
			| '<>' 
			| '>=' 
			| '<=' 
			| '>' 
			| '<'
			;
			
expressao : 		termo_logico outros_termos_logicos
			;
			
op_nao : 		'nao'?
			;
			
termo_logico : 		fator_logico outros_fatores_logicos
			;
			
outros_termos_logicos : ('ou' termo_logico)*
			;
			
outros_fatores_logicos : ('e' fator_logico)*
			;
			
fator_logico : 		op_nao parcela_logica
			;
			
parcela_logica : 	'verdadeiro' 
			| 'falso' 
			| exp_relacional
			;
