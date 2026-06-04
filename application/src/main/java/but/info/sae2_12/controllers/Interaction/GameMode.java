package but.info.sae2_12.controllers.Interaction;

import java.util.Set;

import but.info.sae2_12.model.tokens.Ring;
import coordinates.Coordinate;
import javafx.scene.input.MouseEvent;
import but.info.sae2_12.MainController;

public class GameMode extends InteractionMode{
	
	public GameMode(MainController maincontroller) {	
			super(maincontroller);
		}
	
	private Coordinate selectedRing;
	private Set<Coordinate> coupPossible;
	
	@Override
	public void handleClick(MouseEvent event , HexSquare hex) {
		if (selectedRing == null ) {
			if (hex.getToken() instanceof Ring) {
				selectedRing = hex.getCoordinate();
				coupPossible = mainController.getModel().movesFrom(selectedRing);
			}
		}else if (coupPossible.contains(hex.getCoordinate())) {
		    mainController.getModel().moveRing(selectedRing, hex.getCoordinate());
		    selectedRing = null;
		    coupPossible = null;
		}
	}

	@Override
	public void entered(MouseEvent event, HexSquare hex) {

	}

	@Override
	public void exited(MouseEvent event, HexSquare hex) {

	}

}
