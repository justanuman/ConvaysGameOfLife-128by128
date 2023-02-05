package ConvaysGame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LifeWindow extends JPanel {
    List<Integer> videoMem = Arrays.asList(new Integer[16384]);
    ConvayCalculator conv = new ConvayCalculator();

    @Override
    public void paint(Graphics g) {
        int x_cord;
        int y_cord;
        videoMem = conv.getOldArray();
        Graphics2D graphic2d = (Graphics2D) g;
        for (int i = 0; i < conv.getOldArray().size(); i++) {
            x_cord = (i % 128) * 5;
            y_cord = (i / 128) * 5;
            if (conv.getOldArray().get(i) == 1) {
                graphic2d.setColor(Color.BLACK);
                graphic2d.fillRect(x_cord, y_cord, 5, 5);
                System.out.println("cell at " + i + " " + x_cord + " " + y_cord);
            } else {
                graphic2d.setColor(Color.WHITE);
                graphic2d.fillRect(x_cord, y_cord, 5, 5);
                graphic2d.setColor(Color.BLACK);
                graphic2d.drawRect(x_cord, y_cord, 5, 5);
            }
        }
        //graphic2d.fillRect(100, 50, 60, 80);
    }

    public void makeOneStep() {
        conv.makeOneStep();
    }

    public void addCell(int x_cord, int y_cord) {
        conv.addCell(x_cord, y_cord);
    }

    public void removeCell(int x_cord, int y_cord) {
        conv.removeCell(x_cord, y_cord);
    }

    public LifeWindow() {
        conv = new ConvayCalculator();
        // conv = convayCalculator;
    }

    public void resetCells() {
        conv.resetCells();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 640);
    }

}
