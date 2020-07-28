package main;

import controler.C_Controler;
import vue.C_Vue;
import vue.Pub;
import vue.Fenetre;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JLabel;
import model.C_Model;

/**
 *
 * @author David
 */
public class Main implements Serializable{

    public static void main(String[] args) throws InterruptedException {

        Fenetre fen = new Fenetre();
        fen.setVisible(true);
        
        C_Model model = new C_Model();
        C_Controler controler  = new C_Controler(model);
        
        //Score
            JLabel j= new JLabel("Meilleur :"+model.getJoueur().getNomMax(),0);
            j.setBackground(Color.yellow);
            j.setOpaque(true);
            Font police = new Font("Tahoma", Font.BOLD, 65);
            j.setFont(police);
            
            JLabel k= new JLabel("Score :"+model.getJoueur().getScoreMax(),0);
            k.setBackground(Color.pink);
            k.setOpaque(true);
            k.setFont(police);
            
            
        fen.setLayout(new GridLayout(2,1));
            fen.add(j);
            fen.add(k);
            fen.repaint();
            fen.revalidate();
            Thread.sleep(3*1000);
            
        //PUB
//            Pub pub1 = new Pub("pubHaribo2.jpg");
//        fen.setLayout(null);
//        fen.setContentPane(pub1);
//            fen.repaint();
//            fen.revalidate();
//            Thread.sleep(10*1000);
            
        //CALCULETTE  
        
        C_Vue vue = new C_Vue(model, controler);
            
        fen.setContentPane(vue);
            fen.repaint();
            fen.revalidate();
    }
}
