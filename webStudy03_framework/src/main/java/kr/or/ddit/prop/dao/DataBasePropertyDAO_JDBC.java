package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.Contants;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAO_JDBC implements IDataBasePropertyDAO {
	
	@Override
	public List<DataBasePropertyVO> selectDataBaseProperties(DataBasePropertyVO param) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES");
		
		StringBuffer where = null;
		if (param != null) {
			where = new StringBuffer();
			String pattern = " %s LIKE '%%%s%%' ";
			if (StringUtils.isNotBlank(param.getProperty_name())) {
				where.append(String.format(pattern, Contants.DATABASEPROPERTYNAME, param.getProperty_name()));
			} 
			if (StringUtils.isNotBlank(param.getProperty_value())) {
				if(where.length() > 0) {
					where.append(" AND ");
				}
				where.append(String.format(pattern, Contants.DATABASEPROPERTYVALUE, param.getProperty_value()));
			} 
			if (StringUtils.isNotBlank(param.getDescription())) {
				if(where.length() > 0) {
					where.append(" AND ");
				}
				where.append(String.format(pattern, Contants.DATABASEPDESCRPIPTION, param.getDescription()));
			}
		}
		
		if(where.length() > 0) {
			where.insert(0, " WHERE");
			sql.append(where);
		}
		
		List<DataBasePropertyVO> dbPropertyList = new ArrayList<>();
		try (	Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();) {
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				DataBasePropertyVO dbInfVO = DataBasePropertyVO.getBuilder()
						.property_name(rs.getString(Contants.DATABASEPROPERTYNAME))
						.property_value(rs.getString(Contants.DATABASEPROPERTYVALUE))
						.description(rs.getString(Contants.DATABASEPDESCRPIPTION)).build();
				dbPropertyList.add(dbInfVO);
			}
			return dbPropertyList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> selectAllProperty_names() {
		String sql = "SELECT PROPERTY_NAME FROM DATABASE_PROPERTIES";
		List<String> propertyNameList = new ArrayList<>();
		try (	Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();) {
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				propertyNameList.add(rs.getString(Contants.DATABASEPROPERTYNAME));
			}
			return propertyNameList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}