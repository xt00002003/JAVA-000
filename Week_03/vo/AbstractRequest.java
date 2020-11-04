package week2.vo;

import lombok.Data;

import java.nio.charset.Charset;
import java.util.Map;

/**
 *
 * describe: 抽象的请求设置
 * @author : teng.xue
 **/
@Data
public abstract class AbstractRequest {

    /**
     * 地址
     */
    private String url;
    /**
     * header列表
     */
    private Map<String, String> headers;
    /**
     * 访问方法
     */
    private HttpMethod method;
    /**
     * 编码,默认utf-8
     */
    private Charset charset=Charset.forName("UTF-8");
    /**
     * 是否需要返回header列表.默认是false
     */
    private Boolean needReceiveHeaders=false;


}
