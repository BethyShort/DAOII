package accesoDatos.oracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.QueryType;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoDelete;

public class OracleDelete implements DaoDelete {
	DaoFactory fac = null;

	public OracleDelete(OracleFactory oracleFactory) {
		fac = oracleFactory;
	}

	@Override
	public <T> boolean deleteFrom(Connection con, TableName tableName,
			T currentPojo) throws SQLException {
		PreparedStatement ps = null;
		try {
			if (fac.getDAORead().<T> alreadyExisting(con, tableName,
					currentPojo) == false) {
				return false;
			}
			String primaryKey = OracleSpecifics.<T> getPrimaryKey(tableName,
					currentPojo);
			ps = con.prepareStatement(OracleSpecifics.queryString(tableName,
					primaryKey, QueryType.DELETE));
			ps.execute();
		} finally {
			DaoFactory.closeAll(ps, null);
		}
		return true;
	}
}