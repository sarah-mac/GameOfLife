
package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author e5112931
 */
public class Grid extends JPanel
{

    private int cellSize = 10;
    Rules rules = new Rules();

    public Grid()
    {

        setPreferredSize(new Dimension(520, 520));

    }

    public void setCellSize(int cellSize)
    {
        this.cellSize = cellSize;

    }

    @Override
    public void paintComponent(Graphics g)
    {
        Image image = createGameImage();
        g.drawImage(image, 0, 0, this);
        
        
    }
//creating bufferedimage
    
    private Image createGameImage(){
    BufferedImage bufferedImage = new BufferedImage(520,520,BufferedImage.TYPE_INT_RGB);
      Graphics g = bufferedImage.getGraphics();
      
      g.clearRect(0, 0, 500, 500);
        int aliveMemberX = 0;
        int aliveMemberY = 0;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 520, 520);
        g.setColor(Color.BLACK);
        //calculates the maximum number of cells by subtracting the remainder from the total window size
        int maxCells = 510 - (510 % cellSize);
        //sets grid line colour 
        g.setColor(Color.BLACK);
        //loop to draw lines horizontally
        for (int i = cellSize; i < maxCells + 1; i += cellSize)
        {
            g.drawLine(i, cellSize, i, maxCells -1 + 1);

        }
        //loop to draw lines vertically
        for (int i = cellSize; i < maxCells +1; i += cellSize)
        {
            g.drawLine(cellSize, i, maxCells, i);
        }
        //loops through the 2D array
        for (int i = 1; i < rules.getCurrentArray().length; i++)
        {
            for (int j = 1; j < rules.getCurrentArray().length; j++)
            {
                //if value at array index i,j = 1 fill cell in this position
                if (rules.getCurrentArray()[i][j] == 1)
                {

                    aliveMemberY = i * cellSize;
                    aliveMemberX = j * cellSize;
                    
                    g.fillRect(aliveMemberX, aliveMemberY, cellSize, cellSize);
                }
            }
        }
        return bufferedImage;
    
    }
    //creates random lives and fills cells
    public void random()
    {
        rules.setFirstGeneration();
        repaint();

    }
    //applies rules and re-draws grid and re-fills cells
    public void play(int row, int column)
    {

        rules.checkNeighbours(row, column);
        repaint();

    }

}
