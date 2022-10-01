import java.util.*;

public class Life
{
    private int iter;
    private int sizeOfArray;
    private String pattern;
    private PictureDemo pic;
    private int[][] prev;
    private int[][] curr;
    
   
    public Life (int iter, int sizeOfArray, String pattern)
    {
        this.iter=iter;
        this.sizeOfArray=sizeOfArray;
        this.pattern=pattern;
        pic= new PictureDemo(sizeOfArray,sizeOfArray,10);
        prev=new int[sizeOfArray][sizeOfArray];
        curr=new int[sizeOfArray][sizeOfArray]; 

        if (pattern.equals("P"))
        {
            makePentaArray();
        }
        else if(pattern.equals("S"))
        {
            makeSimArray();
        }
        else if(pattern.equals("R"))
        {
            makeArray();
        }

        for (int x = 0; x < iter; x++)
        {  
          
          pic.GenerateGrid(curr);  
          pic.show();                           //Using the PictureDemo class to display the grid

          for(int p=0; p<sizeOfArray; p++)
           { 
              for(int q=0; q<sizeOfArray; q++)
                {
                    prev[p][q]=curr[p][q];      //Moving the current grid to previous.
                }
 
            }

          for (int a = 0; a < sizeOfArray; a++) 
           { 
              for (int b = 0; b < sizeOfArray; b++) 
                    { 
                         int[][] numberOfNeighbours=getNeighbours(a,b);
                         int neighbours = 0;


                         for (int i = 0; i < numberOfNeighbours.length; i++) //To get the number of neighbours in the varibale "neighbours"
                        {
                         
                            int o = numberOfNeighbours[i][0]; 
                            int p = numberOfNeighbours[i][1];
                        
                        
                            if (prev[o][p] == 1) //if cell is alive 
                            {
                                neighbours++;
                            } 
                        }

                        if(prev[a][b]==1)       //if cell is alive 
                        {
                            if(neighbours<2)
                            {
                                curr[a][b]=0;
                            }
                            else if (neighbours==2 || neighbours==3)
                            {
                                curr[a][b]=1;
                            }
                            else if (neighbours>3)
                            {
                                curr[a][b]=0;
                            }
                        }
                        else if(prev[a][b]==0)
                        {
                            if(neighbours==3)
                            {
                                curr[a][b]=1;
                            }
                            else 
                            {
                                curr[a][b]=0;
                            }
                        }
                     } 
            }

            try               
            {
              Thread.sleep(400);  //Pause for 400 millisecond
            } 
            catch (InterruptedException e) 
            {
              Thread.currentThread().interrupt();
            }
        }
    }
        
    

    public void makeArray()      //function to make array with Random Values
    {
        Random rand = new Random();        
        for (int a = 0; a < sizeOfArray; a++) 
        { 
            for (int b = 0; b < sizeOfArray; b++) 
                   { 
                      curr[a][b] = Math.abs(rand.nextInt() % 2); 
                    } 
        }
    }

    public void makePentaArray() //function to make array with Penta Decathalon Oscillator
    {
        
        for (int a = 0; a < sizeOfArray; a++) 
        { 
            for (int b = 0; b < sizeOfArray; b++) 
                { 
                    curr[a][b] = 0; 
                } 
        }
        int i = sizeOfArray;
        int j = sizeOfArray;
        curr[i/2][j/2]=1;
        curr[i/2+1][j/2]=1;
        curr[i/2+2][j/2]=1;
        curr[i/2-1][j/2]=1;
        curr[i/2-3][j/2]=1;
        curr[i/2-4][j/2]=1;
        curr[i/2+4][j/2]=1;
        curr[i/2+5][j/2]=1;
        curr[i/2-2][j/2-1]=1;
        curr[i/2-2][j/2+1]=1;
        curr[i/2+3][j/2-1]=1;
        curr[i/2+3][j/2+1]=1;
        
    }

    public void makeSimArray()  //function to make array with Simkin Glider Gun 
    {
        for (int a = 0; a < sizeOfArray; a++) 
        { 
            for (int b = 0; b < sizeOfArray; b++) 
                { 
                    curr[a][b] = 0; 
                } 
        }
        int i = sizeOfArray;
        int j = sizeOfArray;
        square(i/2-11,j/2);     //To make the squares
        square(i/2-18,j/2);
        square(i/2-15,j/2+3);
        square(i/2+6,j/2+11);
        square(i/2+13,j/2+11);
        square(i/2+9,j/2+8);

        for(int a=0; a<3; a++) //For the pattern
        {
            curr[i/2+a][j/2]=1;
            
            if(a==0)
            {
            curr[i/2+a][j/2+1]=1;
            curr[i/2+a][j/2+2]=1;
            }
            
            if(a==2)
            {
                curr[i/2+a][j/2-1]=1;
                curr[i/2+a][j/2+1]=1;

            }

        }

    }
    public void square(int x, int y) //Function to make square to be used in makeSimArray
    {
        curr[x][y]=1;
        curr[x][y-1]=1;
        curr[x+1][y-1]=1;
        curr[x+1][y]=1;
    }

    private int[][] getNeighbours(int x, int y)
    
    {
        int[][] neighbours = new int[8][2];  //A two dimensional array to store the data of the neighbours
        
        int[] a = 
        {
            x,Math.floorMod(y-1,sizeOfArray)
        };
        int[] b = 
        {
            x,Math.floorMod(y+1,sizeOfArray)
        }; 
        int[] c = 
        {
            Math.floorMod(x-1,sizeOfArray), y
        };  
        int[] d = 
        {
            Math.floorMod(x+1,sizeOfArray),y
        };
        int[] e = 
        {
            Math.floorMod(x+1,sizeOfArray),Math.floorMod(y-1,sizeOfArray)
        };
        int[] f = 
        {
            Math.floorMod(x-1,sizeOfArray),Math.floorMod(y-1,sizeOfArray)
        };
        int[] g = 
        {
            Math.floorMod(x+1,sizeOfArray),Math.floorMod(y+1,sizeOfArray)
        };
        int[] h = 
        {
            Math.floorMod(x-1,sizeOfArray),Math.floorMod(y+1,sizeOfArray)
        };

        neighbours[0] = a; 
        neighbours[1] = b;
        neighbours[2] = c;
        neighbours[3] = d;
        neighbours[4] = e;
        neighbours[5] = f;
        neighbours[6] = g;
        neighbours[7] = h;

        return neighbours;
    }              
    
       
    public static void main(String[] args) 
    {                
        int iter = Integer.parseInt(args[0]);
        int sizeOfArray = Integer.parseInt(args[1]);
        String pattern  = args[2].toUpperCase();
        Life life= new Life(iter,sizeOfArray,pattern);                         
    }
}
 

      
      




    




