package ca.usherbrooke.ift232.actuRSS.bdd;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;
import ca.usherbrooke.ift232.actuRSS.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DatabaseManager {

	/**
	 * Variable priv�e
	 */
	private Database db;

	/**
	 * Constructeur
	 * 
	 * @param db
	 *            : classe dataBase
	 */
	public DatabaseManager(Database db) {
		this.db = db;
		connect();
		createDB();
	}

	/**
	 * Permet d'effacer la base de donn�e existante.
	 */
	public void deleteDB() {

		db.updateValue("drop table if exists News");
		db.updateValue("drop table if exists Feed");
		db.updateValue("drop table if exists Category");

	}

	/**
	 * Cr�� les tables Feed, News et Category
	 */
	public void createDB() {

		db.updateValue("CREATE TABLE if not exists Feed("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
				+ "URL    TEXT ," + "Title  TEXT ," + "ID_Category  INTEGER ,"
				+ "FOREIGN KEY (ID_Category) REFERENCES Category(ID)"
				+ ");"
				+

				"CREATE TABLE if not exists News("
				+ "URL        TEXT PRIMARY KEY NOT NULL ,"
				+ "Title      TEXT ,"
				+ "Author     TEXT ,"
				+ "Date_News  TEXT ,"
				+ // YYYY-MM-DD HH:MM:SS.SSS
				"Contents   TEXT ," + "Read       INTEGER ,"
				+ "Favorite   INTEGER ," + "ID_Feed    INTEGER ,"
				+ "FOREIGN KEY (ID_Feed) REFERENCES Feed(ID)" + ");" +

				"CREATE TABLE if not exists Category("
				+ "	ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
				+ "	Name  TEXT )");
	}

	/**
	 * Ins�re tout les objets du mod�le dans la base de donn�es
	 * 
	 * @param listCategory
	 *            Liste � inserer dans la Base de donn�es
	 */
	public void insertObjetToDB(ArrayList<Category> listCategory) {

		/*for (int i = 0; i < listCategory.size(); i++) {

			for (int j = 0; j < listCategory.get(i).getListFeed().size(); j++) {

				for (int k = 0; k < listCategory.get(i).getListFeed().get(j)
						.getListNews().size(); k++) {

					insertNews(listCategory.get(i).getListFeed().get(j)
							.getListNews().get(k), listCategory.get(i)
							.getListFeed().get(j).getId());

				}
				insertFeed(listCategory.get(i).getListFeed().get(j),
						listCategory.get(i).getId());
			}

			insertCategory(listCategory.get(i));

		}*/
		
		for(Category cat : listCategory) {
			for(Feed f : cat.getListFeed()) {
				for(News n : f.getListNews()) {
					insertNews(n, f.getId());
				}
				insertFeed(f, cat.getId());
			}
			insertCategory(cat);
		}

	}

	/**
	 * Ins�re une cat�gorie dans la BDD
	 * 
	 * @param category
	 *            Objet contenant la description de la cat�gorie � ins�rer
	 */

	public void insertCategory(Category category) {
		try {
			PreparedStatement prstmt = db.connection
					.prepareStatement("INSERT INTO Category (Name) VALUES(?)");
			prstmt.setString(1, category.getName());
			prstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ins�re un flux dans la BDD
	 * 
	 * @param feed
	 *            Objet contenant la description du flux � ins�rer
	 * @param ID_Category
	 *            Identifiant de la cat�gorie correspondant au flux
	 */
	public void insertFeed(Feed feed, int ID_Category) {
		try {
			PreparedStatement prstmt = db.connection
					.prepareStatement("INSERT INTO Feed (Title, URL, ID_Category) VALUES(?, ?, ?)");
			prstmt.setString(1, feed.getTitle());
			prstmt.setString(2, feed.getUrl());
			prstmt.setInt(3, ID_Category);
			prstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ins�re une news dans la BDD
	 * 
	 * @param News
	 *            Objet contenant la description de la news � ins�rer
	 * @param ID_Feed
	 *            Identifiant du flux correspondant � la news
	 */
	public void insertNews(News news, int ID_Feed) {
		int read = 0;
		int favorite = 0;
		System.out.println();
		String date = "";
		Calendar cal = news.getDate();
		date = DatabaseUtil.ConvertCalendarToString(cal);

		if (news.isRead())
			read = 1;
		if (news.isFavorite())
			favorite = 1;
		try {

			PreparedStatement prstmt = db.connection
					.prepareStatement("INSERT INTO News (URL, Title, Author, Date_News,"
							+ " Contents, Read, Favorite, ID_Feed) "
							+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			prstmt.setString(1, news.getUrl());
			prstmt.setString(2, news.getTitle());
			prstmt.setString(3, news.getAuthor());
			prstmt.setString(4, date);
			prstmt.setString(5, news.getContents());
			prstmt.setInt(6, read);
			prstmt.setInt(7, favorite);
			prstmt.setInt(8, ID_Feed);
			prstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param date
	 *            Date � convertir
	 * @return La date en chaine de caract�res
	 */
	/*
	 * private String ConvertCalendarToString(Calendar date) { StringBuffer
	 * dateToReturn = new StringBuffer();
	 * 
	 * 
	 * dateToReturn.append(date.get(Calendar.YEAR)); dateToReturn.append("-");
	 * String month = null; int mo = date.get(Calendar.MONTH) + 1; if(mo < 10) {
	 * month = "0" + mo; } else { month = "" + mo; } dateToReturn.append(month);
	 * 
	 * dateToReturn.append("-");
	 * 
	 * String day = null; int dt = date.get(Calendar.DATE); if(dt < 10) { day =
	 * "0" + dt; } else { day = "" + dt; } dateToReturn.append(" ");
	 * 
	 * dateToReturn.append(date.get(Calendar.HOUR_OF_DAY));
	 * dateToReturn.append(":"); dateToReturn.append(date.get(Calendar.MINUTE));
	 * dateToReturn.append(":"); dateToReturn.append(date.get(Calendar.SECOND));
	 * 
	 * return dateToReturn.toString(); }
	 * 
	 * public Calendar convertStringToCalendar(String date) { //YYYY-MM-DD
	 * HH:MM:SS.SSS Calendar calendar = Calendar.getInstance();
	 * 
	 * int year = Integer.parseInt(date.substring(0, 3)); int month =
	 * Integer.parseInt(date.substring(4, 5)); int day =
	 * Integer.parseInt(date.substring(6, 7)); int hourOfDay =
	 * Integer.parseInt(date.substring(9, 10)); int minute =
	 * Integer.parseInt(date.substring(11, 12)); int second =
	 * Integer.parseInt(date.substring(13, 14));
	 * 
	 * calendar.set(year, month, day, hourOfDay, minute, second);
	 * 
	 * return calendar; }
	 */

	/**
	 * Conversion de la BDD en Objet
	 */

	/**
	 * 
	 * @return Une liste contenant toutes les cat�gories
	 */
	public ArrayList<Category> getAllCategories() throws SQLException {

		ArrayList<Category> listCategory = new ArrayList<Category>();
		Category category;

		ArrayList<Feed> listFeed = new ArrayList<Feed>();

		ResultSet resultat = db.getResultOf("SELECT * FROM Category;");
		try {
			while (resultat.next()) {

				category = new Category(resultat.getInt("ID"),
						resultat.getString("Name"));

				listFeed = getAllFeedFromCategory(category);

				category.setListFeed(listFeed);

				listCategory.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCategory;
	}

	/**
	 * 
	 * @param category
	 *            : Objet Category
	 * @return une liste de Feed
	 */
	private ArrayList<Feed> getAllFeedFromCategory(Category category) {

		ArrayList<Feed> list = new ArrayList<Feed>();
		ArrayList<News> listNews = new ArrayList<News>();

		Feed feed;

		try {
			PreparedStatement prstmt = db.connection
					.prepareStatement("SELECT * FROM Feed WHERE ID_Category=?");
			prstmt.setInt(1, category.getId());
			ResultSet result = prstmt.executeQuery();

			while (result.next()) {

				feed = new Feed(result.getInt("ID"), result.getString("Title"),
						result.getString("URL"));
				listNews = getAllNewsFromFeed(feed);
				feed.setListNews(listNews);

				list.add(feed);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * @param feed
	 *            Objet Feed
	 * @return La liste de toutes les news correspondant � un flux
	 */
	private ArrayList<News> getAllNewsFromFeed(Feed feed) throws SQLException {
		ArrayList<News> list = new ArrayList<News>();

		boolean read;
		boolean favorite;
		Calendar date;

		PreparedStatement prstmt = db.connection
				.prepareStatement("SELECT * FROM News WHERE ID_Feed=?");
		prstmt.setInt(1, feed.getId());
		ResultSet resultat = prstmt.executeQuery();

		News news;
		while (resultat.next()) {

			read = (resultat.getInt("Read") == 1);
			favorite = (resultat.getInt("Favorite") == 1);
			date = DatabaseUtil.convertStringToCalendar(resultat
					.getString("Date_News"));
			news = new News(resultat.getString("Title"),
					resultat.getString("URL"), resultat.getString("Author"),
					date, resultat.getString("Contents"), read, favorite);
			news.setFeed(feed);
			list.add(news);
		}
		return list;
	}

	/**
	 * 
	 * @return Une liste contenant tout les flux
	 */
	/*
	 * public ArrayList<Feed> getAllFeeds() throws SQLException {
	 * 
	 * int id; String title; String url; List<News> listNews = new
	 * ArrayList<News>(); int ID_category; Category category;
	 * 
	 * ResultSet resultat = db.getResultOf("SELECT * FROM feed;");
	 * ArrayList<Feed> listFeed = new ArrayList<Feed>(); Feed feed; try { while
	 * (resultat.next()) { id = resultat.getInt("ID"); title =
	 * resultat.getString("Title"); url = resultat.getString("Url"); feed = new
	 * Feed(id, title, url);
	 * 
	 * listNews = this.getAllNewsFromFeed(feed); feed.setListNews(listNews);
	 * 
	 * ID_category = resultat.getInt("ID_Category"); category =
	 * GetCategory(ID_category); feed.setCategory(category); listFeed.add(feed);
	 * } } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return listFeed; }
	 */

	/*
	 * 
	 * public void deleteCategory(int id) { String requete =
	 * "DELETE from Category where ID="+ id +""; db.updateValue(requete); }
	 * 
	 * public void deleteNews(int id) { String requete =
	 * "DELETE from News where ID="+ id +""; db.updateValue(requete); }
	 * 
	 * public void deleteFeed(int id) { String requete =
	 * "DELETE from Feed where ID="+ id +""; db.updateValue(requete); }
	 */

	public void clearCategory(Category category) {
		if (category.getListFeed().isEmpty()) {
			try {
				PreparedStatement prstmt = db.connection
						.prepareStatement("DELETE from Category where ID= ?");
				prstmt.setInt(1, category.getId());
				prstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void clearFeed(Feed feed) {
		if (feed.getListNews().isEmpty()) {
			try {
				PreparedStatement prstmt = db.connection
						.prepareStatement("DELETE from Feed where ID= ?");
				prstmt.setInt(1, feed.getId());
				prstmt.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void clearNews(News news) {
		try {
			PreparedStatement prstmt = db.connection
					.prepareStatement("DELETE from News where URL= ?");
			prstmt.setString(1, news.getUrl());
			prstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public void returnCategory() {
	 * 
	 * String requete = "SELECT * FROM Category;"; ResultSet rs =
	 * db.getResultOf(requete); try { while ( rs.next() ) { int id =
	 * rs.getInt("ID"); String nom = rs.getString("Name"); System.out.println(
	 * "ID = " + id ); System.out.println( "Nom = " + nom );
	 * System.out.println(); } } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

	/**
	 * Supprime les donnees de la Bdd
	 * 
	 * @author Vincent Chataignier
	 */
	public void clearDB() {
		String requete = "DELETE FROM News;";
		db.updateValue(requete);
		requete = "DELETE FROM Feed;";
		db.updateValue(requete);
		requete = "DELETE FROM Category;";
		db.updateValue(requete);
	}

	// Récupère toutes les news d'un feed
	/*
	 * public ArrayList<News> getAllNewsFromFeed(Feed feed) throws SQLException
	 * {
	 * 
	 * PreparedStatement prstmt =
	 * db.connection.prepareStatement("SELECT * FROM news WHERE feed=?");
	 * prstmt.setInt(1, feed.getId()); ResultSet resultat =
	 * prstmt.executeQuery(); ArrayList<News> list = new ArrayList<News>(); News
	 * news; while (resultat.next()) { news = new News(resultat.getInt(1),
	 * resultat.getString(2), resultat.getString(4), resultat.getDate(5),
	 * resultat.getString(6), resultat.getString(3), resultat.getInt(8)==1,
	 * resultat.getInt(9)==1); list.add(news); } return list; }
	 */

	/*
	 * // Récupère toutes les news d'un feed public ArrayList<Feed>
	 * getAllFeeds() throws SQLException { ResultSet resultat =
	 * db.getResultOf("SELECT * FROM feed;"); ArrayList<Feed> list = new
	 * ArrayList<Feed>(); Feed feed; while (resultat.next()) { feed = new
	 * Feed(resultat.getString(2), resultat.getString(3), resultat.getInt(1));
	 * list.add(feed); } return list; }
	 * 
	 * // Envoie la news X du feed Y public void addNewsFromFeed(News news, Feed
	 * feed){ try { if(news.getId()==-1){ PreparedStatement prstmt =
	 * db.connection
	 * .prepareStatement("INSERT INTO news VALUES( null, ?, ?, ?, ?, ?, ?, ?, ? )"
	 * ); prstmt.setString(1, news.getTitle()); prstmt.setString(2,
	 * news.getUrl()); prstmt.setString(3, news.getAuthor());
	 * prstmt.setString(4, news.getDate().toString()); prstmt.setString(5,
	 * news.getContent()); prstmt.setInt(6, feed.getId()); prstmt.setInt(7,
	 * news.isRead() ? 1 : 0); prstmt.setInt(8, news.isStarred() ? 1 : 0);
	 * prstmt.execute(); } else { PreparedStatement prstmt =
	 * db.connection.prepareStatement(
	 * "UPDATE news SET title=? , url=? , author=? , date=? , content=? , feed=? , read=? , starred=? WHERE id=?"
	 * ); prstmt.setString(1, news.getTitle()); prstmt.setString(2,
	 * news.getUrl()); prstmt.setString(3, news.getAuthor());
	 * prstmt.setString(4, news.getDate().toString()); prstmt.setString(5,
	 * news.getContent()); prstmt.setInt(6, feed.getId()); prstmt.setInt(7,
	 * news.isRead() ? 1 : 0); prstmt.setInt(8, news.isStarred() ? 1 : 0);
	 * prstmt.setInt(9, news.getId()); prstmt.execute(); }
	 * 
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * // Envoie un feed public void addFeed(Feed feed){ try { PreparedStatement
	 * prstmt =
	 * db.connection.prepareStatement("INSERT INTO feed VALUES(null, ?, ? )");
	 * prstmt.setString(1, feed.getTitle()); prstmt.setString(2, feed.getUrl());
	 * prstmt.execute(); } catch (SQLException e) { e.printStackTrace(); } }
	 */

	public void connect() {
		db.connect();
	}

	public void disconnect() {
		db.disconnect();
	}

	public void emptyNews() {
		db.updateValue("DELETE FROM news;");
	}

	public Database getDb() {
		return db;
	}

}