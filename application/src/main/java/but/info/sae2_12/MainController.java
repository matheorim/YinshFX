package but.info.sae2_12;

import but.info.sae2_12.controllers.CentralController;
import but.info.sae2_12.controllers.GameController;
import but.info.sae2_12.model.Model;
import but.info.sae2_12.model.factory.FactoryDoubled;
import but.info.sae2_12.model.factory.IFactory;
import but.info.sae2_12.model.state.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public CentralController hexGridController;
    @FXML
    public GameController gameController;

    private Model model;



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
}
