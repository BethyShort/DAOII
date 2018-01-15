package accesoDatos.interfaces;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import accesoDatos.cfg.def.TableName;

public interface DaoRead<T extends Serializable> {

	List<T> getAll(Connection con, TableName tableName) throws SQLException;

	List<T> getAllForInput(Connection con, TableName tableName, String columnName, String searchValue)
			throws SQLException;

	T getPojoForPrimarKey(Connection con, TableName tableName, String primaryKey) throws SQLException;

	boolean alreadyExisting(Connection con, TableName tableName, String primaryKey) throws SQLException;

	boolean alreadyExisting(Connection con, TableName tableName, T currentPojo) throws SQLException;
}