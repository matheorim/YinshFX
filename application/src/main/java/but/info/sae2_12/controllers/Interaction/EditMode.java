package but.info.sae2_12.controllers.Interaction;

import but.info.sae2_12.MainController;
import but.info.sae2_12.form.HexSquare;
import but.info.sae2_12.model.Team;
import but.info.sae2_12.model.tokens.Pawn;
import but.info.sae2_12.model.tokens.Ring;
import but.info.sae2_12.model.tokens.Token;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EditMode extends InteractionMode {
	public EditMode(MainController mainController) {
		super(mainController);
	}

	@Override
	public void handleClick(MouseEvent event, HexSquare hex) {
		if (event.getButton() == MouseButton.SECONDARY) {
			getMainController().getModel().removeToken(hex.coordonnee);
		} else if (event.getButton() == MouseButton.PRIMARY) {
			Team equipe = getMainController().getModel().getTurn();
			Token nouveau;
			if (getMainController().getModel().getTokenAt(hex.coordonnee) instanceof Ring) {
				nouveau = new Ring(equipe);
			} else {
				nouveau = new Pawn(equipe);
			}
			getMainController().getModel().toggleToken(hex.coordonnee,nouveau.getClass(),equipe);
		}
		getMainController().hexGridController.refreshGrille();
	}

	@Override
	public void entered(MouseEvent event, HexSquare hex) {

	}

	@Override
	public void exited(MouseEvent event, HexSquare hex) {

	}
}
