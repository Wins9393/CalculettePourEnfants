/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.C_Controler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createTitledBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.C_Model;
import util.ButtonFondImage;
import util.ImageFondImage;


/**
 *
 * @author David
  */

public class C_Vue extends JPanel implements Observer{
    
    protected C_Model model;
    protected C_Controler controler;
    
    private final String source ="/ressource/img/";
    private final Color FOND = new Color(102, 0, 102);
    private final Color FOND2 = new Color(242, 220, 236);
    private final Color contour = new Color(0, 95, 0);
    
    private String[] listeNomButton={"0","1", "2", "3", "4", "5", "6", "7", "8", "9","OK"};
    private ButtonFondImage[] tabButton= new ButtonFondImage[listeNomButton.length];
    
    Border contourEcran = BorderFactory.createLineBorder(contour, 6);
    
    // <editor-fold defaultstate="collapsed" desc="Panel Supp"> 
    private JPanel affichage = new JPanel();    
    private GridLayout interne = new GridLayout(1,6);
    private ImageFondImage aNombre1;
    private ImageFondImage aSigne;
    private ImageFondImage aNombre2;
    private ImageFondImage aEgal;
    
    private ImageFondImage aSaisi;
    private ImageFondImage aSaisi2;
    
    private JPanel achance = new JPanel();
    //</editor-fold>
   
    private GridLayout GBouton = new GridLayout(1,1,0,10);
    private JPanel gestionBouton = new JPanel(GBouton);
    
    // </editor-fold>
    
    public C_Vue(C_Model model, C_Controler controler){
        
        this.model=model;
        this.controler=controler;
        controler.addView(this);
        
        model.addObserver(this);
        
        aNombre1 = new ImageFondImage(source+model.getNombre1()+".png");
        aSigne = new ImageFondImage(source+model.getSigne()+".png");
        aNombre2 = new ImageFondImage(source+model.getNombre2()+".png");
        aEgal = new ImageFondImage(source+"=.png");
        aSaisi = new ImageFondImage(source+"pi.png");
        aSaisi2=new ImageFondImage();
        
        // <editor-fold defaultstate="collapsed" desc="Panel affichage">  
        affichage.setLayout(new GridLayout(1,6));
        affichage.add(aNombre1);
        affichage.add(aSigne);
        affichage.add(aNombre2);
        affichage.add(aEgal);
        affichage.add(aSaisi);
        
        affichage.setBorder(contourEcran);
        affichage.setBackground(FOND2);

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Panel achance">  
        achance.setLayout(new GridLayout(3,1));
        achance.setBorder(contourEcran);
        achance.setBackground(FOND2);
        printChance();
           
        // </editor-fold>
        
        //On définit en grille
        this.setLayout(new GridBagLayout());
        this.setBorder(createTitledBorder(null, "EditCalc-V.3", TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("BelleAllureGS", 1, 24), Color.white));
        
        // dim de pref + couleur de fond
        this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setBackground(FOND);         
        
        // <editor-fold defaultstate="collapsed" desc="Parametre">
        GridBagConstraints grille = new GridBagConstraints(); 
        GridBagConstraints reset = new GridBagConstraints();
        reset.gridwidth = 1;
        reset.gridheight = 1;     
        
        
        reset.insets=grille.insets=new Insets(5,5,5,5);
        reset.fill=grille.fill=GridBagConstraints.BOTH;
        reset.weightx=grille.weightx=8;
        reset.weighty=grille.weighty=4;
        reset.ipady=grille.ipady=0;
        
        // </editor-fold>        
        
        // <editor-fold defaultstate="collapsed" desc="Insertion de la grille">
        //On positionne la case de départ du composant
        
        grille.gridy = 0;
        grille=reset;
        // <editor-fold defaultstate="collapsed" desc="Affichage - >">
        grille.gridx =0;
        grille.gridwidth = 7;
        grille.ipady=120;
        this.add(affichage, grille);
        
        grille.ipady=0;
        grille.gridx =7;
        grille.gridwidth = 1;
        this.add(achance, grille);
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Insertion des Nombre ->">
        int i;
        i=0;
            grille=reset;
            grille.gridx=6;
            grille.gridy=4;
            grille.gridwidth = 2;
            grille.gridheight = 1;
            
            tabButton[i]=new ButtonFondImage(i,source+listeNomButton[i]);
            tabButton[i].addActionListener(new ChiffreListener());                  
            this.add(tabButton[i], grille);
            
        i=10;
            grille.gridy=2;
            grille.gridx = 6;
            grille.gridheight = 2;
            grille.gridwidth = 2;
            tabButton[i]=new ButtonFondImage(10,source+listeNomButton[i]);
            tabButton[i].addActionListener(new OkListener());                  
            this.add(tabButton[i], grille);
        
        int ligne=1;
        int colonne=0;    
        for (i=1;i<10;i++){
            tabButton[i]=new ButtonFondImage(i,source+listeNomButton[i]);

                    grille=reset;
                    grille.gridwidth = 2;
                    grille.gridheight = 1;
                    tabButton[i].addActionListener(new ChiffreListener());
                    
                    if ((i-1)%3==0){
                        ligne++;
                        colonne=0;
                    }
                    grille.gridy = ligne;       
                    grille.gridx = colonne;
                    this.add(tabButton[i], grille);

                    colonne+=2;                
            } 
        // </editor-fold>
        
        grille.gridy = 4;
        grille=reset;
        // <editor-fold defaultstate="collapsed" desc="Version">       
        grille.gridx = 0;
        grille.gridwidth = 8;
        grille.gridheight = 1;
        grille.weighty=0;
        grille.anchor=GridBagConstraints.FIRST_LINE_START;
        grille.fill=GridBagConstraints.NONE;
        grille.ipady=0;
        //</editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Force le découpage"> 
        //Force le découpage en 1x1 des colonne MYSTERE
        for( i=0; i<=7; i++){
            grille.gridy = 0;
            grille.gridx = i;
            grille.gridwidth = 1;
            grille.gridheight = 1;
            grille.fill=GridBagConstraints.NONE;
            this.add(new JLabel(), grille);
        }
        // </editor-fold>  
        // </editor-fold>
//        System.out.println("a");
////        this.rejouer();
//        
//        actuResult();
//        System.out.println("b");
//        this.validate();
//        System.out.println("c");
//        this.repaint();
//        System.out.println("d");
    }
    
    private void afficheCalcul(){  
        aNombre1.setImageName(source+model.getNombre1()+".png");
        aSigne.setImageName(source+model.getSigne()+".png");
        aNombre2.setImageName(source+model.getNombre2()+".png");
    }

    @Override
    public void update(Observable o, Object arg) {
        actuResult();       
        printChance();
        afficheCalcul();
    }
    private void actuResult(){
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                
            if (model.getSaisi()==-1){
                aSaisi.setImageName(source+"pi.png");
                    if(affichage.getComponentCount()==6)
                    {affichage.remove(aSaisi2);}
            }
            else if (model.getSaisi()==10)
                {
                    aSaisi.setImageName(source+1+".png");
                    aSaisi2.setImageName(source+0+".png");
                    affichage.add(aSaisi2);                         
                }       
            else {  
                        aSaisi.setImageName(source+model.getSaisi()+".png");                      
                        aSaisi2.repaint();
                        affichage.remove(aSaisi2); 
                }
            affichage.validate();
            affichage.repaint();

            tabButton[10].setEnabled(true); 
            return null;
            }
        };
            sw.execute();
    }
    
    private void printChance(){
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                achance.removeAll();
                for (int i=1; i<=3 ; i++){

                    if(i<=model.getEssaie()){                
                         achance.add(new ImageFondImage(source+"croixRouge.png"));
                    }
                    else {
                         achance.add(new ImageFondImage(source+"bouleVerte.png"));
                    }     
                }
                achance.validate();
                return null;
            }
        };
        sw.execute();
          
    }

    class ChiffreListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Depart");
            controler.saisi(((ButtonFondImage)e.getSource()).getNombre());
        }
    }
    
    class OkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controler.CompareResultat();
        }
    }
}
