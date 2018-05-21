package week12;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class readingQuranXML {
	static Connection dbConnection = null; 
	static String dbName = "quran.db"; 
	static String urlString ="https://raw.githubusercontent.com/ceefour/qurandatabase/master/English-Shakir-58.xml"; 




	public static void main(String[] args) {
		//readXML();
		searchDatabase();
	}
	public static void searchDatabase(){
		System.out.print("Type word to search for: ");
		Scanner in = new Scanner(System.in);
		String searchWord = in.nextLine();
		if(dbConnection == null) connectDB();
		try{
			Statement statement = dbConnection.createStatement();
			String sql = "SELECT * FROM verses WHERE verse LIKE '%" + searchWord + "%';";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()){
				System.out.println(rs.getString("chapter") + "  " + rs.getString("verse"));
			}
		}catch (Exception e){e.printStackTrace();}
	}

	public static void readXML(){
		Document doc;
		try{
			// doc = DocumentBuilderFactory.newInstance()
			//    .newDocumentBuilder().parse("Book of Mormon.xml");
			//or if the .xml file is available online:
			doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new URL(urlString).openStream());
			// System.out.println("Root element :" + doc.getDocumentElement().getAttribute("biblename"));
			// System.out.println("----------------------------");
			NodeList chapterList = doc.getElementsByTagName("Chapter");
			for (int x = 0; x < chapterList.getLength(); x++) {
				Node chapterNode = chapterList.item(x);
				Element chapterElement = (Element) chapterNode;
				System.out.println("Chapter : " + chapterElement.getAttribute("ChapterName"));
				String chapter = chapterElement.getAttribute("ChapterName");
				NodeList verseList = chapterElement.getElementsByTagName("Verse");
				for (int y = 0; y < verseList.getLength(); y++) {
					Node verseNode = verseList.item(y);
					Element verseElement = (Element) verseNode;
					System.out.println("  Verse " + verseElement.getTextContent());
					String verse = verseElement.getTextContent();
					writeToDb(chapter,verse);

				}
			}

		}catch(Exception e) {e.printStackTrace();}
	}
	private static void writeToDb(String chapter, String verse){
		try{
			if(dbConnection == null) connectDB();
			String sql = "INSERT INTO verses (chapter, verse) VALUES (?,?);";
			PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1,chapter);
			preparedStatement.setString(2,verse);
			preparedStatement .executeUpdate();
		}catch (Exception e){System.out.println(e.getMessage());}
	}

	private static void connectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:"+ dbName;
			dbConnection = DriverManager.getConnection(url);
			Statement statement = dbConnection.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS verses " +
					"('id' INTEGER PRIMARY KEY, 'chapter' TEXT, 'verse' TEXT);";
			statement.executeUpdate(sql);
			//only execute next 2 lines when you want to delete all
			//sql = "DELETE FROM verses;";
			//statement.executeUpdate(sql);
			statement.close();
		} catch (Exception e) {System.out.println(e.getMessage());}
	}

}
