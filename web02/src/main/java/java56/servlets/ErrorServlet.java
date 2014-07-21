package java56.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 다른 서블릿에서 오류가 발생되면 이 서블릿으로 오류 처리를 위임한다.
 * => 다른 서블릿에서 발생한 오류를 출력하는 역할!
 */

@WebServlet("/score/step08/error")
public class ErrorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"UTF-8\">");
    out.println("<title>실행오류!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p>실행 중 오류가 발생하였습니다!</p>");
    
    out.println("<pre>");
    Exception e = (Exception) request.getAttribute("error");
    e.printStackTrace(out);
    out.println("</pre>");
    
    out.println("</body>");
    out.println("</html>");
  
  }
}













