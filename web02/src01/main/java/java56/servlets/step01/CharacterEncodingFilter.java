package java56.servlets.step01;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* Filter
 * - 서블릿 실행 전/후에 호출된다.
 * - 특정 URL에 대해 작업을 추가하거나 제거하기가 쉽다.
 */

public class CharacterEncodingFilter implements Filter {
  FilterConfig config;
  
  // 필터 객체가 생성될 때 딱 한 번 호출된다.
  // => 필터 객체는 딱 한 개만 생성된다.
  @Override
  public void init(FilterConfig config) throws ServletException {
    this.config = config;
  }

  // 웹 애플리케이션이 멈추거나 서블릿 컨테이너가 종료될 때 딱 한 번 호출된다.
  // => 마무리 작업 : 사용했던 자원 해제
  @Override
  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain nextFilter) throws IOException, ServletException {
    
    // 다음 필터(서블릿 포함)를 수행하기 전에 해야할 작업을 기술
    request.setCharacterEncoding(config.getInitParameter("charset"));
    
    // 다음 필터(서블릿 포함)를 호출 
    nextFilter.doFilter(request, response);
    
    
    // 다음 필터 실행이 끝난 다음 수행할 작업을 기술

  }
}







