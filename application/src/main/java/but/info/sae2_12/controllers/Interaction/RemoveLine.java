package but.info.sae2_12.controllers.Interaction;

import java.util.Set;

import but.info.sae2_12.model.tokens.Ring;
import coordinates.Coordinate;
import javafx.scene.input.MouseEvent;

public class RemoveLine extends InteractionMode{
	
	private Set<Coordinate> lockedLine;
	public RemoveLine(MainController mainController) {	
		super(mainController);
	}


@Override
public void handleClick(MouseEvent event , HexSquare hex) {
	if (lockedLine == null) { //premier clique
	    for (Set<Coordinate> line : mainController.getModel().getCurrentState().getLines()) {
	        if (line.contains(hex.getCoordinate())) { // si la ligne selectionner est correcte 
	            lockedLine = line;
	            break;
	        }
	    }
	} else {
		   if (hex.getToken() instanceof Ring) { // 2e clique donc retirer la ligne 
		        mainController.getModel().removeLine(lockedLine, hex.getCoordinate());
		        lockedLine = null;
		    }
	}
}

@Override
public void entered(MouseEvent event, HexSquare hex) {
	
	for (Set<Coordinate> line : mainController.getModel().getCurrentState().getLines()) {
	    if (line.contains(hex.getCoordinate())) {
	        for (Coordinate c : line) {
	            mainController.getHexSquare(c).setHighlight(true);
	        }
	        break;
	    }
	}
}

@Override
public void exited(MouseEvent event, HexSquare hex) {
	for (Set<Coordinate> line : mainController.getModel().getCurrentState().getLines()) {
	    if (line.contains(hex.getCoordinate())) {
	        for (Coordinate c : line) {
	            mainController.getHexSquare(c).setHighlight(false);
	        }
	        break;
	    }
	}
}


}
