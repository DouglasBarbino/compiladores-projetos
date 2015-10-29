// Generated from FAZEDORES.g4 by ANTLR 4.2.2
package trabalho1;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FAZEDORESParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FAZEDORESVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#chamada_atribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChamada_atribuicao(@NotNull FAZEDORESParser.Chamada_atribuicaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(@NotNull FAZEDORESParser.TermoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#comandoSetup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandoSetup(@NotNull FAZEDORESParser.ComandoSetupContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#comandoLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandoLoop(@NotNull FAZEDORESParser.ComandoLoopContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_multiplicacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_multiplicacao(@NotNull FAZEDORESParser.Op_multiplicacaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#intervalo_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalo_opcional(@NotNull FAZEDORESParser.Intervalo_opcionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(@NotNull FAZEDORESParser.Parcela_logicaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(@NotNull FAZEDORESParser.ProgramaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outros_termos_logicos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_termos_logicos(@NotNull FAZEDORESParser.Outros_termos_logicosContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(@NotNull FAZEDORESParser.Declaracao_localContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#ponteiros_opcionais}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonteiros_opcionais(@NotNull FAZEDORESParser.Ponteiros_opcionaisContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(@NotNull FAZEDORESParser.Tipo_basicoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(@NotNull FAZEDORESParser.Fator_logicoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_opcional(@NotNull FAZEDORESParser.Op_opcionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outros_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_ident(@NotNull FAZEDORESParser.Outros_identContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outros_fatores}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_fatores(@NotNull FAZEDORESParser.Outros_fatoresContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(@NotNull FAZEDORESParser.FatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(@NotNull FAZEDORESParser.ParametroContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#cmdLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLoop(@NotNull FAZEDORESParser.CmdLoopContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_expressao(@NotNull FAZEDORESParser.Mais_expressaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(@NotNull FAZEDORESParser.Parcela_nao_unarioContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(@NotNull FAZEDORESParser.Declaracao_globalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_var(@NotNull FAZEDORESParser.Mais_varContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_nao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_nao(@NotNull FAZEDORESParser.Op_naoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_ident(@NotNull FAZEDORESParser.Mais_identContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(@NotNull FAZEDORESParser.CmdContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#chamada_partes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChamada_partes(@NotNull FAZEDORESParser.Chamada_partesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#comandoLCD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandoLCD(@NotNull FAZEDORESParser.ComandoLCDContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(@NotNull FAZEDORESParser.RegistroContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#dispositivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDispositivo(@NotNull FAZEDORESParser.DispositivoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outros_fatores_logicos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_fatores_logicos(@NotNull FAZEDORESParser.Outros_fatores_logicosContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#declaracoes_locais}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes_locais(@NotNull FAZEDORESParser.Declaracoes_locaisContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_variaveis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_variaveis(@NotNull FAZEDORESParser.Mais_variaveisContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(@NotNull FAZEDORESParser.ExpressaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#argumentos_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos_opcional(@NotNull FAZEDORESParser.Argumentos_opcionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#var_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_opcional(@NotNull FAZEDORESParser.Var_opcionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#comandosArduino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandosArduino(@NotNull FAZEDORESParser.ComandosArduinoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(@NotNull FAZEDORESParser.Parcela_unarioContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outros_termos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_termos(@NotNull FAZEDORESParser.Outros_termosContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(@NotNull FAZEDORESParser.ConstantesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#outras_parcelas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutras_parcelas(@NotNull FAZEDORESParser.Outras_parcelasContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(@NotNull FAZEDORESParser.CorpoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(@NotNull FAZEDORESParser.Exp_relacionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(@NotNull FAZEDORESParser.Tipo_basico_identContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(@NotNull FAZEDORESParser.VariavelContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(@NotNull FAZEDORESParser.Decl_local_globalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(@NotNull FAZEDORESParser.Numero_intervaloContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_selecao(@NotNull FAZEDORESParser.Mais_selecaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(@NotNull FAZEDORESParser.DeclaracoesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(@NotNull FAZEDORESParser.ParcelaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_parametros(@NotNull FAZEDORESParser.Mais_parametrosContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(@NotNull FAZEDORESParser.Tipo_estendidoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#comandos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandos(@NotNull FAZEDORESParser.ComandosContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(@NotNull FAZEDORESParser.Op_unarioContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(@NotNull FAZEDORESParser.Exp_aritmeticaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(@NotNull FAZEDORESParser.Valor_constanteContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#mais_constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_constantes(@NotNull FAZEDORESParser.Mais_constantesContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_adicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_adicao(@NotNull FAZEDORESParser.Op_adicaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#dispositivoSaida}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDispositivoSaida(@NotNull FAZEDORESParser.DispositivoSaidaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(@NotNull FAZEDORESParser.DimensaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#dispositivoEntrada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDispositivoEntrada(@NotNull FAZEDORESParser.DispositivoEntradaContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(@NotNull FAZEDORESParser.SelecaoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(@NotNull FAZEDORESParser.IdentificadorContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(@NotNull FAZEDORESParser.Op_relacionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(@NotNull FAZEDORESParser.TipoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#senao_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSenao_opcional(@NotNull FAZEDORESParser.Senao_opcionalContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(@NotNull FAZEDORESParser.Termo_logicoContext ctx);

	/**
	 * Visit a parse tree produced by {@link FAZEDORESParser#parametros_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros_opcional(@NotNull FAZEDORESParser.Parametros_opcionalContext ctx);
}