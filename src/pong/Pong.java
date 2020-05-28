package pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong implements ActionListener, KeyListener {

    public static Pong pong;
    public int width = 700, height = 700;
    public Renderer renderer;
    public Paddle player1;
    public Paddle player2;
    public Ball ball;
    public boolean bot = false;
    public boolean w,s,up,down;
    public int gameStatus = 0;//0 = stopped , 1 = pause , 2 = playing

    public Pong(){
        Timer timer = new Timer(20,this);

        JFrame jFrame = new JFrame("Pong");
        renderer = new Renderer();
        jFrame.setSize(width+15,height+35);
        jFrame.setVisible(true);
        //Para a execucao ao fechar a janela.
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(renderer);
        jFrame.addKeyListener(this);
        timer.start();
    }

    public void start(){
        gameStatus = 2;
        player1 = new Paddle(this,1);
        player2 = new Paddle(this, 2);
        ball = new Ball(this);
    }

    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameStatus == 0){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1,50));

            g.drawString("PONG", width /2 - 75, 50);

            g.setFont(new Font("Arial", 1,30));

            g.drawString("Press space to play", width /2 - 150, height / 2 - 25);
            g.drawString("Press shift to play with bot", width /2 - 200, height / 2 + 25);

        }


        if (gameStatus == 2 || gameStatus == 1){
            g.setColor(Color.white);

            g.setStroke(new BasicStroke(5f));
            g.drawLine(width/2,0,width/2,height);

            g.setStroke(new BasicStroke(2f));
            g.drawOval(width / 2 - 150,height / 2 - 150, 300,300 );

            g.setFont(new Font("Arial", 1,50));
            g.drawString(String.valueOf(player1.score), width /2 - 90, 50);
            g.drawString(String.valueOf(player2.score), width /2 + 65, 50);

            player1.render(g);
            player2.render(g);
            ball.render(g);
        }

        if (gameStatus == 1){
            g.setColor(Color.white);
            g.setFont(new Font("Arial", 1,50));
            g.drawString("PAUSED", width /2 -103, height /2 -25);
        }


    }

    public void update(){
        if (w){
            player1.move(true);
        } else if (s){
            player1.move(false);
        } else if (!bot){
            if (up){
                player2.move(true);
            } else if (down){
                player2.move(false);
            }
        } else {
            int speed = 15;
            if (player2.y + player2.height / 2 < ball.y ){
//                player2.y += speed;
                player2.move(false);
            }
            if (player2.y + player2.height / 2 > ball.y){
//                player2.y -= speed;


                player2.move(true);
            }

        }

        ball.update(this.player1, this.player2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus == 2){
            update();
        }
        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W){
            w = true;
        } else if (id == KeyEvent.VK_S){
            s = true;
        } else if (id == KeyEvent.VK_UP){
            up = true;
        } else if (id == KeyEvent.VK_DOWN){
            down = true;
        } else if (id == KeyEvent.VK_SHIFT && gameStatus == 0){
            bot = true;
            start();
        } else if (id == KeyEvent.VK_ESCAPE && gameStatus == 2){
            gameStatus = 0;
        }else if (id == KeyEvent.VK_SPACE){
            if(gameStatus == 0){
                start();
                bot =false;
            } else  if(gameStatus == 1){
                gameStatus = 2;
            } else if(gameStatus == 2){
                gameStatus = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();

        if (id == KeyEvent.VK_W){
            w = false;
        }
        if (id == KeyEvent.VK_S){
            s = false;
        }
        if (id == KeyEvent.VK_UP){
            up = false;
        }
        if (id == KeyEvent.VK_DOWN){
            down = false;
        }
    }

    public static void main(String[] args) {
        pong = new Pong();
    }
}
