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
public class ScoreAdd01 {
  static Logger logger = Logger.getLogger(ScoreAdd01.class);
  
  @Autowired // setter 메서드 또는 인스턴스 변수에 선언할 수 있다.
  ScoreDao scoreDao;
  
  /* method 속성을 사용하여 클라이언트 요청 명령을 지정할 수 있다.
   * => 요청 명령을 지정하면 해당 명령이 들어올 때만 호출한다.
   * => 생략하면 모든 명령에 대해 호출한다.
   */
  @RequestMapping(value="/score/step01/add", method=RequestMethod.POST)
  public String execute(Score score, Map<String, Object> model)
      throws Exception {
    scoreDao.insert(score);
    return "redirect:list.do";
  }
  
  /* 요청 정보를 파라미터의 값으로 변환해줄 변환기(커스텀 에디터) 등록하기
   * 프런트 컨트롤러는 페이지 컨트롤러의 메서드를 호출하기 전에 
   * 요청정보 변환기를 등록할 기회를 페이지 컨트롤러에게 준다.
   * 
   * 개발자가 할 일 => 커스텀 에디터를 등록하는 함수를 생성한다. => @InitBinder 애노테이션으로 표시한다.
   */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    logger.debug("ScoreAdd의 initBinder() 호출됨");
    // 프런트 컨트롤러가 이 메서드를 호출할 때 커스텀 에디터를 등록할 수 있도록 WebDataBinder를 준다.
    // 우리는 이 객체를 사용하여 커스텀 에디터를 등록할 것이다.
    
    // 문자열을 java.util.Date 객체로 바꿔주는 커스텀 에디터를 스프링에서 제공한다. 그것을 사용.
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor customDateEditor = new CustomDateEditor(
        dateFormat, /* 요청 정보의 문자열 형식 지정*/ 
        true /* 빈 문자열 허용 여부 */);
    
    binder.registerCustomEditor(
        java.util.Date.class, /* 문자열을 어떤 타입의 값으로 바꿀지 지정한다.*/ 
        customDateEditor /* 커스텀 에디터 객체를 지정한다*/);
  }
}














