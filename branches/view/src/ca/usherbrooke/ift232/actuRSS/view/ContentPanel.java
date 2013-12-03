package ca.usherbrooke.ift232.actuRSS.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.w3c.dom.Document;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xml.sax.InputSource;

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

	public void display() 
	{


		/*String title = "Microsoft se paye l'iPad Air dans deux nouvelles vidéos [iGen]";
		String date = "26 Nov 2013 à 23:36";
		String feed = "MacGeneration";
		String author = "Florian Innocente";
		String content = "<p><strong>À l'approche des fêtes de Noël, Microsoft remet le couvert dans ses comparatifs entre sa tablette et celle d'Apple</strong>. Cette fois ce sont les Surface 2 et iPad Air qui sont opposés, d'abord <a href=\"http://www.microsoft.com/surface/en-us/products/compare\">au fil d'une page</a> avec des arguments connus (port USB, présence de Flash et d'Office, etc). </p><br /> <br /> <figure><img src=\"http://img.staticigen.com/2013/11/macgpic-1385508685-3912987173708-sc-op.jpg\" class=\"centre\" alt=\"\" /><figcaption></figcaption></figure><br /> <br /> <p>Ensuite, la compétition se poursuit au travers de deux nouvelles vidéos. La première met l'accent sur le support rétractable intégré à la Surface qui permet de la tenir face à l'utilisateur. Voilà la Surface devenue un parfait compagnon pour la cuisine (c'est très sérieux) puisqu'il sera possible de lire plus facilement ses recettes et même de balayer les pages d'un geste de la main sans avoir à toucher l'écran de ses doigts sales.</p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/XsknmA96Jv0\" frameborder=\"0\"></iframe></p><br /> <br /> <p>Quand on pense qu'Apple dans son dernier clip montre des utilisateurs d'iPad au sommet d'une éolienne, au milieu des vignes, en pleine intervention de pompiers ou sous les fonds marins, c'est vraiment faire bien peu de cas du <i>vrai</i> quotidien des utilisateurs de tablettes.</p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/B8Le9wvoY00?list=PLHFlHpPjgk713fMv5O4s4Fv7k6yTkXwkV\" frameborder=\"0\"  ></iframe></p><br /> <br /> <p>La seconde vidéo en revanche insiste à nouveau sur une lacune de plus en plus criante de l'iPad et plus particulièrement de son iOS : l'absence de sessions utilisateurs. Pour distinguer par exemple le contenu applicatif et les réglages des parents de ceux de leurs enfants. Là-dessus on peut difficilement donner tort à Microsoft. </p><br /> <br /> <p><iframe width=\"480\" height=\"270\" src=\"http://www.youtube.com/embed/knSd7-i9u1c\" frameborder=\"0\"  ></iframe></p><br /><br /><a href=\"http://www.igen.fr/ipad/microsoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067#commentaires\">Voir les commentaires et réagir</a><br /><br /> <p style=\"font-size: 78%;\">2013 - <a href=\"http://www.igen.fr/ipad/microsoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067\"><strong>\"Microsoft se paye l'iPad Air dans deux nouvelles vidéos\"</strong></a> publié sur <a href=\"http://www.igen.fr\">iGeneration</a> par <strong>Florian Innocente</strong>.</p><img width='1' height='1' src='http://feed.macg.co/c/302/f/435189/s/3422e188/sc/23/mf.gif' border='0'/><br clear='all'/><div class='mf-viral'><table border='0'><tr><td valign='middle'><a href=\"http://share.feedsportal.com/share/twitter/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/twitter.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/facebook/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/facebook.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/linkedin/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/linkedin.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/gplus/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/googleplus.png\" border=\"0\" /></a> <a href=\"http://share.feedsportal.com/share/email/?u=http%3A%2F%2Fwww.igen.fr%2Fipad%2Fmicrosoft-se-paye-l-ipad-air-dans-deux-nouvelles-videos-109067&amp;t=Microsoft+se+paye+l%27iPad+Air+dans+deux+nouvelles+vid%C3%A9os+%5BiGen%5D\" target=\"_blank\"><img src=\"http://res3.feedsportal.com/social/email.png\" border=\"0\" /></a></td><td valign='middle'></td></tr></table></div><br/><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/1/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/1/rc.img\" border=\"0\"/></a><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/2/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/2/rc.img\" border=\"0\"/></a><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/3/rc.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/sc/23/rc/3/rc.img\" border=\"0\"/></a><br/><br/><a href=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2.htm\"><img src=\"http://da.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2.img\" border=\"0\"/></a><img width=\"1\" height=\"1\" src=\"http://pi.feedsportal.com/r/180265059809/u/192/f/435189/c/302/s/3422e188/a2t.img\" border=\"0\"/>";
		 */			

		try {
			style = this.getStyleFromFile();
		} catch (Exception e) {
			System.out.println("Impossible de trouver la feuille de style.");
		}

		String fullHTML = "<html><head><style type=\"text/css\">" + 
				style +
				"</style></head><body><div class=\"header\"><div class=\"title\">" +
				title +
				"</div><div class=\"feedName\">" +
				feed +
				"</div><div class=\"author\">Par " +
				author +
				"</div><div class=\"date\">Le " +
				date +
				"</div></div><div class=\"article\">" +
				content +
				"</div></body></html>";



		try {
			InputSource is = new InputSource(new BufferedReader(new StringReader(fullHTML)));
			Document dom = XMLResource.load(is).getDocument();
			this.setDocument(dom);
		} catch (Exception e) {
			System.out.println("Problème lors de l'affichage de la news : elle ne respectait pas le format HTML.");
		}

	}

	public String getStyleFromFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(Controller.properties.getProperty("CSS Style")));
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

	public void setContentPanel(String title, GregorianCalendar dateG, /*String feed,*/ String author, String content, String style)
	{


		this.date = ""+ dateG.get(Calendar.DAY_OF_MONTH) + " "+ (dateG.get(Calendar.MONTH)+1) + " à " + dateG.get(Calendar.HOUR)+ ":"+ dateG.get(Calendar.MINUTE);

		this.title = title;
		//this.date = date;
		/*this.feed = feed;*/
		this.author = author;
		this.content = content;
		this.style = style;
	}

	public String getTitle() {return title;}
	public String getDate() {return date;}
	public String getFeed() {return feed;}
	public String getAuthor() {return author;}
	public String getContent() {return content;}
	public String getStyle() {return style;}

	public void setTitle(String title) {this.title = title;}
	public void setDate(String date) {this.date = date;}
	public void setFeed(String feed) {this.feed = feed;}
	public void setAuthor(String author) {this.author = author;}
	public void setContent(String content) {this.content = content;}
	public void setStyle(String style) {this.style = style;}
}
