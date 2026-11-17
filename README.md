# YinshFX

🇫🇷 [Version française](README.fr.md)

A two-team board game built with **JavaFX**, played on a **hexagonal** grid with **rings** and **pawns**, where players remove aligned lines. The game provides a graphical interface, an edit mode and an **artificial intelligence** based on the **Minimax** algorithm.

Project completed at the IUT as part of SAE 2.12 in a group of 4.

## Features

- JavaFX graphical interface (menu, central game area, bottom bar)
- Hexagonal board handled with two coordinate systems: **cube** and **doubled**
- Two teams playing in turn
- Two types of tokens: **rings** (`Ring`) and **pawns** (`Pawn`)
- Game actions: **move** (`Move`) and **line removal** (`RemoveLine`)
- **Winner** detection
- Several game types (`TypePartie`)
- **Game mode** and **edit mode** for the board
- **Artificial intelligence** (`MiniMax`, `MinimaxAI`) to play against the computer

## Technologies

- **Java** (with modules, see the `module-info.java` files)
- **JavaFX** for the interface (FXML files)
- **Maven** (multi-module project)

## Requirements

- JDK 17 or higher (adjust to the version set in `pom.xml`)
- Maven 3.8 or higher

## Installation and launch

```bash
git clone https://github.com/matheorim/YinshFX.git
cd YinshFX
mvn clean install
cd application
mvn javafx:run
```

> The `mvn javafx:run` command assumes that the `javafx-maven-plugin` is configured in `application/pom.xml`. Otherwise, open the project in your IDE (IntelliJ IDEA, Eclipse, NetBeans) and run the class `but.info.sae2_12.App`.

## Project structure

This is a **multi-module Maven project**: the root `pom.xml` groups two modules.

```
.
├── pom.xml                              # Parent POM
├── hexagonalCoordinate/                 # Module: hexagonal coordinates
│   └── src/main/java/coordinates/
│       ├── Coordinate.java
│       ├── CoordinateCube.java          # Cube coordinates
│       ├── CoordinateDoubled.java       # Doubled coordinates
│       ├── Direction.java
│       ├── Mode.java
│       ├── Point.java
│       └── DifferentAxisException.java
│
└── application/                         # Module: the game
    └── src/main/
        ├── java/but/info/sae2_12/
        │   ├── App.java                 # Entry point
        │   ├── MainController.java
        │   ├── AI/                      # Artificial intelligence
        │   │   ├── AI.java
        │   │   ├── MiniMax.java
        │   │   └── MinimaxAI.java
        │   ├── controllers/             # Interface controllers
        │   │   ├── MenuController.java
        │   │   ├── CentralController.java
        │   │   ├── BottomController.java
        │   │   ├── GameController.java
        │   │   ├── TypePartie.java
        │   │   ├── IA/IAController.java
        │   │   └── Interaction/         # Interaction modes
        │   │       ├── InteractionMode.java
        │   │       ├── GameMode.java
        │   │       ├── EditMode.java
        │   │       ├── RemoveLine.java
        │   │       └── Vainqueur.java
        │   ├── form/HexSquare.java      # Displayed hexagonal cell
        │   └── model/                   # Game logic
        │       ├── Model.java
        │       ├── Team.java
        │       ├── Utils.java
        │       ├── actions/             # Action, Move, RemoveLine
        │       ├── factory/             # AbstractFactory, FactoryCube, FactoryDoubled, IFactory
        │       ├── state/               # IState, State
        │       └── tokens/              # Token, Pawn, Ring
        └── resources/but/info/sae2_12/
            ├── Main.fxml                # Main window
            ├── AI.fxml
            └── controllers/             # FXML views (menu, central, bottom, game)
```

## Architecture

The project follows the **MVC** pattern (Model - View - Controller).

- **Model** (`model`): game rules and state. `Model` manages the game, `State` describes the state of the board, `Team` represents the teams, and `Token` is the base class of `Ring` and `Pawn`.
- **Actions** (*Command* pattern): `Action` is implemented by `Move` and `RemoveLine`, which makes it easy to apply moves and to evaluate them in the AI.
- **Factories** (*Factory* pattern): `IFactory` is implemented by `FactoryCube` and `FactoryDoubled` to create game elements depending on the coordinate system.
- **View** (FXML): the interface is split into several files (menu, central area, bottom bar, game) assembled in `Main.fxml`.
- **Controllers** (`controllers`): they connect the interface to the model. The interaction modes (`GameMode`, `EditMode`) change how the board reacts to clicks.
- **AI** (`AI`): the **Minimax** algorithm explores the possible moves to choose the best one.
- **Coordinates** (`hexagonalCoordinate`): an independent library reused by the application.
