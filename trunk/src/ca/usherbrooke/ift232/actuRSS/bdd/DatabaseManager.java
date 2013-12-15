package ca.usherbrooke.ift232.actuRSS.bdd;

import ca.usherbrooke.ift232.actuRSS.Category;
import ca.usherbrooke.ift232.actuRSS.Feed;
import ca.usherbrooke.ift232.actuRSS.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * Cette classe est un DAO, elle permet d'effectuer des
 * requêtes dans la base de donnée. Principalement 2 cas
 * d'utilisation : <br/> 
 * (1) Certaines méthodes de cette
 * classe prennent des objets (Feed, News ou Category) en 
 * entrée et effectuent des requêtes en BdD, ou bien <br/>
 * (2) Effectuent des requêtes en BdD et créent des objets.
 * 
 * @author Leucistic
 *
 */
public class DatabaseManager {

	/**
	 * Variable priv�e
	 */
	private Database db;

	/**
	 * Constructeur
	 * 
	 * @param db : La Database qui sera utilisée
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
	 * Cr�� les tables vides Feed, News et Category dans la BdD
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
	 * @param listCategory : Liste � inserer dans la Base de donn�es
	 */
	public void insertObjetToDB(ArrayList<Category> listCategory) {

		for (int i = 0; i < listCategory.size(); i++) {

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

		}

	}

	/**
	 * Ins�re une cat�gorie dans la BDD
	 * 
	 * @param category : Objet contenant la description de la cat�gorie � ins�rer
	 */

	public void insertCategory(Category category) {
		try {
			PreparedStatement prstmt;
			if (category.getId() < 0) {
				prstmt = db.connection
						.prepareStatement("INSERT INTO Category (Name) VALUES (?)");
				prstmt.setString(1, category.getName());
			} else {
				prstmt = db.connection
						.prepareStatement("INSERT INTO Category (ID, Name) VALUES (?, ?)");
				prstmt.setLong(1, category.getId());
				prstmt.setString(2, category.getName());
			}
			prstmt.execute();
			prstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ins�re un flux dans la BDD
	 * 
	 * @param feed : Objet contenant la description du flux � ins�rer
	 * @param ID_Category : Identifiant de la cat�gorie correspondant au flux
	 */
	public void insertFeed(Feed feed, int ID_Category) {
		try {
			PreparedStatement prstmt;
			if(feed.getId() < 0) {
				prstmt = db.connection
						.prepareStatement("INSERT INTO Feed (Title, URL, ID_Category) VALUES(?, ?, ?)");
				prstmt.setString(1, feed.getTitle());
				prstmt.setString(2, feed.getUrl());
				prstmt.setInt(3, ID_Category);
			} else {
				prstmt = db.connection
						.prepareStatement("INSERT INTO Feed (ID, Title, URL, ID_Category) VALUES(?, ?, ?, ?)");
				prstmt.setLong(1, feed.getId());
				prstmt.setString(2, feed.getTitle());
				prstmt.setString(3, feed.getUrl());
				prstmt.setInt(4, ID_Category);
			}
			prstmt.execute();
			prstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ins�re une news dans la BDD
	 * 
	 * @param News : Objet contenant la description de la news � ins�rer
	 * @param ID_Feed : Identifiant du flux correspondant � la news
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
			prstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupérer tout le contenu de la BdD. On récupère
	 * d'abord la liste des catégories, puis pour chaque catégorie on
	 * appelle getAllFeedFromCategory.
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
	 * Récupérer tout les flux de la BdD. On récupère
	 * d'abord la liste des flux, puis pour chaque flux on
	 * appelle getAllNewsFromFeed.
	 * 
	 * @param category : Objet Category
	 * @return une liste de Feed appartenant à la catégorie en paramètre
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
				feed.setNameCategory(category.getName());
				listNews = getAllNewsFromFeed(feed);
				feed.setListNews(listNews);

				list.add(feed);
			}

			prstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * Récupérer la liste de toutes les news dans la BdD appartenant
	 * au flux passé en paramètre
	 * 
	 * @param feed : Objet Feed
	 * @return La liste de toutes les news correspondant au flux en paramètre
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
			list.add(news);
		}

		prstmt.close();
		return list;
	}

	
	/**
	 * Efface la catégorie de la BdD dont l'identifiant
	 * est passé en paramètre
	 * @param category : l'id de la catégorie à supprimer
	 */
	public void clearCategory(Category category) {
		if (category.getListFeed().isEmpty()) {
			try {
				PreparedStatement prstmt = db.connection
						.prepareStatement("DELETE from Category where ID= ?");
				prstmt.setInt(1, category.getId());
				prstmt.execute();
				prstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Efface le flux de la BdD dont l'identifiant
	 * est passé en paramètre
	 * @param feed : l'id du flux à supprimer
	 */
	public void clearFeed(Feed feed) {
		if (feed.getListNews().isEmpty()) {
			try {
				PreparedStatement prstmt = db.connection
						.prepareStatement("DELETE from Feed where ID= ?");
				prstmt.setInt(1, feed.getId());
				prstmt.execute();
				prstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Efface la news de la BdD dont l'url
	 * est passé en paramètre
	 * 
	 * @param news : l'url de la news à supprimer
	 */
	public void clearNews(News news) {
		try {
			PreparedStatement prstmt = db.connection
					.prepareStatement("DELETE from News where URL= ?");
			prstmt.setString(1, news.getUrl());
			prstmt.execute();
			prstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Supprime toutes les données de la BdD sans supprimer
	 * les tables
	 * 
	 */
	public void clearDB() {
		String requete = "DELETE FROM News;";
		db.updateValue(requete);
		requete = "DELETE FROM Feed;";
		db.updateValue(requete);
		requete = "DELETE FROM Category;";
		db.updateValue(requete);
	}


	/**
	 * Lance la connexion à la BdD
	 */
	public void connect() {
		db.connect();
	}

	/**
	 * Termine la connexion avec la BdD
	 */
	public void disconnect() {
		db.disconnect();
	}

}