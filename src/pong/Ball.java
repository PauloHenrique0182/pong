package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    public int x,y,width=25,height=25;

    public int motionX, motionY;


    public Random random;

    private Pong pong;

    private int amountOfHits;

    public Ball(Pong pong){

        this.pong = pong;
        this.random = new Random();
        spawn();
    }

    public void update(Paddle paddle1 , Paddle paddle2){

        int speed = 5;
        this.x += this.motionX * speed;
        this.y += this.motionY * speed;

        if (this.y + height > pong.height || y < 0){
            if (this.motionY < 0){
                this.y = 0;
                this.motionY = random.nextInt(4);
                if (this.motionY == 0){
                    this.motionY = 1;
                }
            } else {
                this.motionY = -random.nextInt(4);
                this.y = pong.height - height;
                if (this.motionY == 0){
                    this.motionY = -1;
                }
            }
        }

        if (checkCollision(paddle1) == 1){
            this.motionX = 1 + (amountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);
            if (this.motionY == 0){
                this.motionY = 1;
            }
            amountOfHits++;
        } else  if (checkCollision(paddle2) == 1){
            this.motionX = -1 - (amountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);
            if (this.motionY == 0){
                this.motionY = 1;
            }
            amountOfHits++;
        }
        if (checkCollision(paddle1) == 2){
            paddle2.score++;
            spawn();
        } else  if (checkCollision(paddle2) == 2){
            paddle1.score++;
            spawn();
        }
    }

    public void spawn(){
        this.amountOfHits = 0;
        this.x = pong.width/2- this.width /2;
        this.y = pong.height/2 - this.height /2;
        this.motionY = -2 + random.nextInt(1);

        if (motionY == 0){
            motionY = 1;
        }

        if(random.nextBoolean()){
            motionX = 1;
        } else {
            motionX = -1;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);
    }

    public int checkCollision(final Paddle paddle){
        int hit =0;//nothing
        if (this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y ) {
            hit = 1; //bounce
        } else if ((paddle.x > x  && paddle.paddleNumber == 1) || (paddle.x < x - width && paddle.paddleNumber == 2)){
            hit = 2;//score
        }
        return hit;
    }
}
