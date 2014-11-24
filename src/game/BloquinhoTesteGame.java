/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Leandro
 */
public class BloquinhoTesteGame extends Game {
    
    // Posição do FPS 
    private static final int FPS_X = 10;
    private static final int FPS_Y = 20;
    
    // Posição do mouse
    private int x, y;
    
    //retangulo bacaninha
    private TelaDisponivel t;
    
    private double xDesenhar;
    
    private Forma formaAtual = null;
    private Forma formaAnterior = null;
    
    
    // Lista de bloquinhos
    ArrayList<Bloquinho> listaDeBloquinhos;
    
    private int limitaX(int X) {
        if (X > (WIDTH - Bloquinho.BLOCK_SIDE)) {
            return (WIDTH - Bloquinho.BLOCK_SIDE);
        } else if (X < 0) {
            return 0;
        } else {
            return X;
        }
    }
    
    private int limitaY(int Y) {
        if (Y > (HEIGHT - Bloquinho.BLOCK_SIDE)) {
            return (HEIGHT - Bloquinho.BLOCK_SIDE);
        } else if (Y < 0) {
            return 0;
        } else {
            return Y;
        }
    }    
    
    private void novaForma(){
        Random rand = new Random();

        int randnum = rand.nextInt(4);
        randnum+=2;

        xDesenhar = (randnum * 30) + 250;

        int tipoRandom = rand.nextInt(7);

        formaAtual = new Forma(tipoRandom,(int)xDesenhar, 0, HEIGHT);
}
    
    private class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            switch(keyCode) {
                case KeyEvent.VK_UP:
                    formaAtual.girar();
                    break;
                    
                case KeyEvent.VK_DOWN:
                    formaAtual.aumentaVelocidade();
                    break;
                    
                case KeyEvent.VK_LEFT:
                    if(formaAtual != null) formaAtual.arredaUmX(false);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if(formaAtual != null) formaAtual.arredaUmX(true);
                    break;
                    
                case KeyEvent.VK_ENTER:
                    // Novo bloquinho
                    formaAnterior = formaAtual;
                    novaForma();
                    listaDeBloquinhos.addAll(formaAtual.getBlocos());
                    break;
                    
                case KeyEvent.VK_ESCAPE:
                    finishGame();
                    
            }
        }
    }
    
    public class ML extends MouseAdapter  {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            if(x >= 250  && x <= 550 && formaAtual != null){
                formaAtual.somaX(x);
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            formaAtual.girar();
        }
    }
    
    
    public BloquinhoTesteGame() {
        super("Tetris Type A", 800, 600);
        
        // Teclado
        addKeyListener(new AL());
        // Mouse
        ML ml = new ML();
        addMouseListener(ml);
        addMouseMotionListener(ml);
        // Bloquinhos
        listaDeBloquinhos = new ArrayList<Bloquinho>();
        // retangulo
        t = new TelaDisponivel(250, -1, 300, 601);
        
    }

    @Override
    protected void inicializeGame() {
        Musiquinha mus = new Musiquinha("/miserere.mp3");
        mus.play();
    }

    @Override
    protected void finalizeGame() {
        // Faz nada
    }

    @Override
    protected void updateGame(long gameTime) {
        //checa colisoes entre bloquinhos
        
        int yAtual = 0;
        ArrayList<Bloquinho> hold = null;
        if(formaAtual != null){
            yAtual = formaAtual.getY();
             hold = formaAtual.getBlocos();
        }

        
        loopExterno:
        for(int i=0; i<listaDeBloquinhos.size()-4;i++){
            if(formaAtual.getAltura() > 0){
                if(formaAtual.getY() + formaAtual.getAltura() >= listaDeBloquinhos.get(i).getY()){
                    for(int j=0; j<hold.size(); j++){
                        if(hold.get(j).getY()+30>= listaDeBloquinhos.get(i).getY() && hold.get(j).getX()== listaDeBloquinhos.get(i).getX()){
                            //formaAtual.subtraiLimiteY(formaAtual.getAltura());
                            formaAtual.subtraiLimiteY(formaAtual.getAltura()-30);
                            break loopExterno;
                        }
                    }
                }
            }else{
                if(formaAtual.getY() + 30 >= listaDeBloquinhos.get(i).getY()){
                    for(int j=0; j<hold.size(); j++){
                        if(hold.get(j).getY()+30>= listaDeBloquinhos.get(i).getY() && hold.get(j).getX()== listaDeBloquinhos.get(i).getX()){
                            formaAtual.subtraiLimiteY(30);
                            break loopExterno;
                        }
                    }
                }
            }
        }
        /*
        int[] linhas={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        
        for(int i=0; i<listaDeBloquinhos.size();i++){
            int z = (listaDeBloquinhos.get(i).getY()/30);
            switch(z){
                case(0):
                    linhas[0]++;
                    break;
                case(1):
                    linhas[1]++;
                    break;
                case(2):
                    linhas[2]++;
                    break;
                case(3):
                    linhas[3]++;
                    break;
                case(4):
                    linhas[4]++;
                    break;
                case(5):
                    linhas[5]++;
                    break;
                case(6):
                    linhas[6]++;
                    break;
                case(7):
                    linhas[7]++;
                    break;
                case(8):
                    linhas[8]++;
                    break;
                case(9):
                    linhas[9]++;
                    break;
                case(10):
                    linhas[10]++;
                    break;
                case(11):
                    linhas[11]++;
                    break;
                case(12):
                    linhas[12]++;
                    break;
                case(13):
                    linhas[13]++;
                    break;
                case(14):
                    linhas[14]++;
                    break;
                case(15):
                    linhas[15]++;
                    break;
                case(16):
                    linhas[16]++;
                    break;
                case(17):
                    linhas[17]++;
                    break;
                case(18):
                    linhas[18]++;
                    break;
                case(19):
                    linhas[19]++;
                    break;
                default:
                    System.out.println("tretasM");
                    break;
            }
        }
        
        for(int i = 0; i<=19; i++){
            if (linhas[i]==10){
                for(int j=0; j<listaDeBloquinhos.size();j++){
                    if((listaDeBloquinhos.get(j).getY()/30)-30==i){
                        listaDeBloquinhos.get(j).setVisivel(false);
                    }
                }
            }
        }
        
        
        */
        if(formaAtual!= null){
            int index = listaDeBloquinhos.size()-4;//bloquinho principal
            int y = listaDeBloquinhos.get(index).getY();
            formaAtual.setY(y);
            
            if(formaAtual.encostouEmbaixo()) {
                formaAnterior = formaAtual;
                novaForma();
                listaDeBloquinhos.addAll(formaAtual.getBlocos());
            }
        }else{
            novaForma();
            listaDeBloquinhos.addAll(formaAtual.getBlocos());
        }
        

        
        for(int i = (listaDeBloquinhos.size() - 1); i >= 0; i--) {
            Bloquinho b = listaDeBloquinhos.get(i);
            b.update(gameTime);
            
            // Verifica se está invisivel
            if (!b.isVisible()) {
                listaDeBloquinhos.remove(i);
            }
        }
        
    }

    private ImageIcon fundo = new ImageIcon("space_0.jpg");
    
    @Override
    protected void drawGame(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.white);
        g2d.drawImage(fundo.getImage(), 0, 0, WIDTH, HEIGHT, null);
        g2d.drawString("FPS:" + fps.calcFPS(), FPS_X, FPS_Y);
        g2d.drawString("Ponto:" + x + " / " + y, FPS_X, FPS_Y + 15);
        
        g2d.setColor(Color.orange);
        g2d.draw(t.getRetangulo());
        g2d.setColor(Color.white);
        g2d.fillRect(t.getRetangulo().x+1, t.getRetangulo().y, t.getRetangulo().width-1, t.getRetangulo().height);
        g2d.setColor(Color.black);
           
        
        
        

        
        
        // Desenha os bloquinhos
        if (listaDeBloquinhos == null) return;
        ArrayList<Bloquinho> bloquinhosCopy = (ArrayList<Bloquinho>)listaDeBloquinhos.clone();
        for(Bloquinho b: bloquinhosCopy) {
            b.draw(g2d);
        }
        
        // for(int i=0; i < 500000000; i++);
    }
    
    public static void main(String[] args) {
        BloquinhoTesteGame tg = new BloquinhoTesteGame();
        Thread thread = new Thread(tg);
        thread.start();
        tg.setVisible(true);
    }
    
}
