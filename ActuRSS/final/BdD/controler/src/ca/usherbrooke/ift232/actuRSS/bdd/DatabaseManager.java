

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;


public class DatabaseManager {
	
	private Database db;

	public DatabaseManager(Database db) {
		this.db = db;
		connect();
	}
	
	// Efface toute la DB
	public void clearDB() {
		
		db.updateValue("drop table if exists person");
		db.updateValue("drop table if exists feed");
		db.updateValue("drop table if exists news");
		
	}
	
	// Crée les tables Feed et News et Category
		public void createDB() {
			
			db.updateValue("create table if not exists Feed(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, URL TEXT, Nom TEXT, ID_Category INTEGER, FOREIGN KEY (ID_Category) REFERENCES Category(ID));");
			db.updateValue("create table if not exists News(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Titre TEXT, URL TEXT, Auteur TEXT, Date_News NUMERIC, Contenu TEXT, Lu INTEGER, Favori INTEGER , ID_feed INTEGER, FOREIGN KEY (ID_feed) REFERENCES Feed(ID));");
			db.updateValue("create table if not exists Category(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Nom  TEXT);");
			
		}
	
	public void insertCategory(String nom)
	{
		String requete = "INSERT INTO Category (Nom) " +
                "VALUES ('"+ nom +"');";
		db.updateValue(requete);
	}
	public void insertNews(String titre, String url, String auteur, Date date_News, String contenu, Boolean lu, Boolean favori, Feed feed)
	{
		//a coder
	}
	
	/*public void insertFeed(String url, String nom, Categorie category)
	{
		String requete = "SELECT * FROM Category WHERE Nom="+category.getNom()+";";
		ResultSet rs = db.getResultOf(requete);
		try {
			if(rs.next())
			{
				String requeteInsert = "INSERT INTO Feed (Url, Nom, ID_Category) " +
		                "VALUES ('"+ url +"', '"+ nom +"', "+ category.getID +");";
				db.updateValue(requete);
			}
			else
			{
				this.insertCategory(category.getNom());
				this.insertFeed(url, nom, category);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}*/
	
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
	
	
	public void returnCategory()
	{
		
		String requete = "SELECT * FROM Category;";
		 ResultSet rs = db.getResultOf(requete);
	      try {
			while ( rs.next() ) {
			     int id = rs.getInt("ID");
			     String  nom = rs.getString("Nom");
			     System.out.println( "ID = " + id );
			     System.out.println( "Nom = " + nom );
			     System.out.println();
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
// Récupère toutes les news d'un feed
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
	
	/*// Récupère toutes les news d'un feed
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
