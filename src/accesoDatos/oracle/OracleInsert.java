package accesoDatos.oracle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoInsert;

public class OracleInsert < T extends Serializable > implements DaoInsert<T> {

	private DaoFactory<T> fac = null;
	private OracleSpecifics<T> oraSpecifics=new OracleSpecifics<T>();
	
	OracleInsert(OracleFactory<T> oracleFactory) throws SQLException {
		fac = oracleFactory;
	}

	public boolean putInto(Connection con, TableName tableName,
			T currentPojo, boolean running) throws SQLException {
		PreparedStatement ps = null;
		try {
			if (fac.getDAORead().alreadyExisting(con, tableName,
					currentPojo)) {
				return false;
			}
			ps = oraSpecifics.getPreparedInsert(con, tableName,
					currentPojo, running);
			ps.execute();
			return true;
		} finally {
			DaoFactory.closeAll(ps, null);
		}
	}
}