package java56.servlets.step01;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String pageUrl = request.getServletPath();
    
    //스프링 ContextLoaderListener를 사용할 때, 빈 컨테이너 가져오는 방법
    ApplicationContext beanContainer = 
      (ApplicationContext) WebApplicationContextUtils
                              .getWebApplicationContext(this.getServletContext());
    
    PageController pageController = 
        (PageController)beanContainer.getBean(pageUrl);
    
    try {
      if (pageController == null) {
        throw new Exception("URL 요청을 처리할 수 없습니다.");
      }
      
      Map<String, String[]> paramMap = request.getParameterMap();
      Map<String,Object> model = new HashMap<String,Object>();
      String viewUrl = pageController.execute(paramMap, model);
      
      for (Entry<String,Object> item : model.entrySet()) {
        request.setAttribute(item.getKey(), item.getValue());
      }
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
      } else {
        response.setContentType("text/html; charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/score/step08/error");
      request.setAttribute("error", e);
      rd.forward(request, response);
    }
  }
}

















