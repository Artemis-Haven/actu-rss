#------------------------------------------------------------
#        Script SQLite  
#------------------------------------------------------------

CREATE TABLE feed(
	ID           INTEGER autoincrement NOT NULL ,
	URL          TEXT ,
	Titre        TEXT ,
	ID_Category  INTEGER ,
	PRIMARY KEY (ID) ,
	
	FOREIGN KEY (ID_Category) REFERENCES Category(ID)
);

CREATE TABLE News(
	ID         INTEGER autoincrement NOT NULL ,
	Titre      TEXT ,
	URL        TEXT ,
	Auteur     TEXT ,
	Date_News  NUMERIC ,
	Contenu    TEXT ,
	ID_feed    INTEGER ,
	PRIMARY KEY (ID) ,
	
	FOREIGN KEY (ID_feed) REFERENCES feed(ID)
);

CREATE TABLE Category(
	ID     INTEGER autoincrement NOT NULL ,
	Titre  TEXT ,
	PRIMARY KEY (ID)
);


