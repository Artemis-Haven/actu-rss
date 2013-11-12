package ca.usherbrooke.ift232.actuRSS.bdd;


import ca.usherbrooke.ift232.actuRSS.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Category;
import model.Feed;
import model.News;


public class DatabaseManager {
	
	private Database db;

	public DatabaseManager(Database db) {
		this.db = db;
		connect();
	}
	
	// Efface toute la DB
	public void clearDB() {
		
		db.updateValue("drop table if exists News");
		db.updateValue("drop table if exists Feed");
		db.updateValue("drop table if exists Category");
		
	}
	
	// Créé les tables Feed, News et Category
		public void createDB() {
			
			//db.updateValue("create table if not exists Category(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nom  TEXT);");
			
			db.updateValue("CREATE TABLE if not exists Feed("+
								"ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
								"URL    TEXT ,"+
								"Title  TEXT ,"+
								"ID_Category  INTEGER ,"+
								"FOREIGN KEY (ID_Category) REFERENCES Category(ID)"+
							");"+
							
							"CREATE TABLE if not exists News("+
								"URL        TEXT PRIMARY KEY NOT NULL ,"+
								"Title      TEXT ,"+
								"Author     TEXT ,"+
								"Date_News  NUMERIC ,"+
								"Contents   TEXT ,"+
								"Read       INTEGER ,"+
								"Favorite   INTEGER ,"+
								"ID_Feed    INTEGER ,"+
								"FOREIGN KEY (ID_Feed) REFERENCES Feed(ID)"+
							");"+
							
							"CREATE TABLE if not exists Category("+
							"	ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"+
							"	Name  TEXT )");
			
			
			//db.updateValue("create table if not exists Feed(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, URL TEXT, Nom TEXT, ID_Category INTEGER, FOREIGN KEY (ID_Category) REFERENCES Category(ID));");
			//db.updateValue("create table if not exists News(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Titre TEXT, URL TEXT, Auteur TEXT, Date_News NUMERIC, Contenu TEXT, Lu INTEGER, Favori INTEGER , ID_feed INTEGER, FOREIGN KEY (ID_feed) REFERENCES Feed(ID));");
			
			
		}
	
		
		/**
		 * Insére tout les objets du modèle dans la base de données
		 * @param listCategory Liste à inserer dans la Base de données
		 */
		public void insertObjetToDB(ArrayList<Category> listCategory)
		{
			
			for(int i=0;i<listCategory.size();i++)
			{						
				for(int j = 0; j < listCategory.get(i).getListFeed().size(); j++)
				{
					for(int k = 0; k < listCategory.get(i).getListFeed().get(j).getListNews().size(); k++)
					{
						insertNews(listCategory.get(i).getListFeed().get(j).getListNews().get(k), listCategory.get(i).getListFeed().get(j).getId());
					}
					insertFeed(listCategory.get(i).getListFeed().get(j), listCategory.get(i).getId());
				}
				insertCategory(listCategory.get(i));
			}
						
		}
		
		
		/**
		 * Insére une catégorie dans la BDD
		 * @param category Objet contenant la description de la catégorie à insérer
		 */
		public void insertCategory(Category category)
		{
			try {
	            PreparedStatement prstmt = db.connection.prepareStatement("INSERT INTO Category (Name) VALUES(?)");
	            prstmt.setString(1, category.getName());
	            prstmt.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		
		/**
		 * Insére un flux dans la BDD
		 * @param feed Objet contenant la description du flux à insérer
		 * @param ID_Category Identifiant de la catégorie correspondant au flux
		 */
		public void insertFeed(Feed feed, int ID_Category)
		{
			try {
	            PreparedStatement prstmt = db.connection.prepareStatement("INSERT INTO Feed (Title, URL, ID_Category) VALUES(?, ?, ?)");
	            prstmt.setString(1, feed.getTitle());
	            prstmt.setString(2, feed.getUrl());
	            prstmt.setInt(3, ID_Category);
	            prstmt.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		
		/**
		 * Insére une news dans la BDD
		 * @param News Objet contenant la description de la news à insérer
		 * @param ID_Feed Identifiant du flux correspondant à la news
		 */
		public void insertNews(News news, int ID_Feed)
		{
			int read = 0;
			int favorite = 0;
			
			if (news.isRead())
				read =1;
			if (news.isFavorite())
				favorite = 1;
				
			try {
	            PreparedStatement prstmt = db.connection.prepareStatement("INSERT INTO News (URL, Title, Author, Date_News, Contents, Read, Favorite, ID_Feed) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
	            prstmt.setString(1, news.getUrl());
	            prstmt.setString(2, news.getTitle());
	            prstmt.setString(3, news.getAuthor());
	            prstmt.setDate(4, (java.sql.Date) news.getDate());
	            prstmt.setString(5, news.getContents());
	            prstmt.setInt(6, read);
	            prstmt.setInt(7, favorite);
	            prstmt.setInt(8, ID_Feed);
	            prstmt.execute();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	
	/*public void insertFeed(String url, String nom, Category category)
	{
		String requete = "SELECT * FROM Category WHERE ID="+category.getId()+";";
		ResultSet rs = db.getResultOf(requete);
		try {
			if(rs.next())
			{
				String requeteInsert = "INSERT INTO Feed (Url, Nom, ID_Category) " +
		                "VALUES ('"+ url +"', '"+ nom +"', "+ category.getId() +");";
				db.updateValue(requeteInsert);
			}
			else
			{
				this.insertCategory(category);
				this.insertFeed(url, nom, category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}*/

	
	/**
	 * Conversion de la BDD en Objet
	 */

	

    /**
     * 
     * @return Une liste contenant toutes les catégories
     */
	public ArrayList<Category> getAllCategories() throws SQLException {
		
		ArrayList<Category> listCategory = new ArrayList<Category>();
		Category category;

		ArrayList<Feed> listFeed = new ArrayList<Feed>();

		

				
		ResultSet resultat = db.getResultOf("SELECT * FROM Category;");
		try {
			while (resultat.next()) {
				

				category = new Category(resultat.getInt("ID"), resultat.getString("Name"));
				
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
	

	private ArrayList<Feed> getAllFeedFromCategory(Category category) {
		
		ArrayList<Feed> list = new ArrayList<Feed>();
		ArrayList<News> listNews = new ArrayList<News>();
		
		Feed feed;
		
		try {
			PreparedStatement prstmt = db.connection.prepareStatement("SELECT * FROM Feed WHERE ID_Category=?");
			prstmt.setInt(1, category.getId());
			ResultSet result = prstmt.executeQuery();
			
			while (result.next()) {
				
				feed = new Feed(result.getInt("ID"), result.getString("Title"), result.getString("URL"));
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
     * 
     * @return La liste de toutes les news correspondant à un flux
     */
		private ArrayList<News> getAllNewsFromFeed(Feed feed) throws SQLException {
			ArrayList<News> list = new ArrayList<News>();
			
			boolean read;
			boolean favorite;
			
			
			PreparedStatement prstmt = db.connection.prepareStatement("SELECT * FROM news WHERE URL=?");
			prstmt.setString(1, feed.getUrl());
			ResultSet resultat = prstmt.executeQuery();
			
			News news;
			while (resultat.next()) {
				
				read = (resultat.getInt("Read") == 1);
				favorite = (resultat.getInt("Favorite") == 1);
				
				
				news = new News(resultat.getString("Title"), resultat.getString("URL"), resultat.getString("Author"), resultat.getDate("Date_News"), resultat.getString("Contents"), read, favorite);
				list.add(news);
			}
			return list;
		}

	/**
     * 
     * @return Une liste contenant tout les flux
     */
	/*public ArrayList<Feed> getAllFeeds() throws SQLException {
		
		int id;
		String title;
		String url;
		List<News> listNews = new ArrayList<News>();
		int ID_category;
		Category category;
		
		ResultSet resultat = db.getResultOf("SELECT * FROM feed;");
		ArrayList<Feed> listFeed = new ArrayList<Feed>();
		Feed feed;
		try {
			while (resultat.next()) {
				id = resultat.getInt("ID");
				title = resultat.getString("Title");
				url = resultat.getString("Url");
				feed = new Feed(id, title, url);
				
				listNews = this.getAllNewsFromFeed(feed);  
				feed.setListNews(listNews);
				
				ID_category = resultat.getInt("ID_Category");
				category = GetCategory(ID_category);
				feed.setCategory(category);
				listFeed.add(feed);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listFeed;
	}*/
	

	
	/*
	
	public void deleteCategory(int id)
	{
		String requete = "DELETE from Category where ID="+ id +"";
		db.updateValue(requete);
	}
	
	public void deleteNews(int id)
	{
		String requete = "DELETE from News where ID="+ id +"";
		db.updateValue(requete);
	}
	
	public void deleteFeed(int id)
	{
		String requete = "DELETE from Feed where ID="+ id +"";
		db.updateValue(requete);
	}
	
	*/
	public void returnCategory()
	{
		
		String requete = "SELECT * FROM Category;";
		 ResultSet rs = db.getResultOf(requete);
	      try {
			while ( rs.next() ) {
			     int id = rs.getInt("ID");
			     String  nom = rs.getString("Name");
			     System.out.println( "ID = " + id );
			     System.out.println( "Nom = " + nom );
			     System.out.println();
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
// RÃ©cupÃ¨re toutes les news d'un feed
	/*public ArrayList<News> getAllNewsFromFeed(Feed feed) throws SQLException {
		
		PreparedStatement prstmt = db.connection.prepareStatement("SELECT * FROM news WHERE feed=?");
		prstmt.setInt(1, feed.getId());
		ResultSet resultat = prstmt.executeQuery();
		ArrayList<News> list = new ArrayList<News>();
		News news;
		while (resultat.next()) {
			news = new News(resultat.getInt(1), resultat.getString(2), resultat.getString(4), resultat.getDate(5), resultat.getString(6), resultat.getString(3), resultat.getInt(8)==1, resultat.getInt(9)==1);
			list.add(news);
		}
		return list;
	}*/
	
	/*// RÃ©cupÃ¨re toutes les news d'un feed
	public ArrayList<Feed> getAllFeeds() throws SQLException {
		ResultSet resultat = db.getResultOf("SELECT * FROM feed;");
		ArrayList<Feed> list = new ArrayList<Feed>();
		Feed feed;
		while (resultat.next()) {
			feed = new Feed(resultat.getString(2), resultat.getString(3), resultat.getInt(1));
			list.add(feed);
		}
		return list;
	}
	
	// Envoie la news X du feed Y
	public void addNewsFromFeed(News news, Feed feed){
		try {
			if(news.getId()==-1){
				PreparedStatement prstmt = db.connection.prepareStatement("INSERT INTO news VALUES( null, ?, ?, ?, ?, ?, ?, ?, ? )");
				prstmt.setString(1, news.getTitle());
				prstmt.setString(2, news.getUrl());
				prstmt.setString(3, news.getAuthor());
				prstmt.setString(4, news.getDate().toString());
				prstmt.setString(5, news.getContent());
				prstmt.setInt(6, feed.getId());
				prstmt.setInt(7, news.isRead() ? 1 : 0);
				prstmt.setInt(8, news.isStarred() ? 1 : 0);
				prstmt.execute();
			}
			else {
				PreparedStatement prstmt = db.connection.prepareStatement("UPDATE news SET title=? , url=? , author=? , date=? , content=? , feed=? , read=? , starred=? WHERE id=?");
				prstmt.setString(1, news.getTitle());
				prstmt.setString(2, news.getUrl());
				prstmt.setString(3, news.getAuthor());
				prstmt.setString(4, news.getDate().toString());
				prstmt.setString(5, news.getContent());
				prstmt.setInt(6, feed.getId());
				prstmt.setInt(7, news.isRead() ? 1 : 0);
				prstmt.setInt(8, news.isStarred() ? 1 : 0);
				prstmt.setInt(9, news.getId());
				prstmt.execute();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Envoie un feed
	public void addFeed(Feed feed){
		try {
			PreparedStatement prstmt = db.connection.prepareStatement("INSERT INTO feed VALUES(null, ?, ? )");
			prstmt.setString(1, feed.getTitle());
			prstmt.setString(2, feed.getUrl());
			prstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
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
