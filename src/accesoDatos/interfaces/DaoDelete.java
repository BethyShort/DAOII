package accesoDatos.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoDelete {

	<T> boolean deleteFrom(Connection con, TableName tableName,
			T currentPojo) throws SQLException;
}