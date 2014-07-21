package java56.controller;

import java56.dao.ScoreDao;

import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
public class ScoreList {
  static Logger logger = Logger.getLogger(ScoreList.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  /* 페이지 컨트롤러에서 직접 문자열을 출력 : HttpEntity
   *  
   */
  @RequestMapping("/score/step01/list") 
  public HttpEntity<String> execute(
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
    
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "text/plain; charset=UTF-8");
    
    return new HttpEntity<String>(
        new Gson().toJson(mv.getModel().get("scores")),
        headers);
  }

}










