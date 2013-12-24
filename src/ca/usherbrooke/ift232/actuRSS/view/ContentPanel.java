package ca.usherbrooke.ift232.actuRSS.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xml.sax.InputSource;

import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.controller.Controller;

/**
 * Cette classe est le panel de droite de l'application.
 * C'est lui qui affiche le contenu de la news.
 * Il reçoit ce contenu sous forme de HTML.
 * 
 * @author Rémi Patrizio
 *
 */
public class ContentPanel extends XHTMLPanel {

	String title;
	String date;
	String feed;
	String author;
	String content;
	String style;

	/**
	 * Définit les valeurs par défaut des différents champs
	 */
	public ContentPanel() {
		this.title = "";
		this.date = "";
		this.feed = "";
		this.author = "";
		this.content = "";
		this.style = "";
	}
	
	/**
	 * Prend tous les champs du contentPanel, construit
	 * la page HTML au complet et l'envoie au panel
	 * pour y être affiché
	 */
	public void display() {
		
			try {
				style = this.getStyleFromFile();
			} catch (IOException e1) {
				e1.printStackTrace();
				style = "src/resources/default.css";
			}
		
		String fullHTML = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\"> "
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

	/**
	 * Récupère le style CSS contenu dans le fichier
	 * dont le nom est dans le fichier de config
	 * 
	 * @return chaine de caractere contenant le style CSS
	 * @throws IOException
	 */
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

	/**
	 * Remplit les champs du contentPanel avec les valeurs
	 * d'une news passée en paramètre
	 * 
	 * @param news
	 * @param feedName
	 */
	public void setContentPanel(News news, String feedName) {
		this.title = news.getTitle();
		if (news.getDate() != null) {
			DateFormat dateFormat = new SimpleDateFormat("'Le 'd MMM 'à' HH:mm", Locale.CANADA_FRENCH);
			this.date = dateFormat.format(news.getDate().getTime());
		} else {
			this.date = "";
		}
		this.feed = feedName;
		this.author = news.getAuthor();
		this.content = news.getContents();
		this.style = "";
	}

}
