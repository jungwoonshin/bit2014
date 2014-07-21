package java56.controller;

import java.text.SimpleDateFormat;
import java.util.Map;

import java56.dao.ScoreDao;
import java56.vo.Score;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
@RequestMapping("/score/step01/update")
public class ScoreUpdate01 {
  static Logger logger = Logger.getLogger(ScoreUpdate01.class);
  
  @Autowired
  ScoreDao scoreDao;
  
  //@RequestMapping(value="/score/step01/update", method=RequestMethod.GET)
  @RequestMapping(method=RequestMethod.GET)
  public String detail(int no, Map<String, Object> model)
      throws Exception {
    model.put("score", scoreDao.selectOne(no));
    return "/score/step01/scoreupdateform.jsp";
  }
  
  //@RequestMapping(value="/score/step01/update", method=RequestMethod.POST)
  @RequestMapping(method=RequestMethod.POST)
  public String update(Score score, Map<String, Object> model)
      throws Exception {
    scoreDao.update(score);
    return "redirect:list.do";
  }
  
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    logger.debug("ScoreUpdate의 initBinder() 호출됨");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor customDateEditor = new CustomDateEditor(
        dateFormat, /* 요청 정보의 문자열 형식 지정*/ 
        true /* 빈 문자열 허용 여부 */);
    
    binder.registerCustomEditor(
        java.util.Date.class, /* 문자열을 어떤 타입의 값으로 바꿀지 지정한다.*/ 
        customDateEditor /* 커스텀 에디터 객체를 지정한다*/);
  }
}














