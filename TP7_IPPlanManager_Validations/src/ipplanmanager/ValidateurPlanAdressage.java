package ipplanmanager;

import java.util.ArrayList;

public class ValidateurPlanAdressage {
    
    // Vérifie les chevauchements entre réseaux
    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) 
            throws ChevauchementReseauException {
        
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                
                boolean conflit = CalculateurReseau.reseauxSeChevauchent(
                    r1.getAdresseReseau(), r1.getCidr(),
                    r2.getAdresseReseau(), r2.getCidr()
                );
                
                if (conflit) {
                    throw new ChevauchementReseauException(
                        "Chevauchement détecté entre " + r1.getNomBesoin() + 
                        " (" + r1.getAdresseReseau() + "/" + r1.getCidr() + ") et " +
                        r2.getNomBesoin() + " (" + r2.getAdresseReseau() + "/" + r2.getCidr() + ")"
                    );
                }
            }
        }
        System.out.println("✓ Aucun chevauchement détecté entre les réseaux.");
    }
    
    // Vérifie que toutes les adresses sont valides
    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) 
            throws AdresseIPInvalideException {
        
        for (ResultatVLSM resultat : resultats) {
            CalculateurReseau.verifierAdresseIP(resultat.getAdresseReseau());
        }
        System.out.println("✓ Toutes les adresses sont valides.");
    }
    
    // Vérifie que le réseau de départ est suffisant (Travail supplémentaire)
    public void verifierCapaciteSuffisante(String adresseDepart, int cidrDepart, 
                                            ArrayList<BesoinReseau> besoins) 
            throws ReseauInsuffisantException {
        
        int capaciteTotale = CalculateurReseau.calculerTailleBloc(cidrDepart) - 2;
        int besoinsTotaux = 0;
        
        for (BesoinReseau besoin : besoins) {
            besoinsTotaux += besoin.getNombreHotes();
        }
        
        // Ajouter les adresses de réseau et de broadcast pour chaque sous-réseau
        int nombreSousReseaux = besoins.size();
        besoinsTotaux += nombreSousReseaux * 2;
        
        if (besoinsTotaux > capaciteTotale) {
            throw new ReseauInsuffisantException(
                "Capacité insuffisante ! Besoin: " + besoinsTotaux + 
                " hôtes, Capacité disponible: " + capaciteTotale + " hôtes."
            );
        }
        System.out.println("✓ Capacité réseau suffisante: " + besoinsTotaux + 
                          " hôtes nécessaires, " + capaciteTotale + " disponibles.");
    }
    
    // Validation complète du plan d'adressage
    public void validerPlanComplet(ArrayList<ResultatVLSM> resultats, 
                                    String adresseDepart, int cidrDepart,
                                    ArrayList<BesoinReseau> besoins) {
        System.out.println("\n=== VALIDATION DU PLAN D'ADRESSAGE ===\n");
        
        try {
            verifierAdresses(resultats);
            verifierChevauchements(resultats);
            verifierCapaciteSuffisante(adresseDepart, cidrDepart, besoins);
            afficherValidationReussie();
        } catch (AdresseIPInvalideException e) {
            System.out.println("❌ ERREUR D'ADRESSE IP: " + e.getMessage());
        } catch (ChevauchementReseauException e) {
            System.out.println("❌ ERREUR DE CHEVAUCHEMENT: " + e.getMessage());
        } catch (ReseauInsuffisantException e) {
            System.out.println("❌ ERREUR DE CAPACITÉ: " + e.getMessage());
        }
    }
    
    // Affiche le message de validation réussie
    public void afficherValidationReussie() {
        System.out.println("\n✅ Validation terminée : aucun conflit critique détecté.");
    }
}