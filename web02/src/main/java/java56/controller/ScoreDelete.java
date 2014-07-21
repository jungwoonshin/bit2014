package java56.controller;

import java.util.Map;

import java56.dao.ScoreDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScoreDelete {
  @Autowired
  ScoreDao scoreDao;
  
  @RequestMapping(
      value="/score/step01/delete" 
      ,method=RequestMethod.GET
      //,params="no" /*요청 정보에 no가 있을 때만 메서드를 호출하라!*/
      //,headers="okok" /*요청 헤더에 Host라는 헤더가 있을 때만 호출하라!*/
  )
  public String execute(int no, Map<String, Object> model)
      throws Exception {
    scoreDao.delete(no);
    
    return "redirect:list.do";
  }
}














