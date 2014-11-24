/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;

/**
 *
 * @author aluno
 */
public class TelaDisponivel {
    private int largura;//300
    private int altura;//600
    private int x;
    private int y;
    private Rectangle r;
    
    public TelaDisponivel(int x, int y, int largura, int altura){
        r = new Rectangle(x, y, largura, altura);
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRetangulo(){
        return this.r;
    }
    
    
}
