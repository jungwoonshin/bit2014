package java56.servlets.step01;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("/score/step01/delete.do")
public class ScoreDelete  implements PageController {
  @Autowired
  ScoreDao scoreDao;
  
  @Override
  public String execute(Map<String, String[]> params, Map<String, Object> model)
      throws Exception {
    int no = Integer.parseInt(params.get("no")[0]);
    scoreDao.delete(no);
    
    return "redirect:list.do";
  }
}














