package reseau;

import java.util.ArrayList;

public abstract class MS implements InterfaceMS {

    protected String nom;
    protected String prenom;
    protected String motDePasse;
    protected String msisdn;
    protected String imsi;
    protected BTS btsAttache;
    protected ArrayList<String> appelsRecus;

    public MS(String nom, String prenom, String motDePasse, String msisdn, String imsi) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.msisdn = msisdn;
        this.imsi = imsi;
        this.btsAttache = null;
        this.appelsRecus = new ArrayList<String>();
    }

    public boolean sAttacher(BTS bts) {
        try {
            if (bts == null) {
                System.out.println("ERREUR : BTS inexistante !");
                return false;
            }

            if (bts.getMsAttaches().size() >= bts.getNbMaxUtilisateurs()) {
                throw new EtatCelluleException(
                    "Cellule saturee : impossible d'attacher " + msisdn + " a la BTS " + bts.getNumero()
                );
            }

            if (this.btsAttache != null) {
                this.btsAttache.supprimerMS(this);
                System.out.println(msisdn + " s'est detache de la BTS " + this.btsAttache.getNumero());
            }

            bts.ajouterMS(this);
            this.btsAttache = bts;
            System.out.println("SUCCES : " + msisdn + " maintenant attache a la BTS " + bts.getNumero());
            return true;

        } catch (EtatCelluleException e) {
            System.out.println("ECHEC attachement : " + e.getMessage());
            return false;
        }
    }

    public boolean appeler(MS destinataire) {
        if (this.btsAttache == null) {
            System.out.println("APPEL IMPOSSIBLE : " + msisdn + " n'est attache a aucune BTS.");
            return false;
        }

        if (destinataire == null) {
            System.out.println("APPEL IMPOSSIBLE : destinataire invalide.");
            return false;
        }

        if (destinataire.btsAttache == null) {
            System.out.println("APPEL IMPOSSIBLE : le destinataire " + destinataire.msisdn + " n'est pas sur le reseau.");
            return false;
        }

        String identifiantAppelant = this.msisdn + " (" + this.nom + " " + this.prenom + ")";
        destinataire.recevoirAppel(identifiantAppelant);
        System.out.println("APPEL EN COURS : " + this.msisdn + " -> " + destinataire.msisdn);
        return true;
    }

    public void recevoirAppel(String identifiantAppelant) {
        appelsRecus.add(identifiantAppelant);
    }

    public void afficherAppelsRecus() {
        System.out.println("--------------------------------------------");
        System.out.println("Journal d'appels recus pour " + msisdn + " (" + nom + " " + prenom + ") :");
        if (appelsRecus.isEmpty()) {
            System.out.println("  Aucun appel recu.");
        } else {
            for (int i = 0; i < appelsRecus.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + appelsRecus.get(i));
            }
        }
        System.out.println("--------------------------------------------\n");
    }

    public void afficherCaracteristiques() {
        System.out.println("============================================");
        System.out.println("CARACTERISTIQUES DE L'UTILISATEUR");
        System.out.println("--------------------------------------------");
        System.out.println("Nom            : " + nom);
        System.out.println("Prenom         : " + prenom);
        System.out.println("MSISDN (Tel)   : " + msisdn);
        System.out.println("IMSI (SIM)     : " + imsi);
        System.out.print("BTS attachee   : ");
        if (btsAttache != null) {
            System.out.println(btsAttache.getNumero());
        } else {
            System.out.println("Aucune");
        }
        System.out.print("Type           : ");
        afficherType();
        System.out.println("============================================\n");
    }

    public abstract void afficherType();

    public String getMsisdn() {
        return msisdn;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public BTS getBtsAttache() {
        return btsAttache;
    }
}