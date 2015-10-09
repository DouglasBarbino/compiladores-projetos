/*
	VersÃƒÆ’Ã‚Â£o 1: Grupo 3 (Luazinha)
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

/******************************LÃƒÆ’Ã‚â€°XICO*******************************************/

// Definindo o identificador:
IDENT	: ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
// Definindo nÃƒÆ’Ã‚Âºmero inteiro e nÃƒÆ’Ã‚Âºmero real:
NUM_INT : ('0'..'9')+;
NUM_REAL: ('0'..'9')+ '.' ('0'..'9')+;
// Definindo cadeia de caracteres:
CADEIA 	: '"' ( ~('"') )* '"';
// Definindo comentÃƒÆ’Ã‚Â¡rios:
COMENTARIO : '{' ~('{' | '}')* '}' {skip();}; 
// Definindo espaÃƒÆ’Ã‚Â§os para serem ignorados:
ESPACOS	: (' ' | '\t' | '\r' | '\n') {skip();};
// Definindo quando ocorre erro no comentÃƒÆ’Ã‚Â¡rio
COMENTARIO_ERRADO
    : '{' ~('\r'|'\n'|'}')* '\n' 
      { stop("Linha "+getLine()+": comentario nao fechado"); }
    ;
ERROR
    : . { stop("Linha "+getLine()+": "+getText()+" - simbolo nao identificado"); }
    ;

/*****************************SINTÃƒ?TICO*****************************************/

programa :              
                        declaracoes 'algoritmo' corpo 'fim_algoritmo'
			;
			
declaracoes :           (decl_local_global)*
			; 

decl_local_global :     declaracao_local 
			| declaracao_global
			;
			
declaracao_local    :      'declare' variavel
			| 'constante' IDENT  ':'  tipo_basico '=' valor_constante
			| 'tipo' IDENT ':' tipo
			;
			
variavel returns [ List<String> variaveis, String tipo_var]
@init {$variaveis = new ArrayList<String>();}
            		: var = IDENT {$variaveis.add($var.getText());} dimensao  mais_var {$variaveis.addAll($mais_var.variaveis);} ':' t = tipo {$tipo_var = $t.tipo_var;}
			;
			
mais_var returns [ List<String> variaveis]
@init {$variaveis = new ArrayList<String>();}
            		: (',' var = IDENT {$variaveis.add($var.getText());} dimensao)*
			;

identificador returns [List<String> array_id]  
@init {$array_id = new ArrayList<String>();}
				: ponteiros_opcionais id = IDENT {$array_id.add($id.getText());} dimensao outros_ident
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
			
mais_ident returns [List<String> array_id]  
@init {$array_id = new ArrayList<String>();}
			:	(',' identificador {$array_id.addAll($identificador.array_id);})* // mais ident
			;
			
mais_variaveis returns [List<String> variaveis, String tipo_var]
@init {$variaveis = new ArrayList<String>();}		
			: 	(variavel {$variaveis.addAll($variavel.variaveis); $tipo_var = $variavel.tipo_var;})* //mais variaveis
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
            		'(' parametros_opcional 
            		')' declaracoes_locais comandos 'fim_procedimento'
            		
            		
			| 'funcao' nomeFuncao = IDENT 
			
			'(' parametros_opcional 
			
            		
            		')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao'
            		
			
                        ;

// O retorno da lista de parametros e seus tipos.
parametros_opcional returns [ List<String> parametros, List<String> tipo_parametros ]	
@init { $parametros = new ArrayList<String>(); 
	$tipo_parametros = new ArrayList<String>(); }
			: (parametro {$parametros.addAll($parametro.parametros);
				      $tipo_parametros.addAll($parametro.tipo_parametros);})?
			;

/* Aqui ele recebe o nome de um parametro vindo do identificador e tambem outros vindo do mais_parametros.
   A ideia utilizada aqui eh a mesma que foi utilizada na regra da variavel, ou seja, a lista que o parametro e o tipo
   retorna inclui o primeiro identificador mais a lista retornada pelo mais_var (uso da funcao addAll para isso).*/
parametro returns [ List<String> parametros, List<String> tipo_parametros ]	
@init { $parametros = new ArrayList<String>(); 
	$tipo_parametros = new ArrayList<String>(); }
			: var_opcional par=identificador {$parametros.addAll($par.array_id);}
			  mais_ident ':' tipo_param=tipo_estendido {$tipo_parametros.add($tipo_param.tipo_var);}
			  mais_parametros {$parametros.addAll($mais_parametros.parametros);
			  		   $tipo_parametros.addAll($mais_parametros.tipo_parametros);}
			;
			
var_opcional : 		'var'?
			;

// O retorno de varios outros parametros e seus tipos acumulados de algumas recursoes.
mais_parametros returns [ List<String> parametros, List<String> tipo_parametros ]
@init { $parametros = new ArrayList<String>(); 
	$tipo_parametros = new ArrayList<String>(); }
			: (',' parametro {$parametros.addAll($parametro.parametros);
					  $tipo_parametros.addAll($parametro.tipo_parametros);})?
                        ;
			
declaracoes_locais : 	(declaracao_local)*
			;
			
corpo : declaracoes_locais comandos
			;
			
comandos returns [int contemRetorne]
@init { $contemRetorne = 0;}                        
                        : (retornoCMD = cmd {$contemRetorne = $retornoCMD.contemRetorne;})*
			;
			
cmd returns [int contemRetorne]
@init { $contemRetorne = 0;}   
			: 'leia' '(' identificador mais_ident ')' 
			| 'escreva' '(' expressao mais_expressao ')' 
			| 'se' expressao 'entao' comandos senao_opcional 'fim_se' 
			| 'caso' exp_aritmetica 'seja' selecao senao_opcional 'fim_caso' 
			| 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para' 
			| 'enquanto' expressao 'faca' comandos 'fim_enquanto' 
			| 'faca' comandos 'ate' expressao 
			| '^' IDENT outros_ident dimensao '<-' expressao 
			| IDENT chamada_atribuicao 
			| 'retorne' expressao {$contemRetorne = 1;}
			;
			
mais_expressao returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
	$tipo_par = new ArrayList<String>(); 
	$linha = new ArrayList<Integer>();}
                        : (',' expressao {$nome_par.addAll($expressao.nome_par);
                                           $tipo_par.addAll($expressao.tipo_par);
										   $linha.addAll($expressao.linha);})*
			;
			
senao_opcional : 	('senao' comandos)?
			;
			
chamada_atribuicao returns [int tipoChA]: 	'(' argumentos_opcional ')' {$tipoChA = 1;}
			| outros_ident dimensao '<-' expressao {$tipoChA = 2;}
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
			
exp_aritmetica returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ] 
@init { $nome_par = new ArrayList<String>(); 
	$tipo_par = new ArrayList<String>();
	$linha = new ArrayList<Integer>();	}
                        : termo {$nome_par.addAll($termo.nome_par);
                                 $tipo_par.addAll($termo.tipo_par);
								 $linha.addAll($termo.linha);} 
                          outros_termos {$nome_par.addAll($outros_termos.nome_par);
                                         $tipo_par.addAll($outros_termos.tipo_par);
										 $linha.addAll($outros_termos.linha);} 
			;
			
op_multiplicacao : 	'*' 
			| '/'
			;
			
op_adicao : 		'+' 
			| '-'
			;
			
termo returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ] 
@init { $nome_par = new ArrayList<String>(); 
	$tipo_par = new ArrayList<String>();
	$linha = new ArrayList<Integer>();	}
                        : fator    {$nome_par.addAll($fator.nome_par);
                                   $tipo_par.addAll($fator.tipo_par);
								   $linha.addAll($fator.linha);} 
                          outros_fatores {$nome_par.addAll($outros_fatores.nome_par);
                                          $tipo_par.addAll($outros_fatores.tipo_par);
										  $linha.addAll($outros_fatores.linha);} 
			;
			
outros_termos returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 	
	 $tipo_par = new ArrayList<String>();
	$linha = new ArrayList<Integer>();		}
                        : (op_adicao termo {$nome_par.addAll($termo.nome_par);
                                            $tipo_par.addAll($termo.tipo_par);
											$linha.addAll($termo.linha);} )*
			;
			
fator returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 	$tipo_par = new ArrayList<String>();
	$linha = new ArrayList<Integer>();	}
                        : parcela {$nome_par.add($parcela.nome_par);
                                   $tipo_par.add($parcela.tipo_par);
								   $linha.add($parcela.linha);}
                          outras_parcelas {$nome_par.addAll($outras_parcelas.nome_par);
                                           $tipo_par.addAll($outras_parcelas.tipo_par);
										   $linha.addAll($outras_parcelas.linha);}
			;
			
outros_fatores returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
	 $tipo_par = new ArrayList<String>();
	  $linha = new ArrayList<Integer>();	}
                        : (op_multiplicacao fator {$nome_par.addAll($fator.nome_par);
                                                   $tipo_par.addAll($fator.tipo_par);
												   $linha.addAll($fator.linha);})*
			;
			
parcela returns [String tipo_par, String nome_par, int linha] :
                        op_unario p = parcela_unario {$tipo_par = $p.tipo_par; $nome_par = $p.nome_par; $linha = $p.linha;}
			| p1 = parcela_nao_unario {$tipo_par = $p1.tipo_par; $nome_par = $p1.nome_par; $linha = $p1.linha;}
			;
			
parcela_unario returns [String tipo_par, String nome_par, int linha] :
                        '^' n = IDENT {$nome_par = $n.getText(); $linha = $n.getLine();} outros_ident dimensao 
			| n = IDENT {$nome_par = $n.getText(); $linha = $n.getLine();} chamada_partes 
			| NUM_INT {$tipo_par = "inteiro";} 
			| NUM_REAL {$tipo_par = "real";}
			| '(' expressao ')'
			;
			
parcela_nao_unario returns [String tipo_par, String nome_par, int linha] : 	
                        '&' n = IDENT {$nome_par = $n.getText(); $linha = $n.getLine();} outros_ident dimensao 
			| c = CADEIA {$tipo_par = "literal"; $linha = $c.getLine();}
			;
			
outras_parcelas returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
	 $tipo_par = new ArrayList<String>();
	 $linha = new ArrayList<Integer>();	}
                        : ('%' p = parcela{$nome_par.add($p.nome_par);
                                           $tipo_par.add($p.tipo_par);
										   $linha.add($p.linha);})*
                          
			;
			
chamada_partes returns [ int tipoChamadaPartes ]:
                 	'(' expressao mais_expressao ')' { $tipoChamadaPartes = 1; }
			|  outros_ident dimensao { $tipoChamadaPartes = 2; }
			|  /* vazio*/ { $tipoChamadaPartes = 3; }
			;
			
exp_relacional returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ] 
@init { $nome_par = new ArrayList<String>(); 
	 $tipo_par = new ArrayList<String>();
	 $linha = new ArrayList<Integer>();} 
                        : exp_aritmetica {$nome_par.addAll($exp_aritmetica.nome_par);
                                          $tipo_par.addAll($exp_aritmetica.tipo_par);
										  $linha.addAll($exp_aritmetica.linha);} 
                          op_opcional {$nome_par.addAll($op_opcional.nome_par);
                                       $tipo_par.addAll($op_opcional.tipo_par);
									   $linha.addAll($op_opcional.linha);} 
			;
			
op_opcional returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>();
	 $tipo_par = new ArrayList<String>();
	 $linha = new ArrayList<Integer>();}
    : 		(op_relacional exp_aritmetica {$nome_par.addAll($exp_aritmetica.nome_par);
                                               $tipo_par.addAll($exp_aritmetica.tipo_par);
											   $linha.addAll($exp_aritmetica.linha);}
                )?
			;
			
op_relacional : 	'=' 
			| '<>' 
			| '>=' 
			| '<=' 
			| '>' 
			| '<'
			;
			
expressao returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
$linha = new ArrayList<Integer>(); }
                        : termo_logico {$nome_par.addAll($termo_logico.nome_par);
                                               $tipo_par.addAll($termo_logico.tipo_par);
											   $linha.addAll($termo_logico.linha);}
                          outros_termos_logicos {$nome_par.addAll($outros_termos_logicos.nome_par);
                                                     $tipo_par.addAll($outros_termos_logicos.tipo_par);
													 $linha.addAll($outros_termos_logicos.linha);}
			;
			
op_nao : 		'nao'?
			;
			
termo_logico returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
 $linha = new ArrayList<Integer>(); }
                        : fator_logico  {$nome_par.addAll($fator_logico.nome_par);
                                               $tipo_par.addAll($fator_logico.tipo_par);
											   $linha.addAll($fator_logico.linha);} 
                          outros_fatores_logicos {$nome_par.addAll($outros_fatores_logicos.nome_par);
                                                                $tipo_par.addAll($outros_fatores_logicos.tipo_par);
																$linha.addAll($outros_fatores_logicos.linha);}
			;
			
outros_termos_logicos returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
 $linha = new ArrayList<Integer>(); }
                        : ('ou' termo_logico {$nome_par.addAll($termo_logico.nome_par);
                                               $tipo_par.addAll($termo_logico.tipo_par);
											   $linha.addAll($termo_logico.linha);})*
			;
			
outros_fatores_logicos returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
 $linha = new ArrayList<Integer>(); }
                         : ('e' fator_logico {$nome_par.addAll($fator_logico.nome_par);
                                               $tipo_par.addAll($fator_logico.tipo_par);
											   $linha.addAll($fator_logico.linha);}
                         )*
			;
			
fator_logico returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
 $linha = new ArrayList<Integer>(); }
                        : op_nao parcela_logica {$nome_par.addAll($parcela_logica.nome_par);
                                               $tipo_par.addAll($parcela_logica.tipo_par);
											   $linha.addAll($parcela_logica.linha);}
			;
			
parcela_logica returns [ List<String> tipo_par, List<String> nome_par, List<Integer> linha ]	
@init { $nome_par = new ArrayList<String>(); 
 $tipo_par = new ArrayList<String>();
 $linha = new ArrayList<Integer>(); }
                        : 'verdadeiro' {$tipo_par.add("logico");} 
			| 'falso' {$tipo_par.add("logico");} 
			| exp_relacional {$nome_par.addAll($exp_relacional.nome_par);
                                               $tipo_par.addAll($exp_relacional.tipo_par);
											   $linha.addAll($exp_relacional.linha);}
			;
