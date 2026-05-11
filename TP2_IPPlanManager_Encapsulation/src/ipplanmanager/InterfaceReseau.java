/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class InterfaceReseau {
    
    // Attributs privés
    private String nom;
    private AdresseIP adresseIP;
    private boolean active;
    
    // Constructeur
    public InterfaceReseau(String nom, AdresseIP adresseIP) {
        setNom(nom);
        this.adresseIP = adresseIP;
        this.active = false;  // Par défaut, interface inactive
    }
    
    // Getter pour nom
    public String getNom() {
        return nom;
    }
    
    // Setter pour nom avec validation
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            System.out.println("Erreur : nom d'interface invalide.");
            this.nom = "interface_inconnue";
        } else {
            this.nom = nom;
        }
    }
    
    // Getter et Setter pour adresseIP
    public AdresseIP getAdresseIP() {
        return adresseIP;
    }
    
    public void setAdresseIP(AdresseIP adresseIP) {
        this.adresseIP = adresseIP;
    }
    
    // Getter pour active
    public boolean isActive() {
        return active;
    }
    
    // Méthodes d'activation/désactivation
    public void activer() {
        active = true;
    }
    
    public void desactiver() {
        active = false;
    }
    
    // Méthode d'affichage
    public void afficher() {
        System.out.println("Interface : " + nom);
        if (adresseIP != null) {
            adresseIP.afficher();
        } else {
            System.out.println("Adresse IP : non configurée");
        }
        if (active) {
            System.out.println("État : active");
        } else {
            System.out.println("État : inactive");
        }
    }  
}
