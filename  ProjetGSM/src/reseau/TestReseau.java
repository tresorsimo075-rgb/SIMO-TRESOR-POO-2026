package reseau;

public class TestReseau {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  SIMULATION RESEAU GSM - PROJET POO SIMO TRESOR");
        System.out.println("============================================");

        System.out.println("\n--- TEST 1 : Creation du reseau ---");
        Reseau reseauOrange = new Reseau("Orange", 890.2, 935.2, "OFDMA", 150.0, 300.0, 10.0);
        reseauOrange.afficherPerformances();

        System.out.println("--- TEST 2 : Creation des BTS ---");
        BTS bts1 = new BTS("BTS-001", "Dakar Centre", 35.0, "urbain", 2.0, 46.0, 3);
        BTS bts2 = new BTS("BTS-002", "Thies", 55.0, "rural", 8.0, 43.0, 2);
        BTS bts3 = new BTS("BTS-003", "Pikine", 28.0, "urbain", 1.8, 45.0, 3);
        reseauOrange.ajouterBTS(bts1);
        reseauOrange.ajouterBTS(bts2);
        reseauOrange.ajouterBTS(bts3);

        System.out.println("\n--- TEST 3 : Creation des utilisateurs ---");
        MS user1 = new Smartphone("Diop", "Mamadou", "pass123", "771234567", "IMSI001", "Android", 6.5);
        MS user2 = new Smartphone("Ndiaye", "Awa", "pass456", "778765432", "IMSI002", "iOS", 6.1);
        MS user3 = new Tablette("Fall", "Ibrahima", "pass789", "770000001", "IMSI003", true, 10.9);
        MS user4 = new Tablette("Sarr", "Fatou", "pass000", "770000002", "IMSI004", false, 11.0);

        user1.afficherCaracteristiques();
        user2.afficherCaracteristiques();
        user3.afficherCaracteristiques();
        user4.afficherCaracteristiques();

        System.out.println("--- TEST 4 : Attachement aux BTS ---");
        user1.sAttacher(bts1);
        user2.sAttacher(bts1);
        user3.sAttacher(bts1);
        user4.sAttacher(bts1);
        user4.sAttacher(bts2);

        System.out.println("\n--- TEST 5 : Recherche utilisateur ---");
        try {
            MS trouve = bts1.rechercherMS("771234567");
            System.out.println("Trouve : " + trouve.getNom() + " " + trouve.getPrenom());
        } catch (MSIntrouvableException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n--- TEST 6 : Appels ---");
        user1.appeler(user2);
        user2.appeler(user3);
        user4.appeler(user1);

        System.out.println("\n--- TEST 7 : Journal appels recus ---");
        user1.afficherAppelsRecus();
        user2.afficherAppelsRecus();
        user3.afficherAppelsRecus();
        user4.afficherAppelsRecus();

        System.out.println("--- TEST 8 : Localisation ---");
        reseauOrange.rechercherLocalisationUtilisateur("771234567");
        reseauOrange.rechercherLocalisationUtilisateur("999999999");

        System.out.println("\n--- TEST 9 : Performances finales ---");
        reseauOrange.afficherPerformances();

        System.out.println("--- TEST 10 : Etat des BTS ---");
        bts1.afficherInfos();
        bts2.afficherInfos();
        bts3.afficherInfos();

        System.out.println("--- TEST 11 : Exception BTS inexistante ---");
        try {
            reseauOrange.rechercherBTS("BTS-999");
        } catch (MSIntrouvableException e) {
            System.out.println("Exception capturee : " + e.getMessage());
        }

        System.out.println("\n============================================");
        System.out.println("  FIN DE LA SIMULATION");
        System.out.println("============================================");
    }
}