package vue;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Pub extends JPanel {
    private String imageName;

    public Pub(String image) {
        this.imageName = image;
    }
    
    public void paintComponent(Graphics g){
        try {
            Image img = ImageIO.read(getClass().getResource("/ressource/img/"+imageName));
            //Pour une imageName de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            System.out.println("Erreur image non existante");
            this.imageName="Test.jpg";
        }                
    }               
    
}
