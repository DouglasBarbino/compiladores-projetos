<<<<<<< HEAD
T__29=30
T__28=31
T__27=32
T__26=33
T__25=34
T__24=35
T__23=36
T__22=37
T__21=38
T__20=39
T__9=50
T__8=51
T__7=52
T__6=53
T__5=54
T__4=55
T__55=4
T__56=3
T__19=40
T__57=2
T__58=1
T__51=8
T__16=43
T__52=7
T__15=44
T__53=6
T__18=41
T__54=5
T__17=42
T__12=47
T__11=48
T__14=45
NUM_REAL=62
T__13=46
COMENTARIO=64
T__10=49
IDENT=60
CADEIA=63
T__50=9
T__42=17
T__43=16
T__40=19
T__41=18
T__46=13
T__47=12
T__44=15
T__45=14
T__48=11
T__49=10
ESPACOS=65
T__30=29
T__31=28
T__32=27
T__33=26
T__34=25
T__35=24
T__36=23
T__37=22
T__38=21
T__39=20
T__1=58
T__0=59
NUM_INT=61
T__3=56
T__2=57
'procedimento'=59
'>='=58
'/'=57
'declare'=56
'seja'=55
'registro'=54
'>'=53
'e'=52
'<>'=51
'ou'=50
'+'=49
'.'=48
'^'=47
'falso'=46
'<-'=44
'fim_algoritmo'=45
'algoritmo'=43
'logico'=42
'se'=41
'var'=40
'entao'=37
':'=38
'('=39
'-'=36
','=35
'verdadeiro'=34
'fim_para'=33
']'=32
'fim_funcao'=31
'..'=27
'retorne'=28
'fim_caso'=29
'real'=30
'escreva'=26
'fim_se'=25
'funcao'=24
'literal'=23
'caso'=22
'leia'=21
'='=20
'senao'=19
')'=18
'enquanto'=17
'%'=16
'para'=15
'fim_registro'=14
'inteiro'=13
'ate'=12
'fim_enquanto'=11
'<='=10
'fim_procedimento'=9
'<'=8
'tipo'=7
'*'=5
'['=6
'faca'=4
'nao'=3
'constante'=2
'&'=1
=======
grammar LA;

//parte lexica

IDENT :  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
NUM_INT : ('0'..'9')+;
NUM_REAL: ('0'..'9')+ '.' ('0'..'9')+;
CADEIA : '"' ( ~('"') )* '"';
COMENTARIO : '{' ~('{' | '}')* '}' {skip();};
ESPACOS	: (' ' | '\t' | '\r' | '\n') {skip();};

programa : declaracoes 'algoritmo' corpo 'fim_algoritmo';
declaracoes : (decl_local_global)*; //corrigido
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
tipo_basico : 'literal' | 'inteiro' | 'real' | 'logico';
tipo_basico_ident : tipo_basico | IDENT;
tipo_estendido : ponteiros_opcionais tipo_basico_ident;
valor_constante : CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';
registro : 'registro' variavel mais_variaveis 'fim_registro';
declaracao_global : 'procedimento' IDENT '(' parametros_opcional ')' declaracoes_locais comandos 'fim_procedimento'
		| 'funcao' IDENT '(' parametros_opcional ')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao';

parametros_opcional : (parametro)?;
parametro : var_opcional identificador mais_ident ':' tipo_estendido mais_parametros;
var_opcional : 'var'?;

//D
mais_parametros : (',' parametro)?;
declaracoes_locais : (declaracao_local)*;
corpo : declaracoes_locais comandos;
comandos : (cmd)*;

cmd : 'leia' '(' identificador mais_ident ')' |
	 'escreva' '(' expressao mais_expressao ')' |
	 'se' expressao 'entao' comandos senao_opcional 'fim_se' |
 	'caso' exp_aritmetica 'seja' selecao senao_opcional 'fim_caso' |
	'para' IDENT '<''-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para' |
	'enquanto' expressao 'faca' comandos 'fim_enquanto' |
	 'faca' comandos 'ate' expressao |
	 '^' IDENT outros_ident dimensao '<''-' expressao |
	IDENT chamada_atribuicao |
	'retorne' expressao;
//D
mais_expressao : (',' expressao)*;
senao_opcional : ('senao' comandos)?;
chamada_atribuicao : '(' argumentos_opcional ')' | 
		     outros_ident dimensao '<-' expressao;
argumentos_opcional : (expressao mais_expressao)?;
selecao : constantes ':' comandos mais_selecao;
mais_selecao : (selecao)?;
constantes : numero_intervalo mais_constantes;
mais_constantes : (',' constantes)?;
numero_intervalo : op_unario NUM_INT intervalo_opcional;
intervalo_opcional : ('..' op_unario NUM_INT)?;
op_unario : ('-')?;
exp_aritmetica : termo outros_termos;
op_multiplicacao : '*' | '/';
op_adicao : '+' | '-';
termo : fator outros_fatores;
//D
outros_termos : (op_adicao termo)*;
fator : parcela outras_parcelas;
//D
outros_fatores : (op_multiplicacao fator)*;
parcela : op_unario parcela_unario | parcela_nao_unario;
parcela_unario : '^' IDENT outros_ident dimensao | IDENT chamada_partes | NUM_INT | NUM_REAL | '(' expressao ')';
parcela_nao_unario : '&' IDENT outros_ident dimensao | CADEIA;
//D
outras_parcelas : ('%' parcela)*;
chamada_partes :   '(' expressao mais_expressao ')' |
		  outros_ident dimensao | 
                        /* vazio*/;
exp_relacional : exp_aritmetica op_opcional;
//D
op_opcional : (op_relacional exp_aritmetica)?;
op_relacional : '=' | '<>' | '>=' | '<=' | '>' | '<';
expressao : termo_logico outros_termos_logicos;
//D
op_nao : 'nao'?;
termo_logico : fator_logico outros_fatores_logicos;
outros_termos_logicos : ('ou' termo_logico)*;
outros_fatores_logicos : ('e' fator_logico)*;
fator_logico : op_nao parcela_logica;
parcela_logica : 'verdadeiro' | 
		 'falso' | 
		 exp_relacional;

    




>>>>>>> 834b8b8c2886f05978848b4d33a8503e9d7a6185
