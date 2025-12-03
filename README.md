# VentesBoutique

Application de gestion des produits, clients et ventes

VentesBoutique est une application Java destinée à gérer les produits d’une boutique, suivre les ventes, contrôler les stocks et analyser le chiffre d’affaires mensuel à l’aide de graphiques.

## Table des matières
Contexte

Problématique

Objectifs

Fonctionnalités

Structure des tables

Requêtes SQL

Architecture

Technologies utilisées


## Contexte

Dans la gestion d’une boutique, il est essentiel de suivre en temps réel les produits disponibles, les ventes réalisées et les clients.
Lorsque ce suivi se fait manuellement, il est difficile de maintenir un inventaire précis et d'obtenir des statistiques fiables sur l’activité de la boutique.

## Problématique

Les difficultés constatées dans la gestion actuelle sont :

Mauvaise visibilité sur l’état des stocks (produits disponibles ou en rupture).

Suivi manuel des ventes, entraînant des erreurs.

Aucune statistique sur le chiffre d’affaires ou les ventes par période.

Impossible de filtrer les produits selon la catégorie ou la période de vente.

## Objectifs

L'application VentesBoutique vise à :

Centraliser la gestion des produits, clients et ventes.

Permettre de mettre à jour et contrôler le stock.

Faciliter l’enregistrement et le suivi des ventes.

Identifier rapidement les produits en rupture.

Fournir des statistiques grâce à un graphique du chiffre d’affaires par mois.

## Fonctionnalités
### Gestion des produits

Ajouter des produits

Modifier les informations d’un produit

Supprimer un produit

Mettre à jour le stock (entrée / sortie)

Afficher les produits en rupture

### Gestion des clients

Ajouter, modifier, supprimer et lister les clients

### Gestion des ventes

Enregistrer une vente pour un client

Décrémentation automatique du stock

Suivi des ventes par produit

Filtrer les ventes par catégorie ou par période

### Statistiques

Graphique du chiffre d’affaires par mois

## Structure des tables
Table Produit
Champ	Type	Description
id	INT (PK)	Identifiant unique
libelle	VARCHAR(100)	Nom du produit
categorie	VARCHAR(50)	Catégorie du produit
prix	DECIMAL(10,2)	Prix unitaire
stock	INT	Stock actuel
Table Client
Champ	Type	Description
id	INT (PK)	Identifiant unique
nom	VARCHAR(100)	Nom du client
ville	VARCHAR(100)	Ville du client
email	VARCHAR(100)	Email du client
Table Vente
Champ	Type	Description
id	INT (PK)	Identifiant vente
produit_id	INT (FK)	Produit vendu
client_id	INT (FK)	Client ayant acheté
dateVente	DATE	Date de la vente
quantite	INT	Quantité vendue
## Requêtes SQL
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

## Architecture

Architecture MVC (Model - View - Controller)

entities/ → classes Produit, Client, Vente

service/ → gestion des opérations CRUD + logique métier

ui/ → Formulaires Java Swing (produits, clients, ventes, statistiques)

dao/ → Accès aux données (JDBC + MySQL)


## Technologies utilisées

Java — Développement de l’application

Java Swing — Interface graphique

JFreeChart — Génération des graphiques

MySQL — Base de données

JDBC — Connexion BDD

phpMyAdmin — Administration MySQL

NetBeans IDE — Édition, compilation et exécution



https://github.com/user-attachments/assets/af65cd0d-6d59-4456-8622-14c6c22eb844



Nom : Hafssa CHKOUKED

Cours : Programmation Java

Date : 2 Décembre 2025

Encadré par : Mohamed LACHGAR
