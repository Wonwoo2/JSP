package kr.or.ddit.prop.service;

import java.util.List;

import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * Business Logic Layer (Service Layer)
 */
public interface IDataBasePropertyService {
	
	/**
	 * property 정보를 읽고, property_name 에 시간 데이터를 concat
	 * @param param TODO
	 * @return
	 */
	public List<DataBasePropertyVO> readDataBaseProperties(DataBasePropertyVO param);
	
	public List<String> readAllProperty_names();
}