package accesoDatos.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoUpdate {
	<T> boolean putInto(Connection con, TableName tableName,
			T oldPojo, T currentPojo) throws SQLException;
}