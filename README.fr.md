# YinshFX

🇬🇧 [English version](README.md)

Jeu de plateau à deux équipes en **JavaFX**, joué sur une grille **hexagonale**, avec des **anneaux** et des **pions**, et la suppression de lignes alignées. Le jeu propose une interface graphique, un mode édition et une **intelligence artificielle** basée sur l'algorithme **Minimax**.

Projet réalisé à l'IUT dans le cadre de la SAE 2.12. Par groupe de 4 personnes.

## Fonctionnalités

- Interface graphique JavaFX (menu, zone de jeu centrale, barre du bas)
- Plateau hexagonal géré par deux systèmes de coordonnées : **cubiques** et **doublées**
- Deux équipes qui jouent à tour de rôle
- Jetons de deux types : **anneaux** (`Ring`) et **pions** (`Pawn`)
- Actions de jeu : **déplacement** (`Move`) et **suppression d'une ligne** (`RemoveLine`)
- Détection du **vainqueur**
- Plusieurs types de parties (`TypePartie`)
- **Mode jeu** et **mode édition** du plateau
- **Intelligence artificielle** (`MiniMax`, `MinimaxAI`) pour jouer contre l'ordinateur

## Technologies

- **Java** (avec modules, voir les `module-info.java`)
- **JavaFX** pour l'interface (fichiers FXML)
- **Maven** (projet multi-modules)

## Prérequis

- JDK 17 ou supérieur (à adapter selon le `pom.xml`)
- Maven 3.8 ou supérieur

## Installation et lancement

```bash
git clone https://github.com/matheorim/Groupe1_LK_MA_FE_RM_QN.git
cd Groupe1_LK_MA_FE_RM_QN
mvn clean install
cd application
mvn javafx:run
```

> La commande `mvn javafx:run` suppose que le plugin `javafx-maven-plugin` est configuré dans `application/pom.xml`. Sinon, ouvre le projet dans ton IDE (IntelliJ IDEA, Eclipse, NetBeans) et lance la classe `but.info.sae2_12.App`.

## Structure du projet

Le projet est un **projet Maven multi-modules** : le `pom.xml` à la racine regroupe deux modules.

```
.
├── pom.xml                              # POM parent
├── hexagonalCoordinate/                 # Module : coordonnées hexagonales
│   └── src/main/java/coordinates/
│       ├── Coordinate.java
│       ├── CoordinateCube.java          # Coordonnées cubiques
│       ├── CoordinateDoubled.java       # Coordonnées doublées
│       ├── Direction.java
│       ├── Mode.java
│       ├── Point.java
│       └── DifferentAxisException.java
│
└── application/                         # Module : le jeu
    └── src/main/
        ├── java/but/info/sae2_12/
        │   ├── App.java                 # Point d'entrée
        │   ├── MainController.java
        │   ├── AI/                      # Intelligence artificielle
        │   │   ├── AI.java
        │   │   ├── MiniMax.java
        │   │   └── MinimaxAI.java
        │   ├── controllers/             # Contrôleurs de l'interface
        │   │   ├── MenuController.java
        │   │   ├── CentralController.java
        │   │   ├── BottomController.java
        │   │   ├── GameController.java
        │   │   ├── TypePartie.java
        │   │   ├── IA/IAController.java
        │   │   └── Interaction/         # Modes d'interaction
        │   │       ├── InteractionMode.java
        │   │       ├── GameMode.java
        │   │       ├── EditMode.java
        │   │       ├── RemoveLine.java
        │   │       └── Vainqueur.java
        │   ├── form/HexSquare.java      # Case hexagonale affichée
        │   └── model/                   # Logique du jeu
        │       ├── Model.java
        │       ├── Team.java
        │       ├── Utils.java
        │       ├── actions/             # Action, Move, RemoveLine
        │       ├── factory/             # AbstractFactory, FactoryCube, FactoryDoubled, IFactory
        │       ├── state/               # IState, State
        │       └── tokens/              # Token, Pawn, Ring
        └── resources/but/info/sae2_12/
            ├── Main.fxml                # Fenêtre principale
            ├── AI.fxml
            └── controllers/             # Vues FXML (menu, central, bas, jeu)
```

## Architecture

Le projet suit le patron **MVC** (Modèle - Vue - Contrôleur).

- **Modèle** (`model`) : règles et état du jeu. `Model` gère la partie, `State` décrit l'état du plateau, `Team` représente les équipes, et `Token` est la classe de base de `Ring` et `Pawn`.
- **Actions** (patron *Command*) : `Action` est implémentée par `Move` et `RemoveLine`, ce qui facilite l'application des coups et leur évaluation par l'IA.
- **Fabriques** (patron *Factory*) : `IFactory` est implémentée par `FactoryCube` et `FactoryDoubled`, pour créer les éléments du jeu selon le système de coordonnées.
- **Vue** (FXML) : l'interface est découpée en plusieurs fichiers (menu, zone centrale, barre du bas, jeu) assemblés dans `Main.fxml`.
- **Contrôleurs** (`controllers`) : ils font le lien entre l'interface et le modèle. Les modes d'interaction (`GameMode`, `EditMode`) changent la façon dont le plateau réagit aux clics.
- **IA** (`AI`) : l'algorithme **Minimax** explore les coups possibles pour choisir le meilleur.
- **Coordonnées** (`hexagonalCoordinate`) : bibliothèque indépendante, réutilisée par l'application.

