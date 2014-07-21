package java56.controller;

import java.util.Map;
import java56.dao.ScoreDao;
import java56.vo.Score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/* 스프링 규격에 맞추어 페이지 컨트롤러 제작
 * 1) 인터페이스나 클래스를 상속 받을 필요가 없다.
 * 2) 웹 브라우저의 요청이 들어 왔을 때 호출될 메서드는 @RequestMapping 애노테이션을 붙인다.
 *    => 즉 프런트 컨트롤러에게 요청이 들어 왔을 때 어떤 메서드를 호출해야 하는지 알려주는 용도.
 */
@Component("/score/step01/add.do")
public class ScoreAdd {
  @Autowired // setter 메서드 또는 인스턴스 변수에 선언할 수 있다.
  ScoreDao scoreDao;
  
  @RequestMapping
  public String execute(Score score, Map<String, Object> model)
      throws Exception {
    scoreDao.insert(score);
    return "redirect:list.do";
  }
}














