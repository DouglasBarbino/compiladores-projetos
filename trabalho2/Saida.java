/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
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
