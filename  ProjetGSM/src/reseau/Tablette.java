package reseau;

public class Tablette extends MS {

    private boolean supportCarteSim;
    private double tailleEcran;

    public Tablette(String nom, String prenom, String motDePasse,
                    String msisdn, String imsi, boolean supportCarteSim, double tailleEcran) {
        super(nom, prenom, motDePasse, msisdn, imsi);
        this.supportCarteSim = supportCarteSim;
        this.tailleEcran = tailleEcran;
    }

    public void afficherType() {
        System.out.print("Tablette | SIM : ");
        if (supportCarteSim) {
            System.out.print("Oui");
        } else {
            System.out.print("Non");
        }
        System.out.println(" | Ecran : " + tailleEcran + " pouces");
    }
}