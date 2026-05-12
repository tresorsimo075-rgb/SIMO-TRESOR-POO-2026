package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP7 - Validations avancées =====\n");
        
        // === SCÉNARIO 1: Test normal avec adresse valide ===
        System.out.println("--- SCÉNARIO 1: Plan VLSM normal ---\n");
        
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));
        
        MoteurVLSM moteur = new MoteurVLSM();
        String adresseDepart = "192.168.1.0";
        int cidrDepart = 24;
        
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan(adresseDepart, besoins);
        
        System.out.println("Plan généré à partir de " + adresseDepart + "/" + cidrDepart + " :");
        System.out.println("----------------------------------------");
        for (ResultatVLSM resultat : resultats) {
            resultat.afficher();
        }
        
        // Validation du plan
        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        validateur.validerPlanComplet(resultats, adresseDepart, cidrDepart, besoins);
        
        // === SCÉNARIO 2: Test de conflit VLAN ===
        System.out.println("\n\n--- SCÉNARIO 2: Test de conflit VLAN ---\n");
        
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        
        try {
            VLAN vlan10 = new VLAN(10, "ADMINISTRATION", resultats.get(0), "VLAN Administration");
            VLAN vlan20 = new VLAN(20, "TECHNIQUE", resultats.get(1), "VLAN Technique");
            VLAN vlan10Erreur = new VLAN(10, "WIFI", resultats.get(2), "VLAN WiFi avec ID déjà utilisé");
            
            gestionnaire.ajouterVLAN(vlan10);
            gestionnaire.ajouterVLAN(vlan20);
            gestionnaire.ajouterVLAN(vlan10Erreur);  // Devrait générer une exception
            
            gestionnaire.afficherTousLesVLANs();
            
        } catch (ConflitVLANException e) {
            System.out.println("❌ ERREUR VLAN: " + e.getMessage());
        }
        
        // === SCÉNARIO 3: Test avec adresse IP invalide ===
        System.out.println("\n\n--- SCÉNARIO 3: Test avec adresse de départ invalide ---\n");
        
        try {
            String adresseInvalide = "192.168.300.0";
            System.out.println("Test avec adresse: " + adresseInvalide);
            CalculateurReseau.verifierAdresseIP(adresseInvalide);
        } catch (AdresseIPInvalideException e) {
            System.out.println("✅ Exception correctement déclenchée: " + e.getMessage());
        }
        
        // === SCÉNARIO 4: Test de chevauchement ===
        System.out.println("\n\n--- SCÉNARIO 4: Test de chevauchement entre réseaux ---\n");
        
        ArrayList<ResultatVLSM> resultatsChevauchement = new ArrayList<>();
        ResultatVLSM r1 = new ResultatVLSM("RESEAU_A", "192.168.1.0", 25, "255.255.255.128", 126);
        ResultatVLSM r2 = new ResultatVLSM("RESEAU_B", "192.168.1.64", 26, "255.255.255.192", 62);
        
        resultatsChevauchement.add(r1);
        resultatsChevauchement.add(r2);
        
        System.out.println("Réseau A: 192.168.1.0/25");
        System.out.println("Réseau B: 192.168.1.64/26");
        System.out.println("Ces deux réseaux se chevauchent-ils ?\n");
        
        try {
            validateur.verifierChevauchements(resultatsChevauchement);
        } catch (ChevauchementReseauException e) {
            System.out.println("✅ Exception correctement déclenchée: " + e.getMessage());
        }
        
        System.out.println("\n===== FIN DES TESTS =====");
    }
}