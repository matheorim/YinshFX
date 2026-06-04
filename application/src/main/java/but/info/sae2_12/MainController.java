package but.info.sae2_12;

import but.info.sae2_12.controllers.CentralController;
import but.info.sae2_12.controllers.GameController;
import but.info.sae2_12.controllers.TypePartie;
import but.info.sae2_12.model.Model;
import but.info.sae2_12.model.factory.FactoryDoubled;
import but.info.sae2_12.model.factory.IFactory;
import but.info.sae2_12.model.state.State;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public CentralController hexGridController;
    @FXML
    public GameController gameController;

    private Model model;


    private final SimpleObjectProperty<Color> couleur1 = new SimpleObjectProperty<>(Color.web("#707070"));
    private final SimpleObjectProperty<Color> couleur2 = new SimpleObjectProperty<>(Color.web("#A0A0A0"));
    private final SimpleObjectProperty<Color> couleur3 = new SimpleObjectProperty<>(Color.web("#E0E0E0"));



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IFactory factory = new FactoryDoubled();
        this.model=new Model(factory.emptyState());
        if (hexGridController != null) {
            hexGridController.setController(this);
            hexGridController.desinerGrille();
        }
        if (gameController != null) {
            gameController.setController(this);
        }
    }

    public Model getModel() {
        return this.model;
    }

    public void selectionnerAffichageCoordonnees(boolean active) {
        if (hexGridController != null) {
            hexGridController.setAfficherCoords(active);
        }
    }

    public void PartieAleatoire(TypePartie mode){
        IFactory factory = new FactoryDoubled();
        switch (mode){
            case ALEA -> this.model=new Model(factory.randomGame());
            case TEST -> this.model=new Model(factory.testState());
            case LIGNEBLANC -> this.model=new Model(factory.stateForWhiteLineTest());
            case LIGNENOIR -> this.model=new Model(factory.stateForBlackLineTest());
            default -> this.model=new Model(factory.emptyState());
        }
        hexGridController.desinerGrille();
    }

    public Color getCouleur1() {
        return couleur1.get();
    }

    public SimpleObjectProperty<Color> couleur1Property() {
        return couleur1;
    }

    public Color getCouleur2() {
        return couleur2.get();
    }

    public SimpleObjectProperty<Color> couleur2Property() {
        return couleur2;
    }

    public Color getCouleur3() {
        return couleur3.get();
    }

    public SimpleObjectProperty<Color> couleur3Property() {
        return couleur3;
    }


}
