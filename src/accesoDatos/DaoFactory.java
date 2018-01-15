package accesoDatos;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import accesoDatos.cfg.def.DatabaseType;
import accesoDatos.interfaces.DaoDelete;
import accesoDatos.interfaces.DaoInsert;
import accesoDatos.interfaces.DaoRead;
import accesoDatos.interfaces.DaoUpdate;
import accesoDatos.oracle.OracleFactory;

public abstract class DaoFactory<T extends Serializable> {

	// Abstract Instance methods
	public abstract DaoInsert<T> getDAOInsert() throws SQLException;
	public abstract DaoRead<T> getDAORead() throws SQLException;
	public abstract DaoDelete<T> getDAODelete();
	public abstract DaoUpdate<T> getDAOUpdate();

	// Concrete Class Methods
	public static DaoFactory factoryProducer(DatabaseType db) throws NamingException {
		switch (db) {
		case Oracle:
			return new OracleFactory();
		default:
			return null;
		}
	}

	public static void closeAll(PreparedStatement ps, ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
		}
		try {
			ps.close();
		} catch (Exception e) {
		}
	}
}