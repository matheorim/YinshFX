package but.info.sae2_12.controllers.Interaction;

import but.info.sae2_12.model.Team;
import but.info.sae2_12.model.state.IState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Alert;
import but.info.sae2_12.model.state.State;

public class Vainqueur {
	
	private BooleanProperty vainqueur = new SimpleBooleanProperty(false);

	public BooleanProperty getVainqueur() {
		return vainqueur;
	}
	
	public void verifierVainqueur(State State) {
		Team gagnant =  State.winner();
	    if (gagnant != null) {
	        vainqueur.set(true);
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Fin de partie");
	        alert.setContentText("Le vainqueur est : " + gagnant);
	        alert.showAndWait();
	    }
	}

}
