
package java56.dao;

import java.util.HashMap;
import java56.vo.Student;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	public Student exist(String email, String password) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, String> paramMap = new HashMap<>();
			paramMap.put("email", email);
			paramMap.put("password", password);
			return sqlSession.selectOne("java56.dao.StudentDao.exist", paramMap);
						
		} catch (Exception e) {
			throw e;

		} finally { 
			sqlSession.close();
		}
	}

}










