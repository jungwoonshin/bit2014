package java56.controller;

import java.util.Map;
import java56.dao.ScoreDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* 스프링의 프런트 컨트롤러(DispatcherServlet)는 
 * 클라이언트가 보낸 요청 정보를 페이지 컨트롤러의 메서드를 호출할 때 파라미터로 넘겨 준다. 
 * 
 * 파라미터의 기본 값을 설정하고 싶으면 @RequestParam 애노테이션을 사용한다.
 */

@Component("/score/step01/list.do")
public class ScoreList {
  static Logger logger = Logger.getLogger(ScoreList.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  /* 클라이언트가 보낸 요청 정보를 받고 싶다면, 메서드의 파라미터로 선언하라!
   * 단 파라미터 이름은 요청 정보의 이름과 같게 하라!
   * => 프런트 컨트롤러는 메서드를 호출할 때 파라미터 이름에 해당하는 요청 정보를 찾아서 넣어준다.
   * => 그리고 문자열을 자동으로 파라미터 타입으로 변경하여 넣어준다.
   */
  @RequestMapping
  public String execute(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize, 
      /*@RequestParam(required=false)*/ String order,
      /*@RequestParam(required=false)*/ String columnName,
      /*@RequestParam(required=false)*/ String orderType,
      Map<String, Object> model)
      throws Exception {
    logger.info("성적 목록 가져오기.....");
    
    int countAll = scoreDao.countAll();
    int totalPage = countAll / pageSize;
    if ((countAll % pageSize) > 0) {
      totalPage++;
    }
    
    if (order != null) {
      model.put("order", order);
      model.put("scores", scoreDao.list(pageNo, pageSize, order));
      
    } else if (columnName != null) {
      model.put("scores", scoreDao.list(pageNo, pageSize, columnName, orderType));
      
    } else {
      model.put("scores", scoreDao.list(pageNo, pageSize, null));
    }
    model.put("totalPage", totalPage);
    model.put("pageNo", pageNo);
    model.put("pageSize", pageSize);
    
    
    return "/score/step01/ScoreList.jsp";
  }

}










