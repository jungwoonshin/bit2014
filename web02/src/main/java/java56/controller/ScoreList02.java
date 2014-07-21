package java56.controller;

import java56.dao.ScoreDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Request Handler(요청 처리 메서드) 작성
 * 1) 메서드 파라미터의 타입으로 가능한 것: 
 *    ServletRequest, ServletResponse, HttpServletRequest, HttpServletResponse
 *    HttpSession, InputStream/OutputStream(바이너리 데이터를 직접 읽고 쓸때),
 *    HttpEntity(JSP를 경유하지 않고 직접 콘텐츠를 출력할 때), 
 *    Map/Model/ModelMap(JSP에 값을 전달할 때 사용)
 *     
 */

//@Controller
public class ScoreList02 {
  static Logger logger = Logger.getLogger(ScoreList02.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  /* 메서드 파라미터로 Model 사용 */
  @RequestMapping("/score/step01/list") // 클래스 선언 앞에 @RequestMapping 없다면, 여기서 URL 선언 
  public String execute(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize, 
      String order,
      String columnName,
      String orderType,
      Model model)
      throws Exception {
    logger.info("성적 목록 가져오기.....");
    
    int countAll = scoreDao.countAll();
    int totalPage = countAll / pageSize;
    if ((countAll % pageSize) > 0) {
      totalPage++;
    }
    
    if (order != null) {
      model.addAttribute("order", order);
      model.addAttribute("scores", scoreDao.list(pageNo, pageSize, order));
      
    } else if (columnName != null) {
      model.addAttribute("scores", 
          scoreDao.list(pageNo, pageSize, columnName, orderType));
      
    } else {
      model.addAttribute("scores", scoreDao.list(pageNo, pageSize, null));
    }
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    
    
    return "/score/step01/ScoreList.jsp";
  }

}










