package edu.bsu.cs222.boozepicker;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebReader {

	private final DayOfWeek[] DAYS_OF_WEEK = DayOfWeek.values();
	private Document document;
	private HashMap<DayOfWeek, List<Special>> specialsMap = new HashMap<DayOfWeek, List<Special>>();
	private List<Special> specialsList;

	public WebReader(String filePath, String webDocument) throws IOException {
		File file = new File(filePath);
		document = Jsoup.parse(file, "UTF-8", webDocument);
	}

	public HashMap<DayOfWeek, List<Special>> getNewSpecials() {
		Element element = document.getElementById("main");
		List<Element> elementsList = element.getElementsByTag("ul");
		for (int i = 2; i < elementsList.size(); i++) {
			List<Element> itemsList = elementsList.get(i).getElementsByTag("li");
			specialListMaker(itemsList);
			specialsMap.put(DAYS_OF_WEEK[i - 2], specialsList);
		}
		return specialsMap;
	}

	private void specialListMaker(List<Element> itemsList) {
		specialsList = new ArrayList<Special>();
		for (int j = 0; j < itemsList.size(); j++) {
			String info = itemsList.get(j).text();
			if (info.contains("$")) {
				makeSpecial(info);
			}
		}
	}

	private void makeSpecial(String info) {
		Special.Builder builder = new Special.Builder();
		if (info.contains("*")) {
			builder.setPriceOfAlcohol(info.substring(1, info.indexOf(" ") - 1));
		} 
		else {
			builder.setPriceOfAlcohol(info.substring(1, info.indexOf(" ")));
		}
		builder.setNameOfAlcohol(info.substring(info.indexOf(" ") + 1));
		builder.setTypeOfAlcohol("Unkown");
		specialsList.add(builder.build());
	}
}