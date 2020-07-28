package vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author David Renaud Vincent
 */
public class Fenetre extends JFrame {
        
    public Fenetre(){
    
        //Définit un titre pour notre fenêtre
        this.setTitle("Calculette");
        //Définit sa taille : 1000 pixels de large et 700 pixels de haut
        this.setSize(1000, 700);
        this.setMinimumSize(new Dimension(1000,700));
        //Nous demandons maintenant à notre objet de se positionner au centre
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //DO_NOTHING_ON_CLOSE
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage(getClass()
                .getResource("/ressource/img/logo.png")));
    }  
}


