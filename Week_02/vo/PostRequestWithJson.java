package week2.vo;

import lombok.Data;

/**

 * describe: post请求，使用key value 的参数
 * @author : teng.xue
 **/
@Data
public class PostRequestWithJson extends AbstractRequest {
    /**
     * 请求参数
      */
    private Object obj;

    public Object getObj() {
        return obj;
    }


}
