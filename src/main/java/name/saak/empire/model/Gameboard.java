package name.saak.empire.model;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
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
			Dimension d = calcSize();
			System.out.println(String.format("Map %s is %d width and %d height.", game.getName(), d.width, d.height));
			printMap();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printMap() {
		game.getCities().getSmallCityOrMediumCityOrMajorCity().forEach(c -> System.out
				.println(String.format("City: %s, at (%d, %d) is a %s", c.getName(), c.getPositionX(), c.getPositionY(), c.getClass().getSimpleName())));
	}

	public List<Row> getRows() {
		return game.getMap().getRow();
	}

	private Dimension calcSize() {
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
			if ((start + cols * 2 - 1) > columns) //
				columns = start + cols * 2 - 1;
		}
		return new Dimension(columns, rows);
	}

	public Dimension getDimension() {
		return calcSize();
	}

}
