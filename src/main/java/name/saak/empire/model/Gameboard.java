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
import name.saak.empire.mapper.CityMapper;
import name.saak.empire.mapper.LoadMapper;
import name.saak.empire.mapper.RowMapper;
import name.saak.empire.schema.XmlGame;
import name.saak.empire.schema.XmlRow;
import name.saak.empire.schema.XmlRowMilepost;
import name.saak.empire.util.ImageRegistry;

@Component
public class Gameboard {

	private Logger logger = LoggerFactory.getLogger(Gameboard.class);

	@Getter(AccessLevel.NONE)
	private XmlGame game;

	/**
	 * Die Points sind die logischen Koordinaten. In der Summe müssen diese imm
	 * durch 2 teilbar sein.<br>
	 * (p.x + p.y % 2) == 0
	 */
	private HashMap<Point, Milepost> mileposts = new HashMap<>();

	private LoadMapper loadMapper;

	private RowMapper rowMapper;

	private HashMap<String, Load> loads = new HashMap<>();

	private CityMapper cityMapper;

	private ImageRegistry imageRegistry;

	@Autowired
	public Gameboard(CityMapper cityMapper, LoadMapper loadMapper, RowMapper rowMapper, ImageRegistry imageRegistry) {
		this.cityMapper = cityMapper;
		this.loadMapper = loadMapper;
		this.rowMapper = rowMapper;
		this.imageRegistry = imageRegistry;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlGame.class.getPackageName(), this.getClass().getClassLoader());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			URL url = this.getClass().getResource("/maps/america.xml");
			InputStream inputStream = url.openConnection().getInputStream();
			game = (XmlGame) jaxbUnmarshaller.unmarshal(inputStream);
			inputStream.close();
			mapModel();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}
	}

	private void mapModel() {
		int rowIndex = 0;
		boolean firstRow = true;
		List<XmlRow> row = game.getMap().getRow();
		for (XmlRow r : row) {
			rowMapper.mapRow(mileposts, r, rowIndex++, firstRow);
			firstRow = false;
		}

		game.getLoads().getLoad().forEach(l -> loadMapper.mapLoad(loads, l));
		game.getCities().getSmallCityOrMediumCityOrMajorCity().forEach(c -> cityMapper.mapCity(mileposts, c));
	}

	private Dimension calcSize() {
		if (game == null) {
			logger.warn("No map loaded");
			return new Dimension(0, 0);
		}

		int rows = game.getMap().getRow().size();
		int columns = 0;
		int start;
		int cols;

		for (XmlRow r : game.getMap().getRow()) {
			start = Optional.ofNullable(r.getClearOrMountain().getFirst().getValue().getStart()).orElse(0);
			cols = 0;
			for (JAXBElement<XmlRowMilepost> rm : r.getClearOrMountain()) {
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

	/**
	 * @return ImageRegistry for icons
	 */
	public ImageRegistry getImageRegistry() {
		return imageRegistry;
	}
}
