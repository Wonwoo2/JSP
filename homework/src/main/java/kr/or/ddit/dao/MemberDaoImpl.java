package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.Contants;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(Contants.URL, Contants.USER, Contants.PASSWORD);
		return conn;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		String sql = "INSERT INTO users(userid, usernm, pass, reg_dt, alias, add1, add2, zipcode)"
				+ " VALUES('" + memVo.getUserid() + "', '" + memVo.getUsernm() + "', SYSDATE" 
				+ ", '" + memVo.getAlias() + "', '" + memVo.getAddr1() + "', '" + memVo.getAddr2() + "', " 
				+ ", '" + memVo.getZipcode() + "')";
		
		try (
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
		) {
			return stmt.executeUpdate(sql);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO memVo) {
		String sql = "UPDATE users SET usernm = '" + memVo.getUsernm() + "', '"  
													+ memVo.getPass() + "', '"
													+ memVo.getAlias() + "', '"
													+ memVo.getAddr1() + "', '"
													+ memVo.getAddr2() + "', '"
													+ memVo.getZipcode() + ", '"
													+ memVo.getFilename() + ", '"
													+ memVo.getRealfilename() + "'"
													+ " WHERE userid = '" + memVo.getUserid() + "'";
		try (
				Connection conn = getConnection();
				Statement stmt = conn.createStatement();
		) {
			return stmt.executeUpdate(sql);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String userid) {
		String sql = "DELETE FROM users WHERE userid = '" + userid + "'";
		try (
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
		) {
			return stmt.executeUpdate(sql);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public MemberVO getMember(String userid) {
		String sql = "SELECT * FROM users WHERE userid = '" + userid + "'";
		try (Connection conn = getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql);
			MemberVO memVo = null;
			while (rs.next()) {
				memVo = setMember(rs);
			}
			return memVo;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMember(MemberVO memVo) {
		List<MemberVO> memberList = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM users");

		StringBuffer where = null;
		if (memVo != null) {
			where = new StringBuffer();
			String pattern = " %s = '%s'";
			if (StringUtils.isNotBlank(memVo.getUserid())) {
				where.append(String.format(pattern, "userid"));
			} else if (StringUtils.isNotBlank(memVo.getUsernm())) {
				if (where.length() > 0) {
					where.append(" AND");
				}
				where.append(String.format(pattern, "usernm"));
			} else if (StringUtils.isNotBlank(memVo.getAlias())) {
				if (where.length() > 0) {
					where.append(" AND");
				}
				where.append(String.format(pattern, "alias"));
			}

			if (where.length() > 0) {
				where.append("WHERE");
				sql.append(where);
			}
		}

		try (Connection conn = getConnection(); Statement stmt = conn.createStatement();) {
			ResultSet rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				MemberVO memberVo = setMember(rs);
				memberList.add(memberVo);
			}
			return memberList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MemberVO setMember(ResultSet rs) throws SQLException {
		return MemberVO.getBuilder().userid(rs.getString("userid"))
							.usernm(rs.getString("usernm"))
							.pass(rs.getString("pass"))
							.reg_dt(rs.getString("reg_dt"))
							.alias(rs.getString("alias"))
							.addr1(rs.getString("addr1"))
							.addr2(rs.getString("addr2"))
							.zipcode(rs.getString("zipcode"))
							.filename(rs.getString("filename"))
							.realfilename(rs.getString("realfilename")).build();
	}
}