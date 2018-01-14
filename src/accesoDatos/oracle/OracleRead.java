package accesoDatos.oracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import accesoDatos.DaoFactory;
import accesoDatos.cfg.def.QueryType;
import accesoDatos.cfg.def.TableName;
import accesoDatos.interfaces.DaoRead;

public class OracleRead implements DaoRead {

	DaoFactory fac = null;

	OracleRead(DaoFactory fac) throws SQLException {
		this.fac = fac;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Connection con, TableName tableName)
			throws SQLException {
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("select * from " + tableName);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add((T) OracleSpecifics
						.getPojoFromResultSet(tableName, rs));
			}
		} finally {
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAllForInput(Connection con, TableName tableName,
			String columnName, String searchValue) throws SQLException {
		List<T> list = new ArrayList<T>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder statement=new StringBuilder();
			statement.append("SELECT * FROM ").append(tableName).append(" WHERE ").append(columnName).append(" LIKE '%")
					.append(searchValue).append("%'");
			ps = con.prepareStatement(statement.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add((T) OracleSpecifics
						.getPojoFromResultSet(tableName, rs));
			}
		} finally {
			DaoFactory.closeAll(ps, rs);
		}
		return list;
	}

	@Override
	public <T> T getPojoForPrimarKey(Connection con, TableName tableName,
			String primaryKey) throws SQLException {
		T currentPojo = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String queryString = OracleSpecifics.queryString(tableName,
					primaryKey, QueryType.READ);
			ps = con.prepareStatement(queryString);
			rs = ps.executeQuery();
			if (rs.next()) {
				currentPojo = OracleSpecifics.getPojoFromResultSet(tableName,
						rs);
			}
		} finally {
			DaoFactory.closeAll(ps, rs);
		}
		return currentPojo;
	}

	@Override
	public <T> boolean alreadyExisting(Connection con, TableName tableName,
			String primaryKey) throws SQLException {
		if (getPojoForPrimarKey(con, tableName, primaryKey) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public <T> boolean alreadyExisting(Connection con, TableName tableName,
			T currentPojo) throws SQLException {
		String primaryKey = OracleSpecifics.<T> getPrimaryKey(tableName,
				currentPojo);
		if (alreadyExisting(con, tableName, primaryKey) == false) {
			return false;
		} else {
			return true;
		}
	}
}