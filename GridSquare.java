import javax.swing.*;
import java.awt.*;

public class GridSquare extends JPanel {
    private int xcoord, ycoord;     // location of this square

    // constructor takes the x and y coordinates of this square
    public GridSquare( int xcoord, int ycoord)
    {
        super();
        this.setSize(50, 50);
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    // if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
    public void setColor()
    {
        //Color colour = (int) (decider/2.0) == (decider/2.0) ? Color.black : Color.white;
        this.setBackground(Color.WHITE);
    }

    // if the square is black it becomes white, and vice-versa
    //public void switchColor()
    //{
    //    Color colour = (getBackground() == Color.black) ? Color.white: Color.black;
    //    this.setBackground( colour);
    //}

    // simple setters and getters
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
}
