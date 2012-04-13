package org.linuxcoffee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMetaData {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver").newInstance();
		Connection conn = DriverManager
				.getConnection("jdbc:h2:~/test;USER=sa;PASSWORD=");
		java.sql.DatabaseMetaData m_DBMetaData = conn.getMetaData();
		ResultSet tableRet = m_DBMetaData.getTables(null, "%", "%",
				new String[] { "TABLE" });
		while (tableRet.next()) {
			String tableName = tableRet.getString("TABLE_NAME");
			System.out.println(tableName);
			ResultSet colRet = m_DBMetaData.getColumns(null, "%", tableName,
					"%");

			while (colRet.next()) {
				String columnName = colRet.getString("COLUMN_NAME");
				String columnType = colRet.getString("TYPE_NAME");
				int datasize = colRet.getInt("COLUMN_SIZE");
				int digits = colRet.getInt("DECIMAL_DIGITS");
				int nullable = colRet.getInt("NULLABLE");
				System.out.println(columnName + " " + columnType + " "
						+ datasize + " " + digits + " " + nullable);
			}
		}

	}
}

