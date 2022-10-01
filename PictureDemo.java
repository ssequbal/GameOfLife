/******************************************************************************
 *  Compilation:  javac *.java
 *  Execution:    java PictureDemo x y m
 *  Dependencies: Picture.java
 *
 *  Simple demo of how you can use the Picture class for graphical output
 *
 *  In this demo an x-by-y grid of cells will be drawn in random colours, to level of magnification m
 *
 *  m represents the number of screen pixels per cell
 *
 ******************************************************************************/

import java.awt.Color;

public class PictureDemo
{
    private int x,y;            // x-by-y grid of cells
    private int magnification;  // pixel-width of each cell
    private int[][] cells;      // cells to be randomly coloured
    private Picture pic;        // picture to be drawn on screen

    public PictureDemo(int x, int y, int magnification)
    {
        this.x = x;
        this.y = y;
        this.magnification = magnification;
        cells = new int[x][y];
        pic = new Picture(x * magnification, y * magnification);
    }
    
    // fill a cell with a random colour
    private void drawCell(int i, int j, Color color)
    {

        Color col = color;
        
        for (int offsetX = 0; offsetX < magnification; offsetX++)
        {
            for (int offsetY = 0; offsetY < magnification; offsetY++)
            {
                // set() colours an individual pixel
                pic.set((i*magnification)+offsetX,
                        (j*magnification)+offsetY, col);
            }
        }
    }

    public void GenerateGrid(int cells[][])
    {
        for (int i = 0; i < cells.length; i++) 
        {
 
            for (int j = 0; j < cells.length; j++) {
                
                if (cells[i][j]==0)
                    {
                      drawCell(i,j,Color.white);
                    }
                if (cells[i][j]==1)
                    {
                      drawCell(i,j,Color.black);
                    }
        }
        }      
            
        
    }
    
    
    // display (or update) the picture on screen
    public void show()
    {
        pic.show();     // without calling this the pic will not show
    }

    // must provide grid size (x & y) and level of magnification (m)
    public static void main(String[] args)
    {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        
        PictureDemo picDemo = new PictureDemo(x,y,m);

        
        picDemo.show();
    }
}
