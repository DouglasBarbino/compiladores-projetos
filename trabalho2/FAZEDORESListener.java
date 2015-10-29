// Generated from FAZEDORES.g4 by ANTLR 4.2.2
package trabalho3;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FAZEDORESParser}.
 */
public interface FAZEDORESListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#chamada_atribuicao}.
	 * @param ctx the parse tree
	 */
	void enterChamada_atribuicao(@NotNull FAZEDORESParser.Chamada_atribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#chamada_atribuicao}.
	 * @param ctx the parse tree
	 */
	void exitChamada_atribuicao(@NotNull FAZEDORESParser.Chamada_atribuicaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(@NotNull FAZEDORESParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(@NotNull FAZEDORESParser.TermoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#comandoSetup}.
	 * @param ctx the parse tree
	 */
	void enterComandoSetup(@NotNull FAZEDORESParser.ComandoSetupContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#comandoSetup}.
	 * @param ctx the parse tree
	 */
	void exitComandoSetup(@NotNull FAZEDORESParser.ComandoSetupContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#comandoLoop}.
	 * @param ctx the parse tree
	 */
	void enterComandoLoop(@NotNull FAZEDORESParser.ComandoLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#comandoLoop}.
	 * @param ctx the parse tree
	 */
	void exitComandoLoop(@NotNull FAZEDORESParser.ComandoLoopContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_multiplicacao}.
	 * @param ctx the parse tree
	 */
	void enterOp_multiplicacao(@NotNull FAZEDORESParser.Op_multiplicacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_multiplicacao}.
	 * @param ctx the parse tree
	 */
	void exitOp_multiplicacao(@NotNull FAZEDORESParser.Op_multiplicacaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#intervalo_opcional}.
	 * @param ctx the parse tree
	 */
	void enterIntervalo_opcional(@NotNull FAZEDORESParser.Intervalo_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#intervalo_opcional}.
	 * @param ctx the parse tree
	 */
	void exitIntervalo_opcional(@NotNull FAZEDORESParser.Intervalo_opcionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(@NotNull FAZEDORESParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(@NotNull FAZEDORESParser.Parcela_logicaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(@NotNull FAZEDORESParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(@NotNull FAZEDORESParser.ProgramaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outros_termos_logicos}.
	 * @param ctx the parse tree
	 */
	void enterOutros_termos_logicos(@NotNull FAZEDORESParser.Outros_termos_logicosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outros_termos_logicos}.
	 * @param ctx the parse tree
	 */
	void exitOutros_termos_logicos(@NotNull FAZEDORESParser.Outros_termos_logicosContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(@NotNull FAZEDORESParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(@NotNull FAZEDORESParser.Declaracao_localContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#ponteiros_opcionais}.
	 * @param ctx the parse tree
	 */
	void enterPonteiros_opcionais(@NotNull FAZEDORESParser.Ponteiros_opcionaisContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#ponteiros_opcionais}.
	 * @param ctx the parse tree
	 */
	void exitPonteiros_opcionais(@NotNull FAZEDORESParser.Ponteiros_opcionaisContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(@NotNull FAZEDORESParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(@NotNull FAZEDORESParser.Tipo_basicoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(@NotNull FAZEDORESParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(@NotNull FAZEDORESParser.Fator_logicoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_opcional}.
	 * @param ctx the parse tree
	 */
	void enterOp_opcional(@NotNull FAZEDORESParser.Op_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_opcional}.
	 * @param ctx the parse tree
	 */
	void exitOp_opcional(@NotNull FAZEDORESParser.Op_opcionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outros_ident}.
	 * @param ctx the parse tree
	 */
	void enterOutros_ident(@NotNull FAZEDORESParser.Outros_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outros_ident}.
	 * @param ctx the parse tree
	 */
	void exitOutros_ident(@NotNull FAZEDORESParser.Outros_identContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outros_fatores}.
	 * @param ctx the parse tree
	 */
	void enterOutros_fatores(@NotNull FAZEDORESParser.Outros_fatoresContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outros_fatores}.
	 * @param ctx the parse tree
	 */
	void exitOutros_fatores(@NotNull FAZEDORESParser.Outros_fatoresContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(@NotNull FAZEDORESParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(@NotNull FAZEDORESParser.FatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(@NotNull FAZEDORESParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(@NotNull FAZEDORESParser.ParametroContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#cmdLoop}.
	 * @param ctx the parse tree
	 */
	void enterCmdLoop(@NotNull FAZEDORESParser.CmdLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#cmdLoop}.
	 * @param ctx the parse tree
	 */
	void exitCmdLoop(@NotNull FAZEDORESParser.CmdLoopContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_expressao}.
	 * @param ctx the parse tree
	 */
	void enterMais_expressao(@NotNull FAZEDORESParser.Mais_expressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_expressao}.
	 * @param ctx the parse tree
	 */
	void exitMais_expressao(@NotNull FAZEDORESParser.Mais_expressaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(@NotNull FAZEDORESParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(@NotNull FAZEDORESParser.Parcela_nao_unarioContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(@NotNull FAZEDORESParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(@NotNull FAZEDORESParser.Declaracao_globalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_var}.
	 * @param ctx the parse tree
	 */
	void enterMais_var(@NotNull FAZEDORESParser.Mais_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_var}.
	 * @param ctx the parse tree
	 */
	void exitMais_var(@NotNull FAZEDORESParser.Mais_varContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_nao}.
	 * @param ctx the parse tree
	 */
	void enterOp_nao(@NotNull FAZEDORESParser.Op_naoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_nao}.
	 * @param ctx the parse tree
	 */
	void exitOp_nao(@NotNull FAZEDORESParser.Op_naoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_ident}.
	 * @param ctx the parse tree
	 */
	void enterMais_ident(@NotNull FAZEDORESParser.Mais_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_ident}.
	 * @param ctx the parse tree
	 */
	void exitMais_ident(@NotNull FAZEDORESParser.Mais_identContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(@NotNull FAZEDORESParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(@NotNull FAZEDORESParser.CmdContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#chamada_partes}.
	 * @param ctx the parse tree
	 */
	void enterChamada_partes(@NotNull FAZEDORESParser.Chamada_partesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#chamada_partes}.
	 * @param ctx the parse tree
	 */
	void exitChamada_partes(@NotNull FAZEDORESParser.Chamada_partesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#comandoLCD}.
	 * @param ctx the parse tree
	 */
	void enterComandoLCD(@NotNull FAZEDORESParser.ComandoLCDContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#comandoLCD}.
	 * @param ctx the parse tree
	 */
	void exitComandoLCD(@NotNull FAZEDORESParser.ComandoLCDContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(@NotNull FAZEDORESParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(@NotNull FAZEDORESParser.RegistroContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#dispositivo}.
	 * @param ctx the parse tree
	 */
	void enterDispositivo(@NotNull FAZEDORESParser.DispositivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#dispositivo}.
	 * @param ctx the parse tree
	 */
	void exitDispositivo(@NotNull FAZEDORESParser.DispositivoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outros_fatores_logicos}.
	 * @param ctx the parse tree
	 */
	void enterOutros_fatores_logicos(@NotNull FAZEDORESParser.Outros_fatores_logicosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outros_fatores_logicos}.
	 * @param ctx the parse tree
	 */
	void exitOutros_fatores_logicos(@NotNull FAZEDORESParser.Outros_fatores_logicosContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#declaracoes_locais}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes_locais(@NotNull FAZEDORESParser.Declaracoes_locaisContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#declaracoes_locais}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes_locais(@NotNull FAZEDORESParser.Declaracoes_locaisContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_variaveis}.
	 * @param ctx the parse tree
	 */
	void enterMais_variaveis(@NotNull FAZEDORESParser.Mais_variaveisContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_variaveis}.
	 * @param ctx the parse tree
	 */
	void exitMais_variaveis(@NotNull FAZEDORESParser.Mais_variaveisContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(@NotNull FAZEDORESParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(@NotNull FAZEDORESParser.ExpressaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#argumentos_opcional}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos_opcional(@NotNull FAZEDORESParser.Argumentos_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#argumentos_opcional}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos_opcional(@NotNull FAZEDORESParser.Argumentos_opcionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#var_opcional}.
	 * @param ctx the parse tree
	 */
	void enterVar_opcional(@NotNull FAZEDORESParser.Var_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#var_opcional}.
	 * @param ctx the parse tree
	 */
	void exitVar_opcional(@NotNull FAZEDORESParser.Var_opcionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(@NotNull FAZEDORESParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(@NotNull FAZEDORESParser.Parcela_unarioContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outros_termos}.
	 * @param ctx the parse tree
	 */
	void enterOutros_termos(@NotNull FAZEDORESParser.Outros_termosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outros_termos}.
	 * @param ctx the parse tree
	 */
	void exitOutros_termos(@NotNull FAZEDORESParser.Outros_termosContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(@NotNull FAZEDORESParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(@NotNull FAZEDORESParser.ConstantesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#outras_parcelas}.
	 * @param ctx the parse tree
	 */
	void enterOutras_parcelas(@NotNull FAZEDORESParser.Outras_parcelasContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#outras_parcelas}.
	 * @param ctx the parse tree
	 */
	void exitOutras_parcelas(@NotNull FAZEDORESParser.Outras_parcelasContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(@NotNull FAZEDORESParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(@NotNull FAZEDORESParser.CorpoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(@NotNull FAZEDORESParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(@NotNull FAZEDORESParser.Exp_relacionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(@NotNull FAZEDORESParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(@NotNull FAZEDORESParser.Tipo_basico_identContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(@NotNull FAZEDORESParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(@NotNull FAZEDORESParser.VariavelContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(@NotNull FAZEDORESParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(@NotNull FAZEDORESParser.Decl_local_globalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(@NotNull FAZEDORESParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(@NotNull FAZEDORESParser.Numero_intervaloContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_selecao}.
	 * @param ctx the parse tree
	 */
	void enterMais_selecao(@NotNull FAZEDORESParser.Mais_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_selecao}.
	 * @param ctx the parse tree
	 */
	void exitMais_selecao(@NotNull FAZEDORESParser.Mais_selecaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(@NotNull FAZEDORESParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(@NotNull FAZEDORESParser.DeclaracoesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(@NotNull FAZEDORESParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(@NotNull FAZEDORESParser.ParcelaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_parametros}.
	 * @param ctx the parse tree
	 */
	void enterMais_parametros(@NotNull FAZEDORESParser.Mais_parametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_parametros}.
	 * @param ctx the parse tree
	 */
	void exitMais_parametros(@NotNull FAZEDORESParser.Mais_parametrosContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(@NotNull FAZEDORESParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(@NotNull FAZEDORESParser.Tipo_estendidoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#comandos}.
	 * @param ctx the parse tree
	 */
	void enterComandos(@NotNull FAZEDORESParser.ComandosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#comandos}.
	 * @param ctx the parse tree
	 */
	void exitComandos(@NotNull FAZEDORESParser.ComandosContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(@NotNull FAZEDORESParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(@NotNull FAZEDORESParser.Op_unarioContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(@NotNull FAZEDORESParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(@NotNull FAZEDORESParser.Exp_aritmeticaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(@NotNull FAZEDORESParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(@NotNull FAZEDORESParser.Valor_constanteContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#mais_constantes}.
	 * @param ctx the parse tree
	 */
	void enterMais_constantes(@NotNull FAZEDORESParser.Mais_constantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#mais_constantes}.
	 * @param ctx the parse tree
	 */
	void exitMais_constantes(@NotNull FAZEDORESParser.Mais_constantesContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#dispositivoSaida}.
	 * @param ctx the parse tree
	 */
	void enterDispositivoSaida(@NotNull FAZEDORESParser.DispositivoSaidaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#dispositivoSaida}.
	 * @param ctx the parse tree
	 */
	void exitDispositivoSaida(@NotNull FAZEDORESParser.DispositivoSaidaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_adicao}.
	 * @param ctx the parse tree
	 */
	void enterOp_adicao(@NotNull FAZEDORESParser.Op_adicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_adicao}.
	 * @param ctx the parse tree
	 */
	void exitOp_adicao(@NotNull FAZEDORESParser.Op_adicaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(@NotNull FAZEDORESParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(@NotNull FAZEDORESParser.DimensaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#dispositivoEntrada}.
	 * @param ctx the parse tree
	 */
	void enterDispositivoEntrada(@NotNull FAZEDORESParser.DispositivoEntradaContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#dispositivoEntrada}.
	 * @param ctx the parse tree
	 */
	void exitDispositivoEntrada(@NotNull FAZEDORESParser.DispositivoEntradaContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(@NotNull FAZEDORESParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(@NotNull FAZEDORESParser.SelecaoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(@NotNull FAZEDORESParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(@NotNull FAZEDORESParser.IdentificadorContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(@NotNull FAZEDORESParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(@NotNull FAZEDORESParser.Op_relacionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(@NotNull FAZEDORESParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(@NotNull FAZEDORESParser.TipoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#senao_opcional}.
	 * @param ctx the parse tree
	 */
	void enterSenao_opcional(@NotNull FAZEDORESParser.Senao_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#senao_opcional}.
	 * @param ctx the parse tree
	 */
	void exitSenao_opcional(@NotNull FAZEDORESParser.Senao_opcionalContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(@NotNull FAZEDORESParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(@NotNull FAZEDORESParser.Termo_logicoContext ctx);

	/**
	 * Enter a parse tree produced by {@link FAZEDORESParser#parametros_opcional}.
	 * @param ctx the parse tree
	 */
	void enterParametros_opcional(@NotNull FAZEDORESParser.Parametros_opcionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link FAZEDORESParser#parametros_opcional}.
	 * @param ctx the parse tree
	 */
	void exitParametros_opcional(@NotNull FAZEDORESParser.Parametros_opcionalContext ctx);
}