package accesoDatos.interfaces;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoUpdate <T extends Serializable>{
	boolean putInto(Connection con, TableName tableName,
			T oldPojo, T currentPojo) throws SQLException;
}