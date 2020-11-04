package week2;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import week2.vo.*;

import java.io.IOException;
import java.util.Map;

/**
 * project_name: common-core
 * package: com.hongli.com.hongli.lahuobao
 * describe: http工具类
 *
 * @author : teng.xue
 * creat_date: 2018/10/13
 **/
public class OkHttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtil.class);

    private static OkHttpUtil okHttpUtil;

    private OkHttpClient okHttpClient;

    private OkHttpUtil(OkHttpClient.Builder defaultBuilder) {
        this.okHttpClient = defaultBuilder.build();
    }

    /**
     * 使用默认的Builder创建工具类
     * @return
     */
    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                //二次检查
                if (okHttpUtil == null) {
                    OkHttpClient.Builder defaultBuilder=new OkHttpClient.Builder();
                    okHttpUtil = new OkHttpUtil(defaultBuilder);
                }
            }
        }
        return okHttpUtil;
    }

    /**
     * 自定义Builder创建工具类
     * @param defaultBuilder
     * @return
     */
    public static OkHttpUtil getInstance(OkHttpClient.Builder defaultBuilder) {
        if (okHttpUtil == null) {
            synchronized (OkHttpUtil.class) {
                //二次检查
                if (okHttpUtil == null) {

                    okHttpUtil = new OkHttpUtil(defaultBuilder);
                }
            }
        }
        return okHttpUtil;
    }

    /**
     * 异步get请求
     *
     * @param getRequest
     * @param callback
     */
    public void asynGet(GetRequest getRequest, Callback callback) {
        getRequest.setMethod(HttpMethod.GET);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);
    }

    /**
     * 异步put请求
     *
     * @param getRequest
     * @param callback
     */
    public void asynPut(GetRequest getRequest, Callback callback) {
        getRequest.setMethod(HttpMethod.PUT);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);

    }

    /**
     * 异步delete请求
     *
     * @param getRequest
     * @param callback
     */
    public void asynDelete(DeleteRequest getRequest, Callback callback) {
        getRequest.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);

    }

    /**
     * 同步get请求
     *
     * @param getRequest
     */
    public Response get(GetRequest getRequest) throws IOException {
        getRequest.setMethod(HttpMethod.GET);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    /**
     * 同步put请求
     *
     * @param getRequest
     */
    public Response put(GetRequest getRequest) throws IOException {
        getRequest.setMethod(HttpMethod.PUT);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

    /**
     * 同步put请求
     *
     * @param getRequest
     */
    public Response delete(DeleteRequest getRequest) throws IOException {
        getRequest.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(getRequest);
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

    /**
     * 异步post请求
     *
     * @param postRequestWithMap
     * @param callback
     */
    public void asynPostWithMap(PostRequestWithMap postRequestWithMap, Callback callback) {
        postRequestWithMap.setMethod(HttpMethod.POST);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);

    }

    /**
     * 异步put请求
     *
     * @param postRequestWithMap
     * @param callback
     */
    public void asynPutWithMap(PostRequestWithMap postRequestWithMap, Callback callback) {
        postRequestWithMap.setMethod(HttpMethod.PUT);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);

    }

    /**
     * 异步delete请求
     *
     * @param postRequestWithMap
     * @param callback
     */
    public void asynDeleteWithMap(PostRequestWithMap postRequestWithMap, Callback callback) {
        postRequestWithMap.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);

    }

    /**
     * 同步post请求
     *
     * @param postRequestWithMap
     */
    public Response postWithMap(PostRequestWithMap postRequestWithMap) throws IOException {
        postRequestWithMap.setMethod(HttpMethod.POST);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

    /**
     * 同步put请求
     *
     * @param postRequestWithMap
     */
    public Response putWithMap(PostRequestWithMap postRequestWithMap) throws IOException {
        postRequestWithMap.setMethod(HttpMethod.PUT);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

    /**
     * 同步delete请求
     *
     * @param postRequestWithMap
     */
    public Response deleteWithMap(PostRequestWithMap postRequestWithMap) throws IOException {
        postRequestWithMap.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(postRequestWithMap);
        Call call = okHttpClient.newCall(request);
        return call.execute();

    }

    /**
     * 异步json请求
     *
     * @param postRequestWithJson
     * @param callback
     */
    public void asynPostWithJson(PostRequestWithJson postRequestWithJson, Callback callback) {
        postRequestWithJson.setMethod(HttpMethod.POST);
        Request request = convertRequest(postRequestWithJson);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);
    }

    /**
     * 异步json请求
     *
     * @param postRequestWithJson
     * @param callback
     */
    public void asynPutWithJson(PostRequestWithJson postRequestWithJson, Callback callback) {
        postRequestWithJson.setMethod(HttpMethod.PUT);
        Request request = convertRequest(postRequestWithJson);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);
    }

    /**
     * 异步json请求
     *
     * @param postRequestWithJson
     * @param callback
     */
    public void asynDeleteWithJson(PostRequestWithJson postRequestWithJson, Callback callback) {
        postRequestWithJson.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(postRequestWithJson);
        Call call = okHttpClient.newCall(request);
        //加入队列 异步操作
        call.enqueue(callback);
    }

    /**
     * 同步json请求
     *
     * @param postRequestWithJson
     */
    public Response postWithJson(PostRequestWithJson postRequestWithJson) throws IOException {
        postRequestWithJson.setMethod(HttpMethod.POST);
        Request request = convertRequest(postRequestWithJson);
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    /**
     * 同步json请求
     *
     * @param postRequestWithJson
     */
    public Response putWithJson(PostRequestWithJson postRequestWithJson) throws IOException {
        Request request = convertRequest(postRequestWithJson);
        postRequestWithJson.setMethod(HttpMethod.PUT);
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    /**
     * 同步json请求
     *
     * @param postRequestWithJson
     */
    public Response deleteWithJson(PostRequestWithJson postRequestWithJson) throws IOException {
        postRequestWithJson.setMethod(HttpMethod.DELETE);
        Request request = convertRequest(postRequestWithJson);
        Call call = okHttpClient.newCall(request);
        return call.execute();
    }

    private static Request convertRequest(AbstractRequest request) {
        final Request.Builder requestBuilder = new Request.Builder();
        final FormBody.Builder formBodyBuilder = new FormBody.Builder();
        final Map<String, String> headers = request.getHeaders();
        //设置头部信息
        if (headers != null) {
            headers.forEach((key, value) -> {
                requestBuilder.addHeader(key, value);
            });
        }
        //普通post请求
        if (request instanceof PostRequestWithMap) {
            PostRequestWithMap postRequestWithMap = (PostRequestWithMap) request;
            final Map<String, String> params = postRequestWithMap.getParams();
            if (null != params) {
                if (params != null) {
                    params.forEach((key, value) -> {
                        formBodyBuilder.add(key, value);
                    });
                }
            }
            final FormBody requestBody = formBodyBuilder.build();
            return requestBuilder.url(postRequestWithMap.getUrl()).method(postRequestWithMap.getMethod().name(),
                    requestBody).build();
        }
        //json请求
        if (request instanceof PostRequestWithJson) {
            PostRequestWithJson postRequestWithJson = (PostRequestWithJson) request;
            Object obj = postRequestWithJson.getObj();
            if (null != obj) {
                String jsonStr = JSON.toJSONString(obj);
                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , jsonStr);
                if (postRequestWithJson.getMethod().equals(HttpMethod.POST)) {
                    return requestBuilder.url(postRequestWithJson.getUrl()).method(postRequestWithJson.getMethod().name(), requestBody).post(requestBody).build();
                }
                if (postRequestWithJson.getMethod().equals(HttpMethod.PUT)) {
                    return requestBuilder.url(postRequestWithJson.getUrl()).method(postRequestWithJson.getMethod().name(), requestBody).put(requestBody).build();
                }
                if (postRequestWithJson.getMethod().equals(HttpMethod.DELETE)) {
                    return requestBuilder.url(postRequestWithJson.getUrl()).method(postRequestWithJson.getMethod().name(), requestBody).delete(requestBody).build();
                }
            }
        }
        if (request instanceof DeleteRequest) {
            if (request.getMethod().equals(HttpMethod.DELETE)) {
                return requestBuilder.url(request.getUrl()).delete().build();
            }
        }
        return requestBuilder.url(request.getUrl()).build();
    }


}

