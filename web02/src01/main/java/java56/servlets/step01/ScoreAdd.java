package java56.servlets.step01;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/score/step01/add.do")
public class ScoreAdd implements PageController {
  @Autowired // setter 메서드 또는 인스턴스 변수에 선언할 수 있다.
  ScoreDao scoreDao;
  
  @Override
  public String execute(Map<String, String[]> params, Map<String, Object> model)
      throws Exception {
    Score score = new Score();
    score.setName(params.get("name")[0]);
    score.setKor(Integer.parseInt(params.get("kor")[0]));
    score.setEng(Integer.parseInt(params.get("eng")[0]));
    score.setMath(Integer.parseInt(params.get("math")[0]));
    
    scoreDao.insert(score);
    return "redirect:list.do";
  }
}














