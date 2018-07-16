package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnector {
	private static DBConnector instance = null;

	private Connection connection = null;
	private Statement statement = null;

	private String lastMsg = null;

	public static DBConnector getInstance() {
		if (instance == null) {
			instance = new DBConnector();
		}
		return instance;
	}

	public boolean initJDBC(String driverClassName, String dbURL, String dbLogin, String dbPass) {
		boolean res = false;
		try {
			Class.forName(driverClassName);
			Properties dbProps = new Properties();
			dbProps.setProperty("user", dbLogin);
			dbProps.setProperty("password", dbPass);
			dbProps.setProperty("encoding", "UTF8");
			connection = DriverManager.getConnection(dbURL, dbProps);
		} catch (ClassNotFoundException e) {
			lastMsg = "Не вдалося завантажити драйвер бази даних. " + e.getMessage() + "\n";
		} catch (SQLException e) {
			lastMsg = "Не вдалося встановити з'єднання з базою даних. " + e.getMessage() + "\n";
		}

		if (connection != null) {
			try {
				statement = connection.createStatement();
				res = true;
			} catch (SQLException e) {
				lastMsg += "Не вдалося сворити контейнер виконання SQL запитів. " + e.getMessage() + "\n";
			}
		} else {
			lastMsg += "Відсутнє з'єднання з базою даних. \n";
		}
		return res;
	}

	public ResultSet selectQuery(String qText) {
		ResultSet res = null;
		try {
			res = statement.executeQuery(qText);
		} catch (NullPointerException e) {
			lastMsg = "Відсутній контейнер виконання SQL запитів. " + e.getMessage() + "\n";
		} catch (SQLException e) {
			lastMsg = "Помилка виконання SQL запиту. " + e.getMessage() + "\n" + qText + "\n";
		}
		return res;
	}

	public Integer updateQuery(String qText) {
		Integer res = null;
		try {
			res = statement.executeUpdate(qText);
		} catch (NullPointerException e) {
			lastMsg = "Відсутній контейнер виконання SQL запитів. " + e.getMessage() + "\n";
		} catch (SQLException e) {
			lastMsg = "Помилка виконання SQL запиту. " + e.getMessage() + "\n" + qText + "\n";
		}
		return res;
	}

	public String getLastMsg() {
		return lastMsg;
	}

}
