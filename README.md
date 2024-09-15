
# Bati-Cuisine

## Titre : Application d'estimation des coûts de construction des cuisines

### Description rapide :
Bati-Cuisine est une application Java conçue pour les professionnels de la construction et de la rénovation de cuisines. Elle permet d'estimer les coûts des projets en tenant compte des matériaux, de la main-d'œuvre et des autres éléments logistiques. L'objectif est d'aider les professionnels à gérer efficacement les projets de rénovation et de construction.

### Contexte :
L'application propose un ensemble de fonctionnalités permettant de calculer le coût total des travaux tout en intégrant des aspects clés tels que :
- La gestion des clients (particuliers ou professionnels).
- L'ajout et la gestion des matériaux.
- La génération de devis détaillés avant le lancement des travaux.
- La prise en compte des taxes, remises, et marges bénéficiaires pour offrir une vue d'ensemble des coûts réels.

### Fonctionnalités principales :
1. **Gestion des Projets** :
   - Ajouter un client.
   - Gérer les composants du projet (matériaux et main-d'œuvre).
   - Associer un devis au projet.
   - Statuts des projets : En cours, Terminé, Annulé.
   
2. **Gestion des Composants** :
   - **Matériaux** : Ajout de matériaux avec des informations sur le coût unitaire, la quantité, le coût de transport, et un coefficient de qualité.
   - **Main-d'œuvre** : Gestion des coûts basés sur le taux horaire, les heures travaillées et la productivité des ouvriers.
   
3. **Gestion des Clients** :
   - Enregistrement des informations des clients (particuliers ou professionnels).
   - Application de remises spécifiques selon le type de client.
   
4. **Création de Devis** :
   - Génération de devis avant le démarrage du projet.
   - Prise en compte des matériaux, de la main-d'œuvre et des taxes.
   
5. **Calcul des Coûts** :
   - Intégration des coûts des composants dans le calcul du coût total.
   - Application des marges bénéficiaires et des taxes.
   
6. **Affichage des Résultats** :
   - Affichage des détails complets du projet (composants, client, devis, coût total).
   - Génération d'un récapitulatif détaillé.

### Exigences fonctionnelles :
- Gestion des projets : Ajout de clients, gestion des composants, création de devis, etc.
- Gestion des matériaux et de la main-d'œuvre : Ajout, coût unitaire, quantité, taux horaire.
- Gestion des clients : Enregistrement et différenciation des clients particuliers et professionnels.
- Création de devis : Génération de devis avec date d'émission et validité.
- Calcul des coûts : Intégration des coûts des matériaux et de la main-d'œuvre, gestion des taxes.
- Affichage des détails et récapitulatif des coûts.

### Prérequis :
- Java 8 ou plus récent
- IDE Java (IntelliJ)
- Base de données relationnelle PostgreSQL)

### Installation et utilisation :
Cloner le dépôt :
```bash
git clone [https://github.com/votre-utilisateur/bati-cuisine.git](https://github.com/J-Maryam/Bati-Cuisine.git)
```
Configurer la base de données en modifiant le fichier `config.properties`.

Compiler et exécuter le programme :
```bash
javac src/com/baticuisine/*.java
java com.baticuisine.Main
```

### Exemple d'utilisation :
Voici un exemple simple d'exécution de l'application pour un projet de rénovation de cuisine :
```plaintext
=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===
=== Menu Principal ===
1. Créer un nouveau projet
2. Afficher les projets existants
3. Calculer le coût d'un projet
4. Quitter
Choisissez une option : 1
```

### Auteur :
Développé par [Jammar Maryam](https://github.com/J-Maryam)

### Licence :
Ce projet est sous licence MIT.
