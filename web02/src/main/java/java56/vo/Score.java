/* Entity 역할
 * - 성적 정보를 표현하는 역할 => Value Object(VO)
 */
package java56.vo;

import java.io.Serializable;
import java.util.Date;

/* transient
 * - 지속성(Persistence)을 가질 필요가 없는 데이터에 대해 표시한다.
 * - 저장할 필요가 없는 데이터에 대해 지정한다.
 * - 시리얼 데이터로 출력되지 않는다.
 * - 다른 인스턴스 변수로 계산되는 값은 시리얼 데이터로 출력하지 않는다.
 */

public class Score implements Serializable  {
  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private int kor;
  private int eng;
  private int math;
  private Date execDate;
  transient private int total;
  transient private float average;

  public Score() {}

  public Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  public void compute() {
    this.total = this.kor + this.eng + this.math;
    this.average = this.total / 3.0f;
  }

  public int getTotal() {
    return this.total;
  }

  public float getAverage() {
    return this.average;
  }

  public Score setKor(int kor) {
    this.kor = kor;
    this.compute();
    return this;
  }

  public Score setEng(int eng) {
    this.eng = eng;
    this.compute();
    return this;
  }

  public Score setMath(int math) {
    this.math = math;
    this.compute();
    return this;
  }

  public String getName() {
    return name;
  }

  public Score setName(String name) {
    this.name = name;
    return this;
  }

  public int getKor() {
    return kor;
  }

  public int getEng() {
    return eng;
  }

  public int getMath() {
    return math;
  }

  public int getNo() {
    return no;
  }

  public Score setNo(int no) {
    this.no = no;
    return this;
  }
  
  public Date getExecDate() {
    return execDate;
  }

  public Score setExecDate(Date execDate) {
    this.execDate = execDate;
    return this;
  }

  @Override
  public String toString() {
    return "Score [no=" + no + ", name=" + name + ", kor=" + kor + ", eng="
        + eng + ", math=" + math + ", execDate=" + execDate + "]";
  }

}


















