package accesoDatos.oracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import accesoDatos.SpecStudent;
import accesoDatos.Student;
import accesoDatos.cfg.def.QueryType;
import accesoDatos.cfg.def.TableName;

public class OracleSpecifics {


	static String queryString(TableName tableName, String keyValue,
			QueryType type) {
		StringBuilder firstHalf = new StringBuilder();
		switch (type) {
		case READ:
			firstHalf.append("select * from ").append(tableName).append(" where ");
			break;
		case DELETE:
			firstHalf.append("DELETE from ").append(tableName).append(" where ");
			break;
		default:
		}

		switch (tableName) {
		case STUDENT_TABLE:
			return firstHalf.append("STUDENT_ID='").append(keyValue).append("'").toString();
		default:
			return null;
		}
	}

	static <T> String getPrimaryKey(TableName tableName, T currentPojo) {

		switch (tableName) {
		case STUDENT_TABLE:
			return ((Student) currentPojo).getStudentId();
		default:
			return null;
		}
	}

	// These functions call table-specific functions
	@SuppressWarnings("unchecked")
	static <T> T getPojoFromResultSet(TableName tableName, ResultSet rs)
			throws SQLException {

		switch (tableName) {
		case STUDENT_TABLE:
			return (T) SpecStudent.getPojo(rs);
		default:
			return null;
		}
	}

	static <T> PreparedStatement getPreparedInsert(Connection con,
			TableName tableName, T currentPojo, boolean running)
			throws SQLException {

		switch (tableName) {
		case STUDENT_TABLE:
			if (running) {
				return SpecStudent.getPreparedInsertRunning(con, tableName,
						currentPojo);
			} else {
				return SpecStudent.getPreparedInsert(con, tableName,
						currentPojo);
			}
		default:
			return null;
		}

	}

	static <T> PreparedStatement getPreparedUpdate(Connection con,
			TableName tableName, T oldPojo, T currentPojo) throws SQLException {

		switch (tableName) {
		case STUDENT_TABLE:
			return SpecStudent.getPreparedUpdate(con, tableName, oldPojo,
					currentPojo);
		default:
			return null;
		}
	}
}