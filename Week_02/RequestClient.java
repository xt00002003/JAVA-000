/**
 * @author teng.xue
 * @date 2020/10/27
 * @since
 **/
package week2;

import okhttp3.Response;
import week2.vo.GetRequest;


public class RequestClient {

    private static final String URL="http://localhost:8088/";
    public static void main(String[] args) throws Exception {
        OkHttpUtil okHttpUtil=OkHttpUtil.getInstance();
        GetRequest getRequest=new GetRequest();
        getRequest.setUrl(URL+"api/hello");
        Response result=okHttpUtil.get(getRequest);
        String strResult=result.body().string();
        System.out.println(strResult);
    }
}
