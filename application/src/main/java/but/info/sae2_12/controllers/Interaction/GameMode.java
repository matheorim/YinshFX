Java

package but.info.sae2_12.controllers.Interaction;

import java.util.Set;
import but.info.sae2_12.MainController;
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
                selectedRing = hex.getCoordinate();
                coupPossible = mainController.getModel().movesFrom(selectedRing);
                afficherIndices(true);
            }
        } else {
            if (coupPossible != null && coupPossible.contains(hex.getCoordinate())) {
                afficherIndices(false);
                mainController.getModel().moveRing(selectedRing, hex.getCoordinate());
                selectedRing = null;
                coupPossible = null;
            }
        }
    }

    private void afficherIndices(boolean visible) {
        if (coupPossible != null) {
            for (Coordinate c : coupPossible) {
                HexSquare hexPossible = mainController.getHexSquare(c);
                if (hexPossible != null) {
                    hexPossible.showAccessibleMarker(visible); 
                }
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