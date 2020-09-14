package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {
	
	private static MemberDAOImpl dao;
	
	private MemberDAOImpl() {
		super();
	}
	
	public static MemberDAOImpl getInstance() {
		if (dao == null) {
			dao = new MemberDAOImpl();
		}
		return dao;
	}

	@Override
	public int insertMember(MemberVO member) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("INSERT INTO member(mem_id, mem_pass, mem_name, mem_regno1, mem_regno2, mem_bir, mem_zip, mem_add1, mem_add2, mem_hometel, ");
		sql.append("mem_comtel, mem_hp, mem_job, mem_like, mem_memorial, mem_memorialday, mem_mileage, mem_delete)");
		sql.append("VALUES(?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int idx = 1;

			SimpleDateFormat transFormat = new SimpleDateFormat("yyMMdd");
			
			Date memorialDate = transFormat.parse(member.getMem_memorialday());
			Date birDate = transFormat.parse(member.getMem_bir());
			
			pstmt.setString(idx++, member.getMem_id());
			pstmt.setString(idx++, member.getMem_pass());
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_regno1());
			pstmt.setString(idx++, member.getMem_regno2());
			pstmt.setDate(idx++, new java.sql.Date(birDate.getTime()));
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setDate(idx++, new java.sql.Date(memorialDate.getTime()));
			pstmt.setInt(idx++, member.getMem_mileage());
			pstmt.setString(idx++, member.getMem_delete());
			
			return pstmt.executeUpdate();
		} catch (SQLException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT mem_id, mem_pass, mem_name,                   ");
		sql.append(" mem_regno1, mem_regno2, mem_bir,                     ");
		sql.append(" mem_zip, mem_add1, mem_add2, mem_hometel,            ");
		sql.append(" mem_comtel, mem_hp, mem_job, mem_like, mem_memorial, ");
		sql.append(" mem_memorialday, mem_mileage, mem_delete             ");
		sql.append(" FROM member                                          ");
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			ResultSet rs = pstmt.executeQuery();
			List<MemberVO> memberList = new LinkedList<>();
			MemberVO savedMember = null;
			while (rs.next()) {
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
				memberList.add(savedMember);
			}
			return memberList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String mem_id) {
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
			pstmt.setString(1, mem_id);
			
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

	@Override
	public int updateMember(MemberVO member) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("UPDATE member SET mem_pass = '?', mem_name = '?', mem_regno1 = '?', mem_regno2 = '?', ");
		sql.append("mem_bir = '?', mem_zip = '?', mem_add1 = '?', mem_add2 = '?', mem_hometel = '?', ");
		sql.append("mem_hometel = '?', mem_comtel = '?', mem_hp = '?', mem_job = '?', mem_like = '?', ");
		sql.append("mem_memorial = '?', mem_memorialday = '?', mem_mileage = '?', mem_delete = '?' ");
		sql.append("WHERE mem_id = '?'");
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int idx = 1;
			pstmt.setString(idx++, member.getMem_id());
			pstmt.setString(idx++, member.getMem_pass());
			pstmt.setString(idx++, member.getMem_name());
			pstmt.setString(idx++, member.getMem_regno1());
			pstmt.setString(idx++, member.getMem_regno2());
			pstmt.setString(idx++, member.getMem_bir());
			pstmt.setString(idx++, member.getMem_zip());
			pstmt.setString(idx++, member.getMem_add1());
			pstmt.setString(idx++, member.getMem_add2());
			pstmt.setString(idx++, member.getMem_hometel());
			pstmt.setString(idx++, member.getMem_comtel());
			pstmt.setString(idx++, member.getMem_hp());
			pstmt.setString(idx++, member.getMem_job());
			pstmt.setString(idx++, member.getMem_like());
			pstmt.setString(idx++, member.getMem_memorial());
			pstmt.setString(idx++, member.getMem_memorialday());
			pstmt.setInt(idx++, member.getMem_mileage());
			pstmt.setString(idx++, member.getMem_delete());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMember(String mem_id) {
		String sql = "DELETE FROM member WHERE mem_id = '?'";
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, mem_id);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}