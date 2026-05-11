/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class ReseauIP {
  
    // Attributs privés
    private String adresseReseau;
    private int masqueCidr;
    private String description;
    
    // Constructeur avec validations
    public ReseauIP(String adresseReseau, int masqueCidr, String description) {
        setAdresseReseau(adresseReseau);
        setMasqueCidr(masqueCidr);
        setDescription(description);
    }
    
    // Getter et Setter pour adresseReseau
    public String getAdresseReseau() {
        return adresseReseau;
    }
    
    public void setAdresseReseau(String adresseReseau) {
        if (adresseReseau == null || adresseReseau.isEmpty()) {
            System.out.println("Erreur : adresse réseau invalide.");
            this.adresseReseau = "0.0.0.0";
        } else {
            this.adresseReseau = adresseReseau;
        }
    }
    
    // Getter et Setter pour masqueCidr
    public int getMasqueCidr() {
        return masqueCidr;
    }
    
    public void setMasqueCidr(int masqueCidr) {
        // Validation : masque CIDR doit être entre 0 et 32
        if (masqueCidr < 0 || masqueCidr > 32) {
            System.out.println("Erreur : masque CIDR invalide.");
            this.masqueCidr = 24;  // Valeur par défaut
        } else {
            this.masqueCidr = masqueCidr;
        }
    }
    
    // Getter et Setter pour description
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "Aucune description";
        } else {
            this.description = description;
        }
    }
    
    // Méthode d'affichage
    public void afficher() {
        System.out.println("Réseau : " + adresseReseau + "/" + masqueCidr);
        System.out.println("Description : " + description);
    }   
}
