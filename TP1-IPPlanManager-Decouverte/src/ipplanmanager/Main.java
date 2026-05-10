package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP1 =====\n");
        System.out.println("Découverte des premières classes du projet\n");
        
        // Adresses IP existantes
        AdresseIP ipRouteur = new AdresseIP("192.168.1.1");
        AdresseIP ipServeur = new AdresseIP("192.168.1.10");
        AdresseIP ipClient = new AdresseIP("192.168.1.50");
        
        // NOUVEAU: Adresses pour le deuxième réseau
        AdresseIP ipSwitch = new AdresseIP("10.0.1.5");
        AdresseIP ipAP = new AdresseIP("10.0.1.20");
        AdresseIP ipClient2 = new AdresseIP("10.0.1.100");
        
        // Interfaces pour le premier réseau
        InterfaceReseau interfaceRouteur = new InterfaceReseau("eth0", ipRouteur);
        InterfaceReseau interfaceServeur = new InterfaceReseau("eth0", ipServeur);
        InterfaceReseau interfaceClient = new InterfaceReseau("wlan0", ipClient);
        
        // NOUVEAU: Interfaces pour le deuxième réseau
        InterfaceReseau interfaceSwitch = new InterfaceReseau("gig0/1", ipSwitch);
        InterfaceReseau interfaceAP = new InterfaceReseau("wlan0", ipAP);
        
        // Interface SANS adresse IP (demandé)
        InterfaceReseau interfaceSansIP = new InterfaceReseau("eth1", null);
        
        // Interface INACTIVE (activé puis désactivé)
        InterfaceReseau interfaceClient2 = new InterfaceReseau("eth0", ipClient2);
        
        // Activation des interfaces
        interfaceRouteur.activer();
        interfaceServeur.activer();
        interfaceSwitch.activer();
        interfaceAP.activer();
        // interfaceClient2 reste inactive
        // interfaceSansIP reste inactive
        
        // Équipements du premier réseau
        Equipement routeur = new Equipement("R1_EDGE", "Routeur", interfaceRouteur);
        Equipement serveur = new Equipement("SRV_DNS", "Serveur", interfaceServeur);
        Equipement client = new Equipement("PC_ADMIN", "Poste client", interfaceClient);
        
        // NOUVEAU: Équipements du deuxième réseau
        Equipement switchEquip = new Equipement("SW1_CORE", "Switch", interfaceSwitch);
        Equipement pointAcces = new Equipement("AP1_LABO", "Point d'accès WiFi", interfaceAP);
        Equipement clientSupplementaire = new Equipement("PC_VISITOR", "Poste client", interfaceClient2);
        
        // Équipement avec interface sans IP
        Equipement equipementSansIP = new Equipement("TEST_DEV", "Périphérique test", interfaceSansIP);
        
        // Réseaux
        ReseauIP reseauPrincipal = new ReseauIP("192.168.1.0", 24, "Réseau principal du laboratoire IRT");
        
        // NOUVEAU: Deuxième réseau
        ReseauIP reseauSecondaire = new ReseauIP("10.0.1.0", 24, "Réseau secondaire pour équipements réseau");
        
        // Affichage des réseaux
        System.out.println("--- Réseaux créés ---");
        reseauPrincipal.afficher();
        System.out.println();
        reseauSecondaire.afficher();
        
        System.out.println("\n--- Équipements créés ---\n");
        
        // Affichage de tous les équipements
        routeur.afficher();
        System.out.println();
        serveur.afficher();
        System.out.println();
        client.afficher();
        System.out.println();
        switchEquip.afficher();
        System.out.println();
        pointAcces.afficher();
        System.out.println();
        clientSupplementaire.afficher();
        System.out.println();
        equipementSansIP.afficher();
    }
}
