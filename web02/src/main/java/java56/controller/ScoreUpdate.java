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
@RequestMapping("/score/step01/update")
public class ScoreUpdate {
  static Logger logger = Logger.getLogger(ScoreUpdate.class);
  
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
}














