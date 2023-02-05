package ConvaysGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu {
    private JPanel panel1;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private ConvayCalculator convayCalculator = new ConvayCalculator();
    private LifeWindow canvas;
    // private StateMachine fsm;

    boolean start = false;
    boolean stop = true;
    boolean reset = false;
    boolean draw = true;
    private ActionListener startAct;

    public static void main(String[] args) {
        JFrame frame = new JFrame("life");

        frame.setContentPane(new MainMenu().panel1);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MainMenu() {
        //  convayCalculator = new ConvayCalculator();
        stopButton.setEnabled(false);
        // fsm = new StateMachine();
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (start) {
                    canvas.makeOneStep();
                    System.out.println("canvas Step");
                }
                System.out.println("wind draw");
                canvas.paint(canvas.getGraphics());
                // else {System.out.println(" stopped");}
            }
        };
        Timer timer = new Timer(1000, task);
        timer.setRepeats(true);
        timer.start();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                resetButton.setEnabled(true);
                start = true;
                stop = false;
                reset = false;
                //canvas.paint(panel1.getGraphics());
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(true);
                start = false;
                stop = true;
                reset = false;
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                resetButton.setEnabled(true);
                start = false;
                stop = true;
                reset = true;
                canvas.resetCells();
            }
        });
       /* panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
               // super.mouseDragged(e);
                System.out.println(e.getX()+" "+e.getY());
            }
        });*/
        //TODO ADJUST COORDINATES TO 680 by 680 outer 640 by 640 inner
        /*panel1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getX() > 20 && e.getY() > 40 && e.getX() < 660 && e.getY() < 680 && stop && !e.isShiftDown()) {
                    canvas.addCell((e.getX() - 20) / 5, (e.getY() - 40) / 5);
                   // System.out.println(e.getX()  + " mouseClicked " + e.getY());
                } else if (e.getX() > 20 && e.getY() > 40 && e.getX() < 660 && e.getY() < 680 && stop && e.isShiftDown()) {
                    canvas.removeCell((e.getX() - 20) / 5, (e.getY() - 40) / 5);
                  //  System.out.println(e.getX() + " mouseClicked + shift " + e.getY() );
                }
                canvas.paint(canvas.getGraphics());
                System.out.println(e.getX()  + " mouseClicked " + e.getY());
            }*/
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getX() > 0 && e.getY() > 0 && e.getX() < 640 && e.getY() < 640 && stop && !e.isShiftDown()) {
                    canvas.addCell((e.getX()) / 5, (e.getY()) / 5);
                    // System.out.println(e.getX()  + " mouseClicked " + e.getY());
                } else if (e.getX() > 0 && e.getY() > 0 && e.getX() < 640 && e.getY() < 640 && stop && e.isShiftDown()) {
                    canvas.removeCell((e.getX()) / 5, (e.getY()) / 5);
                    //  System.out.println(e.getX() + " mouseClicked + shift " + e.getY() );
                }
                canvas.paint(canvas.getGraphics());
                System.out.println(e.getX()  + " mouseClicked " + e.getY());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                // System.out.println(e.getX()+" "+e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //  System.out.println(e.getX()+" "+e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //  System.out.println(e.getX()+" "+e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // System.out.println(e.getX()+" "+e.getY());
            }
        });
    }

    private void createUIComponents() {
        canvas = new LifeWindow();
    }
}
