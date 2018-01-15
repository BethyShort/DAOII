package accesoDatos.interfaces;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import accesoDatos.cfg.def.TableName;

public interface DaoInsert < T extends Serializable >{

	boolean putInto(Connection con, TableName tableName,
			T currentPojo, boolean running) throws SQLException;
}