
package java56.dao;

import java.util.HashMap;
import java.util.List;
import java56.vo.Score;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDao {
  @Autowired
  SqlSessionFactory sqlSessionFactory;
  
  public List<Score> list(int pageNo, int pageSize, String order) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("pageStartIndex", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("order", order);
      
      return sqlSession.selectList("servlets.step08.ScoreDao.list", params);
      
    } catch (Exception e) {
      throw e;
      
    } finally {
      sqlSession.close();
    }
  }
  
  public List<Score> list(int pageNo, int pageSize, 
      String columnName, String orderType) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      HashMap<String,Object> params = new HashMap<String,Object>();
      params.put("pageStartIndex", (pageNo - 1) * pageSize);
      params.put("pageSize", pageSize);
      params.put("orderType", orderType);
      params.put("columnName", columnName);
      
      return sqlSession.selectList("servlets.step08.ScoreDao.list2", params);
      
    } catch (Exception e) {
      throw e;
      
    } finally {
      sqlSession.close();
    }
  }

  public int countAll() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectOne("servlets.step08.ScoreDao.countAll");
      
    } catch (Exception e) {
      throw e;
      
    } finally {
      sqlSession.close();
    }
  }
  
  public Score selectOne(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      return sqlSession.selectOne("servlets.step08.ScoreDao.selectOne", no);
      
    } catch (Exception e) {
      throw e;
      
    } finally { 
      sqlSession.close();
    }
  }

  public void insert(Score score) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      sqlSession.insert("servlets.step08.ScoreDao.insert", score);
      sqlSession.commit();
      
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;
      
    } finally {
      sqlSession.close();
    }
  }

  public int delete(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      int count = sqlSession.delete("servlets.step08.ScoreDao.delete", no);
      sqlSession.commit();
      return count;
      
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;
      
    } finally { 
      sqlSession.close();
    }
  }

  public void update(Score score) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      sqlSession.update("servlets.step08.ScoreDao.update", score);
      sqlSession.commit();
      
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;
      
    } finally { 
      sqlSession.close();
    }
  }

  
  
}










