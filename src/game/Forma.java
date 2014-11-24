/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class Forma {
    public static final int I = 0;
    public static final int J = 1;
    public static final int L = 2;
    public static final int O = 3;
    public static final int S = 4;
    public static final int T = 5;
    public static final int Z = 6;
    
    private int forma;
    private int x;
    private int y;
    private int limiteY;
    
    private int largura = 0;
    private int altura = 0;
    
    private Bloquinho b1;
    private Bloquinho b2;
    private Bloquinho b3;
    private Bloquinho b4;
    
    private boolean estaVertical = false;
    private int posicao = 0;//0 -> normal 1 -> 90 graus 2 ->180 3->270
    
    private ArrayList<Bloquinho> blocos = new ArrayList<>();
    
    
    public Forma(int forma, int x, int y, int limiteY){
        this.x = x;
        this.y = y;
        this.limiteY = limiteY;
        setForma(forma);
    }
    
    public void setForma(int forma){
        this.forma = forma;
        if(forma == 0){
            //linha
            b1 = new Bloquinho(x, y, limiteY, Color.cyan);//bloco principal
            b2 = new Bloquinho(x+30, y, limiteY, Color.cyan);
            b3 = new Bloquinho(x+60, y, limiteY, Color.cyan);
            b4 = new Bloquinho(x+90, y, limiteY, Color.cyan);
     
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura=120;
            altura=30;
        }else if(forma ==1){
            //L
            b1 = new Bloquinho(x, y, limiteY, Color.orange);
            b2 = new Bloquinho(x+30, y, limiteY, Color.orange);
            b3 = new Bloquinho(x+60, y, limiteY, Color.orange);
            b4 = new Bloquinho(x+60, y-30, limiteY-30, Color.orange);
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 90;
            altura = -30;
        }else if(forma ==2){
            //J
            b1 = new Bloquinho(x, y, limiteY-30, Color.blue);
            b2 = new Bloquinho(x+30, y, limiteY-30, Color.blue);
            b3 = new Bloquinho(x+60, y, limiteY-30, Color.blue);
            b4 = new Bloquinho(x+60, y+30, limiteY, Color.blue);
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 90;
            altura = 60;
        }else if(forma ==3){
            //quadrado
            b1 = new Bloquinho(x, y, limiteY-30, Color.yellow);
            b2 = new Bloquinho(x+30, y, limiteY-30, Color.yellow);
            b3 = new Bloquinho(x, y+30, limiteY, Color.yellow);
            b4 = new Bloquinho(x+30, y+30, limiteY, Color.yellow);
            
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 60;
            altura = 60;
        }else if(forma ==4){
            //S
            b1 = new Bloquinho(x, y, limiteY, Color.green);
            b2 = new Bloquinho(x+30, y, limiteY, Color.green);
            b3 = new Bloquinho(x+30, y-30, limiteY-30, Color.green);
            b4 = new Bloquinho(x+60, y-30, limiteY-30, Color.green);
            
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 90;
            altura = -30;
        }else if(forma ==5){
            //T
            b1 = new Bloquinho(x, y, limiteY, Color.magenta);
            b2 = new Bloquinho(x+30, y, limiteY, Color.magenta);
            b3 = new Bloquinho(x+30, y-30, limiteY-30, Color.magenta);
            b4 = new Bloquinho(x+60, y, limiteY, Color.magenta);
            
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 90;
            altura = -30;
        }else if(forma ==6){
            //Z
            b1 = new Bloquinho(x, y, limiteY-30, Color.red);
            b2 = new Bloquinho(x+30, y, limiteY-30, Color.red);
            b3 = new Bloquinho(x+30, y+30, limiteY, Color.red);
            b4 = new Bloquinho(x+60, y+30, limiteY, Color.red);
            
            
            blocos.add(b1);
            blocos.add(b2);
            blocos.add(b3);
            blocos.add(b4);
            largura = 90;
            altura = 60;
        }
    }

    public void girar(){
        //L, J E T tem 4 POSICOES
        
        if(!estaVertical){
            switch(forma){//lembrei do switch case dessa vez
                case(0)://linha
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x);
                    b2.setY(y+30);
                    b3.setX(x);
                    b3.setY(y+60);
                    b4.setX(x);
                    b4.setY(y+90);
                    
                    b1.setLimiteY(limiteY-90);
                    b2.setLimiteY(limiteY-60);
                    b3.setLimiteY(limiteY-30);
                    b4.setLimiteY(limiteY);
                    
                    largura=30;
                    altura=120;
                    
                    estaVertical = true;
                    break;
                case(1)://L
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(1):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y+30);
                            b3.setX(x);
                            b3.setY(y+60);
                            b4.setX(x+30);
                            b4.setY(y+60);

                            b1.setLimiteY(limiteY-60);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY);
                            b4.setLimiteY(limiteY);

                            largura=60;
                            altura=90;

                            estaVertical = true;
                            break;
                        case(3):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y-30);
                            b3.setX(x);
                            b3.setY(y-60);
                            b4.setX(x-30);
                            b4.setY(y-60);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-60);
                            b4.setLimiteY(limiteY-60);

                            largura=-30;
                            altura=-60;

                            estaVertical = true;
                            break;
                    }
                    
                    break;
                case(2)://J
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(1):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y+30);
                            b3.setX(x);
                            b3.setY(y+60);
                            b4.setX(x-30);
                            b4.setY(y+60);

                            b1.setLimiteY(limiteY-60);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY);
                            b4.setLimiteY(limiteY);

                            if(x <= 250){
                                b1.setX(x+30);
                                b2.setX(x+30);
                                b3.setX(x+30);
                                b4.setX(x);
                            }

                            largura=-30;
                            altura=90;//o bloco principal não é o mais a esquerda

                            estaVertical = true;
                            break;
                        case(3):
                            largura=60;
                            altura=-30;
                            
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y-30);
                            b3.setX(x);
                            b3.setY(y-60);
                            b4.setX(x+30);
                            b4.setY(y-60);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-60);
                            b4.setLimiteY(limiteY-60);

                            if(x+largura >= 550){
                                x=490;
                                b1.setX(490);
                                b2.setX(490);
                                b3.setX(490);
                                b4.setX(520);
                            }

                            

                            estaVertical = true;
                            break;
                    }
                    
                    break;
                case(3)://quadrado ->>>> nada
                    break;
                case(4)://S
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x);
                    b2.setY(y+30);
                    b3.setX(x+30);
                    b3.setY(y+30);
                    b4.setX(x+30);
                    b4.setY(y+60);
                    
                    b1.setLimiteY(limiteY-60);
                    b2.setLimiteY(limiteY-30);
                    b3.setLimiteY(limiteY-30);
                    b4.setLimiteY(limiteY);
                    
                    largura=60;
                    altura=90;
                    
                    estaVertical = true;
                    break;
                case(5)://T
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(1):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y+30);
                            b3.setX(x+30);
                            b3.setY(y+30);
                            b4.setX(x);
                            b4.setY(y+60);

                            b1.setLimiteY(limiteY-60);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-30);
                            b4.setLimiteY(limiteY);

                            largura=60;
                            altura=90;

                            estaVertical = true;
                            break;
                        case(3):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x);
                            b2.setY(y-30);
                            b3.setX(x-30);
                            b3.setY(y-30);
                            b4.setX(x);
                            b4.setY(y-60);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-30);
                            b4.setLimiteY(limiteY-60);

                            largura=-30;
                            altura=-60;

                            estaVertical = true;
                            break;
                    }
                    break;
                case(6)://Z
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x);
                    b2.setY(y+30);
                    b3.setX(x-30);
                    b3.setY(y+30);
                    b4.setX(x-30);
                    b4.setY(y+60);
                    
                    b1.setLimiteY(limiteY-60);
                    b2.setLimiteY(limiteY-30);
                    b3.setLimiteY(limiteY-30);
                    b4.setLimiteY(limiteY);
                    
                    if(x <= 250){
                        b1.setX(x+30);
                        b2.setX(x+30);
                        b3.setX(x);
                        b4.setX(x);
                    }
                    
                    largura=-30;
                    altura=90;//o bloco principal não é o mais a esquerda
                    
                    estaVertical = true;
                    break;
            }
            
            
        }else{
            switch(forma){
                case(0)://linha
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x+30);
                    b2.setY(y);
                    b3.setX(x+60);
                    b3.setY(y);
                    b4.setX(x+90);
                    b4.setY(y);
                    
                    b1.setLimiteY(limiteY);
                    b2.setLimiteY(limiteY);
                    b3.setLimiteY(limiteY);
                    b4.setLimiteY(limiteY);
                    
                    if(largura+x >= 430){//4 ultimos blocos direita - tem que arredar
                        x=430;
                        b1.setX(430);
                        b2.setX(460);
                        b3.setX(490);
                        b4.setX(520);
                    }
                                        
                    largura=120;
                    altura=30;

                    estaVertical = false;
                    break;
                case(1)://L
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(0):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x+30);
                            b2.setY(y);
                            b3.setX(x+60);
                            b3.setY(y);
                            b4.setX(x+60);
                            b4.setY(y-30);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY);
                            b3.setLimiteY(limiteY);
                            b4.setLimiteY(limiteY-30);

                            if(largura+x >= 460){//3 ultimos blocos direita - tem que arredar
                                x=460;
                                b1.setX(460);
                                b2.setX(490);
                                b3.setX(520);
                                b4.setX(520);
                            }

                            largura=90;
                            altura=-30;

                            estaVertical = false;
                            break;
                        case(2):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x-30);
                            b2.setY(y);
                            b3.setX(x-60);
                            b3.setY(y);
                            b4.setX(x-60);
                            b4.setY(y+30);

                            b1.setLimiteY(limiteY-30);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-30);
                            b4.setLimiteY(limiteY);

                            if(x <= 250){//3 ultimos blocos direita - tem que arredar
                                x=250+60;
                                b1.setX(250+60);
                                b2.setX(250+30);
                                b3.setX(250);
                                b4.setX(250);
                            }

                            largura=-60;
                            altura=60;

                            estaVertical = false;
                            break;
                    }
                    
                    break;
                case(2)://J
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(0):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x+30);
                            b2.setY(y);
                            b3.setX(x+60);
                            b3.setY(y);
                            b4.setX(x+60);
                            b4.setY(y+30);

                            b1.setLimiteY(limiteY-30);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY-30);
                            b4.setLimiteY(limiteY);

                            if(largura+x >= 460){//3 ultimos blocos direita - tem que arredar
                                x=460;
                                b1.setX(460);
                                b2.setX(490);
                                b3.setX(520);
                                b4.setX(520);
                            }

                            largura=90;
                            altura=60;

                            estaVertical = false;
                            break;
                        case(2):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x-30);
                            b2.setY(y);
                            b3.setX(x-60);
                            b3.setY(y);
                            b4.setX(x-60);
                            b4.setY(y-30);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY);
                            b3.setLimiteY(limiteY);
                            b4.setLimiteY(limiteY-30);

                            if(x <= 250){//3 ultimos blocos direita - tem que arredar
                                x=250;
                                b1.setX(250+60);
                                b2.setX(250+30);
                                b3.setX(250);
                                b4.setX(250);
                            }

                            largura=-60;
                            altura=-30;

                            estaVertical = false;
                            break;
                    }
                    break;
                case(3)://quadrado
                    break;
                case(4)://S
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x+30);
                    b2.setY(y);
                    b3.setX(x+30);
                    b3.setY(y-30);
                    b4.setX(x+60);
                    b4.setY(y-30);
                    
                    b1.setLimiteY(limiteY);
                    b2.setLimiteY(limiteY);
                    b3.setLimiteY(limiteY-30);
                    b4.setLimiteY(limiteY-30);
                    
                    if(x >= 460){//3 ultimos blocos direita - tem que arredar
                        x=460;
                        b1.setX(460);
                        b2.setX(490);
                        b3.setX(490);
                        b4.setX(520);
                    }
                                        
                    largura=90;
                    altura=-30;

                    estaVertical = false;
                    break;
                case(5)://T
                    if(posicao >= 3){
                        posicao = 0;
                    }else{
                        posicao++;
                    }
                    switch(posicao){
                        case(0):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x+30);
                            b2.setY(y);
                            b3.setX(x+30);
                            b3.setY(y-30);
                            b4.setX(x+60);
                            b4.setY(y);

                            b1.setLimiteY(limiteY);
                            b2.setLimiteY(limiteY);
                            b3.setLimiteY(limiteY-30);
                            b4.setLimiteY(limiteY);

                            if(largura+x >= 460){//3 ultimos blocos direita - tem que arredar
                                x=460;
                                b1.setX(460);
                                b2.setX(490);
                                b3.setX(490);
                                b4.setX(520);
                            }

                            largura=90;
                            altura=-30;

                            estaVertical = false;
                            break;
                        case(2):
                            b1.setX(x);
                            b1.setY(y);
                            b2.setX(x-30);
                            b2.setY(y);
                            b3.setX(x-30);
                            b3.setY(y+30);
                            b4.setX(x-60);
                            b4.setY(y);

                            b1.setLimiteY(limiteY-30);
                            b2.setLimiteY(limiteY-30);
                            b3.setLimiteY(limiteY);
                            b4.setLimiteY(limiteY-30);

                            if(x <= 250){//3 ultimos blocos direita - tem que arredar
                                x=250+60;
                                b1.setX(250+60);
                                b2.setX(250+30);
                                b3.setX(250+30);
                                b4.setX(250);
                            }

                            largura=-60;
                            altura=60;

                            estaVertical = false;
                            break;
                    }
                    
                    break;
                case(6)://Z
                    b1.setX(x);
                    b1.setY(y);
                    b2.setX(x+30);
                    b2.setY(y);
                    b3.setX(x+30);
                    b3.setY(y+30);
                    b4.setX(x+60);
                    b4.setY(y+30);
                    
                    b1.setLimiteY(limiteY-30);
                    b2.setLimiteY(limiteY-30);
                    b3.setLimiteY(limiteY);
                    b4.setLimiteY(limiteY);
                    
                    if(largura+x >= 460){//3 ultimos blocos direita - tem que arredar
                        x=460;
                        b1.setX(460);
                        b2.setX(490);
                        b3.setX(490);
                        b4.setX(520);
                    }
                                        
                    largura=90;
                    altura=60;

                    estaVertical = false;
                    break;
            }
        }
    }
    
    public int getForma() {
        return forma;
    }

    public ArrayList<Bloquinho> getBlocos() {
        return blocos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLimiteY() {
        return limiteY;
    }

    
    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }
    
    public void aumentaVelocidade(){
        if(y+60 < limiteY){
            b1.aumentaVelocidade();
            b2.aumentaVelocidade();
            b3.aumentaVelocidade();
            b4.aumentaVelocidade();
        }
    }

    public void subtraiLimiteY(int limiteY){
        this.limiteY -= limiteY;
        b1.subtraiLimiteY(limiteY);
        b2.subtraiLimiteY(limiteY);
        b3.subtraiLimiteY(limiteY);
        b4.subtraiLimiteY(limiteY);
        
    }
    
    public void setLimiteY(int limiteY){
        this.limiteY = limiteY;
        b1.setLimiteY(limiteY);
        b2.setLimiteY(limiteY);
        b3.setLimiteY(limiteY);
        b4.setLimiteY(limiteY);
    }
    
    public boolean encostouEmbaixo(){
        boolean encostou;
        if(altura>0){
            if(y + altura >=limiteY){
                return true;
            }
            else{
                return false;
            }
        }else{
            if(y + 30 >= limiteY){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    public void somaX(int x) {
        
        if(x+largura>=550){
            x=550-largura;
        }else if(largura<0 && x+30>=550){
            x=550-30;
        }

        else if(x+largura < 250){
           x=250-largura;
        }
        System.out.println(b1.getX() +","+b2.getX() +","+b3.getX() +","+b4.getX());
        int hold = x-this.x;
        this.x+=hold;
        System.out.println(hold);

        b1.moveX(hold);
        b2.moveX(hold);
        b3.moveX(hold);
        b4.moveX(hold);
        
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void arredaUmX(boolean direita){
        if(direita){
            int tempLargura = largura;
            if(largura < 0){//o primeiro bloco não é o mais a esquerda
               if(x+30>=550){
                   return;
               }else{
                x+=30;
                b1.moveX(30);
                b2.moveX(30);
                b3.moveX(30);
                b4.moveX(30);
               }
            }else{
                if(x+largura >= 550){
                    return;
                }
                else{
                    x+=30;
                    b1.moveX(30);
                    b2.moveX(30);
                    b3.moveX(30);
                    b4.moveX(30);
                }
            }
        }else{
            if(largura<0){
                if(x+largura-30 < 250){
                    return;
                }else{
                    x-=30;
                    b1.moveX(-30);
                    b2.moveX(-30);
                    b3.moveX(-30);
                    b4.moveX(-30);
                }
            }else{
                if(x-30<250){
                    return;
                }else{
                    x-=30;
                    b1.moveX(-30);
                    b2.moveX(-30);
                    b3.moveX(-30);
                    b4.moveX(-30);
                }
            }
        }
    }
    
}
