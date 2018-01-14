package accesoDatos.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoInsert {

	<T> boolean putInto(Connection con, TableName tableName,
			T currentPojo, boolean running) throws SQLException;
}