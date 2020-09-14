package kr.or.ddit.commons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateDAOImpl implements IAuthenticateDAO {
	
	@Override
	public MemberVO selectMember(MemberVO member) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT mem_id, mem_pass, mem_name,                   ");
		sql.append(" mem_regno1, mem_regno2, mem_bir,                     ");
		sql.append(" mem_zip, mem_add1, mem_add2, mem_hometel,            ");
		sql.append(" mem_comtel, mem_hp, mem_job, mem_like, mem_memorial, ");
		sql.append(" mem_memorialday, mem_mileage, mem_delete             ");
		sql.append(" FROM member                                          ");
		sql.append(" WHERE mem_id = ?");
	    
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString())
		) {
			pstmt.setString(1, member.getMem_id());
			
			ResultSet rs = pstmt.executeQuery();
			MemberVO savedMember = null;
			if (rs.next()) {
				savedMember = MemberVO.builder()
						.mem_id(rs.getString("mem_id"))
						.mem_pass(rs.getString("mem_pass"))
						.mem_name(rs.getString("mem_name"))
						.mem_regno1(rs.getString("mem_regno1"))
						.mem_regno2(rs.getString("mem_regno2"))
						.mem_bir(rs.getString("mem_bir"))
						.mem_zip(rs.getString("mem_zip"))
						.mem_add1(rs.getString("mem_add1"))
						.mem_add2(rs.getString("mem_add2"))
						.mem_hometel(rs.getString("mem_hometel"))
						.mem_comtel(rs.getString("mem_comtel"))
						.mem_hp(rs.getString("mem_hp"))
						.mem_job(rs.getString("mem_job"))
						.mem_like(rs.getString("mem_job"))
						.mem_memorial(rs.getString("mem_memorial"))
						.mem_memorialday(rs.getString("mem_memorialday"))
						.mem_mileage(rs.getInt("mem_mileage"))
						.mem_delete(rs.getString("mem_delete"))
						.build();
			}
			return savedMember;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}