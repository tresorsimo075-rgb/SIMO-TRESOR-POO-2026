/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */

public class RecommendationAdministration implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        String nomUpper = vlan.getNom().toUpperCase();
        if (nomUpper.contains("ADMIN") || nomUpper.contains("ADMINISTRATION")) {
            return new Recommandation(
                "Sécurisation du VLAN Administration",
                "ÉLEVÉE",
                "Le VLAN " + vlan.getNom() + " (Administration) doit avoir un accès strictement réservé aux administrateurs réseau."
            );
        }
        return null;
    }
    
}
