package java56.servlets.step01;

import java.util.Map;

/* 프론트 컨트롤러와 페이지 컨트롤러 사이의 호출 규칙이다.
 * - 호출자 => 프론트 컨트롤러
 * - 피호출자 => 페이지 컨트롤러
 * 
 */

public interface PageController {
  String execute(
      Map<String,String[]> params, Map<String,Object> model) throws Exception;
}
