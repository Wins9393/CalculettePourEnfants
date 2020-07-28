package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author David
 */
public class ButtonFondImage extends JButton {
    
    Color color = new Color(102, 0, 102);
    private int nombre;
    private Image img;
    protected String imageName;
    
    public ButtonFondImage(int nombre,String image) {
        this.nombre=nombre;
        imageName=image;
    }   
    
    @Override
    public void paintComponent(Graphics g){
        try {
            this.setBorder(BorderFactory.createEmptyBorder());          
            g.setColor(color);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            Image img = ImageIO.read(getClass().getResource(imageName+"e.png"));
            
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            System.out.println("1Button: "+ nombre+" Erreur image non existante");
            System.out.println("2Button: "+ imageName+" Erreur image non existante");
        }                
    } 

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
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
    
    
}