package accesoDatos.interfaces;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoDelete < T extends Serializable > {

	boolean deleteFrom(Connection con, TableName tableName,
			T currentPojo) throws SQLException;
}