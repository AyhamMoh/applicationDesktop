package edu.fbansept.applicationdesktop;

public class Utilisateur {

  protected   String nom;
   protected String prenom;

    public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

   /*@Override
    public String toString () {
        return nom + "  " + prenom;
    }*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

