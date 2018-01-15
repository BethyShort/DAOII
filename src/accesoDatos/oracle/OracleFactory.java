package accesoDatos.oracle;
import java.io.Serializable;
import java.sql.SQLException;

import accesoDatos.DaoFactory;
import accesoDatos.interfaces.DaoDelete;
import accesoDatos.interfaces.DaoInsert;
import accesoDatos.interfaces.DaoRead;
import accesoDatos.interfaces.DaoUpdate;

public class OracleFactory < T extends Serializable >extends DaoFactory<T> {

	@Override
	public DaoInsert<T> getDAOInsert() throws SQLException {
		return new OracleInsert<T>(this);
	}

	@Override
	public DaoRead<T> getDAORead() throws SQLException {
		return new OracleRead<T>(this);
	}

	@Override
	public DaoDelete<T> getDAODelete() {
		return new OracleDelete<T>(this);
	}

	@Override
	public DaoUpdate<T> getDAOUpdate() {
		return new OracleUpdate<T>(this);
	}
}