package reseau;

import java.util.ArrayList;

public class Reseau {

    private String nom;
    private double frequenceUplink;
    private double frequenceDownlink;
    private String typeAccesMultiple;
    private double debitMaxUplink;
    private double debitMaxDownlink;
    private double delaiMax;
    private ArrayList<BTS> listeBTS;

    public Reseau(String nom, double frequenceUplink, double frequenceDownlink,
                  String typeAccesMultiple, double debitMaxUplink,
                  double debitMaxDownlink, double delaiMax) {
        this.nom = nom;
        this.frequenceUplink = frequenceUplink;
        this.frequenceDownlink = frequenceDownlink;
        this.typeAccesMultiple = typeAccesMultiple;
        this.debitMaxUplink = debitMaxUplink;
        this.debitMaxDownlink = debitMaxDownlink;
        this.delaiMax = delaiMax;
        this.listeBTS = new ArrayList<BTS>();
    }

    public void ajouterBTS(BTS bts) {
        if (bts != null) {
            listeBTS.add(bts);
            System.out.println("RESEAU " + nom + " : BTS " + bts.getNumero() + " ajoutee.");
        }
    }

    public boolean supprimerBTS(String numero) {
        for (int i = 0; i < listeBTS.size(); i++) {
            if (listeBTS.get(i).getNumero().equals(numero)) {
                listeBTS.remove(i);
                System.out.println("RESEAU " + nom + " : BTS " + numero + " supprimee.");
                return true;
            }
        }
        return false;
    }

    public BTS rechercherBTS(String numero) throws MSIntrouvableException {
        for (int i = 0; i < listeBTS.size(); i++) {
            BTS bts = listeBTS.get(i);
            if (bts.getNumero().equals(numero)) {
                return bts;
            }
        }
        throw new MSIntrouvableException("BTS " + numero + " introuvable dans le reseau " + nom);
    }

    public int calculerNombreBTSParMilieu(String typeMilieu) {
        int compteur = 0;
        for (int i = 0; i < listeBTS.size(); i++) {
            if (listeBTS.get(i).getTypeMilieu().equalsIgnoreCase(typeMilieu)) {
                compteur++;
            }
        }
        return compteur;
    }

    public int calculerNombreAbonnesInscrits() {
        int total = 0;
        for (int i = 0; i < listeBTS.size(); i++) {
            total = total + listeBTS.get(i).getMsAttaches().size();
        }
        return total;
    }

    public BTS rechercherLocalisationUtilisateur(String msisdn) {
        for (int i = 0; i < listeBTS.size(); i++) {
            try {
                listeBTS.get(i).rechercherMS(msisdn);
                System.out.println("UTILISATEUR " + msisdn + " localise dans BTS " + listeBTS.get(i).getNumero());
                return listeBTS.get(i);
            } catch (MSIntrouvableException e) {
            }
        }
        System.out.println("UTILISATEUR " + msisdn + " introuvable.");
        return null;
    }

    public void afficherPerformances() {
        System.out.println("============================================");
        System.out.println("  PERFORMANCES DU RESEAU " + nom);
        System.out.println("============================================");
        System.out.println("Bande Uplink        : " + frequenceUplink + " MHz");
        System.out.println("Bande Downlink      : " + frequenceDownlink + " MHz");
        System.out.println("Acces multiple      : " + typeAccesMultiple);
        System.out.println("Debit max Uplink    : " + debitMaxUplink + " Mbps");
        System.out.println("Debit max Downlink  : " + debitMaxDownlink + " Mbps");
        System.out.println("Delai max           : " + delaiMax + " ms");
        System.out.println("--------------------------------------------");
        System.out.println("Nombre total de BTS : " + listeBTS.size());
        System.out.println("BTS en zone urbaine : " + calculerNombreBTSParMilieu("urbain"));
        System.out.println("BTS en zone rurale  : " + calculerNombreBTSParMilieu("rural"));
        System.out.println("Abonnes inscrits    : " + calculerNombreAbonnesInscrits());
        System.out.println("============================================\n");
    }

    public ArrayList<BTS> getListeBTS() {
        return listeBTS;
    }
}