/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import main.Joueur;

/**
 *
 * @author David
 */
public class C_Model extends Observable{ 
    
    private int nombre1;
    private String signe;
    private int nombre2;
    private int resultat;
    private int essaie;
    // 10 car 0 exclu pour eviter 0+0
    private final int AMPLITUDE = 10;
    private int saisi=-1;
    private Joueur j;

    public C_Model() {
        j=new Joueur();
        genereCalcul();
    }
    
    public void genereCalcul(){
        int choix;
        // pour exclure =0
        resultat=(int)(Math.random() * AMPLITUDE+1);
        
        if (resultat==10){
            choix=0;
        }
        else { choix = (int)(Math.random() * 2);}
        
            //0 +
            if (choix==0){
                // idem -1 et +1 pour exclure le 0
                nombre1 =(int)(Math.random() * (resultat-1)+1);
                nombre2=(int)resultat-nombre1;
                signe= "+";
            }
            //1 -
            else {
                // idem +1 pour exclure le 0
                    nombre1 =(int)(Math.random() * (AMPLITUDE-(resultat)))+resultat;
                    nombre2=(int)nombre1-resultat;               
                signe= "-";
            }
            
        this.essaie=0;
        
        setChanged();
        notifyObservers ();
    }
    
    public boolean compare(){   
        return (resultat==saisi);
    }
    
    public int getNombre1() {
        return nombre1;
    }
    
    public String getSigne() {
        return signe;
    }
    
    public int getNombre2() {
        return nombre2;
    }

    public int getResultat() {
        return resultat;
    }
    
    public int getEssaie() {
        return essaie;
    }

    public void setEssaie() {
        this.essaie++;
        setChanged();
        notifyObservers ();
    }

    public int getSaisi() {
        return saisi;
    }

    public void setSaisi(int saisi) {      
        this.saisi = saisi;
        System.out.println("Model");
        setChanged();
        notifyObservers ();
    }

    public Joueur getJoueur() {
        return j;
    }
    
}
