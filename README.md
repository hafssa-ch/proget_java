# VentesBoutique

**Application de gestion des produits, clients et ventes d'une boutique**

VentesBoutique est une application Java complète destinée à gérer les produits d’une boutique, suivre les ventes, contrôler les stocks et analyser le chiffre d’affaires mensuel à l’aide de graphiques interactifs.

---

## Table des matières
1. [Contexte](#contexte)
2. [Problématique](#problématique)
3. [Objectifs](#objectifs)
4. [Fonctionnalités](#fonctionnalités)
5. [Architecture du projet](#architecture-du-projet)
6. [MCD](#mcd)
7. [Requêtes SQL](#requêtes-sql)
8. [Technologies utilisées](#technologies-utilisées)
9. [Vidéo démonstrative](#vidéo-démonstrative)

---

## Contexte
Dans la gestion d’une boutique, il est essentiel de suivre en temps réel :
- Les produits disponibles
- Les ventes réalisées
- Les clients

La gestion manuelle entraîne des erreurs dans le suivi des ventes et des stocks, et ne fournit aucune statistique fiable sur l’activité.

---

## Problématique
Les principales difficultés rencontrées sont :
- Mauvaise visibilité sur l’état des stocks (produits disponibles ou en rupture)
- Suivi manuel des ventes, entraînant des erreurs
- Absence de statistiques sur le chiffre d’affaires ou les ventes par période
- Impossibilité de filtrer les produits selon la catégorie ou la période de vente

---

## Objectifs
L'application VentesBoutique permet de :
- Centraliser la gestion des produits, clients et ventes
- Mettre à jour et contrôler le stock
- Enregistrer et suivre les ventes
- Identifier rapidement les produits en rupture
- Fournir des statistiques graphiques du chiffre d’affaires par mois

---

## Fonctionnalités

### Gestion des produits
- Ajouter, modifier et supprimer des produits
- Mettre à jour le stock (entrée / sortie)
- Afficher les produits en rupture de stock

### Gestion des clients
- Ajouter, modifier, supprimer et lister les clients

### Gestion des ventes
- Enregistrer une vente pour un client
- Décrémentation automatique du stock
- Suivi des ventes par produit
- Filtrer les ventes par catégorie ou par période

### Statistiques
- Graphique du chiffre d’affaires par mois

---

## Architecture du projet

Le projet est structuré de manière modulaire pour séparer la logique métier, l’accès aux données et l’interface utilisateur.

boutique/
└─ src/
└─ com.example.tp/
├─ connexion/
│ └─ Connexion.java # Gestion de la connexion à la BDD
├─ dao/
│ └─ IDao.java # Interface DAO
├─ entities/
│ ├─ Client.java
│ ├─ Produit.java
│ └─ Vente.java
├─ service/
│ ├─ ClientService.java
│ ├─ LoginService.java
│ ├─ ProduitService.java
│ └─ VenteService.java
├─ ui/
│ ├─ ClientFormmm.java
│ ├─ Mainform.java
│ ├─ ProduitFormm.java
│ ├─ RechechePeriode.java
│ ├─ RechercheCategorie.java
│ ├─ Statistique.java
│ ├─ loginForm.java
│ └─ venteForm.java
└─ Test/
├─ TestClient.java
├─ TestProduit.java
└─ TestVente.java

##  MCD
<img width="353" height="200" alt="mcd (1)" src="https://github.com/user-attachments/assets/783a2a8a-c94d-4d98-b3f3-57119dcd1a7e" />

---

## Requêtes SQL

```sql
CREATE TABLE Produit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    categorie VARCHAR(50) NOT NULL,
    prix DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    ville VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE Vente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    produit_id INT NOT NULL,
    client_id INT NOT NULL,
    dateVente DATE DEFAULT CURRENT_DATE,
    quantite INT NOT NULL,
    FOREIGN KEY (produit_id) REFERENCES Produit(id),
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

## Technologies utilisées

Java — Développement de l’application

Java Swing — Interface graphique

JFreeChart — Génération des graphiques

MySQL — Base de données

JDBC — Connexion à la base de données

phpMyAdmin — Administration MySQL

NetBeans IDE — Édition, compilation et exécution
##video

https://github.com/user-attachments/assets/af65cd0d-6d59-4456-8622-14c6c22eb844



Nom : Hafssa CHKOUKED

Cours : Programmation Java

Date : 2 Décembre 2025

Encadré par : Mohamed LACHGAR
