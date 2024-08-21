package name.saak.empire.ui.handler;

import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;

import javax.swing.JOptionPane;

public class EmpireQuitHandler implements QuitHandler {

	@Override
	public void handleQuitRequestWith(QuitEvent e, QuitResponse response) {
		int result = JOptionPane.showConfirmDialog(null, "Wollen Sie die Anwendung beenden??", "Anwendung beenden", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			response.performQuit();
		} else {
			response.cancelQuit();
		}
	}

}
