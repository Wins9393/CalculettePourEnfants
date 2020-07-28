/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.C_Model;
import vue.C_Vue;
import vue.Pub;

/**
 *
 * @author David
 */
public class C_Controler {
    
    protected C_Model model;
    protected C_Vue vue;

    public C_Controler(C_Model model) {
        this.model = model;
    }
    public void addView(C_Vue vue){
        this.vue= vue;
    }
    
    public void saisi(int pSaisi ){
        // controle que on envoie un numero
        
                 if (pSaisi==0 & model.getSaisi()==1)
                {
                        model.setSaisi(10);
                }       
                else {  
                        model.setSaisi(pSaisi);
                }
            }
    
    public void CompareResultat(){
        SwingWorker sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                if(model.compare()){
                    model.setSaisi(-1);
                    model.getJoueur().addScore();
                    Thread t = new Thread(new PlayAnimation("fanfare"));
                    t.start();

                    if (model.getJoueur().getScore()>model.getJoueur().getScoreMax() && model.getJoueur().getNom()!=model.getJoueur().getNomMax()){
                        String nom = (JOptionPane.showInputDialog(null, "Veuillez saisir votre nom, nouveau champion !", "Meilleur SCORE "+model.getJoueur().getScore(), JOptionPane.QUESTION_MESSAGE));
                            if(!nom.isEmpty())
                            {   
                                model.getJoueur().setNom(nom.trim());
                                model.getJoueur().setNomMax(nom.trim());
                                model.getJoueur().setScoreMax(model.getJoueur().getScore());
                                model.getJoueur().create(true);
                            }
                    }
                    if (model.getJoueur().getScore()>model.getJoueur().getScoreMax() && model.getJoueur().getNom()==model.getJoueur().getNomMax()){
                        model.getJoueur().setScoreMax(model.getJoueur().getScore());
                        model.getJoueur().create(true);
                    }
                    ImageIcon pikaVictoire = new ImageIcon(getClass().getResource("/ressource/img/pikaVictoire.gif"));
                    String[] btn = {"Rejouer", "Quitter"};
                    int rang = JOptionPane.showOptionDialog(null, "", "Bravo !!  Votre Score est "+model.getJoueur().getScore(), JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, pikaVictoire, btn, btn[0]);
                    if(btn[rang].equals("Quitter")){
                        quitter();
                    }else{
                         model.genereCalcul();
                    }
                } 
                else {
                    Thread t = new Thread(new PlayAnimation("erreur"));
                    t.start();
                    model.setEssaie();
                    
                    if (model.getEssaie()==3){
                        model.setSaisi(-1);
                        t = new Thread(new PlayAnimation("defaite"));
                        t.start();
                        model.getJoueur().setScore(0);
                        model.getJoueur().setNom("");
                        ImageIcon pikaDefaite = new ImageIcon(getClass().getResource("/ressource/img/pikaDefaite.gif"));
                        String[] btn = {"Rejouer", "Quitter"};

                        int rang = JOptionPane.showOptionDialog(null, "Presque, le résultat était: "+model.getResultat(), "Dommage..", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, pikaDefaite, btn, btn[0]);

                        if(btn[rang].equals("Quitter")){
                            quitter();
                        }else{
                             model.genereCalcul();;
                        }
                    }
                }
                return null;
            }
            };
            sw.execute();
    } 
    
    private void quitter() throws InterruptedException{
        
        Pub pubFin = new Pub("pubHaribo2.jpg");
        pubFin.setVisible(true);
        JFrame j;
        j= (JFrame) vue.getTopLevelAncestor();  
        j.setContentPane(pubFin);

        j.validate();
        j.repaint();
        
        Thread.sleep(5*1000);
        System.exit(0);
        
    }

class PlayAnimation implements Runnable{
        
        private String musique;
        
        public PlayAnimation(String music){
            this.musique = music;
        }
        
        @Override
        public synchronized void run(){
            son(musique);
        }
        
    public void son(String link){
        
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/son/"+link+".wav")));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e){ }
    }
}

}