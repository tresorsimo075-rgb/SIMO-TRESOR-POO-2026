/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class VLAN {
 private int id;
    private String nom;
    private ResultatVLSM sousReseau;
    private String description;
    
    public VLAN(int id, String nom, ResultatVLSM sousReseau, String description) {
        this.id = id;
        this.nom = nom;
        this.sousReseau = sousReseau;
        this.description = description;
    }
    
    public VLAN(int id, String nom, ResultatVLSM sousReseau) {
        this(id, nom, sousReseau, "Aucune description");
    }
    
    public int getId() { return id; }
    public String getNom() { return nom; }
    public ResultatVLSM getSousReseau() { return sousReseau; }
    public String getDescription() { return description; }
    
    public void afficher() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│ VLAN ID : " + id);
        System.out.println("│ Nom      : " + nom);
        System.out.println("│ Réseau   : " + sousReseau.getAdresseReseau() + "/" + sousReseau.getCidr());
        System.out.println("│ Plage    : " + sousReseau.getPremiereAdresseUtilisable() + 
                          " - " + sousReseau.getDerniereAdresseUtilisable());
        System.out.println("│ Capacité : " + sousReseau.getCapacite() + " hôtes");
        System.out.println("└─────────────────────────────────────────────────┘");
    }   
}
