package accesoDatos.oracle;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.interfaces.DaoDelete;
import accesoDatos.interfaces.DaoInsert;
import accesoDatos.interfaces.DaoRead;
import accesoDatos.interfaces.DaoUpdate;

public class OracleFactory extends DaoFactory {

	@Override
	public DaoInsert getDAOInsert() throws SQLException {
		return new OracleInsert(this);
	}

	@Override
	public DaoRead getDAORead() throws SQLException {
		return new OracleRead(this);
	}

	@Override
	public DaoDelete getDAODelete() {
		return new OracleDelete(this);
	}

	@Override
	public DaoUpdate getDAOUpdate() {
		return new OracleUpdate(this);
	}
}