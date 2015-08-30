package trabalho1comp2;

import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class T1ErrorListener implements ANTLRErrorListener {
    Saida sp;
    
    public T1ErrorListener(Saida sp) {
        this.sp = sp;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
        System.out.println("Aqui!");
        sp.println("Linha " + i + ": erro sintatico proximo a " + ((CommonToken)o).getText());
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
        sp.println("Ambiguidade: linha " + i + ":" + i1 + " " + dfa.toString());
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
