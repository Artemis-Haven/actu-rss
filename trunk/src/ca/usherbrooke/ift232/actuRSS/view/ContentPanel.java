package ca.usherbrooke.ift232.actuRSS.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xml.sax.InputSource;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.Controller;

public class ContentPanel extends XHTMLPanel {

	String title;
	String date;
	String feed;
	String author;
	String content;
	String style;

	public ContentPanel() {
		this.title = "";
		this.date = "";
		this.feed = "";
		this.author = "";
		this.content = "";
		this.style = "";
	}
	
	public void display() {
		try {
			style = this.getStyleFromFile();
		} catch (Exception e) {
			System.out.println("Impossible de trouver la feuille de style.");
		}

		String fullHTML = ""
				+ "<html><head><style type=\"text/css\">"
				+ style
				+ "</style></head><body><div class=\"header\"><div class=\"title\">"
				+ title + "</div><div class=\"feedName\">" + feed
				+ "</div><div class=\"author\">Par " + author
				+ "</div><div class=\"date\">" + date
				+ "</div></div><div class=\"article\">" + content
				+ "</div></body></html>";

		try {
			InputSource is = new InputSource(new BufferedReader(
					new StringReader(fullHTML)));
			Document dom = XMLResource.load(is).getDocument();
			this.setDocument(dom);
		} catch (Exception e) {
			System.out.println("Problème lors de l'affichage de la news : elle ne respectait pas le format HTML.");
		}

	}

	public String getStyleFromFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				Controller.properties.getProperty("CSS Style")));
		String everything = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append('\n');
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}
		return everything;
	}

	public void setContentPanel(News news) {
		this.title = news.getTitle();
		if (news.getDate() != null) {
			DateFormat dateFormat = new SimpleDateFormat("'Le 'd MMM 'à' HH:mm", Locale.CANADA_FRENCH);
			this.date = dateFormat.format(news.getDate().getTime());
		} else {
			this.date = "";
		}
		if (news.getFeed() != null) {
			this.feed = news.getFeed().getTitle();
		} else {
			this.feed = "";
		}
		this.author = news.getAuthor();
		this.content = news.getContents();
		this.style = "";
	}




}
