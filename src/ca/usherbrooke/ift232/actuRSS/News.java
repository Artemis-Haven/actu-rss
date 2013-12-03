package ca.usherbrooke.ift232.actuRSS;

import java.util.Calendar;


/**
 * Actu-RSS
 * Couche : Model
 * Date de creation : 2013-10-30
 * Description : 
 *   Classe News
 *   Une news est determinee par un titre, une url, un auteur, une date, un contenu, 
 *   et des booleens marquant si la news a ete lue et/ou mise en favori. 
 *   
 * @author Benjamin Ferre
 * @author David Boas
 * @version 0.4
 */
public class News {
	
	//variables de classe
	private String title;
	private String url;
	private String author;
	private Calendar date;
	private String contents;
	private boolean read;
	private boolean favorite;
	private Feed feed;

	//constructeurs
		public News(){
			this.title = "";
			this.url = "";
			this.author = "";
			this.date = null;
			this.contents = "";
			this.read = false;
			this.favorite = false;
			this.feed = null;
		}
		
		/**
		 * @param title le titre de la news
		 * @param url l'url de la news
		 * @param author l'auteur de la news
		 * @param date la date de la news
		 * @param contents le contenu de la news
		 * @param read renseigne si la news a ete lue
		 * @param favorite renseigne si la news a ete classee en favori
		 */
		public News(String title, String url, String author, Calendar date,
				String contents, boolean read, boolean favorite) {
			this.title = title;
			this.url = url;
			this.author = author;
			this.date = date;
			this.contents = contents;
			this.read = read;
			this.favorite = favorite;
			this.feed = null;
		}
		
	//accesseurs
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getAuthor() {
		return author;
	}
	public Calendar getDate() {
		return date;
	}
	public String getContents() {
		return contents;
	}
	public boolean isRead() {
		return read;
	}
	public boolean isFavorite() {
		return favorite;
	}
	public Feed getFeed() {
		return feed;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	
	//methodes
	@Override
	public String toString() {
	    return "News [title = " + title + ", read = " + read + "favori = " + favorite + " contents = " + contents 
	    		+ ", url = " + url + ", author = " + author + "]";
	}
}
