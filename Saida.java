/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1comp2;

/**
 *
 * @author daniel
 */
public class Saida {
    private StringBuffer texto = new StringBuffer();
    
    public void println(String txt) {
        texto.append(txt).append("\n");
    }
    
    public void clear() {
        texto = new StringBuffer();
    }
    
    public String getTexto() {
        return texto.toString();
    }
}
