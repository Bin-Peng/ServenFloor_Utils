package seven.utils.dto;

import java.util.List;

/**
 * ClassName: AuthServiceImpl2DTO <br/>
 * Description: <br/>
 * date: 2019/11/29 11:53 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class AuthServiceImpl2DTO {
    private String test1;
    private String test2;
    private List<String> stringList;

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }
}
