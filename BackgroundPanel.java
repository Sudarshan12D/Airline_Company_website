import javax.swing.*;
import java.awt.*;

// Custom panel class for background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Constructor for the panel, takes the path to the image as a parameter
    public BackgroundPanel(String filePath) {
        backgroundImage = new ImageIcon(filePath).getImage();
    }

    // Overridden paintComponent to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}