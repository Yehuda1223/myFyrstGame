package Gemmme;

import java.awt.*;

public class MapGenrator {
    public int map[][];
    public int brickWidth;
    public int bricHeight;

    public MapGenrator(int row,int col){
        map=new int[row][col];

        for (int i = 0; i <row ; i++) {
            for (int j = 0; j <col ; j++) {
                map[i][j]=1;

            }

        }
        brickWidth=540/col;
        bricHeight=150/row;

    }
    public void setBrick(int value,int r,int c){
        map[r][c]=value;

    }
    public void drew(Graphics2D g){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]>0){
                    g.setColor(Color.MAGENTA);
                    g.fillRect(j*brickWidth+80,i*bricHeight+50,brickWidth,bricHeight);


                    g.setColor(Color.blue);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickWidth+80,i*bricHeight+50,brickWidth,bricHeight);
                }

            }
        }
    }






}
