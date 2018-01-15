package accesoDatos.oracle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.QueryType;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoDelete;

public class OracleDelete < T extends Serializable > implements DaoDelete<T> {
	private DaoFactory<T> fac = null;
	private OracleSpecifics<T> oraSpecifics=new OracleSpecifics<T>();

	public OracleDelete(OracleFactory<T> oracleFactory) {
		fac = oracleFactory;
	}

	@Override
	public boolean deleteFrom(Connection con, TableName tableName,
			T currentPojo) throws SQLException {
		PreparedStatement ps = null;
		try {
			if (!fac.getDAORead().alreadyExisting(con, tableName,currentPojo)) 
				return false;
			
			String primaryKey = oraSpecifics.getPrimaryKey(tableName,
					currentPojo);
			ps = con.prepareStatement(oraSpecifics.queryString(tableName,
					primaryKey, QueryType.DELETE));
			ps.execute();
		} finally {
			DaoFactory.closeAll(ps, null);
		}
		return true;
	}
}