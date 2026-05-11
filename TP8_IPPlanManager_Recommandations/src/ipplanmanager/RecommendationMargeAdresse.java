/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ipplanmanager;

/**
 *
 * @author Trésor
 */

public class RecommendationMargeAdresse implements RegleRecommandation {
    
    @Override
    public Recommandation analyser(VLAN vlan) {
        if (vlan.getReseauAssocie() != null) {
            int demandes = vlan.getNombreHotesDemandes();
            int capacite = vlan.getCapacite();
            int marge = capacite - demandes;
            
            if (marge < 10 && marge >= 0) {
                return new Recommandation(
                    "Marge d'adresses insuffisante",
                    "MOYENNE",
                    "Le VLAN " + vlan.getNom() + " a une marge de seulement " + marge + " adresses. Prévoir une marge plus confortable pour les évolutions futures."
                );
            }
        }
        return null;
    }
    
}
