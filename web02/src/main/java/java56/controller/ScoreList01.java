package java56.controller;

import java.util.Map;
import java56.dao.ScoreDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
//@RequestMapping(value="/score/step01/list.do")
//@RequestMapping(value="/score/step01/list") // 가능한 확장자 명은 생략하라!
//@RequestMapping("/score/step01/list") // value 속성명 생략
// 메서드 호출에만 선언해도 된다.
public class ScoreList01 {
  static Logger logger = Logger.getLogger(ScoreList01.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  /* 클라이언트가 보낸 요청 정보를 받고 싶다면, 메서드의 파라미터로 선언하라!
   * 단 파라미터 이름은 요청 정보의 이름과 같게 하라!
   * => 프런트 컨트롤러는 메서드를 호출할 때 파라미터 이름에 해당하는 요청 정보를 찾아서 넣어준다.
   * => 그리고 문자열을 자동으로 파라미터 타입으로 변경하여 넣어준다.
   */
  //@RequestMapping // 클래스 선언 앞에 @RequestMapping(url)이 선언되어 있다면, 여기서는 URL 생략함.
  @RequestMapping("/score/step01/list") // 클래스 선언 앞에 @RequestMapping 없다면, 여기서 URL 선언 
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










