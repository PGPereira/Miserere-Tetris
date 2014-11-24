/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author Leandro
 */
abstract public class Game extends JFrame implements Runnable {
    
    // Controle da atualização da tela
    static final int TICKS_PER_SECOND = 25;
    static final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    static final int MAX_FRAMESKIP = 5;    
    
    /**
     * Possíveis estados em que o jogo pode estar.
     */
    public static enum GameState {
        eGameStoped,
        eGameStarting,
        eGameRunning,
        eGameFinishing,
        eGameRestarting,
    }

    // Estado do jogo
    private GameState state = GameState.eGameStoped;
    
    // FPS
    protected FPS fps;
    
    // Double Buffering
    private Image dbImage;
    private Graphics dbGraphics;
    
    // Tamanho da janela
    protected int WIDTH, HEIGHT;
    
    
    /**
     * Construtor do jogo.
     */
    public Game(String title, int width, int height) {
        
        this.fps = new FPS();

        setTitle(title);
        setSize(width,height);
        setResizable(false);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WIDTH = width;
        HEIGHT = height;
        setUndecorated(true);

        GraphicsDevice d = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
         /*if (d.isFullScreenSupported()) {
            setUndecorated(true);
            d.setFullScreenWindow(this);
            d.getFullScreenWindow().setCursor(null);

            WIDTH = d.getFullScreenWindow().getWidth();
            HEIGHT = d.getFullScreenWindow().getHeight();
         }*/
        
    }
    
    /**
     * Define o novo estado do jogo.
     * @param state Estado do jogo.
     */
    private void setGameState(GameState state) {
        this.state = state;
    }
    
    /**
     * Método que obtem o estado atual do jogo.
     * @return Estado do jogo.
     */
    public GameState getGameState() {
        return this.state;
    }
    
    /**
     * Método chamado para inicializar os componentes necessários ao jogo.
     */
    abstract protected void inicializeGame();
    
    /**
     * Método chamado para finalizar os componentes utilizados no jogo.
     */
    abstract protected void finalizeGame();
    
    /**
     * 
     * @param gameTime 
     */
    abstract protected void updateGame(long gameTime);
    
    /**
     * 
     * @param interpolation 
     */
    abstract protected void drawGame(Graphics g);
    
    /**
     * Solicita que o jogo seja finalizado.
     */
    protected void finishGame() {
        setGameState(GameState.eGameFinishing);
    }
    
    /**
     * Solicita que o jogo seja reiniciado.
     */
    protected void restartGame() {
        setGameState(GameState.eGameRestarting);
    }
    
    /**
     * Método que calcula o FPS do jogo.
     * @return FPS.
     */
    protected float getFPS() {
        return fps.calcFPS();
    }
    
    /**
     * Método que define quantos milissegundos o jogo deverá espera entre
     * sucessivas chamadas do método gameUpdate.
     * @return Tempo de espera.
     */
    protected int getSkipTicks() {
        return SKIP_TICKS;
    }
    
    /**
     * Método que executa o loop do jogo.
     * @return Verdadeiro caso o jogo deva ser reiniciado, ou falso caso contrário.
     */
    private boolean gameLoop() {
        
        long nextGameTick = System.currentTimeMillis();
        int loops;
        long lastTime, atualTime = System.currentTimeMillis();
        
        lastTime = atualTime;
        
        while (getGameState() == GameState.eGameRunning) {
            loops = 0;
            
            atualTime = System.currentTimeMillis();
            
            // Chama o gameUpdate se estiver na hora de chamar mas não muitas
            // vezes seguidas sem tentar atualizar a tela
            while ((atualTime > nextGameTick) && (loops < MAX_FRAMESKIP)) {
                atualTime = System.currentTimeMillis();
                updateGame(atualTime - lastTime);
                lastTime = atualTime;

                nextGameTick += getSkipTicks();
                loops++;
            };
            
            // Se saiu do loop, ou ainda tem tempo até a próxima chamada ou
            // demorou tanto que já atualizou tanto e provavelmente ainda
            // não desenhou. Dorme um pouco!
            repaint();
       }        
        
        return (getGameState() == GameState.eGameRestarting);
    }
    
    
    /**
     * Método principal do jogo.
     */
    @Override
    public final void run() {
        for(;;) {
        
            // Chama o inicialize para carregar os componentes necessarios para o jogo
            setGameState(GameState.eGameStarting);
            inicializeGame();

            // Loop principal do jogo
            setGameState(GameState.eGameRunning);
            boolean restart = gameLoop();
            
            // Finaliza o jogo
            setGameState(GameState.eGameFinishing);
            finalizeGame();

            // Se foi solicitado reiniciar, reinicie
            if (!restart) break;
        }
        
        System.exit(0);
    }
    
    
    /**
     * Classe interna utilizada para calcular o FPS
     */
    static class FPS {
        
        private static final int NUM_FPS_SAMPLES = 25;
        private long fpsSamples[];
        int currentSample = 0;
        
        public FPS() {
            fpsSamples = new long[NUM_FPS_SAMPLES];
            
            // Por garantia, coloca todos como 0
            for(int i=0; i < NUM_FPS_SAMPLES; i++) {
                fpsSamples[i] = 0;
            }
        }
        
        /**
         * Adiciona um sample para o calculo do FPS.
         * @param dt Tempo em milissegundos entre os dois últimos quadros.
         */
        public void addSample(long dt) {
            synchronized(this) {
                fpsSamples[currentSample++ % NUM_FPS_SAMPLES] = dt;
            }
        }

        /**
         * Calcula o FPS.
         * @return FPS.
         */
        public float calcFPS()
        {
            
            long[] copySamples;
            synchronized(this) {
                copySamples = fpsSamples.clone();
            }
            
            long fps = 0;
            for (int i = 0; i < NUM_FPS_SAMPLES; i++) {
                fps += copySamples[i];
            }
            return 1000.0f / ((float)fps / NUM_FPS_SAMPLES);
        }        
        
    };
    
    

    private long lastPaintTime = System.currentTimeMillis();
    /**
     * Método que desenha o jogo na tela.
     * @param g Graphics utilizado para desenhar o jogo.
     */
    @Override
    public void paint(Graphics g) {

        dbImage = createImage(getWidth(), getHeight());
        dbGraphics = dbImage.getGraphics();
        
        drawGame(dbGraphics);
        g.drawImage(dbImage, 0, 0, this);
        
        long atualPaintTime = System.currentTimeMillis();
        fps.addSample(atualPaintTime - lastPaintTime);
        lastPaintTime = atualPaintTime;        
    }    
    
}
