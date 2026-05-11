/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class Equipement {
  
    // Attributs privés
    private String nom;
    private String type;
    private InterfaceReseau interfacePrincipale;
    
    // Constructeur avec validations
    public Equipement(String nom, String type, InterfaceReseau interfacePrincipale) {
        setNom(nom);
        setType(type);
        this.interfacePrincipale = interfacePrincipale;
    }
    
    // Getter pour nom
    public String getNom() {
        return nom;
    }
    
    // Setter pour nom avec validation
    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            System.out.println("Erreur : nom d'équipement invalide.");
            this.nom = "equipement_inconnu";
        } else {
            this.nom = nom;
        }
    }
    
    // Getter pour type
    public String getType() {
        return type;
    }
    
    // Setter pour type avec validation
    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            System.out.println("Erreur : type d'équipement invalide.");
            this.type = "Type inconnu";
        } else {
            this.type = type;
        }
    }
    
    // Getter et Setter pour interfacePrincipale
    public InterfaceReseau getInterfacePrincipale() {
        return interfacePrincipale;
    }
    
    public void setInterfacePrincipale(InterfaceReseau interfacePrincipale) {
        this.interfacePrincipale = interfacePrincipale;
    }
    
    // Méthode d'affichage
    public void afficher() {
        System.out.println("Nom de l'équipement : " + nom);
        System.out.println("Type d'équipement : " + type);
        if (interfacePrincipale != null) {
            interfacePrincipale.afficher();
        } else {
            System.out.println("Aucune interface réseau configurée.");
        }
    }   
}
