package accesoDatos.oracle;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoUpdate;

public class OracleUpdate < T extends Serializable > implements DaoUpdate<T> {

	DaoFactory<T> fac = null;
	private OracleSpecifics<T> oraSpecifics=new OracleSpecifics<T>();

	OracleUpdate(DaoFactory<T> fac) {
		this.fac = fac;
	}

	@Override
	public boolean putInto(Connection con, TableName tableName, T oldPojo,
			T currentPojo) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = oraSpecifics.getPreparedUpdate(con, tableName, oldPojo,
					currentPojo);
			ps.execute();
		} finally {
			DaoFactory.closeAll(ps, null);
		}
		return true;
	}

}