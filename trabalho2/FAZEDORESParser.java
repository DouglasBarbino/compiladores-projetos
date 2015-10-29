// Generated from FAZEDORES.g4 by ANTLR 4.2.2
package trabalho1;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FAZEDORESParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__76=1, T__75=2, T__74=3, T__73=4, T__72=5, T__71=6, T__70=7, T__69=8, 
		T__68=9, T__67=10, T__66=11, T__65=12, T__64=13, T__63=14, T__62=15, T__61=16, 
		T__60=17, T__59=18, T__58=19, T__57=20, T__56=21, T__55=22, T__54=23, 
		T__53=24, T__52=25, T__51=26, T__50=27, T__49=28, T__48=29, T__47=30, 
		T__46=31, T__45=32, T__44=33, T__43=34, T__42=35, T__41=36, T__40=37, 
		T__39=38, T__38=39, T__37=40, T__36=41, T__35=42, T__34=43, T__33=44, 
		T__32=45, T__31=46, T__30=47, T__29=48, T__28=49, T__27=50, T__26=51, 
		T__25=52, T__24=53, T__23=54, T__22=55, T__21=56, T__20=57, T__19=58, 
		T__18=59, T__17=60, T__16=61, T__15=62, T__14=63, T__13=64, T__12=65, 
		T__11=66, T__10=67, T__9=68, T__8=69, T__7=70, T__6=71, T__5=72, T__4=73, 
		T__3=74, T__2=75, T__1=76, T__0=77, IDENT=78, NUM_INT=79, NUM_REAL=80, 
		CADEIA=81, COMENTARIO=82, ESPACOS=83, COMENTARIO_ERRADO=84, ERROR=85, 
		COR=86;
	public static final String[] tokenNames = {
		"<INVALID>", "'&'", "'constante'", "'nao'", "'faca'", "'['", "'*'", "'tipo'", 
		"'comando_loop'", "'<'", "'fim_procedimento'", "'esperar'", "'desligar'", 
		"'<='", "'LCD'", "'fim_enquanto'", "'fim_comando_loop'", "'ate'", "'inteiro'", 
		"'fim_registro'", "'para'", "'%'", "'enquanto'", "')'", "'fim_comando_setup'", 
		"'senao'", "'som'", "'='", "'ler'", "'leia'", "'caso'", "'literal'", "'funcao'", 
		"'fim_se'", "'escreva'", "'retorne'", "'fim_caso'", "'real'", "'..'", 
		"'fim_funcao'", "']'", "'led'", "'fim_para'", "'ligar'", "'verdadeiro'", 
		"','", "'-'", "'entao'", "':'", "'('", "'var'", "'comando_setup'", "'potenciometro'", 
		"'se'", "'logico'", "'luz'", "'<-'", "'falso'", "'ativar'", "'definirCor'", 
		"'LCD,'", "'^'", "'.'", "'+'", "'ou'", "'<>'", "'e'", "'escrever'", "'botao'", 
		"'>'", "'registro'", "'seja'", "'sensortoque'", "'declare'", "'/'", "'lcd'", 
		"'>='", "'procedimento'", "IDENT", "NUM_INT", "NUM_REAL", "CADEIA", "COMENTARIO", 
		"ESPACOS", "COMENTARIO_ERRADO", "ERROR", "COR"
	};
	public static final int
		RULE_programa = 0, RULE_declaracoes = 1, RULE_decl_local_global = 2, RULE_declaracao_local = 3, 
		RULE_variavel = 4, RULE_mais_var = 5, RULE_identificador = 6, RULE_ponteiros_opcionais = 7, 
		RULE_outros_ident = 8, RULE_dimensao = 9, RULE_tipo = 10, RULE_mais_ident = 11, 
		RULE_mais_variaveis = 12, RULE_tipo_basico = 13, RULE_tipo_basico_ident = 14, 
		RULE_tipo_estendido = 15, RULE_valor_constante = 16, RULE_registro = 17, 
		RULE_declaracao_global = 18, RULE_parametros_opcional = 19, RULE_parametro = 20, 
		RULE_var_opcional = 21, RULE_mais_parametros = 22, RULE_declaracoes_locais = 23, 
		RULE_corpo = 24, RULE_comandos = 25, RULE_cmd = 26, RULE_comandosArduino = 27, 
		RULE_comandoSetup = 28, RULE_comandoLoop = 29, RULE_cmdLoop = 30, RULE_dispositivo = 31, 
		RULE_dispositivoSaida = 32, RULE_dispositivoEntrada = 33, RULE_comandoLCD = 34, 
		RULE_mais_expressao = 35, RULE_senao_opcional = 36, RULE_chamada_atribuicao = 37, 
		RULE_argumentos_opcional = 38, RULE_selecao = 39, RULE_mais_selecao = 40, 
		RULE_constantes = 41, RULE_mais_constantes = 42, RULE_numero_intervalo = 43, 
		RULE_intervalo_opcional = 44, RULE_op_unario = 45, RULE_exp_aritmetica = 46, 
		RULE_op_multiplicacao = 47, RULE_op_adicao = 48, RULE_termo = 49, RULE_outros_termos = 50, 
		RULE_fator = 51, RULE_outros_fatores = 52, RULE_parcela = 53, RULE_parcela_unario = 54, 
		RULE_parcela_nao_unario = 55, RULE_outras_parcelas = 56, RULE_chamada_partes = 57, 
		RULE_exp_relacional = 58, RULE_op_opcional = 59, RULE_op_relacional = 60, 
		RULE_expressao = 61, RULE_op_nao = 62, RULE_termo_logico = 63, RULE_outros_termos_logicos = 64, 
		RULE_outros_fatores_logicos = 65, RULE_fator_logico = 66, RULE_parcela_logica = 67;
	public static final String[] ruleNames = {
		"programa", "declaracoes", "decl_local_global", "declaracao_local", "variavel", 
		"mais_var", "identificador", "ponteiros_opcionais", "outros_ident", "dimensao", 
		"tipo", "mais_ident", "mais_variaveis", "tipo_basico", "tipo_basico_ident", 
		"tipo_estendido", "valor_constante", "registro", "declaracao_global", 
		"parametros_opcional", "parametro", "var_opcional", "mais_parametros", 
		"declaracoes_locais", "corpo", "comandos", "cmd", "comandosArduino", "comandoSetup", 
		"comandoLoop", "cmdLoop", "dispositivo", "dispositivoSaida", "dispositivoEntrada", 
		"comandoLCD", "mais_expressao", "senao_opcional", "chamada_atribuicao", 
		"argumentos_opcional", "selecao", "mais_selecao", "constantes", "mais_constantes", 
		"numero_intervalo", "intervalo_opcional", "op_unario", "exp_aritmetica", 
		"op_multiplicacao", "op_adicao", "termo", "outros_termos", "fator", "outros_fatores", 
		"parcela", "parcela_unario", "parcela_nao_unario", "outras_parcelas", 
		"chamada_partes", "exp_relacional", "op_opcional", "op_relacional", "expressao", 
		"op_nao", "termo_logico", "outros_termos_logicos", "outros_fatores_logicos", 
		"fator_logico", "parcela_logica"
	};

	@Override
	public String getGrammarFileName() { return "FAZEDORES.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private void stop(String msg)
		{
			throw new ParseCancellationException(msg);
		}
	   
	        PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
		
		Saida outSemantico = new Saida();

	        private void erroSemantico(String msg) {
			outSemantico.println(msg);        
		}

	public FAZEDORESParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramaContext extends ParserRuleContext {
		public DeclaracoesContext declaracoes() {
			return getRuleContext(DeclaracoesContext.class,0);
		}
		public ComandoSetupContext comandoSetup() {
			return getRuleContext(ComandoSetupContext.class,0);
		}
		public ComandoLoopContext comandoLoop() {
			return getRuleContext(ComandoLoopContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); declaracoes();
			setState(137); match(51);
			setState(138); comandoSetup();
			setState(139); match(24);
			setState(140); match(8);
			setState(141); comandoLoop();
			setState(142); match(16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaracoesContext extends ParserRuleContext {
		public List<Decl_local_globalContext> decl_local_global() {
			return getRuleContexts(Decl_local_globalContext.class);
		}
		public Decl_local_globalContext decl_local_global(int i) {
			return getRuleContext(Decl_local_globalContext.class,i);
		}
		public DeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDeclaracoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDeclaracoes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDeclaracoes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracoesContext declaracoes() throws RecognitionException {
		DeclaracoesContext _localctx = new DeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracoes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 7) | (1L << 32))) != 0) || _la==73 || _la==77) {
				{
				{
				setState(144); decl_local_global();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decl_local_globalContext extends ParserRuleContext {
		public Declaracao_globalContext declaracao_global() {
			return getRuleContext(Declaracao_globalContext.class,0);
		}
		public Declaracao_localContext declaracao_local() {
			return getRuleContext(Declaracao_localContext.class,0);
		}
		public Decl_local_globalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl_local_global; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDecl_local_global(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDecl_local_global(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDecl_local_global(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decl_local_globalContext decl_local_global() throws RecognitionException {
		Decl_local_globalContext _localctx = new Decl_local_globalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl_local_global);
		try {
			setState(152);
			switch (_input.LA(1)) {
			case 2:
			case 7:
			case 73:
				enterOuterAlt(_localctx, 1);
				{
				setState(150); declaracao_local();
				}
				break;
			case 32:
			case 77:
				enterOuterAlt(_localctx, 2);
				{
				setState(151); declaracao_global();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declaracao_localContext extends ParserRuleContext {
		public Tipo_basicoContext tipo_basico() {
			return getRuleContext(Tipo_basicoContext.class,0);
		}
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public Valor_constanteContext valor_constante() {
			return getRuleContext(Valor_constanteContext.class,0);
		}
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public Declaracao_localContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao_local; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDeclaracao_local(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDeclaracao_local(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDeclaracao_local(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracao_localContext declaracao_local() throws RecognitionException {
		Declaracao_localContext _localctx = new Declaracao_localContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracao_local);
		try {
			setState(167);
			switch (_input.LA(1)) {
			case 73:
				enterOuterAlt(_localctx, 1);
				{
				setState(154); match(73);
				setState(155); variavel();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156); match(2);
				setState(157); match(IDENT);
				setState(158); match(48);
				setState(159); tipo_basico();
				setState(160); match(27);
				setState(161); valor_constante();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 3);
				{
				setState(163); match(7);
				setState(164); match(IDENT);
				setState(165); match(48);
				setState(166); tipo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariavelContext extends ParserRuleContext {
		public Mais_varContext mais_var() {
			return getRuleContext(Mais_varContext.class,0);
		}
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public VariavelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variavel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterVariavel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitVariavel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitVariavel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariavelContext variavel() throws RecognitionException {
		VariavelContext _localctx = new VariavelContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variavel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(IDENT);
			setState(170); dimensao();
			setState(171); mais_var();
			setState(172); match(48);
			setState(173); tipo();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_varContext extends ParserRuleContext {
		public DimensaoContext dimensao(int i) {
			return getRuleContext(DimensaoContext.class,i);
		}
		public List<DimensaoContext> dimensao() {
			return getRuleContexts(DimensaoContext.class);
		}
		public List<TerminalNode> IDENT() { return getTokens(FAZEDORESParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(FAZEDORESParser.IDENT, i);
		}
		public Mais_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_varContext mais_var() throws RecognitionException {
		Mais_varContext _localctx = new Mais_varContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_mais_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==45) {
				{
				{
				setState(175); match(45);
				setState(176); match(IDENT);
				setState(177); dimensao();
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentificadorContext extends ParserRuleContext {
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public Ponteiros_opcionaisContext ponteiros_opcionais() {
			return getRuleContext(Ponteiros_opcionaisContext.class,0);
		}
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public IdentificadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identificador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterIdentificador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitIdentificador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitIdentificador(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentificadorContext identificador() throws RecognitionException {
		IdentificadorContext _localctx = new IdentificadorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identificador);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); ponteiros_opcionais();
			setState(184); match(IDENT);
			setState(185); dimensao();
			setState(186); outros_ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ponteiros_opcionaisContext extends ParserRuleContext {
		public Ponteiros_opcionaisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ponteiros_opcionais; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterPonteiros_opcionais(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitPonteiros_opcionais(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitPonteiros_opcionais(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ponteiros_opcionaisContext ponteiros_opcionais() throws RecognitionException {
		Ponteiros_opcionaisContext _localctx = new Ponteiros_opcionaisContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ponteiros_opcionais);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==61) {
				{
				{
				setState(188); match(61);
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outros_identContext extends ParserRuleContext {
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public Outros_identContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutros_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutros_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutros_ident(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outros_identContext outros_ident() throws RecognitionException {
		Outros_identContext _localctx = new Outros_identContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_outros_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if (_la==62) {
				{
				setState(194); match(62);
				setState(195); identificador();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DimensaoContext extends ParserRuleContext {
		public List<Exp_aritmeticaContext> exp_aritmetica() {
			return getRuleContexts(Exp_aritmeticaContext.class);
		}
		public Exp_aritmeticaContext exp_aritmetica(int i) {
			return getRuleContext(Exp_aritmeticaContext.class,i);
		}
		public DimensaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dimensao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDimensao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDimensao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDimensao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DimensaoContext dimensao() throws RecognitionException {
		DimensaoContext _localctx = new DimensaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dimensao);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(198); match(5);
					setState(199); exp_aritmetica();
					setState(200); match(40);
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public RegistroContext registro() {
			return getRuleContext(RegistroContext.class,0);
		}
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTipo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tipo);
		try {
			setState(209);
			switch (_input.LA(1)) {
			case 70:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); registro();
				}
				break;
			case 18:
			case 31:
			case 37:
			case 54:
			case 61:
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(208); tipo_estendido();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_identContext extends ParserRuleContext {
		public IdentificadorContext identificador(int i) {
			return getRuleContext(IdentificadorContext.class,i);
		}
		public List<IdentificadorContext> identificador() {
			return getRuleContexts(IdentificadorContext.class);
		}
		public Mais_identContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_ident(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_identContext mais_ident() throws RecognitionException {
		Mais_identContext _localctx = new Mais_identContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_mais_ident);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==45) {
				{
				{
				setState(211); match(45);
				setState(212); identificador();
				}
				}
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_variaveisContext extends ParserRuleContext {
		public List<VariavelContext> variavel() {
			return getRuleContexts(VariavelContext.class);
		}
		public VariavelContext variavel(int i) {
			return getRuleContext(VariavelContext.class,i);
		}
		public Mais_variaveisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_variaveis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_variaveis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_variaveis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_variaveis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_variaveisContext mais_variaveis() throws RecognitionException {
		Mais_variaveisContext _localctx = new Mais_variaveisContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_mais_variaveis);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(218); variavel();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tipo_basicoContext extends ParserRuleContext {
		public Tipo_basicoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_basico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTipo_basico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTipo_basico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTipo_basico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_basicoContext tipo_basico() throws RecognitionException {
		Tipo_basicoContext _localctx = new Tipo_basicoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_tipo_basico);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 18) | (1L << 31) | (1L << 37) | (1L << 54))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tipo_basico_identContext extends ParserRuleContext {
		public Tipo_basicoContext tipo_basico() {
			return getRuleContext(Tipo_basicoContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public Tipo_basico_identContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_basico_ident; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTipo_basico_ident(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTipo_basico_ident(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTipo_basico_ident(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_basico_identContext tipo_basico_ident() throws RecognitionException {
		Tipo_basico_identContext _localctx = new Tipo_basico_identContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_tipo_basico_ident);
		try {
			setState(228);
			switch (_input.LA(1)) {
			case 18:
			case 31:
			case 37:
			case 54:
				enterOuterAlt(_localctx, 1);
				{
				setState(226); tipo_basico();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(227); match(IDENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tipo_estendidoContext extends ParserRuleContext {
		public Tipo_basico_identContext tipo_basico_ident() {
			return getRuleContext(Tipo_basico_identContext.class,0);
		}
		public Ponteiros_opcionaisContext ponteiros_opcionais() {
			return getRuleContext(Ponteiros_opcionaisContext.class,0);
		}
		public Tipo_estendidoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_estendido; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTipo_estendido(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTipo_estendido(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTipo_estendido(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_estendidoContext tipo_estendido() throws RecognitionException {
		Tipo_estendidoContext _localctx = new Tipo_estendidoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tipo_estendido);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230); ponteiros_opcionais();
			setState(231); tipo_basico_ident();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Valor_constanteContext extends ParserRuleContext {
		public TerminalNode NUM_INT() { return getToken(FAZEDORESParser.NUM_INT, 0); }
		public TerminalNode NUM_REAL() { return getToken(FAZEDORESParser.NUM_REAL, 0); }
		public TerminalNode CADEIA() { return getToken(FAZEDORESParser.CADEIA, 0); }
		public Valor_constanteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valor_constante; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterValor_constante(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitValor_constante(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitValor_constante(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Valor_constanteContext valor_constante() throws RecognitionException {
		Valor_constanteContext _localctx = new Valor_constanteContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_valor_constante);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !(((((_la - 44)) & ~0x3f) == 0 && ((1L << (_la - 44)) & ((1L << (44 - 44)) | (1L << (57 - 44)) | (1L << (NUM_INT - 44)) | (1L << (NUM_REAL - 44)) | (1L << (CADEIA - 44)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegistroContext extends ParserRuleContext {
		public VariavelContext variavel() {
			return getRuleContext(VariavelContext.class,0);
		}
		public Mais_variaveisContext mais_variaveis() {
			return getRuleContext(Mais_variaveisContext.class,0);
		}
		public RegistroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_registro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterRegistro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitRegistro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitRegistro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegistroContext registro() throws RecognitionException {
		RegistroContext _localctx = new RegistroContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_registro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235); match(70);
			setState(236); variavel();
			setState(237); mais_variaveis();
			setState(238); match(19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declaracao_globalContext extends ParserRuleContext {
		public Declaracoes_locaisContext declaracoes_locais() {
			return getRuleContext(Declaracoes_locaisContext.class,0);
		}
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public ComandosContext comandos() {
			return getRuleContext(ComandosContext.class,0);
		}
		public Parametros_opcionalContext parametros_opcional() {
			return getRuleContext(Parametros_opcionalContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public Declaracao_globalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao_global; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDeclaracao_global(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDeclaracao_global(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDeclaracao_global(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracao_globalContext declaracao_global() throws RecognitionException {
		Declaracao_globalContext _localctx = new Declaracao_globalContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_declaracao_global);
		try {
			setState(260);
			switch (_input.LA(1)) {
			case 77:
				enterOuterAlt(_localctx, 1);
				{
				setState(240); match(77);
				setState(241); match(IDENT);
				setState(242); match(49);
				setState(243); parametros_opcional();
				setState(244); match(23);
				setState(245); declaracoes_locais();
				setState(246); comandos();
				setState(247); match(10);
				}
				break;
			case 32:
				enterOuterAlt(_localctx, 2);
				{
				setState(249); match(32);
				setState(250); match(IDENT);
				setState(251); match(49);
				setState(252); parametros_opcional();
				setState(253); match(23);
				setState(254); match(48);
				setState(255); tipo_estendido();
				setState(256); declaracoes_locais();
				setState(257); comandos();
				setState(258); match(39);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parametros_opcionalContext extends ParserRuleContext {
		public ParametroContext parametro() {
			return getRuleContext(ParametroContext.class,0);
		}
		public Parametros_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParametros_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParametros_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParametros_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parametros_opcionalContext parametros_opcional() throws RecognitionException {
		Parametros_opcionalContext _localctx = new Parametros_opcionalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parametros_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if (((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & ((1L << (50 - 50)) | (1L << (61 - 50)) | (1L << (IDENT - 50)))) != 0)) {
				{
				setState(262); parametro();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametroContext extends ParserRuleContext {
		public Var_opcionalContext var_opcional() {
			return getRuleContext(Var_opcionalContext.class,0);
		}
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public Mais_parametrosContext mais_parametros() {
			return getRuleContext(Mais_parametrosContext.class,0);
		}
		public Mais_identContext mais_ident() {
			return getRuleContext(Mais_identContext.class,0);
		}
		public Tipo_estendidoContext tipo_estendido() {
			return getRuleContext(Tipo_estendidoContext.class,0);
		}
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParametro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParametro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParametro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_parametro);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265); var_opcional();
			setState(266); identificador();
			setState(267); mais_ident();
			setState(268); match(48);
			setState(269); tipo_estendido();
			setState(270); mais_parametros();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_opcionalContext extends ParserRuleContext {
		public Var_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterVar_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitVar_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitVar_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_opcionalContext var_opcional() throws RecognitionException {
		Var_opcionalContext _localctx = new Var_opcionalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_var_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_la = _input.LA(1);
			if (_la==50) {
				{
				setState(272); match(50);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_parametrosContext extends ParserRuleContext {
		public ParametroContext parametro() {
			return getRuleContext(ParametroContext.class,0);
		}
		public Mais_parametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_parametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_parametros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_parametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_parametrosContext mais_parametros() throws RecognitionException {
		Mais_parametrosContext _localctx = new Mais_parametrosContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_mais_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			_la = _input.LA(1);
			if (_la==45) {
				{
				setState(275); match(45);
				setState(276); parametro();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declaracoes_locaisContext extends ParserRuleContext {
		public List<Declaracao_localContext> declaracao_local() {
			return getRuleContexts(Declaracao_localContext.class);
		}
		public Declaracao_localContext declaracao_local(int i) {
			return getRuleContext(Declaracao_localContext.class,i);
		}
		public Declaracoes_locaisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracoes_locais; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDeclaracoes_locais(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDeclaracoes_locais(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDeclaracoes_locais(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracoes_locaisContext declaracoes_locais() throws RecognitionException {
		Declaracoes_locaisContext _localctx = new Declaracoes_locaisContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_declaracoes_locais);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2 || _la==7 || _la==73) {
				{
				{
				setState(279); declaracao_local();
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CorpoContext extends ParserRuleContext {
		public Declaracoes_locaisContext declaracoes_locais() {
			return getRuleContext(Declaracoes_locaisContext.class,0);
		}
		public ComandosContext comandos() {
			return getRuleContext(ComandosContext.class,0);
		}
		public CorpoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_corpo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterCorpo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitCorpo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitCorpo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CorpoContext corpo() throws RecognitionException {
		CorpoContext _localctx = new CorpoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_corpo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285); declaracoes_locais();
			setState(286); comandos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandosContext extends ParserRuleContext {
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public ComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterComandos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitComandos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitComandos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandosContext comandos() throws RecognitionException {
		ComandosContext _localctx = new ComandosContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_comandos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 11) | (1L << 12) | (1L << 20) | (1L << 22) | (1L << 28) | (1L << 29) | (1L << 30) | (1L << 34) | (1L << 35) | (1L << 43) | (1L << 53) | (1L << 58) | (1L << 59) | (1L << 61))) != 0) || _la==67 || _la==IDENT) {
				{
				{
				setState(288); cmd();
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public SelecaoContext selecao() {
			return getRuleContext(SelecaoContext.class,0);
		}
		public ComandosArduinoContext comandosArduino() {
			return getRuleContext(ComandosArduinoContext.class,0);
		}
		public IdentificadorContext identificador() {
			return getRuleContext(IdentificadorContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public Mais_identContext mais_ident() {
			return getRuleContext(Mais_identContext.class,0);
		}
		public Mais_expressaoContext mais_expressao() {
			return getRuleContext(Mais_expressaoContext.class,0);
		}
		public Exp_aritmeticaContext exp_aritmetica(int i) {
			return getRuleContext(Exp_aritmeticaContext.class,i);
		}
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public Chamada_atribuicaoContext chamada_atribuicao() {
			return getRuleContext(Chamada_atribuicaoContext.class,0);
		}
		public List<Exp_aritmeticaContext> exp_aritmetica() {
			return getRuleContexts(Exp_aritmeticaContext.class);
		}
		public Senao_opcionalContext senao_opcional() {
			return getRuleContext(Senao_opcionalContext.class,0);
		}
		public ComandosContext comandos() {
			return getRuleContext(ComandosContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitCmd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_cmd);
		try {
			setState(353);
			switch (_input.LA(1)) {
			case 29:
				enterOuterAlt(_localctx, 1);
				{
				setState(294); match(29);
				setState(295); match(49);
				setState(296); identificador();
				setState(297); mais_ident();
				setState(298); match(23);
				}
				break;
			case 34:
				enterOuterAlt(_localctx, 2);
				{
				setState(300); match(34);
				setState(301); match(49);
				setState(302); expressao();
				setState(303); mais_expressao();
				setState(304); match(23);
				}
				break;
			case 53:
				enterOuterAlt(_localctx, 3);
				{
				setState(306); match(53);
				setState(307); expressao();
				setState(308); match(47);
				setState(309); comandos();
				setState(310); senao_opcional();
				setState(311); match(33);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 4);
				{
				setState(313); match(30);
				setState(314); exp_aritmetica();
				setState(315); match(71);
				setState(316); selecao();
				setState(317); senao_opcional();
				setState(318); match(36);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 5);
				{
				setState(320); match(20);
				setState(321); match(IDENT);
				setState(322); match(56);
				setState(323); exp_aritmetica();
				setState(324); match(17);
				setState(325); exp_aritmetica();
				setState(326); match(4);
				setState(327); comandos();
				setState(328); match(42);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 6);
				{
				setState(330); match(22);
				setState(331); expressao();
				setState(332); match(4);
				setState(333); comandos();
				setState(334); match(15);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 7);
				{
				setState(336); match(4);
				setState(337); comandos();
				setState(338); match(17);
				setState(339); expressao();
				}
				break;
			case 61:
				enterOuterAlt(_localctx, 8);
				{
				setState(341); match(61);
				setState(342); match(IDENT);
				setState(343); outros_ident();
				setState(344); dimensao();
				setState(345); match(56);
				setState(346); expressao();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 9);
				{
				setState(348); match(IDENT);
				setState(349); chamada_atribuicao();
				}
				break;
			case 11:
			case 12:
			case 28:
			case 43:
			case 58:
			case 59:
			case 67:
				enterOuterAlt(_localctx, 10);
				{
				setState(350); comandosArduino();
				}
				break;
			case 35:
				enterOuterAlt(_localctx, 11);
				{
				setState(351); match(35);
				setState(352); expressao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandosArduinoContext extends ParserRuleContext {
		public ComandoSetupContext comandoSetup() {
			return getRuleContext(ComandoSetupContext.class,0);
		}
		public ComandoLoopContext comandoLoop() {
			return getRuleContext(ComandoLoopContext.class,0);
		}
		public ComandosArduinoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandosArduino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterComandosArduino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitComandosArduino(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitComandosArduino(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandosArduinoContext comandosArduino() throws RecognitionException {
		ComandosArduinoContext _localctx = new ComandosArduinoContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_comandosArduino);
		try {
			setState(357);
			switch (_input.LA(1)) {
			case 58:
				enterOuterAlt(_localctx, 1);
				{
				setState(355); comandoSetup();
				}
				break;
			case 11:
			case 12:
			case 28:
			case 43:
			case 59:
			case 67:
				enterOuterAlt(_localctx, 2);
				{
				setState(356); comandoLoop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoSetupContext extends ParserRuleContext {
		public TerminalNode NUM_INT(int i) {
			return getToken(FAZEDORESParser.NUM_INT, i);
		}
		public List<DispositivoContext> dispositivo() {
			return getRuleContexts(DispositivoContext.class);
		}
		public List<TerminalNode> NUM_INT() { return getTokens(FAZEDORESParser.NUM_INT); }
		public DispositivoContext dispositivo(int i) {
			return getRuleContext(DispositivoContext.class,i);
		}
		public ComandoSetupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoSetup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterComandoSetup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitComandoSetup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitComandoSetup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoSetupContext comandoSetup() throws RecognitionException {
		ComandoSetupContext _localctx = new ComandoSetupContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_comandoSetup);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(366); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(359); match(58);
					setState(360); match(49);
					setState(361); dispositivo();
					setState(362); match(45);
					setState(363); match(NUM_INT);
					setState(364); match(23);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(368); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			} while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoLoopContext extends ParserRuleContext {
		public List<CmdLoopContext> cmdLoop() {
			return getRuleContexts(CmdLoopContext.class);
		}
		public CmdLoopContext cmdLoop(int i) {
			return getRuleContext(CmdLoopContext.class,i);
		}
		public ComandoLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterComandoLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitComandoLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitComandoLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoLoopContext comandoLoop() throws RecognitionException {
		ComandoLoopContext _localctx = new ComandoLoopContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_comandoLoop);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(371); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(370); cmdLoop();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(373); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			} while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdLoopContext extends ParserRuleContext {
		public ComandoLCDContext comandoLCD() {
			return getRuleContext(ComandoLCDContext.class,0);
		}
		public TerminalNode NUM_INT(int i) {
			return getToken(FAZEDORESParser.NUM_INT, i);
		}
		public List<TerminalNode> NUM_INT() { return getTokens(FAZEDORESParser.NUM_INT); }
		public DispositivoSaidaContext dispositivoSaida() {
			return getRuleContext(DispositivoSaidaContext.class,0);
		}
		public DispositivoEntradaContext dispositivoEntrada() {
			return getRuleContext(DispositivoEntradaContext.class,0);
		}
		public CmdLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterCmdLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitCmdLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitCmdLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdLoopContext cmdLoop() throws RecognitionException {
		CmdLoopContext _localctx = new CmdLoopContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_cmdLoop);
		int _la;
		try {
			setState(393);
			switch (_input.LA(1)) {
			case 12:
			case 43:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				_la = _input.LA(1);
				if ( !(_la==12 || _la==43) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(376); match(49);
				setState(377); dispositivoSaida();
				setState(378); match(45);
				setState(379); match(NUM_INT);
				setState(382);
				_la = _input.LA(1);
				if (_la==45) {
					{
					setState(380); match(45);
					setState(381); match(NUM_INT);
					}
				}

				setState(384); match(23);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 2);
				{
				setState(386); match(28);
				setState(387); dispositivoEntrada();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 3);
				{
				setState(388); match(11);
				setState(389); match(49);
				setState(390); match(NUM_INT);
				setState(391); match(23);
				}
				break;
			case 59:
			case 67:
				enterOuterAlt(_localctx, 4);
				{
				setState(392); comandoLCD();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DispositivoContext extends ParserRuleContext {
		public DispositivoSaidaContext dispositivoSaida() {
			return getRuleContext(DispositivoSaidaContext.class,0);
		}
		public DispositivoEntradaContext dispositivoEntrada() {
			return getRuleContext(DispositivoEntradaContext.class,0);
		}
		public DispositivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dispositivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDispositivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDispositivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDispositivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DispositivoContext dispositivo() throws RecognitionException {
		DispositivoContext _localctx = new DispositivoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_dispositivo);
		try {
			setState(398);
			switch (_input.LA(1)) {
			case 26:
			case 41:
			case 55:
				enterOuterAlt(_localctx, 1);
				{
				setState(395); dispositivoSaida();
				}
				break;
			case 52:
			case 68:
			case 72:
				enterOuterAlt(_localctx, 2);
				{
				setState(396); dispositivoEntrada();
				}
				break;
			case 75:
				enterOuterAlt(_localctx, 3);
				{
				setState(397); match(75);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DispositivoSaidaContext extends ParserRuleContext {
		public DispositivoSaidaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dispositivoSaida; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDispositivoSaida(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDispositivoSaida(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDispositivoSaida(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DispositivoSaidaContext dispositivoSaida() throws RecognitionException {
		DispositivoSaidaContext _localctx = new DispositivoSaidaContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_dispositivoSaida);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 26) | (1L << 41) | (1L << 55))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DispositivoEntradaContext extends ParserRuleContext {
		public DispositivoEntradaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dispositivoEntrada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterDispositivoEntrada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitDispositivoEntrada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitDispositivoEntrada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DispositivoEntradaContext dispositivoEntrada() throws RecognitionException {
		DispositivoEntradaContext _localctx = new DispositivoEntradaContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_dispositivoEntrada);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_la = _input.LA(1);
			if ( !(((((_la - 52)) & ~0x3f) == 0 && ((1L << (_la - 52)) & ((1L << (52 - 52)) | (1L << (68 - 52)) | (1L << (72 - 52)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoLCDContext extends ParserRuleContext {
		public TerminalNode COR() { return getToken(FAZEDORESParser.COR, 0); }
		public TerminalNode NUM_INT() { return getToken(FAZEDORESParser.NUM_INT, 0); }
		public TerminalNode CADEIA() { return getToken(FAZEDORESParser.CADEIA, 0); }
		public ComandoLCDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comandoLCD; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterComandoLCD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitComandoLCD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitComandoLCD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoLCDContext comandoLCD() throws RecognitionException {
		ComandoLCDContext _localctx = new ComandoLCDContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_comandoLCD);
		try {
			setState(419);
			switch (_input.LA(1)) {
			case 59:
				enterOuterAlt(_localctx, 1);
				{
				setState(404); match(59);
				setState(405); match(49);
				setState(406); match(60);
				setState(407); match(NUM_INT);
				setState(408); match(45);
				setState(409); match(COR);
				setState(410); match(23);
				}
				break;
			case 67:
				enterOuterAlt(_localctx, 2);
				{
				setState(411); match(67);
				setState(412); match(49);
				setState(413); match(14);
				setState(414); match(45);
				setState(415); match(NUM_INT);
				setState(416); match(45);
				setState(417); match(CADEIA);
				setState(418); match(23);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_expressaoContext extends ParserRuleContext {
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public Mais_expressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_expressao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_expressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_expressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_expressao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_expressaoContext mais_expressao() throws RecognitionException {
		Mais_expressaoContext _localctx = new Mais_expressaoContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_mais_expressao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==45) {
				{
				{
				setState(421); match(45);
				setState(422); expressao();
				}
				}
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Senao_opcionalContext extends ParserRuleContext {
		public ComandosContext comandos() {
			return getRuleContext(ComandosContext.class,0);
		}
		public Senao_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_senao_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterSenao_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitSenao_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitSenao_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Senao_opcionalContext senao_opcional() throws RecognitionException {
		Senao_opcionalContext _localctx = new Senao_opcionalContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_senao_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(428); match(25);
				setState(429); comandos();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Chamada_atribuicaoContext extends ParserRuleContext {
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public Argumentos_opcionalContext argumentos_opcional() {
			return getRuleContext(Argumentos_opcionalContext.class,0);
		}
		public Chamada_atribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chamada_atribuicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterChamada_atribuicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitChamada_atribuicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitChamada_atribuicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chamada_atribuicaoContext chamada_atribuicao() throws RecognitionException {
		Chamada_atribuicaoContext _localctx = new Chamada_atribuicaoContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_chamada_atribuicao);
		try {
			setState(441);
			switch (_input.LA(1)) {
			case 49:
				enterOuterAlt(_localctx, 1);
				{
				setState(432); match(49);
				setState(433); argumentos_opcional();
				setState(434); match(23);
				}
				break;
			case 5:
			case 56:
			case 62:
				enterOuterAlt(_localctx, 2);
				{
				setState(436); outros_ident();
				setState(437); dimensao();
				setState(438); match(56);
				setState(439); expressao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Argumentos_opcionalContext extends ParserRuleContext {
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public Mais_expressaoContext mais_expressao() {
			return getRuleContext(Mais_expressaoContext.class,0);
		}
		public Argumentos_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentos_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterArgumentos_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitArgumentos_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitArgumentos_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argumentos_opcionalContext argumentos_opcional() throws RecognitionException {
		Argumentos_opcionalContext _localctx = new Argumentos_opcionalContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_argumentos_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 3) | (1L << 44) | (1L << 46) | (1L << 49) | (1L << 57) | (1L << 61))) != 0) || ((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (IDENT - 78)) | (1L << (NUM_INT - 78)) | (1L << (NUM_REAL - 78)) | (1L << (CADEIA - 78)))) != 0)) {
				{
				setState(443); expressao();
				setState(444); mais_expressao();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelecaoContext extends ParserRuleContext {
		public Mais_selecaoContext mais_selecao() {
			return getRuleContext(Mais_selecaoContext.class,0);
		}
		public ComandosContext comandos() {
			return getRuleContext(ComandosContext.class,0);
		}
		public ConstantesContext constantes() {
			return getRuleContext(ConstantesContext.class,0);
		}
		public SelecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterSelecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitSelecao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitSelecao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelecaoContext selecao() throws RecognitionException {
		SelecaoContext _localctx = new SelecaoContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_selecao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448); constantes();
			setState(449); match(48);
			setState(450); comandos();
			setState(451); mais_selecao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_selecaoContext extends ParserRuleContext {
		public SelecaoContext selecao() {
			return getRuleContext(SelecaoContext.class,0);
		}
		public Mais_selecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_selecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_selecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_selecao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_selecao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_selecaoContext mais_selecao() throws RecognitionException {
		Mais_selecaoContext _localctx = new Mais_selecaoContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_mais_selecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			_la = _input.LA(1);
			if (_la==46 || _la==NUM_INT) {
				{
				setState(453); selecao();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantesContext extends ParserRuleContext {
		public Mais_constantesContext mais_constantes() {
			return getRuleContext(Mais_constantesContext.class,0);
		}
		public Numero_intervaloContext numero_intervalo() {
			return getRuleContext(Numero_intervaloContext.class,0);
		}
		public ConstantesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterConstantes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitConstantes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitConstantes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantesContext constantes() throws RecognitionException {
		ConstantesContext _localctx = new ConstantesContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_constantes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(456); numero_intervalo();
			setState(457); mais_constantes();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mais_constantesContext extends ParserRuleContext {
		public ConstantesContext constantes() {
			return getRuleContext(ConstantesContext.class,0);
		}
		public Mais_constantesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mais_constantes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterMais_constantes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitMais_constantes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitMais_constantes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mais_constantesContext mais_constantes() throws RecognitionException {
		Mais_constantesContext _localctx = new Mais_constantesContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_mais_constantes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			_la = _input.LA(1);
			if (_la==45) {
				{
				setState(459); match(45);
				setState(460); constantes();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numero_intervaloContext extends ParserRuleContext {
		public Op_unarioContext op_unario() {
			return getRuleContext(Op_unarioContext.class,0);
		}
		public TerminalNode NUM_INT() { return getToken(FAZEDORESParser.NUM_INT, 0); }
		public Intervalo_opcionalContext intervalo_opcional() {
			return getRuleContext(Intervalo_opcionalContext.class,0);
		}
		public Numero_intervaloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numero_intervalo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterNumero_intervalo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitNumero_intervalo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitNumero_intervalo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numero_intervaloContext numero_intervalo() throws RecognitionException {
		Numero_intervaloContext _localctx = new Numero_intervaloContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_numero_intervalo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463); op_unario();
			setState(464); match(NUM_INT);
			setState(465); intervalo_opcional();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Intervalo_opcionalContext extends ParserRuleContext {
		public Op_unarioContext op_unario() {
			return getRuleContext(Op_unarioContext.class,0);
		}
		public TerminalNode NUM_INT() { return getToken(FAZEDORESParser.NUM_INT, 0); }
		public Intervalo_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intervalo_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterIntervalo_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitIntervalo_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitIntervalo_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Intervalo_opcionalContext intervalo_opcional() throws RecognitionException {
		Intervalo_opcionalContext _localctx = new Intervalo_opcionalContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_intervalo_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			_la = _input.LA(1);
			if (_la==38) {
				{
				setState(467); match(38);
				setState(468); op_unario();
				setState(469); match(NUM_INT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_unarioContext extends ParserRuleContext {
		public Op_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_unario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_unario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_unario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_unario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_unarioContext op_unario() throws RecognitionException {
		Op_unarioContext _localctx = new Op_unarioContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_op_unario);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			_la = _input.LA(1);
			if (_la==46) {
				{
				setState(473); match(46);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exp_aritmeticaContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Outros_termosContext outros_termos() {
			return getRuleContext(Outros_termosContext.class,0);
		}
		public Exp_aritmeticaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_aritmetica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterExp_aritmetica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitExp_aritmetica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitExp_aritmetica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_aritmeticaContext exp_aritmetica() throws RecognitionException {
		Exp_aritmeticaContext _localctx = new Exp_aritmeticaContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_exp_aritmetica);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476); termo();
			setState(477); outros_termos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_multiplicacaoContext extends ParserRuleContext {
		public Op_multiplicacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_multiplicacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_multiplicacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_multiplicacao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_multiplicacao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_multiplicacaoContext op_multiplicacao() throws RecognitionException {
		Op_multiplicacaoContext _localctx = new Op_multiplicacaoContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_op_multiplicacao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			_la = _input.LA(1);
			if ( !(_la==6 || _la==74) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_adicaoContext extends ParserRuleContext {
		public Op_adicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_adicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_adicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_adicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_adicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_adicaoContext op_adicao() throws RecognitionException {
		Op_adicaoContext _localctx = new Op_adicaoContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_op_adicao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			_la = _input.LA(1);
			if ( !(_la==46 || _la==63) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Outros_fatoresContext outros_fatores() {
			return getRuleContext(Outros_fatoresContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTermo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTermo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483); fator();
			setState(484); outros_fatores();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outros_termosContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<Op_adicaoContext> op_adicao() {
			return getRuleContexts(Op_adicaoContext.class);
		}
		public Op_adicaoContext op_adicao(int i) {
			return getRuleContext(Op_adicaoContext.class,i);
		}
		public Outros_termosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_termos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutros_termos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutros_termos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutros_termos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outros_termosContext outros_termos() throws RecognitionException {
		Outros_termosContext _localctx = new Outros_termosContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_outros_termos);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(486); op_adicao();
					setState(487); termo();
					}
					} 
				}
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorContext extends ParserRuleContext {
		public ParcelaContext parcela() {
			return getRuleContext(ParcelaContext.class,0);
		}
		public Outras_parcelasContext outras_parcelas() {
			return getRuleContext(Outras_parcelasContext.class,0);
		}
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitFator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitFator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_fator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494); parcela();
			setState(495); outras_parcelas();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outros_fatoresContext extends ParserRuleContext {
		public List<Op_multiplicacaoContext> op_multiplicacao() {
			return getRuleContexts(Op_multiplicacaoContext.class);
		}
		public FatorContext fator(int i) {
			return getRuleContext(FatorContext.class,i);
		}
		public List<FatorContext> fator() {
			return getRuleContexts(FatorContext.class);
		}
		public Op_multiplicacaoContext op_multiplicacao(int i) {
			return getRuleContext(Op_multiplicacaoContext.class,i);
		}
		public Outros_fatoresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_fatores; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutros_fatores(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutros_fatores(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutros_fatores(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outros_fatoresContext outros_fatores() throws RecognitionException {
		Outros_fatoresContext _localctx = new Outros_fatoresContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_outros_fatores);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==6 || _la==74) {
				{
				{
				setState(497); op_multiplicacao();
				setState(498); fator();
				}
				}
				setState(504);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParcelaContext extends ParserRuleContext {
		public Parcela_unarioContext parcela_unario() {
			return getRuleContext(Parcela_unarioContext.class,0);
		}
		public Op_unarioContext op_unario() {
			return getRuleContext(Op_unarioContext.class,0);
		}
		public Parcela_nao_unarioContext parcela_nao_unario() {
			return getRuleContext(Parcela_nao_unarioContext.class,0);
		}
		public ParcelaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParcela(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParcela(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParcela(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParcelaContext parcela() throws RecognitionException {
		ParcelaContext _localctx = new ParcelaContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_parcela);
		try {
			setState(509);
			switch (_input.LA(1)) {
			case 46:
			case 49:
			case 61:
			case IDENT:
			case NUM_INT:
			case NUM_REAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(505); op_unario();
				setState(506); parcela_unario();
				}
				break;
			case 1:
			case CADEIA:
				enterOuterAlt(_localctx, 2);
				{
				setState(508); parcela_nao_unario();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parcela_unarioContext extends ParserRuleContext {
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode NUM_INT() { return getToken(FAZEDORESParser.NUM_INT, 0); }
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public Chamada_partesContext chamada_partes() {
			return getRuleContext(Chamada_partesContext.class,0);
		}
		public TerminalNode NUM_REAL() { return getToken(FAZEDORESParser.NUM_REAL, 0); }
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public Parcela_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_unario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParcela_unario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParcela_unario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParcela_unario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parcela_unarioContext parcela_unario() throws RecognitionException {
		Parcela_unarioContext _localctx = new Parcela_unarioContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_parcela_unario);
		try {
			setState(524);
			switch (_input.LA(1)) {
			case 61:
				enterOuterAlt(_localctx, 1);
				{
				setState(511); match(61);
				setState(512); match(IDENT);
				setState(513); outros_ident();
				setState(514); dimensao();
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(516); match(IDENT);
				setState(517); chamada_partes();
				}
				break;
			case NUM_INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(518); match(NUM_INT);
				}
				break;
			case NUM_REAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(519); match(NUM_REAL);
				}
				break;
			case 49:
				enterOuterAlt(_localctx, 5);
				{
				setState(520); match(49);
				setState(521); expressao();
				setState(522); match(23);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parcela_nao_unarioContext extends ParserRuleContext {
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public TerminalNode CADEIA() { return getToken(FAZEDORESParser.CADEIA, 0); }
		public TerminalNode IDENT() { return getToken(FAZEDORESParser.IDENT, 0); }
		public Parcela_nao_unarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_nao_unario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParcela_nao_unario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParcela_nao_unario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParcela_nao_unario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parcela_nao_unarioContext parcela_nao_unario() throws RecognitionException {
		Parcela_nao_unarioContext _localctx = new Parcela_nao_unarioContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_parcela_nao_unario);
		try {
			setState(532);
			switch (_input.LA(1)) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(526); match(1);
				setState(527); match(IDENT);
				setState(528); outros_ident();
				setState(529); dimensao();
				}
				break;
			case CADEIA:
				enterOuterAlt(_localctx, 2);
				{
				setState(531); match(CADEIA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outras_parcelasContext extends ParserRuleContext {
		public ParcelaContext parcela(int i) {
			return getRuleContext(ParcelaContext.class,i);
		}
		public List<ParcelaContext> parcela() {
			return getRuleContexts(ParcelaContext.class);
		}
		public Outras_parcelasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outras_parcelas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutras_parcelas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutras_parcelas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutras_parcelas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outras_parcelasContext outras_parcelas() throws RecognitionException {
		Outras_parcelasContext _localctx = new Outras_parcelasContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_outras_parcelas);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==21) {
				{
				{
				setState(534); match(21);
				setState(535); parcela();
				}
				}
				setState(540);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Chamada_partesContext extends ParserRuleContext {
		public DimensaoContext dimensao() {
			return getRuleContext(DimensaoContext.class,0);
		}
		public Outros_identContext outros_ident() {
			return getRuleContext(Outros_identContext.class,0);
		}
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public Mais_expressaoContext mais_expressao() {
			return getRuleContext(Mais_expressaoContext.class,0);
		}
		public Chamada_partesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chamada_partes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterChamada_partes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitChamada_partes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitChamada_partes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chamada_partesContext chamada_partes() throws RecognitionException {
		Chamada_partesContext _localctx = new Chamada_partesContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_chamada_partes);
		try {
			setState(550);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(541); match(49);
				setState(542); expressao();
				setState(543); mais_expressao();
				setState(544); match(23);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(546); outros_ident();
				setState(547); dimensao();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exp_relacionalContext extends ParserRuleContext {
		public Op_opcionalContext op_opcional() {
			return getRuleContext(Op_opcionalContext.class,0);
		}
		public Exp_aritmeticaContext exp_aritmetica() {
			return getRuleContext(Exp_aritmeticaContext.class,0);
		}
		public Exp_relacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_relacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterExp_relacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitExp_relacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitExp_relacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_relacionalContext exp_relacional() throws RecognitionException {
		Exp_relacionalContext _localctx = new Exp_relacionalContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_exp_relacional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(552); exp_aritmetica();
			setState(553); op_opcional();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_opcionalContext extends ParserRuleContext {
		public Exp_aritmeticaContext exp_aritmetica() {
			return getRuleContext(Exp_aritmeticaContext.class,0);
		}
		public Op_relacionalContext op_relacional() {
			return getRuleContext(Op_relacionalContext.class,0);
		}
		public Op_opcionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_opcional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_opcional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_opcional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_opcional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_opcionalContext op_opcional() throws RecognitionException {
		Op_opcionalContext _localctx = new Op_opcionalContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_op_opcional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 13) | (1L << 27))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (69 - 65)) | (1L << (76 - 65)))) != 0)) {
				{
				setState(555); op_relacional();
				setState(556); exp_aritmetica();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_relacionalContext extends ParserRuleContext {
		public Op_relacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_relacional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_relacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_relacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_relacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_relacionalContext op_relacional() throws RecognitionException {
		Op_relacionalContext _localctx = new Op_relacionalContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_op_relacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << 13) | (1L << 27))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (69 - 65)) | (1L << (76 - 65)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressaoContext extends ParserRuleContext {
		public Termo_logicoContext termo_logico() {
			return getRuleContext(Termo_logicoContext.class,0);
		}
		public Outros_termos_logicosContext outros_termos_logicos() {
			return getRuleContext(Outros_termos_logicosContext.class,0);
		}
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitExpressao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_expressao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562); termo_logico();
			setState(563); outros_termos_logicos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Op_naoContext extends ParserRuleContext {
		public Op_naoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_nao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOp_nao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOp_nao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOp_nao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_naoContext op_nao() throws RecognitionException {
		Op_naoContext _localctx = new Op_naoContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_op_nao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(565); match(3);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Termo_logicoContext extends ParserRuleContext {
		public Outros_fatores_logicosContext outros_fatores_logicos() {
			return getRuleContext(Outros_fatores_logicosContext.class,0);
		}
		public Fator_logicoContext fator_logico() {
			return getRuleContext(Fator_logicoContext.class,0);
		}
		public Termo_logicoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_logico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterTermo_logico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitTermo_logico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitTermo_logico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Termo_logicoContext termo_logico() throws RecognitionException {
		Termo_logicoContext _localctx = new Termo_logicoContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_termo_logico);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568); fator_logico();
			setState(569); outros_fatores_logicos();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outros_termos_logicosContext extends ParserRuleContext {
		public Termo_logicoContext termo_logico(int i) {
			return getRuleContext(Termo_logicoContext.class,i);
		}
		public List<Termo_logicoContext> termo_logico() {
			return getRuleContexts(Termo_logicoContext.class);
		}
		public Outros_termos_logicosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_termos_logicos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutros_termos_logicos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutros_termos_logicos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutros_termos_logicos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outros_termos_logicosContext outros_termos_logicos() throws RecognitionException {
		Outros_termos_logicosContext _localctx = new Outros_termos_logicosContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_outros_termos_logicos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==64) {
				{
				{
				setState(571); match(64);
				setState(572); termo_logico();
				}
				}
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Outros_fatores_logicosContext extends ParserRuleContext {
		public Fator_logicoContext fator_logico(int i) {
			return getRuleContext(Fator_logicoContext.class,i);
		}
		public List<Fator_logicoContext> fator_logico() {
			return getRuleContexts(Fator_logicoContext.class);
		}
		public Outros_fatores_logicosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_outros_fatores_logicos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterOutros_fatores_logicos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitOutros_fatores_logicos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitOutros_fatores_logicos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Outros_fatores_logicosContext outros_fatores_logicos() throws RecognitionException {
		Outros_fatores_logicosContext _localctx = new Outros_fatores_logicosContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_outros_fatores_logicos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==66) {
				{
				{
				setState(578); match(66);
				setState(579); fator_logico();
				}
				}
				setState(584);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fator_logicoContext extends ParserRuleContext {
		public Op_naoContext op_nao() {
			return getRuleContext(Op_naoContext.class,0);
		}
		public Parcela_logicaContext parcela_logica() {
			return getRuleContext(Parcela_logicaContext.class,0);
		}
		public Fator_logicoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator_logico; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterFator_logico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitFator_logico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitFator_logico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fator_logicoContext fator_logico() throws RecognitionException {
		Fator_logicoContext _localctx = new Fator_logicoContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_fator_logico);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585); op_nao();
			setState(586); parcela_logica();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parcela_logicaContext extends ParserRuleContext {
		public Exp_relacionalContext exp_relacional() {
			return getRuleContext(Exp_relacionalContext.class,0);
		}
		public Parcela_logicaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parcela_logica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).enterParcela_logica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FAZEDORESListener ) ((FAZEDORESListener)listener).exitParcela_logica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FAZEDORESVisitor ) return ((FAZEDORESVisitor<? extends T>)visitor).visitParcela_logica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Parcela_logicaContext parcela_logica() throws RecognitionException {
		Parcela_logicaContext _localctx = new Parcela_logicaContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_parcela_logica);
		try {
			setState(591);
			switch (_input.LA(1)) {
			case 44:
				enterOuterAlt(_localctx, 1);
				{
				setState(588); match(44);
				}
				break;
			case 57:
				enterOuterAlt(_localctx, 2);
				{
				setState(589); match(57);
				}
				break;
			case 1:
			case 46:
			case 49:
			case 61:
			case IDENT:
			case NUM_INT:
			case NUM_REAL:
			case CADEIA:
				enterOuterAlt(_localctx, 3);
				{
				setState(590); exp_relacional();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3X\u0254\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\7\3\u0094\n\3\f\3\16\3\u0097\13\3\3\4\3\4\5\4\u009b\n\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00aa\n\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7\u00b5\n\7\f\7\16\7\u00b8\13\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\7\t\u00c0\n\t\f\t\16\t\u00c3\13\t\3\n\3\n\5\n\u00c7"+
		"\n\n\3\13\3\13\3\13\3\13\7\13\u00cd\n\13\f\13\16\13\u00d0\13\13\3\f\3"+
		"\f\5\f\u00d4\n\f\3\r\3\r\7\r\u00d8\n\r\f\r\16\r\u00db\13\r\3\16\7\16\u00de"+
		"\n\16\f\16\16\16\u00e1\13\16\3\17\3\17\3\20\3\20\5\20\u00e7\n\20\3\21"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u0107\n\24\3\25\5\25\u010a\n\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\5\27\u0114\n\27\3\30\3\30\5\30\u0118\n\30\3\31\7\31\u011b"+
		"\n\31\f\31\16\31\u011e\13\31\3\32\3\32\3\32\3\33\7\33\u0124\n\33\f\33"+
		"\16\33\u0127\13\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0164\n\34\3\35\3\35\5\35\u0168"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\6\36\u0171\n\36\r\36\16\36\u0172"+
		"\3\37\6\37\u0176\n\37\r\37\16\37\u0177\3 \3 \3 \3 \3 \3 \3 \5 \u0181\n"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u018c\n \3!\3!\3!\5!\u0191\n!\3\"\3\""+
		"\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u01a6\n$\3%\3%"+
		"\7%\u01aa\n%\f%\16%\u01ad\13%\3&\3&\5&\u01b1\n&\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\5\'\u01bc\n\'\3(\3(\3(\5(\u01c1\n(\3)\3)\3)\3)\3)\3*\5"+
		"*\u01c9\n*\3+\3+\3+\3,\3,\5,\u01d0\n,\3-\3-\3-\3-\3.\3.\3.\3.\5.\u01da"+
		"\n.\3/\5/\u01dd\n/\3\60\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3"+
		"\64\3\64\3\64\7\64\u01ec\n\64\f\64\16\64\u01ef\13\64\3\65\3\65\3\65\3"+
		"\66\3\66\3\66\7\66\u01f7\n\66\f\66\16\66\u01fa\13\66\3\67\3\67\3\67\3"+
		"\67\5\67\u0200\n\67\38\38\38\38\38\38\38\38\38\38\38\38\38\58\u020f\n"+
		"8\39\39\39\39\39\39\59\u0217\n9\3:\3:\7:\u021b\n:\f:\16:\u021e\13:\3;"+
		"\3;\3;\3;\3;\3;\3;\3;\3;\5;\u0229\n;\3<\3<\3<\3=\3=\3=\5=\u0231\n=\3>"+
		"\3>\3?\3?\3?\3@\5@\u0239\n@\3A\3A\3A\3B\3B\7B\u0240\nB\fB\16B\u0243\13"+
		"B\3C\3C\7C\u0247\nC\fC\16C\u024a\13C\3D\3D\3D\3E\3E\3E\5E\u0252\nE\3E"+
		"\2\2F\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>"+
		"@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\2\n\6"+
		"\2\24\24!!\'\'88\5\2..;;QS\4\2\16\16--\5\2\34\34++99\5\2\66\66FFJJ\4\2"+
		"\b\bLL\4\2\60\60AA\b\2\13\13\17\17\35\35CCGGNN\u024e\2\u008a\3\2\2\2\4"+
		"\u0095\3\2\2\2\6\u009a\3\2\2\2\b\u00a9\3\2\2\2\n\u00ab\3\2\2\2\f\u00b6"+
		"\3\2\2\2\16\u00b9\3\2\2\2\20\u00c1\3\2\2\2\22\u00c6\3\2\2\2\24\u00ce\3"+
		"\2\2\2\26\u00d3\3\2\2\2\30\u00d9\3\2\2\2\32\u00df\3\2\2\2\34\u00e2\3\2"+
		"\2\2\36\u00e6\3\2\2\2 \u00e8\3\2\2\2\"\u00eb\3\2\2\2$\u00ed\3\2\2\2&\u0106"+
		"\3\2\2\2(\u0109\3\2\2\2*\u010b\3\2\2\2,\u0113\3\2\2\2.\u0117\3\2\2\2\60"+
		"\u011c\3\2\2\2\62\u011f\3\2\2\2\64\u0125\3\2\2\2\66\u0163\3\2\2\28\u0167"+
		"\3\2\2\2:\u0170\3\2\2\2<\u0175\3\2\2\2>\u018b\3\2\2\2@\u0190\3\2\2\2B"+
		"\u0192\3\2\2\2D\u0194\3\2\2\2F\u01a5\3\2\2\2H\u01ab\3\2\2\2J\u01b0\3\2"+
		"\2\2L\u01bb\3\2\2\2N\u01c0\3\2\2\2P\u01c2\3\2\2\2R\u01c8\3\2\2\2T\u01ca"+
		"\3\2\2\2V\u01cf\3\2\2\2X\u01d1\3\2\2\2Z\u01d9\3\2\2\2\\\u01dc\3\2\2\2"+
		"^\u01de\3\2\2\2`\u01e1\3\2\2\2b\u01e3\3\2\2\2d\u01e5\3\2\2\2f\u01ed\3"+
		"\2\2\2h\u01f0\3\2\2\2j\u01f8\3\2\2\2l\u01ff\3\2\2\2n\u020e\3\2\2\2p\u0216"+
		"\3\2\2\2r\u021c\3\2\2\2t\u0228\3\2\2\2v\u022a\3\2\2\2x\u0230\3\2\2\2z"+
		"\u0232\3\2\2\2|\u0234\3\2\2\2~\u0238\3\2\2\2\u0080\u023a\3\2\2\2\u0082"+
		"\u0241\3\2\2\2\u0084\u0248\3\2\2\2\u0086\u024b\3\2\2\2\u0088\u0251\3\2"+
		"\2\2\u008a\u008b\5\4\3\2\u008b\u008c\7\65\2\2\u008c\u008d\5:\36\2\u008d"+
		"\u008e\7\32\2\2\u008e\u008f\7\n\2\2\u008f\u0090\5<\37\2\u0090\u0091\7"+
		"\22\2\2\u0091\3\3\2\2\2\u0092\u0094\5\6\4\2\u0093\u0092\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\5\3\2\2\2"+
		"\u0097\u0095\3\2\2\2\u0098\u009b\5\b\5\2\u0099\u009b\5&\24\2\u009a\u0098"+
		"\3\2\2\2\u009a\u0099\3\2\2\2\u009b\7\3\2\2\2\u009c\u009d\7K\2\2\u009d"+
		"\u00aa\5\n\6\2\u009e\u009f\7\4\2\2\u009f\u00a0\7P\2\2\u00a0\u00a1\7\62"+
		"\2\2\u00a1\u00a2\5\34\17\2\u00a2\u00a3\7\35\2\2\u00a3\u00a4\5\"\22\2\u00a4"+
		"\u00aa\3\2\2\2\u00a5\u00a6\7\t\2\2\u00a6\u00a7\7P\2\2\u00a7\u00a8\7\62"+
		"\2\2\u00a8\u00aa\5\26\f\2\u00a9\u009c\3\2\2\2\u00a9\u009e\3\2\2\2\u00a9"+
		"\u00a5\3\2\2\2\u00aa\t\3\2\2\2\u00ab\u00ac\7P\2\2\u00ac\u00ad\5\24\13"+
		"\2\u00ad\u00ae\5\f\7\2\u00ae\u00af\7\62\2\2\u00af\u00b0\5\26\f\2\u00b0"+
		"\13\3\2\2\2\u00b1\u00b2\7/\2\2\u00b2\u00b3\7P\2\2\u00b3\u00b5\5\24\13"+
		"\2\u00b4\u00b1\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\r\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\5\20\t\2\u00ba"+
		"\u00bb\7P\2\2\u00bb\u00bc\5\24\13\2\u00bc\u00bd\5\22\n\2\u00bd\17\3\2"+
		"\2\2\u00be\u00c0\7?\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\21\3\2\2\2\u00c3\u00c1\3\2\2"+
		"\2\u00c4\u00c5\7@\2\2\u00c5\u00c7\5\16\b\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7"+
		"\3\2\2\2\u00c7\23\3\2\2\2\u00c8\u00c9\7\7\2\2\u00c9\u00ca\5^\60\2\u00ca"+
		"\u00cb\7*\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00c8\3\2\2\2\u00cd\u00d0\3\2"+
		"\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\25\3\2\2\2\u00d0\u00ce"+
		"\3\2\2\2\u00d1\u00d4\5$\23\2\u00d2\u00d4\5 \21\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d2\3\2\2\2\u00d4\27\3\2\2\2\u00d5\u00d6\7/\2\2\u00d6\u00d8\5\16\b"+
		"\2\u00d7\u00d5\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\31\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00de\5\n\6\2\u00dd"+
		"\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\33\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\t\2\2\2\u00e3\35"+
		"\3\2\2\2\u00e4\u00e7\5\34\17\2\u00e5\u00e7\7P\2\2\u00e6\u00e4\3\2\2\2"+
		"\u00e6\u00e5\3\2\2\2\u00e7\37\3\2\2\2\u00e8\u00e9\5\20\t\2\u00e9\u00ea"+
		"\5\36\20\2\u00ea!\3\2\2\2\u00eb\u00ec\t\3\2\2\u00ec#\3\2\2\2\u00ed\u00ee"+
		"\7H\2\2\u00ee\u00ef\5\n\6\2\u00ef\u00f0\5\32\16\2\u00f0\u00f1\7\25\2\2"+
		"\u00f1%\3\2\2\2\u00f2\u00f3\7O\2\2\u00f3\u00f4\7P\2\2\u00f4\u00f5\7\63"+
		"\2\2\u00f5\u00f6\5(\25\2\u00f6\u00f7\7\31\2\2\u00f7\u00f8\5\60\31\2\u00f8"+
		"\u00f9\5\64\33\2\u00f9\u00fa\7\f\2\2\u00fa\u0107\3\2\2\2\u00fb\u00fc\7"+
		"\"\2\2\u00fc\u00fd\7P\2\2\u00fd\u00fe\7\63\2\2\u00fe\u00ff\5(\25\2\u00ff"+
		"\u0100\7\31\2\2\u0100\u0101\7\62\2\2\u0101\u0102\5 \21\2\u0102\u0103\5"+
		"\60\31\2\u0103\u0104\5\64\33\2\u0104\u0105\7)\2\2\u0105\u0107\3\2\2\2"+
		"\u0106\u00f2\3\2\2\2\u0106\u00fb\3\2\2\2\u0107\'\3\2\2\2\u0108\u010a\5"+
		"*\26\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a)\3\2\2\2\u010b\u010c"+
		"\5,\27\2\u010c\u010d\5\16\b\2\u010d\u010e\5\30\r\2\u010e\u010f\7\62\2"+
		"\2\u010f\u0110\5 \21\2\u0110\u0111\5.\30\2\u0111+\3\2\2\2\u0112\u0114"+
		"\7\64\2\2\u0113\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114-\3\2\2\2\u0115"+
		"\u0116\7/\2\2\u0116\u0118\5*\26\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118/\3\2\2\2\u0119\u011b\5\b\5\2\u011a\u0119\3\2\2\2\u011b\u011e"+
		"\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\61\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011f\u0120\5\60\31\2\u0120\u0121\5\64\33\2\u0121\63\3"+
		"\2\2\2\u0122\u0124\5\66\34\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\65\3\2\2\2\u0127\u0125\3\2\2"+
		"\2\u0128\u0129\7\37\2\2\u0129\u012a\7\63\2\2\u012a\u012b\5\16\b\2\u012b"+
		"\u012c\5\30\r\2\u012c\u012d\7\31\2\2\u012d\u0164\3\2\2\2\u012e\u012f\7"+
		"$\2\2\u012f\u0130\7\63\2\2\u0130\u0131\5|?\2\u0131\u0132\5H%\2\u0132\u0133"+
		"\7\31\2\2\u0133\u0164\3\2\2\2\u0134\u0135\7\67\2\2\u0135\u0136\5|?\2\u0136"+
		"\u0137\7\61\2\2\u0137\u0138\5\64\33\2\u0138\u0139\5J&\2\u0139\u013a\7"+
		"#\2\2\u013a\u0164\3\2\2\2\u013b\u013c\7 \2\2\u013c\u013d\5^\60\2\u013d"+
		"\u013e\7I\2\2\u013e\u013f\5P)\2\u013f\u0140\5J&\2\u0140\u0141\7&\2\2\u0141"+
		"\u0164\3\2\2\2\u0142\u0143\7\26\2\2\u0143\u0144\7P\2\2\u0144\u0145\7:"+
		"\2\2\u0145\u0146\5^\60\2\u0146\u0147\7\23\2\2\u0147\u0148\5^\60\2\u0148"+
		"\u0149\7\6\2\2\u0149\u014a\5\64\33\2\u014a\u014b\7,\2\2\u014b\u0164\3"+
		"\2\2\2\u014c\u014d\7\30\2\2\u014d\u014e\5|?\2\u014e\u014f\7\6\2\2\u014f"+
		"\u0150\5\64\33\2\u0150\u0151\7\21\2\2\u0151\u0164\3\2\2\2\u0152\u0153"+
		"\7\6\2\2\u0153\u0154\5\64\33\2\u0154\u0155\7\23\2\2\u0155\u0156\5|?\2"+
		"\u0156\u0164\3\2\2\2\u0157\u0158\7?\2\2\u0158\u0159\7P\2\2\u0159\u015a"+
		"\5\22\n\2\u015a\u015b\5\24\13\2\u015b\u015c\7:\2\2\u015c\u015d\5|?\2\u015d"+
		"\u0164\3\2\2\2\u015e\u015f\7P\2\2\u015f\u0164\5L\'\2\u0160\u0164\58\35"+
		"\2\u0161\u0162\7%\2\2\u0162\u0164\5|?\2\u0163\u0128\3\2\2\2\u0163\u012e"+
		"\3\2\2\2\u0163\u0134\3\2\2\2\u0163\u013b\3\2\2\2\u0163\u0142\3\2\2\2\u0163"+
		"\u014c\3\2\2\2\u0163\u0152\3\2\2\2\u0163\u0157\3\2\2\2\u0163\u015e\3\2"+
		"\2\2\u0163\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0164\67\3\2\2\2\u0165\u0168"+
		"\5:\36\2\u0166\u0168\5<\37\2\u0167\u0165\3\2\2\2\u0167\u0166\3\2\2\2\u0168"+
		"9\3\2\2\2\u0169\u016a\7<\2\2\u016a\u016b\7\63\2\2\u016b\u016c\5@!\2\u016c"+
		"\u016d\7/\2\2\u016d\u016e\7Q\2\2\u016e\u016f\7\31\2\2\u016f\u0171\3\2"+
		"\2\2\u0170\u0169\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0170\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173;\3\2\2\2\u0174\u0176\5> \2\u0175\u0174\3\2\2\2\u0176"+
		"\u0177\3\2\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178=\3\2\2\2"+
		"\u0179\u017a\t\4\2\2\u017a\u017b\7\63\2\2\u017b\u017c\5B\"\2\u017c\u017d"+
		"\7/\2\2\u017d\u0180\7Q\2\2\u017e\u017f\7/\2\2\u017f\u0181\7Q\2\2\u0180"+
		"\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0183\7\31"+
		"\2\2\u0183\u018c\3\2\2\2\u0184\u0185\7\36\2\2\u0185\u018c\5D#\2\u0186"+
		"\u0187\7\r\2\2\u0187\u0188\7\63\2\2\u0188\u0189\7Q\2\2\u0189\u018c\7\31"+
		"\2\2\u018a\u018c\5F$\2\u018b\u0179\3\2\2\2\u018b\u0184\3\2\2\2\u018b\u0186"+
		"\3\2\2\2\u018b\u018a\3\2\2\2\u018c?\3\2\2\2\u018d\u0191\5B\"\2\u018e\u0191"+
		"\5D#\2\u018f\u0191\7M\2\2\u0190\u018d\3\2\2\2\u0190\u018e\3\2\2\2\u0190"+
		"\u018f\3\2\2\2\u0191A\3\2\2\2\u0192\u0193\t\5\2\2\u0193C\3\2\2\2\u0194"+
		"\u0195\t\6\2\2\u0195E\3\2\2\2\u0196\u0197\7=\2\2\u0197\u0198\7\63\2\2"+
		"\u0198\u0199\7>\2\2\u0199\u019a\7Q\2\2\u019a\u019b\7/\2\2\u019b\u019c"+
		"\7X\2\2\u019c\u01a6\7\31\2\2\u019d\u019e\7E\2\2\u019e\u019f\7\63\2\2\u019f"+
		"\u01a0\7\20\2\2\u01a0\u01a1\7/\2\2\u01a1\u01a2\7Q\2\2\u01a2\u01a3\7/\2"+
		"\2\u01a3\u01a4\7S\2\2\u01a4\u01a6\7\31\2\2\u01a5\u0196\3\2\2\2\u01a5\u019d"+
		"\3\2\2\2\u01a6G\3\2\2\2\u01a7\u01a8\7/\2\2\u01a8\u01aa\5|?\2\u01a9\u01a7"+
		"\3\2\2\2\u01aa\u01ad\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac"+
		"I\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ae\u01af\7\33\2\2\u01af\u01b1\5\64\33"+
		"\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1K\3\2\2\2\u01b2\u01b3"+
		"\7\63\2\2\u01b3\u01b4\5N(\2\u01b4\u01b5\7\31\2\2\u01b5\u01bc\3\2\2\2\u01b6"+
		"\u01b7\5\22\n\2\u01b7\u01b8\5\24\13\2\u01b8\u01b9\7:\2\2\u01b9\u01ba\5"+
		"|?\2\u01ba\u01bc\3\2\2\2\u01bb\u01b2\3\2\2\2\u01bb\u01b6\3\2\2\2\u01bc"+
		"M\3\2\2\2\u01bd\u01be\5|?\2\u01be\u01bf\5H%\2\u01bf\u01c1\3\2\2\2\u01c0"+
		"\u01bd\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1O\3\2\2\2\u01c2\u01c3\5T+\2\u01c3"+
		"\u01c4\7\62\2\2\u01c4\u01c5\5\64\33\2\u01c5\u01c6\5R*\2\u01c6Q\3\2\2\2"+
		"\u01c7\u01c9\5P)\2\u01c8\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9S\3\2"+
		"\2\2\u01ca\u01cb\5X-\2\u01cb\u01cc\5V,\2\u01ccU\3\2\2\2\u01cd\u01ce\7"+
		"/\2\2\u01ce\u01d0\5T+\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0"+
		"W\3\2\2\2\u01d1\u01d2\5\\/\2\u01d2\u01d3\7Q\2\2\u01d3\u01d4\5Z.\2\u01d4"+
		"Y\3\2\2\2\u01d5\u01d6\7(\2\2\u01d6\u01d7\5\\/\2\u01d7\u01d8\7Q\2\2\u01d8"+
		"\u01da\3\2\2\2\u01d9\u01d5\3\2\2\2\u01d9\u01da\3\2\2\2\u01da[\3\2\2\2"+
		"\u01db\u01dd\7\60\2\2\u01dc\u01db\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd]\3"+
		"\2\2\2\u01de\u01df\5d\63\2\u01df\u01e0\5f\64\2\u01e0_\3\2\2\2\u01e1\u01e2"+
		"\t\7\2\2\u01e2a\3\2\2\2\u01e3\u01e4\t\b\2\2\u01e4c\3\2\2\2\u01e5\u01e6"+
		"\5h\65\2\u01e6\u01e7\5j\66\2\u01e7e\3\2\2\2\u01e8\u01e9\5b\62\2\u01e9"+
		"\u01ea\5d\63\2\u01ea\u01ec\3\2\2\2\u01eb\u01e8\3\2\2\2\u01ec\u01ef\3\2"+
		"\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01eeg\3\2\2\2\u01ef\u01ed"+
		"\3\2\2\2\u01f0\u01f1\5l\67\2\u01f1\u01f2\5r:\2\u01f2i\3\2\2\2\u01f3\u01f4"+
		"\5`\61\2\u01f4\u01f5\5h\65\2\u01f5\u01f7\3\2\2\2\u01f6\u01f3\3\2\2\2\u01f7"+
		"\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9k\3\2\2\2"+
		"\u01fa\u01f8\3\2\2\2\u01fb\u01fc\5\\/\2\u01fc\u01fd\5n8\2\u01fd\u0200"+
		"\3\2\2\2\u01fe\u0200\5p9\2\u01ff\u01fb\3\2\2\2\u01ff\u01fe\3\2\2\2\u0200"+
		"m\3\2\2\2\u0201\u0202\7?\2\2\u0202\u0203\7P\2\2\u0203\u0204\5\22\n\2\u0204"+
		"\u0205\5\24\13\2\u0205\u020f\3\2\2\2\u0206\u0207\7P\2\2\u0207\u020f\5"+
		"t;\2\u0208\u020f\7Q\2\2\u0209\u020f\7R\2\2\u020a\u020b\7\63\2\2\u020b"+
		"\u020c\5|?\2\u020c\u020d\7\31\2\2\u020d\u020f\3\2\2\2\u020e\u0201\3\2"+
		"\2\2\u020e\u0206\3\2\2\2\u020e\u0208\3\2\2\2\u020e\u0209\3\2\2\2\u020e"+
		"\u020a\3\2\2\2\u020fo\3\2\2\2\u0210\u0211\7\3\2\2\u0211\u0212\7P\2\2\u0212"+
		"\u0213\5\22\n\2\u0213\u0214\5\24\13\2\u0214\u0217\3\2\2\2\u0215\u0217"+
		"\7S\2\2\u0216\u0210\3\2\2\2\u0216\u0215\3\2\2\2\u0217q\3\2\2\2\u0218\u0219"+
		"\7\27\2\2\u0219\u021b\5l\67\2\u021a\u0218\3\2\2\2\u021b\u021e\3\2\2\2"+
		"\u021c\u021a\3\2\2\2\u021c\u021d\3\2\2\2\u021ds\3\2\2\2\u021e\u021c\3"+
		"\2\2\2\u021f\u0220\7\63\2\2\u0220\u0221\5|?\2\u0221\u0222\5H%\2\u0222"+
		"\u0223\7\31\2\2\u0223\u0229\3\2\2\2\u0224\u0225\5\22\n\2\u0225\u0226\5"+
		"\24\13\2\u0226\u0229\3\2\2\2\u0227\u0229\3\2\2\2\u0228\u021f\3\2\2\2\u0228"+
		"\u0224\3\2\2\2\u0228\u0227\3\2\2\2\u0229u\3\2\2\2\u022a\u022b\5^\60\2"+
		"\u022b\u022c\5x=\2\u022cw\3\2\2\2\u022d\u022e\5z>\2\u022e\u022f\5^\60"+
		"\2\u022f\u0231\3\2\2\2\u0230\u022d\3\2\2\2\u0230\u0231\3\2\2\2\u0231y"+
		"\3\2\2\2\u0232\u0233\t\t\2\2\u0233{\3\2\2\2\u0234\u0235\5\u0080A\2\u0235"+
		"\u0236\5\u0082B\2\u0236}\3\2\2\2\u0237\u0239\7\5\2\2\u0238\u0237\3\2\2"+
		"\2\u0238\u0239\3\2\2\2\u0239\177\3\2\2\2\u023a\u023b\5\u0086D\2\u023b"+
		"\u023c\5\u0084C\2\u023c\u0081\3\2\2\2\u023d\u023e\7B\2\2\u023e\u0240\5"+
		"\u0080A\2\u023f\u023d\3\2\2\2\u0240\u0243\3\2\2\2\u0241\u023f\3\2\2\2"+
		"\u0241\u0242\3\2\2\2\u0242\u0083\3\2\2\2\u0243\u0241\3\2\2\2\u0244\u0245"+
		"\7D\2\2\u0245\u0247\5\u0086D\2\u0246\u0244\3\2\2\2\u0247\u024a\3\2\2\2"+
		"\u0248\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u0085\3\2\2\2\u024a\u0248"+
		"\3\2\2\2\u024b\u024c\5~@\2\u024c\u024d\5\u0088E\2\u024d\u0087\3\2\2\2"+
		"\u024e\u0252\7.\2\2\u024f\u0252\7;\2\2\u0250\u0252\5v<\2\u0251\u024e\3"+
		"\2\2\2\u0251\u024f\3\2\2\2\u0251\u0250\3\2\2\2\u0252\u0089\3\2\2\2/\u0095"+
		"\u009a\u00a9\u00b6\u00c1\u00c6\u00ce\u00d3\u00d9\u00df\u00e6\u0106\u0109"+
		"\u0113\u0117\u011c\u0125\u0163\u0167\u0172\u0177\u0180\u018b\u0190\u01a5"+
		"\u01ab\u01b0\u01bb\u01c0\u01c8\u01cf\u01d9\u01dc\u01ed\u01f8\u01ff\u020e"+
		"\u0216\u021c\u0228\u0230\u0238\u0241\u0248\u0251";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}