package Gemmme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    private boolean play=false;
    private  int score=0;
    private int totlBrick=21;
    private Timer timer;
    private int dalay =8;
    private int bellposX=120;
    private int bellposY=350;
    private int bellXdir=-1;
    private int bellYdir=-2;
    private int playerX=350;
private MapGenrator map;


    public GamePlay(){
addKeyListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(true);


timer=new Timer(dalay,this);
timer.start();
map=new MapGenrator(3,7);




    }
public void paint(Graphics g){
//black canvas
g.setColor(Color.BLUE);
g.fillRect(1,1,692,592);


//border
g.setColor(Color.YELLOW);
g.fillRect(0,0,693,3);
    g.fillRect(0,3,3,592);
    g.fillRect(691,3,3,592);



    //paddle
    g.setColor(Color.GREEN);
    g.fillRect(playerX,560,110,8);
//bricks
    map.drew((Graphics2D)g);

//bell
    g.setColor(Color.red);
    g.fillOval(bellposX,bellposY,20,20);
//score
    g.setColor(Color.CYAN);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("Score :"+score,550,30);
    //Game over
    if(bellposY>=570){
        play=false;
        bellXdir=0;
        bellYdir=0;


        g.setColor(Color.GREEN);
        g.setFont(new Font("serif",Font.BOLD,40));
    }


}
 private void moveLeft(){
        play=true;
        playerX-=20;

}
    private void moveRight() {
        play=true;
        playerX += 20;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
if(play){
  if(bellposX<=0) {
      bellXdir =-bellXdir;
  }
  if(bellposX>=670){
      bellXdir=-bellXdir;
  }
    if(bellposY<=0) {
        bellYdir =-bellYdir;
    }
    Rectangle bellRect =new Rectangle(bellposX,bellposY,20,20);
    Rectangle paddieRect =new Rectangle(playerX,550,100,8);

    if(bellRect.intersects(paddieRect)){
        bellYdir=-bellYdir;

    }
   A: for (int i = 0; i <map.map.length ; i++) {
        for (int j = 0; j < map.map[0].length; j++) {
            if(map.map[i][j]>0){
                int width=map.brickWidth;
                int height=map.bricHeight;
                int brickXpos=80+j*width;
                int brickYpos=50+i*height;


                Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);



                if(bellRect.intersects(brickRect)){
                    map.setBrick(0,i,j);
                    totlBrick--;
                    score+=5;

                    if(bellposX+19<=brickXpos||bellposX+1>=brickXpos+width){
                        bellXdir=-bellXdir;

                    }else {
                        bellYdir=-bellYdir;
                    }
                    break A;
                }

            }

        }

    }


    bellposX+=bellXdir;
    bellposY+=bellYdir;
}
repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_LEFT){
            moveLeft();
            if (playerX<=0) {
                playerX = 0;
            } else{
                moveLeft();
            }

        }
        if ( e.getKeyCode() == KeyEvent.VK_RIGHT){
            moveRight();
            if (playerX>=600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
      repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
