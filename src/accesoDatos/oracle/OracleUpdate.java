package accesoDatos.oracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoUpdate;

public class OracleUpdate implements DaoUpdate {

	DaoFactory fac = null;

	OracleUpdate(DaoFactory fac) {
		this.fac = fac;
	}

	@Override
	public <T> boolean putInto(Connection con, TableName tableName, T oldPojo,
			T currentPojo) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = OracleSpecifics.<T> getPreparedUpdate(con, tableName, oldPojo,
					currentPojo);
			ps.execute();
		} finally {
			DaoFactory.closeAll(ps, null);
		}
		return true;
	}

}