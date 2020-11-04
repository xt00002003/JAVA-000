package week2.vo;

import lombok.Data;

import java.util.Map;

/**
 * describe: post请求，使用key value 的参数
 *
 * @author : teng.xue
 * creat_date: 2018/10/13
 **/
@Data
public class PostRequestWithMap extends AbstractRequest {
    /**
     * 传入的参数
     */
    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }


}
