
package but.info.sae2_12.controllers.Interaction;

import java.util.Set;
import but.info.sae2_12.MainController;
import but.info.sae2_12.form.HexSquare;
import but.info.sae2_12.model.tokens.Ring;
import coordinates.Coordinate;
import javafx.scene.input.MouseEvent;

public class GameMode extends InteractionMode {

    private Coordinate selectedRing;
    private Set<Coordinate> coupPossible;

    public GameMode(MainController mainController) {    
        super(mainController);
    }

    @Override
    public void handleClick(MouseEvent event, HexSquare hex) {
        if (selectedRing == null) {
            if (hex.getToken() instanceof Ring) {
                selectedRing = hex.coordonnee;
                coupPossible = getMainController().getModel().getCurrentState().availableMoves(selectedRing);
                getMainController().hexGridController.afficherIndice(coupPossible);
            }
        } else {
            if (coupPossible != null && coupPossible.contains(hex.coordonnee)) {
                getMainController().getModel().moveRing(selectedRing, hex.coordonnee);
                selectedRing = null;
            }
        }
    }

    @Override
    public void entered(MouseEvent event, HexSquare hex) {

    }

    @Override
    public void exited(MouseEvent event, HexSquare hex) {

    }
}