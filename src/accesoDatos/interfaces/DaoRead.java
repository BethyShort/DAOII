package accesoDatos.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import accesoDatos.cfg.def.TableName;

public interface DaoRead {

	<T> List<T> getAll(Connection con, TableName tableName)
			throws SQLException;

	<T> List<T> getAllForInput(Connection con,
			TableName tableName, String columnName, String searchValue)
			throws SQLException;

	<T> T getPojoForPrimarKey(Connection con,
			TableName tableName, String primaryKey) throws SQLException;

	<T> boolean alreadyExisting(Connection con,
			TableName tableName, String primaryKey) throws SQLException;

	<T> boolean alreadyExisting(Connection con,
			TableName tableName, T currentPojo) throws SQLException;
}