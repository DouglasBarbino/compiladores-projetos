grammar LL;

//parte lexica

IDENT :  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
NUM_INT : ('0'..'9')+;
NUM_REAL: ('0'..'9')+ '.' ('0'..'9')+;
CADEIA : '"' ( ~('"') )* '"';


programa : declaracoes 'algoritmo' corpo 'fim_algoritmo';
declaracoes : (decl_local_global declaracoes)*; //revisar se essa é a melhor forma
decl_local_global : declaracao_local | declaracao_global;
declaracao_local : 'declare' variavel | 
		 'constante' IDENT ':' tipo_basico '=' valor_constante |
		 'tipo' IDENT ':' tipo;
variavel : IDENT dimensao mais_var ':' tipo;
mais_var : (',' IDENT dimensao mais_var)*;
identificador : ponteiros_opcionais IDENT dimensao outros_ident;
ponteiros_opcionais:  ('^' ponteiros_opcionais)*;


outros_ident : ('.' identificador)*;
dimensao : ('[' exp_aritmetica ']' dimensao)*;
tipo : registro | tipo_estendido;
mais_ident : (',' identificador mais_ident)*;
mais_variaveis : (variavel mais_variaveis)*;
tipo_basico : literal | inteiro | real | logico;
tipo_basico_ident : tipo_basico | IDENT;
tipo_estendido : ponteiros_opcionais tipo_basico_ident;
valor_constante : CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';
registro : 'registro' variavel mais_variaveis 'fim_registro';
declaracao_global : 'procedimento' IDENT '(' parametros_opcional ')' declaracoes_locais comandos 'fim_procedimento';
		| 'funcao' IDENT '(' parametros_opcional ')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao';

parametros_opcional : (parametro)*;
parametro : var_opcional identificador mais_ident ':' tipo_estendido mais_parametros;
var_opcional : (var)*;


23. <mais_parametros>
::= , <parametro> | ε
24. <declaracoes_locais>
::= <declaracao_local> <declaracoes_locais> | ε
25. <corpo>
::= <declaracoes_locais> <comandos>
26. <comandos>
::= <cmd> <comandos> | ε


cmd : 'leia' '(' identificador mais_ident ')' |
	 'escreva' '(' expressao mais_expressao ')' |
	 'se' expressao 'entao' comandos senao_opcional 'fim_se' |
 	'caso exp_aritmetica 'seja' selecao senao_opcional 'fim_caso' |
	'para' IDENT '<''-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para' |
	'enquanto' expressao 'faca' comandos 'fim_enquanto' |
	 'faca' comandos 'ate' expressao |
	 '^' IDENT outros_ident dimensao '<''-' expressao |
	IDENT chamada_atribuicao |
	'retorne' expressao;

28. <mais_expressao>
::= , <expressao> <mais_expressao> | ε
29. <senao_opcional>
::= senao <comandos> | ε

30. <chamada_atribuicao>
::= ( <argumentos_opcional> ) | <outros_ident> <dimensao> <- <expressao>
31. <argumentos_opcional> ::= <expressao> <mais_expressao> | ε
32. <selecao>
::= <constantes> : <comandos> <mais_selecao>
33. <mais_selecao>
::= <selecao> | ε
34. <constantes>
::= <numero_intervalo> <mais_constantes>
35. <mais_constantes>
::= , <constantes> | ε
36. <numero_intervalo>
::= <op_unario> NUM_INT <intervalo_opcional>
37. <intervalo_opcional>
::= .. <op_unario> NUM_INT | ε
38. <op_unario>
::= - | ε

exp_aritmetica : termo outros_termos;

op_multiplicacao : '*' | '/';
op_adicao : '+' | '-';

termo : fator outros_fatores;

43. <outros_termos>
::= <op_adicao> <termo> <outros_termos> | ε

fator : parcela outras_parcelas;

45. <outros_fatores>
::= <op_multiplicacao> <fator> <outros_fatores> | ε

parcela : op_unario parcela_unario | parcela_nao_unario;
parcela_unario : '^' IDENT outros_ident dimensao | IDENT chamada_partes | NUM_INT | NUM_REAL | '(' expressao ')';
parcela_nao_unario : '&' IDENT outros_ident dimensao | CADEIA;

49. <outras_parcelas>
::= % <parcela> <outras_parcelas> | ε
50. <chamada_partes>
::= ( <expressao> <mais_expressao> ) | <outros_ident> <dimensao> | ε

exp_relacional : exp_aritmetica op_opcional;

52. <op_opcional>
::= <op_relacional> <exp_aritmetica> | ε

fragment op_relacional : '=' | '<>' | '>=' | '<=' | '>' | '<';

expressao : termo_logico outros_termos_logicos;

55. <op_nao>
::= nao | ε

56. <termo_logico>
::= <fator_logico> <outros_fatores_logicos>
57. <outros_termos_logicos> ::= ou <termo_logico> <outros_termos_logicos> | ε
58. <outros_fatores_logicos> ::= e <fator_logico> <outros_fatores_logicos> | ε
59. <fator_logico>
::= <op_nao> <parcela_logica>
60. <parcela_logica>
::= verdadeiro | falso | <exp_relacional>


    




