package but.info.sae2_12.controllers.Interaction;

import java.util.Set;

import but.info.sae2_12.MainController;
import but.info.sae2_12.form.HexSquare;
import but.info.sae2_12.model.tokens.Ring;
import coordinates.Coordinate;
import javafx.scene.input.MouseEvent;

public class RemoveLine extends InteractionMode {

    private Set<Coordinate> lockedLine;

    public RemoveLine(MainController mainController) {
        super(mainController);
    }

    @Override
    public void handleClick(MouseEvent event, HexSquare hex) {
        if (lockedLine == null) {
            for (Set<Coordinate> line : getMainController().getModel().getCurrentState().getLines()) {
                if (line.contains(hex.coordonnee)) {
                    lockedLine = line;
                    for (Coordinate c : lockedLine) {
                        getMainController().hexGridController.setVerrouille(true, c);
                    }
                    break;
                }
            }
        } else {
            if (hex.getToken() instanceof Ring ring
                    && ring.getTeam() == getMainController().getModel().getTurn()) {
                getMainController().getModel().removeLine(lockedLine, hex.coordonnee);
                lockedLine = null;
                getMainController().hexGridController.refreshGrille();
            }
        }
    }

    @Override
    public void entered(MouseEvent event, HexSquare hex) {
        if (lockedLine == null) {
            for (Set<Coordinate> line : getMainController().getModel().getCurrentState().getLines()) {
                if (line.contains(hex.coordonnee)) {
                    for (Coordinate c : line) {
                        getMainController().hexGridController.setHighlight(true,line);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void exited(MouseEvent event, HexSquare hex) {
        if (lockedLine == null) {
            for (Set<Coordinate> line : getMainController().getModel().getCurrentState().getLines()) {
                if (line.contains(hex.coordonnee)) {
                    for (Coordinate c : line) {
                        getMainController().hexGridController.setHighlight(false,line);
                    }
                    break;
                }
            }
        }
    }
}