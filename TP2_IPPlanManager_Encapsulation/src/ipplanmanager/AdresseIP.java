/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class AdresseIP {
  
    // Attribut privé (encapsulation)
    private String valeur;
    
    // Constructeur avec validation
    public AdresseIP(String valeur) {
        setValeur(valeur);  // Appelle le setter pour validation
    }
    
    // Getter
    public String getValeur() {
        return valeur;
    }
    
    // Setter avec validation
    public void setValeur(String valeur) {
        if (valeur == null || valeur.isEmpty()) {
            System.out.println("Erreur : adresse IP invalide.");
            this.valeur = "0.0.0.0";
        } else {
            this.valeur = valeur;
        }
    }
    
    // Méthode supplémentaire (Travail demandé section 14)
    public boolean estAdresseLocale() {
        if (valeur != null && valeur.startsWith("192")) {
            return true;
        }
        return false;
    }
    
    // Méthode d'affichage
    public void afficher() {
        System.out.println("Adresse IP : " + valeur);
    }  
}
