import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
public class Main extends JFrame implements MouseMotionListener, MouseListener {
    private static final int WIDTH = 1800;
    private static final int HEIGHT = 900;
    private static final int MARGIN = 50;
    private final double[] values;
    private final String[] labels;
    private Component l;

    public Main(double[] values, String[] labels) {
        this.values = values;
        this.labels = labels;

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        super.paint(g);

        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;
        for (double value : values) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }

        double graphHeight = HEIGHT - 2 * MARGIN;
        double graphWidth = WIDTH - 2 * MARGIN;

        for (int i = 0; i < values.length; i++) {
            int valueX = i * (int) graphWidth / values.length + MARGIN;
            int valueY = (int) (HEIGHT - MARGIN - ((values[i] - minValue) / (maxValue - minValue)) * graphHeight);
            l=new Component(){};
            l.setBounds(valueX, valueY, (int) graphWidth / values.length - 1, HEIGHT - valueY - MARGIN);
            l.setSize((int) graphWidth / values.length - 1, HEIGHT - valueY - MARGIN);
            add(l);
            setLayout(null);
            l.setVisible(true);
            l.addMouseListener(this);
            l.setName(labels[i] + " : " + values[i]);
            //g.drawString(labels[i], valueX, HEIGHT - MARGIN / 2);
            g.fillRect(valueX, valueY, (int) graphWidth / values.length - 1, HEIGHT - valueY - MARGIN);
        }
    }

    public static void main(String[] args) throws IOException {
        EqParser eqParser = new EqParser("src/graphiceq");
        String data = eqParser.parseEqString();
        System.out.println(data);
        String[] pairs = data.split(",");

        double[] values = new double[pairs.length];
        String[] labels = new String[pairs.length];

        for (int i = 0; i < pairs.length; i++) {
            String[] split = pairs[i].split("=");
            labels[i] = split[0];
            values[i] = Double.parseDouble(split[1]);
        }

        SwingUtilities.invokeLater(() -> {
            Main chart = new Main(values, labels);
            chart.setVisible(true);
        });
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getComponent().getName());
        
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}