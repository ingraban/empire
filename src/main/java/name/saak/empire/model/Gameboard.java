package name.saak.empire.model;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AccessLevel;
import lombok.Getter;
import name.saak.empire.mapper.LoadMapper;
import name.saak.empire.schema.Game;
import name.saak.empire.schema.Row;
import name.saak.empire.schema.RowMilepost;
import name.saak.empire.schema.SmallCity;
import name.saak.empire.schema.XmlLoad;
import name.saak.empire.util.MilepostLocator;

@Component
public class Gameboard {

	private Logger logger = LoggerFactory.getLogger(Gameboard.class);

	@Getter(AccessLevel.NONE)
	private Game game;

	/**
	 * Die Points sind die logischen Koordinaten. In der Summe müssen diese imm
	 * durch 2 teilbar sein.<br>
	 * (p.x + p.y % 2) == 0
	 */
	private HashMap<Point, Milepost> mileposts = new HashMap<>();

	private LoadMapper loadMapper;

	private HashMap<String, Load> loads = new HashMap<>();

	@Autowired
	public Gameboard(LoadMapper loadMapper) {
		this.loadMapper = loadMapper;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class.getPackageName(), this.getClass().getClassLoader());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			URL url = this.getClass().getResource("/maps/america.xml");
			InputStream inputStream = url.openConnection().getInputStream();
			game = (Game) jaxbUnmarshaller.unmarshal(inputStream);
			inputStream.close();
			mapModel();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}

	private void mapModel() {
		int rowIndex = 0;
		boolean firstRow = true;
		List<Row> row = game.getMap().getRow();
		for (Row r : row) {
			mapRow(r, rowIndex++, firstRow);
			firstRow = false;
		}

		game.getLoads().getLoad().forEach(this::mapLoad);
		game.getCities().getSmallCityOrMediumCityOrMajorCity().forEach(this::mapCity);
	}

	private void mapCity(SmallCity c) {
		Point location = new Point(c.getPositionX(), c.getPositionY());
		String name = c.getName();
		String className = c.getClass().getSimpleName();
		City city;

		switch (className) {
		case "SmallCity":
			city = new City(location.x, location.y, name);
			mileposts.put(location, city);
			break;
		case "MediumCity":
			city = new MediumCity(location.x, location.y, name);
			mileposts.put(location, city);
			break;
		default:
			city = new MajorCity(location.x, location.y, name);
			mileposts.put(location, city);
			// Major citoy consists of 7 Mileposts
			mileposts.put(new Point(location.x + 0, location.y - 2), city); // top
			mileposts.put(new Point(location.x + 1, location.y - 1), city); // top right
			mileposts.put(new Point(location.x + 1, location.y + 1), city); // bottom right
			mileposts.put(new Point(location.x + 0, location.y + 2), city); // bottom
			mileposts.put(new Point(location.x + 1, location.y + 1), city); // bottom left
			mileposts.put(new Point(location.x - 1, location.y - 1), city); // top left
			break;
		}
	}

	private void mapRow(Row r, int rowIndex, boolean firstRow) {
		List<JAXBElement<RowMilepost>> clearOrMountain = r.getClearOrMountain();
		int column = 0;
		for (JAXBElement<RowMilepost> rm : clearOrMountain) {
			column += Optional.ofNullable(rm.getValue().getStart()).orElse(0);
			if (firstRow) MilepostLocator.setOdd(column % 2 != 0);
			column += rm.getValue().getOffset() * 2;
			if ("mountain".equals(rm.getName().toString())) {
				for (int i = 0; i < rm.getValue().getLength(); i++) {
					Point l = new Point(column, rowIndex);
					mileposts.put(l, new MountainMilepost(l.x, l.y));
					column += 2;
				}
			} else {
				for (int i = 0; i < rm.getValue().getLength(); i++) {
					Point l = new Point(column, rowIndex);
					mileposts.put(l, new Milepost(l.x, l.y));
					column += 2;
				}
			}

		}
	}

	public List<Row> getRows() {
		return game.getMap().getRow();
	}

	private Dimension calcSize() {
		if (game == null) logger.warn("No map loaded");

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

	public Collection<Milepost> getMileposts() {
		return mileposts.values();
	}

	public Milepost getMilepostAt(Point mapLocation) {
		return mileposts.get(mapLocation);
	}

	/**
	 * @param id internal id for the load (e.g. "oil")
	 * @return the Load with that id or null
	 */
	public Load getLoad(String id) {
		return loads.get(id);
	}

	private void mapLoad(XmlLoad xmlload1) {
		Load mapLoad = loadMapper.mapLoad(xmlload1);
		loads.put(mapLoad.getId(), mapLoad);
	}

}
