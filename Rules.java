
package gameoflife;

/**
 *
 * @author E5112931
 */
public class Rules
{

    private static int[][] currentArray;
    private static int[][] newGenArray;
    private int row;
    private int column;

    public Rules()
    {
        row = 51;
        column = 51;
        this.currentArray = new int[row][column];
        
    }
    //sets size of current array with passed values
    public void setCurrentArray(int row, int column)
    {
        this.currentArray = new int[row][column];
    }
    //sets size of new gen  array with passed values
    public void setNewGenArray(int row, int column)
    {
        this.newGenArray = new int[row][column];
    }
    //randomly fills current array with 0's and 1's 
    public void setFirstGeneration()
    {
        for (int i = 0; i < currentArray.length; i++)
        {
            for (int j = 0; j < currentArray.length; j++)
            {
                currentArray[i][j] = (int) (Math.random() * 2);
                System.out.print(currentArray[i][j]);

            }
           
        }
    }
    //returns current array
    public int[][] getCurrentArray()
    {
        return currentArray;
    }
    //checks surrounding cells for 1 values and applies rules to determine next generation
    public void checkNeighbours(int row, int column)
    {
         this.newGenArray = new int[row][column];
       //Loops arrray but excludes the edges
        for (int i = 1; i < newGenArray.length-1; i++)
        {
            for (int j = 1; j < newGenArray.length-1; j++)
            {
                int count = 0;
                //counts up alive neighbouring cells
              
                if (currentArray[i - 1][j - 1] == 1)
                {
                    count++;
                }

                if (currentArray[i][j - 1] == 1)
                {
                    count++;
                }

                if (currentArray[i + 1][j - 1] == 1)
                {
                    count++;
                }

                if (currentArray[i - 1][j] == 1)
                {
                    count++;
                }

                if (currentArray[i + 1][j] == 1)

                {
                    count++;
                }

                if (currentArray[i - 1][j + 1] == 1)
                {
                    count++;
                }

                if (currentArray[i][j + 1] == 1)
                {
                    count++;
                }

                if (currentArray[i + 1][j + 1] == 1)
                {
                    count++;
                }

                //apply rules
                if ((currentArray[i][j] == 1) && (count > 3))
                {
                    newGenArray[i][j] = 0;
                } else if ((currentArray[i][j] == 1) && (count < 2))
                {
                    newGenArray[i][j] = 0;
                } else if ((currentArray[i][j] == 0) && (count == 3))
                {
                    newGenArray[i][j] = 1;

                } else
                {
                    newGenArray[i][j] = currentArray[i][j];
                }
            }
           
        }

        //newGenArray is now currentArray
        currentArray = newGenArray;
    }
}
