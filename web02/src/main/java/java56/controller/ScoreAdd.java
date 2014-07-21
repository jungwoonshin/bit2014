package java56.controller;

import java.util.Map;
import java56.dao.ScoreDao;
import java56.vo.Score;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScoreAdd {
  static Logger logger = Logger.getLogger(ScoreAdd.class);
  
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
}














