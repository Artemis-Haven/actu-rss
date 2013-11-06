package ca.usherbrooke.ift232.actuRSS.model;

import java.util.Date;

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
 * @version 0.5
 */
public class News {
	private String title;
	private String url;
	private String author;
	private Date date;
	private String contents;
	private boolean read;
	private boolean favorite;
	private Feed feed;

	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getAuthor() {
		return author;
	}
	public Date getDate() {
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
	public void setDate(Date date) {
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
	
	@Override
	public String toString() {
	    return "News [title = " + title + ", contents = " + contents 
	    		+ ", url = " + url + ", author = " + author + ", date = " + date + "]";
	}
}
