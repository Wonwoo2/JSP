package kr.or.ddit.prop.service;

import java.util.Calendar;
import java.util.List;

import kr.or.ddit.prop.dao.DataBasePropertyDAO_Mybatis;
import kr.or.ddit.prop.dao.IDataBasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyServiceImpl implements IDataBasePropertyService {
	
	private static IDataBasePropertyService service;
	
	// 결합력이 높음(최상), HCLC(High Cohesion, Loose Coupling) 응집력은 높이고, 결합력은 낮추는 것
	private IDataBasePropertyDAO dao = DataBasePropertyDAO_Mybatis.getInstance();
	
	private DataBasePropertyServiceImpl() { }
	
	public static IDataBasePropertyService getInstance() {
		if (service == null) {
			service = new DataBasePropertyServiceImpl();
		}
		return service;
	}

	@Override
	public List<DataBasePropertyVO> readDataBaseProperties(DataBasePropertyVO param) {
		List<DataBasePropertyVO> propertyList = dao.selectDataBaseProperties(param);
		Calendar cal = Calendar.getInstance();
		String pattern = "%s[%tc]";
		for (DataBasePropertyVO property : propertyList) {
			property.setProperty_name(String.format(pattern, property.getProperty_name(), cal));
		}
		return propertyList;
	}

	@Override
	public List<String> readAllProperty_names() {
		return dao.selectAllProperty_names();
	}
}