package util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author David
 */
public class ImageFondImage extends JLabel {
    
    private Image img;
    protected String imageName;
    protected String type;
    
     public ImageFondImage(){
         
     }
    
    public ImageFondImage(String image) {
        imageName=image;
    }
    
    @Override
    public void paintComponent(Graphics g){
            
            try { 
                Image img = ImageIO.read(getClass().getResource(imageName));
                //Pour une image de fond
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException e) {
                System.out.println("Image :"+imageName +" Erreur image non existante");
            }             
    } 

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
