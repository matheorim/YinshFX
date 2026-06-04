package but.info.sae2_12.controllers.Interaction;

import javafx.scene.input.MouseEvent;

public abstract class InteractionMode {
	
	private MainController mainController;
	
	public InteractionMode(MainController maincontroller) {
		this.mainController = mainController ;
	}
	
	public abstract void handleClick(MouseEvent event , HexSquare hex);
	public abstract void entered(MouseEvent event , HexSquare hex);
	public abstract void exited(MouseEvent event , HexSquare hex);
}
