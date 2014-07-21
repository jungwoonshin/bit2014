package java56.controller;

import java.util.Map;
import java56.dao.ScoreDao;
import java56.vo.Score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component("/score/step01/update.do")
public class ScoreUpdate {
  @Autowired
  ScoreDao scoreDao;
  
  @RequestMapping
  public String execute(Score score, Map<String, Object> model)
      throws Exception {
    if (score.getName() == null) { // 변경폼에서 값이 넘어오는 것이 아니다.
      model.put("score", scoreDao.selectOne(score.getNo()));
      return "/score/step01/scoreupdateform.jsp";
      
    } else {
      scoreDao.update(score);
      return "redirect:list.do";
    }
  }
}














