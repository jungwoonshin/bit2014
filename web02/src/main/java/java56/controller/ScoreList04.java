package java56.controller;

import java.util.Map;

import javax.servlet.ServletResponse;

import java56.dao.ScoreDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/* Request Handler(요청 처리 메서드) 작성
 * 1) 메서드 파라미터의 타입으로 가능한 것: 
 *    ServletRequest, ServletResponse, HttpServletRequest, HttpServletResponse
 *    HttpSession, InputStream/OutputStream(바이너리 데이터를 직접 읽고 쓸때),
 *    Map/Model/ModelMap(JSP에 값을 전달할 때 사용)
 * 
 * 2) 메서드의 리턴 타입으로 가능한 것:
 *    String(JSP URL 또는 콘텐츠), Model(JSP에 값을 전달할 때 사용), 
 *    ModelAndView(JSP에 리턴할 값과 JSP URL을 지정)
 *    View(JSP URL을 지정할 때), 
 *    HttpEntity(JSP를 경유하지 않고 직접 콘텐츠를 출력할 때) 
 */

//@Controller
public class ScoreList04 {
  static Logger logger = Logger.getLogger(ScoreList04.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  /* 페이지 컨트롤러에서 직접 문자열을 출력
   * => 메서드 선언 앞에 @ResponseBody 애노테이션을 붙인다.
   * => 메서드의 리턴 타입은 String 이다.
   * => Context-Type의 문자 집합 설정이 동작되지 않는다. 
   */
  @RequestMapping("/score/step01/list") // 클래스 선언 앞에 @RequestMapping 없다면, 여기서 URL 선언
  @ResponseBody
  public String execute(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize, 
      String order,
      String columnName,
      String orderType,
      ServletResponse response)
      throws Exception {
    logger.info("성적 목록 가져오기.....");
    
    ModelAndView mv = new ModelAndView();
    
    int countAll = scoreDao.countAll();
    int totalPage = countAll / pageSize;
    if ((countAll % pageSize) > 0) {
      totalPage++;
    }
    
    if (order != null) {
      mv.addObject("order", order);
      mv.addObject("scores", scoreDao.list(pageNo, pageSize, order));
      
    } else if (columnName != null) {
      mv.addObject("scores", 
          scoreDao.list(pageNo, pageSize, columnName, orderType));
      
    } else {
      mv.addObject("scores", scoreDao.list(pageNo, pageSize, null));
    }
    mv.addObject("totalPage", totalPage);
    mv.addObject("pageNo", pageNo);
    mv.addObject("pageSize", pageSize);
    
    mv.setViewName("/score/step01/ScoreList.jsp");
    
    /*
    Map<String,Object> model = mv.getModel();
    Object scores = model.get("scores"); 
    Gson gson = new Gson();
    return gson.toJson(scores);
    */
    
    // 요청 핸들러에서 직접 콘텐츠를 출력할 때는 setContextType()이 작동되지 않는다.
    // => 해결책: HttpEntity 객체를 리턴하라!
    response.setContentType("text/json; charset=UTF-8");
    
    return new Gson().toJson(mv.getModel().get("scores"));
  }

}










