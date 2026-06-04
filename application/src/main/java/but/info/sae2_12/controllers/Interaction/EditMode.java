package but.info.sae2_12.controllers.Interaction;

import javafx.scene.input.MouseEvent;

public class EditMode extends InteractionMode {
	public EditMode(MainController mainController) {	
		super(mainController);
	}
	
	@Override
	public void handleClick(MouseEvent event , HexSquare hex) {
		if (event.getButton() == MouseButton.SECONDARY) {
			mainController.getModel().removeToken(hex.getCoordinates());
		} else if (event.getButton() == MouseButton.PRIMARY) {
			mainController.getModel().toggleToken(hex.getCoordinate(), 
		    mainController.getSelectedTokenClass(), 
		    mainController.getSelectedTeam());
		}
	}

	@Override
	public void entered(MouseEvent event, HexSquare hex) {

	}

	@Override
	public void exited(MouseEvent event, HexSquare hex) {

	}
	
}
