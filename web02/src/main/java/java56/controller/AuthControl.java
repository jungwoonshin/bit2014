package java56.controller;

import java56.dao.StudentDao;
import java56.vo.Student;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthControl {
	 static Logger logger = Logger.getLogger(ScoreList.class);

	@Autowired
	StudentDao studentDao; 
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String form(){
		return "/auth/form.jsp";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			String email,
			String password,
			HttpSession session) throws Exception{
		Student student = studentDao.exist(email, password);
		if(student!=null){
			session.setAttribute("loginUser", student); // 세션에 Student 객체 보관.
			logger.debug("==================>>>  students!=null");
			return "redirect:../score/step02/list.do";
		
		} else {
			session.invalidate(); // 로그인 실패 세션 무효화 : 세션 객체를 삭제한다. 
			logger.debug("==================>>>  students==null");
			return "redirect:login.do";
		}
	}
}
