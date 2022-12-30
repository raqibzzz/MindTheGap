import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.Random;

public class GameWindow extends JFrame implements ActionListener, MouseListener {
    private JLabel instructionLabel;
    private int rows,columns;
    private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
    private JButton topButton;				// a 'reset' button to appear in the top panel
    private GridSquare [][] gridSquares;
    private boolean gameStatus;

    public GameWindow(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.setSize(600,600);

        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(rows, columns));
        bottomPanel.setSize(500,500);
        Border blackline = BorderFactory.createLineBorder(Color.black);

        // then create the components for each panel and add them to it

        // for the top panel:
        instructionLabel = new JLabel("Don't select neighbour squares! Click to begin >> ");
        //infoLabel = new JLabel("No square clicked yet.");
        topButton = new JButton("New Game");
        topButton.addActionListener(this);			// IMPORTANT! Without this, clicking the square does nothing.

        topPanel.add(instructionLabel);
        topPanel.add (topButton);
        //topPanel.add(infoLabel);


        // for the bottom panel:
        // create the squares and add them to the grid
        gridSquares = new GridSquare[rows][columns];
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y] = new GridSquare(x, y);
                gridSquares[x][y].setSize(20, 20);
                gridSquares[x][y].setBackground(Color.WHITE);
                gridSquares[x][y].setBorder(blackline);
                //while(gameStatus==true){
                gridSquares[x][y].addMouseListener(this);		// AGAIN, don't forget this line!
                //}
                bottomPanel.add(gridSquares[x][y]);
            }
        }

        // now add the top and bottom panels to the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.CENTER);		// needs to be center or will draw too small

        // housekeeping : behaviour
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();

        // if resetting the squares' colours is requested then do so
        if ( selected.equals(topButton) )
        {
        //gameStatus = true;
        for ( int x = 0; x < columns; x ++)
        {
            for ( int y = 0; y < rows; y ++)
            {
                gridSquares[x][y].setColor();		// AGAIN, don't forget this line
            }
        }
        Random num = new Random();
        int playerNum = num.nextInt(2);
        if(playerNum==0){
            instructionLabel.setText("Player 1's turn");
        }
        else if(playerNum==1){
            instructionLabel.setText("Player 2's turn");
        }
        }
        //{
        //    for ( int x = 0; x < columns; x ++)
        //    {
        //        for ( int y = 0; y < rows; y ++)
        //        {
        //            gridSquares [x][y].setColor(x + y);
        //        }
        //    }
        //}
    }


    // Mouse Listener events
    public void mouseClicked(MouseEvent mevt)
    {
        // get the object that was selected in the gui
        Object selected = mevt.getSource();

        /*
         * I'm using instanceof here so that I can easily cover the selection of any of the gridsquares
         * with just one piece of code.
         * In a real system you'll probably have one piece of action code per selectable item.
         * Later in the course we'll see that the Command Holder pattern is a much smarter way to handle actions.
         */

        // if a gridsquare is selected then switch its color
        if (selected instanceof GridSquare)
        {
            GridSquare square = (GridSquare) selected;
            //for ( int x = 0; x < columns; x ++)
            //{
            //for ( int y = 0; y < rows; y ++)
            //{
            int currXcoord = square.getXcoord();
            int currYcoord = square.getYcoord();
            boolean lightswitch = true;
            //if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)){}
            if(instructionLabel.getText().equals("Player 1's turn")&&square.getBackground().equals(Color.WHITE))
            {
                if(currXcoord==0&&currYcoord==0){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==1){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==2){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==3){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==0){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==0){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==0){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==1){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==2){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==3){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==3){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==3){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==1){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==2){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==1){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==2){
                    if(square.getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.RED)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.RED)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 2 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                if(lightswitch==true){
                square.setBackground(Color.RED);
                instructionLabel.setText("Player 2's turn");
                }
            }
            
            else if(instructionLabel.getText().equals("Player 2's turn")&&square.getBackground().equals(Color.WHITE))
            {
                if(currXcoord==0&&currYcoord==0){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==1){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==2){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==0&&currYcoord==3){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==0){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==0){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==0){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==1){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==2){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==3&&currYcoord==3){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==3){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==3){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==1){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==1&&currYcoord==2){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==1){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                else if(currXcoord==2&&currYcoord==2){
                    if(square.getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord-1][currYcoord+1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord-1].getBackground().equals(Color.BLUE)||gridSquares[currXcoord+1][currYcoord+1].getBackground().equals(Color.BLUE)){
                        JOptionPane.showMessageDialog(bottomPanel, "Player 1 has won the game", "Game Over", JOptionPane.ERROR_MESSAGE);
                        lightswitch = false;
                    }
                }
                if(lightswitch==true){
                square.setBackground(Color.BLUE);
                instructionLabel.setText("Player 1's turn");
                }
            }
            //int x = square.getXcoord();
            //int y = square.getYcoord();
            //infoLabel.setText("("+x+","+y+") last selected.");
            //}
            //}   

        }
    }

    // not used but must be present to fulfil MouseListener contract
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
}
