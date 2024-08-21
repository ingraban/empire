package name.saak.empire;

import java.awt.Desktop;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import name.saak.empire.ui.MapFrame;
import name.saak.empire.ui.handler.EmpireQuitHandler;

@SpringBootApplication
public class EmpireBuilderApplication {

	public static void main(String... args) {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("apple.awt.application.appearance", "system");
		System.setProperty("apple.awt.application.name", "Empire Builder");

		ConfigurableApplicationContext context = createApplicationContext(args);
		displayMainFrame(context);
	}

	private static ConfigurableApplicationContext createApplicationContext(String... args) {
		return new SpringApplicationBuilder(EmpireBuilderApplication.class).headless(false).run(args);
	}

	private static void displayMainFrame(ConfigurableApplicationContext context) {
		SwingUtilities.invokeLater(() -> {
			MapFrame mapFrame = context.getBean(MapFrame.class);
			mapFrame.setVisible(true);

			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.APP_ABOUT)) {
				desktop.setAboutHandler(e -> {
					JOptionPane.showMessageDialog(null, "Hallo", "Title", JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
				});
			}
			if (desktop.isSupported(Desktop.Action.APP_PREFERENCES)) {
				desktop.setPreferencesHandler(e -> {
					JOptionPane.showConfirmDialog(null, "Wir haben noch kene Einstellungen", "Einstellungen", JOptionPane.OK_OPTION,
							JOptionPane.WARNING_MESSAGE);
				});
			}
			if (desktop.isSupported(Desktop.Action.APP_QUIT_HANDLER)) {
				desktop.setQuitHandler(new EmpireQuitHandler());
			}
		});
	}

}
