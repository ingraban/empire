package name.saak.empire.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import name.saak.empire.schema.Game;
import name.saak.empire.schema.Row;
import name.saak.empire.schema.RowMilepost;

@Component
public class Gameboard {

	Game game;

	public Gameboard() {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class.getPackageName(), this.getClass().getClassLoader());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			URL url = this.getClass().getResource("/maps/america.xml");
			InputStream inputStream = url.openConnection().getInputStream();
			game = (Game) jaxbUnmarshaller.unmarshal(inputStream);
//				in.close();
			calcSize();
			System.out.println(game.getName());
		} catch (IOException e) {
			e.printStackTrace();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void calcSize() {
		if (game == null) System.out.println("No map loaded");

		int rows = game.getMap().getRow().size();
		int columns = 0;
		int start, cols;

		for (Row r : game.getMap().getRow()) {
			start = Optional.ofNullable(r.getClearOrMountain().getFirst().getValue().getStart()).orElse(0);
			cols = 0;
			for (JAXBElement<RowMilepost> rm : r.getClearOrMountain()) {
				cols += rm.getValue().getLength();
			}
			if (start + cols * 2 > columns) columns = start + cols * 2;
		}
		System.out.println(String.format("%s has %d rows and %d columns", game.getName(), rows, columns + 1));
	}

}
