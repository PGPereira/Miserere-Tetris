/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Leandro
 */
public class Bloquinho {
    
    // Tamanho do bloquinho
    public static final int BLOCK_SIDE = 30;
    
    // Posição do bloquinho
    private int x, y, limiteY;
    private int tempo = 0;
    private boolean visivel = true;
    // Cor
    private final Color cor;
    
    // Velocidade do bloquinho 10px / s
    private static final int DESLOCAMENTO_BLOQUINHO = 100;
    private static final int TEMPO_ATUALIZACAO = 1000 / DESLOCAMENTO_BLOQUINHO;
    
    private boolean parado = false;
    
    public Bloquinho(int x, int y, int limiteY, Color cor) {
        this.x = x;
        this.y = y;
        this.limiteY = limiteY;
        this.cor = cor;
    }
    
    public void update(long gameTime) {
        
        tempo+=gameTime;
        
        if(tempo >= TEMPO_ATUALIZACAO) {
            int nDescidas = tempo / TEMPO_ATUALIZACAO;
            y+=nDescidas;
            tempo-=(TEMPO_ATUALIZACAO * nDescidas);            
        }
        
        if(y >= limiteY-30){
            y = limiteY-30;
            parado = true;
        }
        
        // Verifica se o bloquinho está visível
        /*if (y > limiteY) {
            visivel = false;
        }*/
    }
    
    public void draw(Graphics g) {
        
        // Se o bloquinho não está visível, não há o que desenhar
        if (!visivel) return;
        
        // Desenha
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(cor);
        g2d.fillRect(x, y, BLOCK_SIDE, BLOCK_SIDE);
    }
    
    public boolean isVisible() {
        return this.visivel;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }

    public boolean isParado() {
        return parado;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public void subtraiLimiteY(int limiteY){
        this.limiteY -= limiteY;
    }
    
    public int getLimiteY() {
        return limiteY;
    }

    public void setLimiteY(int limiteY) {
        this.limiteY = limiteY;
    }
 
    public void setParado(boolean parado){
        this.parado = parado;
    }
    
    public void aumentaVelocidade(){
        y += 10;
    }
    
}
