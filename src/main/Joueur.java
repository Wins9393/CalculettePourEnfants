/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author David
 */
public class Joueur implements Serializable {
    
    private String nom;
    private int score;
    private String nomMax;
    private int scoreMax;

    public Joueur() {
        this.score=0;
        read();
    }
    
    public Joueur(String nom, int score) {
        this.nom="";
        this.score=0;
        this.nomMax = nom;
        this.scoreMax = score;
    }
    
    public void read(){
        
        try {
            FileInputStream file = new FileInputStream("score.dat");
            ObjectInputStream lecturefile = new ObjectInputStream(file);
            
            boolean endOfFile=false;
            
            while (!endOfFile)
            try {
                Joueur j = (Joueur)lecturefile.readObject();
                this.setNomMax(j.getNomMax());
                this.setScoreMax(j.getScoreMax());
            } 
            catch (Exception e) {
                endOfFile = true;
            }      
            lecturefile.close();
        } 
        catch (Exception e) {
            create(false);
            read();
        }      
    }
    
    public void create(boolean valide){
        
        try {
            FileOutputStream file = new FileOutputStream("score.dat");
            ObjectOutputStream ecriturefile = new ObjectOutputStream(file);
            if (valide==true)
            {ecriturefile.writeObject(this);}
            else
            {ecriturefile.writeObject(new Joueur("LE CREATEUR",5));}
            
            ecriturefile.close();
        }
        catch (Exception e) {
        }
    }
    
    public void addScore(){
        this.score++;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public String getNomMax() {
        return nomMax;
    }

    public void setNomMax(String nomMax) {
        this.nomMax = nomMax;
    }
    

}
