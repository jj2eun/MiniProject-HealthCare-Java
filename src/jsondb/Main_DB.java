package jsondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main_DB {

	public static void main(String[] args) {
		NutrientJSON nutJson = new NutrientJSON();
		nutJson.getAPI();
	}
}
