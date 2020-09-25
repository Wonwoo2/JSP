package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.listener.SampleListener;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	
	private IProdDAO dao = ProdDAOImpl.getInstance();
	
	private static IProdService service;
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	private File folder;
	
	private ProdServiceImpl() {
		String folderURL = "/prodImages";
		String folderPath = SampleListener.currentContext.getRealPath(folderURL);
		folder = new File(folderPath);
	}
	
	public static IProdService getInstance() {
		if (service == null) {
			service = new ProdServiceImpl();
		}
		return service;
	}

	@Override
	public ServiceResult createProd(ProdVO prod) {
		try (
				SqlSession session = sqlSessionFactory.openSession()
		) {
			int insertResult = dao.insertProd(prod, session);
			// 파일 업로드
			prod.getProd_image().saveToRealPath(folder);
			
			ServiceResult result = ServiceResult.FAILED;
			if (insertResult > 0) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prodVo = null;
		prodVo = dao.selectProd(prod_id);
		if (prodVo == null) {
			throw new CustomException(prod_id + "은 존재하지 않는 상품입니다.");
		}
		return prodVo;
	}

	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVo) {
		return dao.selectProdCount(pagingVo);
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVo) {
		return dao.selectProdList(pagingVo);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		try (
				SqlSession session = sqlSessionFactory.openSession();
		) {
			int updateResult = dao.updateProd(prod, session);
			
			if (prod.getProd_image() != null) {
				prod.getProd_image().saveToRealPath(folder);
			}
			
			ServiceResult result  = ServiceResult.FAILED;
			if (updateResult > 0) {
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}