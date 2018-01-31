
package gameoflife;

/**
 *
 * @author E5112931
 */
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.event.ChangeListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;

//Driver class creates GUI implements actionlistener events
public class GameOfLife extends JFrame implements ActionListener
{

    private JButton randomButton;
    private JButton pauseButton;
    private JButton startButton;
    private JLabel timeLabel;
    private JLabel sizeLabel;
    private Grid grid;
    private JSlider timeSlider;
    private JSlider sizeSlider;
    private int speed;
    private Timer timer;
    private int cellWidth = 10;
    //implements game of life rules
    Rules rules = new Rules();

    private int x = 0;
    private int y = 0;
    
    //creates the listeners and the sliders
    private ChangeListener changeListener = new ChangeListener()
    {
        public void stateChanged(ChangeEvent ce)
        {
            //if the source is the timeslider
            if (ce.getSource() == timeSlider)
            {
                if (!timeSlider.getValueIsAdjusting())
                {
                    //calls newgeneration method passing slider value as parameter
                    speed = timeSlider.getValue();
                    newGeneration(timeSlider.getValue());
                }
            }

            if (ce.getSource() == sizeSlider)
            {
                if (!sizeSlider.getValueIsAdjusting())
                {
                    //calls set new array , set current array and cellsize methods passing slider value as parameter
                    cellWidth = sizeSlider.getValue();
                    grid.setCellSize(cellWidth);
                    rules.setCurrentArray((510 / cellWidth), (510 / cellWidth));
                    rules.setNewGenArray((510 / cellWidth), (510 / cellWidth));
                }
            }

        }

    };

    public GameOfLife()
    {
        //draws grid
        grid = new Grid();
    }

    private void createGUI()
    {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        add(grid);

        randomButton = new JButton("Populate Grid");
        add(randomButton);
        randomButton.addActionListener(this);

        startButton = new JButton("Start");
        add(startButton);
        startButton.addActionListener(this);

        pauseButton = new JButton("Pause");
        add(pauseButton);
        pauseButton.addActionListener(this);

        timeLabel = new JLabel("change speed (ms)");
        add(timeLabel);
        timeSlider = new JSlider(0, 50, 25);
        add(timeSlider);
        timeSlider.setMinorTickSpacing(2);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeSlider.setLabelTable(timeSlider.createStandardLabels(10));
        timeSlider.addChangeListener(changeListener);

        sizeLabel = new JLabel("resize grid");
        add(sizeLabel);
        sizeSlider = new JSlider(0, 60, 30);
        add(sizeSlider);
        sizeSlider.setMinorTickSpacing(2);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setLabelTable(sizeSlider.createStandardLabels(10));
        sizeSlider.addChangeListener(changeListener);

    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        //start button calls the newGeneration method
        if (event.getSource() == startButton)
        {

            newGeneration(timeSlider.getValue());
        }
        //random button calls random method
        if (event.getSource() == randomButton)
        {
            //populates grid with random filled cells (live cells)
            grid.random();
        }
        //pauses timer, and resumes on second press
        if (event.getSource() == pauseButton)
        {
            if (event.getActionCommand().equals("Pause"))
            {
                pause();
                pauseButton.setText("Resume");
            } else
            {
                newGeneration(timeSlider.getValue());
                pauseButton.setText("Pause");
            }
        }
    }

    //repeats play method with timer at variable intervals
    public void newGeneration(int time)
    {

        if (timer != null)
        {

            timer.cancel();
        }

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                //checks neighbours, repaints grid and fills cells
                grid.play((510 / cellWidth), (510 / cellWidth));
            }
        };
        // sets time between repeating task
        timer = new Timer();
        timer.schedule(task, 0, time * 10 + 1);

    }
    //pauses the timer
    public void pause()
    {
        timer.cancel();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        GameOfLife frame = new GameOfLife();

        frame.setSize(800, 700);
        frame.createGUI();
        frame.setVisible(true);

    }

}
