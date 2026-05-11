# TP2 - IPPlan-Manager : Encapsulation

## Objectif du TP

Ce TP introduit le concept fondamental de l'**encapsulation** en Programmation Orientée Objet. 
L'objectif est de protéger les données des objets et de contrôler les modifications grâce à :
- La privatisation des attributs (`private`)
- Les **getters** (accès lecture)
- Les **setters** (accès écriture avec validation)

## Structure du projet
TP2_IPPlanManager_Encapsulation/
├── src/
│ └── ipplanmanager/
│ ├── AdresseIP.java
│ ├── ReseauIP.java
│ ├── InterfaceReseau.java
│ ├── Equipement.java
│ └── Main.java
└── README.md

text

## Notions étudiées

| Notion | Description |
|--------|-------------|
| `private` | Rend l'attribut inaccessible depuis l'extérieur |
| `public` | Rend la méthode accessible partout |
| Getter | Permet de lire un attribut privé |
| Setter | Permet de modifier un attribut privé avec validation |
| `this` | Référence à l'objet courant |
| Validation | Vérification des données avant assignation |

## Tests réalisés

### 1. Test de la classe AdresseIP

| Cas testé | Résultat attendu | Résultat obtenu |
|-----------|------------------|-----------------|
| "192.168.1.1" | Adresse acceptée | ✅ OK |
| "" (chaîne vide) | Erreur + "0.0.0.0" | ✅ OK |
| null | Erreur + "0.0.0.0" | ✅ OK |
| estAdresseLocale() sur 192.x | true | ✅ OK |
| estAdresseLocale() sur 10.x | false | ✅ OK |

### 2. Test de la classe ReseauIP

| Cas testé | Résultat attendu | Résultat obtenu |
|-----------|------------------|-----------------|
| CIDR = 24 | Accepté | ✅ OK |
| CIDR = 55 | Erreur + 24 par défaut | ✅ OK |
| Adresse vide | Erreur + "0.0.0.0" | ✅ OK |
| Description vide | "Aucune description" | ✅ OK |

### 3. Test de la classe InterfaceReseau

| Cas testé | Résultat attendu | Résultat obtenu |
|-----------|------------------|-----------------|
| Nom "eth0" | Accepté | ✅ OK |
| Nom vide | "interface_inconnue" | ✅ OK |
| Activation | active = true | ✅ OK |
| Désactivation | active = false | ✅ OK |

### 4. Test de la classe Equipement

| Cas testé | Résultat attendu | Résultat obtenu |
|-----------|------------------|-----------------|
| Nom "R1_EDGE" | Accepté | ✅ OK |
| Nom vide | "equipement_inconnu" | ✅ OK |
| Type vide | "Type inconnu" | ✅ OK |

### 5. Équipements supplémentaires créés

| Équipement | Type | Interface | Adresse IP | État |
|------------|------|-----------|------------|------|
| R1_EDGE | Routeur | eth0 | 192.168.1.1 | active |
| SRV_DNS | Serveur | eth1 | non configurée | inactive |
| SW1_CORE | Switch | gig0/1 | 192.168.1.2 | active |
| AP1_LABO | Point d'accès WiFi | wlan0 | 192.168.1.3 | active |
| PC_USER | Poste client | eth0 | 192.168.1.50 | inactive |

## Difficultés rencontrées

1. **Compréhension de l'utilité des setters dans le constructeur**
   - Solution : Le constructeur appelle les setters pour réutiliser la même validation

2. **Gestion des null et chaînes vides**
   - Solution : Toujours vérifier les deux cas avec `valeur == null || valeur.isEmpty()`

3. **Différence entre attribut et paramètre**
   - Solution : Utilisation du mot-clé `this` pour distinguer `this.nom` du paramètre `nom`

## Réponses aux questions de compréhension

### 1. Pourquoi utilise-t-on private dans les classes ?
On utilise `private` pour **protéger les attributs** contre les accès ou modifications non contrôlés depuis l'extérieur. C'est le principe fondamental de l'encapsulation.

### 2. Quelle différence entre un attribut public et un attribut privé ?
- **`public`** : accessible directement depuis n'importe quelle autre classe
- **`private`** : accessible uniquement à l'intérieur de sa propre classe

### 3. Pourquoi utilise-t-on des getters et setters ?
Les getters/setters permettent un **accès contrôlé** aux attributs privés. On peut :
- Ajouter des validations dans les setters
- Rendre un attribut accessible en lecture mais pas en écriture (getter sans setter)
- Modifier l'implémentation interne sans changer l'interface publique

### 4. Pourquoi les validations sont-elles importantes dans un logiciel réseau ?
Dans un logiciel réseau, des données invalides peuvent causer :
- Des conflits d'adresses IP
- Des masques de sous-réseau incorrects
- Des équipements mal configurés
- Des pannes réseau
- Des failles de sécurité

### 5. Quel est le rôle du mot-clé this ?
`this` fait référence à **l'objet courant**. Il permet de :
- Distinguer les attributs de l'objet des paramètres de méthode
- Appeler un constructeur d'un autre constructeur (`this(...)`)
- Passer l'objet courant comme paramètre

### 6. Pourquoi le constructeur appelle-t-il les setters ?
Le constructeur appelle les setters pour **réutiliser les validations** déjà écrites. Cela évite la duplication du code de validation et garantit qu'aucun objet n'est créé avec des données invalides.

### 7. Pourquoi la validation du masque CIDR est-elle importante ?
Le masque CIDR doit être compris entre 0 et 32. Un masque invalide (ex: 45) n'a pas de sens en réseau. Sans validation, l'application pourrait calculer des adresses réseau ou des plages d'adresses incorrectes.

### 8. Pourquoi l'encapsulation améliore-t-elle la sécurité logicielle ?
L'encapsulation améliore la sécurité en :
- Empêchant les modifications accidentelles
- Contrôlant toutes les modifications via des setters
- Permettant l'ajout de validations
- Rendant le code plus robuste et prévisible

## Commandes Git utilisées

```bash
# Se placer dans le dossier Projet1POO
cd Projet1POO

# Ajouter le TP2 au suivi de version
git add TP2_IPPlanManager_Encapsulation

# Créer un commit
git commit -m "Ajout du TP2 - Encapsulation et validations"

# Pousser vers le dépôt distant
git push origin main
