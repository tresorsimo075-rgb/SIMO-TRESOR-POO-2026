/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */
public class Main {
 
    public static void main(String[] args) {
        
        System.out.println("=========================================");
        System.out.println("     TP2 : IPPlan-Manager - Encapsulation");
        System.out.println("=========================================\n");
        
        // ============================================================
        // SECTION 1 : TEST DES ADRESSES IP
        // ============================================================
        System.out.println("1. TEST DES ADRESSES IP");
        System.out.println("-----------------------------------------\n");
        
        // Cas valide
        System.out.println(">>> Création d'une adresse IP valide :");
        AdresseIP ipValide = new AdresseIP("192.168.1.1");
        ipValide.afficher();
        
        // Cas invalide : chaîne vide
        System.out.println("\n>>> Création avec chaîne vide :");
        AdresseIP ipVide = new AdresseIP("");
        ipVide.afficher();
        
        // Cas invalide : null
        System.out.println("\n>>> Création avec null :");
        AdresseIP ipNull = new AdresseIP(null);
        ipNull.afficher();
        
        // Test de la méthode estAdresseLocale()
        System.out.println("\n>>> Test de estAdresseLocale() :");
        System.out.println("  ipValide (192.168.1.1) est locale ? " + ipValide.estAdresseLocale());
        System.out.println("  ipVide (0.0.0.0) est locale ? " + ipVide.estAdresseLocale());
        
        // ============================================================
        // SECTION 2 : TEST DES INTERFACES RESEAU
        // ============================================================
        System.out.println("\n\n2. TEST DES INTERFACES RESEAU");
        System.out.println("-----------------------------------------\n");
        
        // Interface avec nom valide
        System.out.println(">>> Interface avec nom valide :");
        InterfaceReseau interface1 = new InterfaceReseau("eth0", ipValide);
        interface1.activer();  // On l'active
        interface1.afficher();
        
        // Interface avec nom vide
        System.out.println("\n>>> Interface avec nom vide :");
        InterfaceReseau interface2 = new InterfaceReseau("", ipVide);
        interface2.afficher();
        
        // Interface sans adresse IP
        System.out.println("\n>>> Interface sans adresse IP :");
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null);
        interfaceSansIP.afficher();
        
        // ============================================================
        // SECTION 3 : TEST DES RESEAUX
        // ============================================================
        System.out.println("\n\n3. TEST DES RESEAUX");
        System.out.println("-----------------------------------------\n");
        
        // Réseau valide
        System.out.println(">>> Réseau valide :");
        ReseauIP reseau1 = new ReseauIP("192.168.1.0", 24, "Réseau principal");
        reseau1.afficher();
        
        // Réseau avec masque invalide
        System.out.println("\n>>> Réseau avec masque invalide (55 → 24 par défaut) :");
        ReseauIP reseau2 = new ReseauIP("10.0.0.0", 55, "Test masque invalide");
        reseau2.afficher();
        
        // Réseau avec adresse vide
        System.out.println("\n>>> Réseau avec adresse réseau vide :");
        ReseauIP reseau3 = new ReseauIP("", 16, null);
        reseau3.afficher();
        
        // ============================================================
        // SECTION 4 : TEST DES EQUIPEMENTS
        // ============================================================
        System.out.println("\n\n4. TEST DES EQUIPEMENTS");
        System.out.println("-----------------------------------------\n");
        
        // Routeur
        System.out.println(">>> Routeur :");
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interface1);
        routeur.afficher();
        
        // Serveur
        System.out.println("\n>>> Serveur :");
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceSansIP);
        serveur.afficher();
        
        // Équipement avec nom vide
        System.out.println("\n>>> Équipement avec nom vide :");
        Equipement equipSansNom = new Equipement("", "Switch", null);
        equipSansNom.afficher();
        
        // Équipement avec type vide
        System.out.println("\n>>> Équipement avec type vide :");
        Equipement equipSansType = new Equipement("SW1_CORE", "", null);
        equipSansType.afficher();
        
        // ============================================================
        // SECTION 5 : TEST DES SETTERS (MODIFICATIONS)
        // ============================================================
        System.out.println("\n\n5. TEST DES SETTERS (MODIFICATIONS)");
        System.out.println("-----------------------------------------\n");
        
        System.out.println(">>> Modification d'une adresse IP :");
        AdresseIP ipModifiable = new AdresseIP("10.0.0.1");
        System.out.print("  Avant modification : ");
        ipModifiable.afficher();
        
        ipModifiable.setValeur("172.16.0.1");
        System.out.print("  Après modification : ");
        ipModifiable.afficher();
        
        // Tentative de modification invalide
        System.out.println("\n>>> Tentative de modification invalide (null) :");
        ipModifiable.setValeur(null);
        System.out.print("  Résultat : ");
        ipModifiable.afficher();
        
        // Modification d'un équipement
        System.out.println("\n>>> Modification du nom d'un équipement :");
        Equipement equipModifiable = new Equipement("AncienNom", "Switch", null);
        System.out.println("  Avant : " + equipModifiable.getNom());
        equipModifiable.setNom("NouveauNom");
        System.out.println("  Après : " + equipModifiable.getNom());
        
        // ============================================================
        // SECTION 6 : EQUIPEMENTS SUPPLEMENTAIRES (Travail demandé)
        // ============================================================
        System.out.println("\n\n6. EQUIPEMENTS SUPPLEMENTAIRES");
        System.out.println("-----------------------------------------\n");
        
        // Switch
        AdresseIP ipSwitch = new AdresseIP("192.168.1.2");
        InterfaceReseau interfaceSwitch = new InterfaceReseau("gig0/1", ipSwitch);
        interfaceSwitch.activer();
        Equipement switchEquip = new Equipement("SW1_CORE", "Switch", interfaceSwitch);
        System.out.println(">>> Switch :");
        switchEquip.afficher();
        
        // Point d'accès WiFi
        AdresseIP ipAP = new AdresseIP("192.168.1.3");
        InterfaceReseau interfaceAP = new InterfaceReseau("wlan0", ipAP);
        interfaceAP.activer();
        Equipement pointAcces = new Equipement("AP1_LABO", "Point d'accès WiFi", interfaceAP);
        System.out.println("\n>>> Point d'accès WiFi :");
        pointAcces.afficher();
        
        // Poste client supplémentaire (interface inactive)
        AdresseIP ipClient = new AdresseIP("192.168.1.50");
        InterfaceReseau interfaceClient = new InterfaceReseau("eth0", ipClient);
        // interfaceClient reste inactive (pas d'appel à activer())
        Equipement client = new Equipement("PC_USER", "Poste client", interfaceClient);
        System.out.println("\n>>> Poste client (interface inactive) :");
        client.afficher();
        
        // ============================================================
        // SECTION 7 : EQUIPEMENT SANS INTERFACE
        // ============================================================
        System.out.println("\n\n7. EQUIPEMENT SANS INTERFACE");
        System.out.println("-----------------------------------------\n");
        
        Equipement equipementSansInterface = new Equipement("TEST_NULL", "Périphérique test", null);
        equipementSansInterface.afficher();
        
        System.out.println("\n=========================================");
        System.out.println("              FIN DES TESTS");
        System.out.println("=========================================");
    }   
}
