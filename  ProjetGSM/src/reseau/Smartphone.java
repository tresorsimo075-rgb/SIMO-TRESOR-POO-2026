package reseau;

public class Smartphone extends MS {

    private String systemeExploitation;
    private double tailleEcran;

    public Smartphone(String nom, String prenom, String motDePasse,
                      String msisdn, String imsi, String systemeExploitation, double tailleEcran) {
        super(nom, prenom, motDePasse, msisdn, imsi);
        this.systemeExploitation = systemeExploitation;
        this.tailleEcran = tailleEcran;
    }

    public void afficherType() {
        System.out.println("Smartphone | OS : " + systemeExploitation + " | Ecran : " + tailleEcran + " pouces");
    }
}