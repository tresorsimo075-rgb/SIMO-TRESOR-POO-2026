package ipplanmanager;

import java.util.ArrayList;

public class GestionnaireVLAN {
    private ArrayList<VLAN> vlans;
    
    public GestionnaireVLAN() {
        vlans = new ArrayList<>();
    }
    
    public void ajouterVLAN(VLAN vlan) throws ConflitVLANException {
        if (vlan == null) {
            return;
        }
        
        // Vérifier si un VLAN avec le même ID existe déjà
        for (VLAN v : vlans) {
            if (v.getId() == vlan.getId()) {
                // ⚠️ Important: un seul argument String ici
                throw new ConflitVLANException("Conflit VLAN : l'identifiant " + vlan.getId() + " est déjà utilisé.");
            }
        }
        vlans.add(vlan);
        System.out.println("VLAN " + vlan.getId() + " ajouté avec succès.");
    }
    
    public void supprimerVLAN(int id) {
        for (int i = 0; i < vlans.size(); i++) {
            if (vlans.get(i).getId() == id) {
                vlans.remove(i);
                System.out.println("VLAN " + id + " supprimé.");
                return;
            }
        }
        System.out.println("VLAN " + id + " non trouvé.");
    }
    
    public VLAN rechercherVLAN(int id) {
        for (VLAN vlan : vlans) {
            if (vlan.getId() == id) {
                return vlan;
            }
        }
        return null;
    }
    
    public void afficherTousLesVLANs() {
        if (vlans.isEmpty()) {
            System.out.println("Aucun VLAN configuré.");
        } else {
            System.out.println("\n=== Liste des VLANs ===");
            for (VLAN vlan : vlans) {
                vlan.afficher();
                System.out.println();
            }
        }
    }
    
    public ArrayList<VLAN> getVlans() {
        return vlans;
    }
}