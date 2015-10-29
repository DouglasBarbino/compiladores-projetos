package trabalho3;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 */

import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class T1ErrorListener implements ANTLRErrorListener {
    Saida sp;
    
    public T1ErrorListener(Saida sp) {
        this.sp = sp;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
        Token t = (Token)o;
        String tkName = t.getText();
        if(tkName.equals("<EOF>")) tkName = "EOF";
        System.out.println("Linha " + i + ": erro sintatico proximo a " + tkName);
    
        sp.println("Linha " + i + ": erro sintatico proximo a " + tkName);
        throw new ParseCancellationException();
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
        //sp.println("Ambiguidade: linha " + i + ":" + i1 + " " + dfa.toString());
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
//        sp.println("Attempting full context: linha " + i + ":" + i1 + " " + dfa.toString());
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
//        sp.println("Context sensitivity: linha " + i + ":" + i1 + " " + dfa.toString());
    }
}
