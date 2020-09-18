package kr.or.ddit.prop.dao;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBaseProperties(DataBasePropertyVO param);
	public List<String> selectAllProperty_names();
}