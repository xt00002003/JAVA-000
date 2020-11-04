/**
 * @author teng.xue
 * @date 2020/11/2
 * @since
 **/
package week2.netty.outbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import week2.OkHttpUtil;
import week2.vo.GetRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


public class HttpOutboundHandler {

    private String backendUrl;

    public HttpOutboundHandler(String backendUrl){
        this.backendUrl=backendUrl.endsWith("/")?backendUrl.substring(0,backendUrl.length()-1):backendUrl;
    }

    public void handle(final FullHttpRequest fullRequest,final ChannelHandlerContext ctx) {
        String url=this.backendUrl+fullRequest.uri();
        OkHttpUtil okHttpUtil=OkHttpUtil.getInstance();
        GetRequest request=new GetRequest();
        request.setUrl(url);
        Map<String,String> headers=new HashMap<>(1);
        headers.put("Connection","Keep-Alive");
        request.setHeaders(headers);
        okHttpUtil.asynGet(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                fetchGet(fullRequest,ctx,response);
            }
        });
    }

    private void fetchGet(final FullHttpRequest fullRequest,final ChannelHandlerContext ctx,Response response){
        FullHttpResponse fullHttpResponse = null;
        try {
//            String value = "hello,kimmking";
//            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
//            response.headers().set("Content-Type", "application/json");
//            response.headers().setInt("Content-Length", response.content().readableBytes());


            byte[] body = response.body().bytes();
//            System.out.println(new String(body));
//            System.out.println(body.length);

            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(response.headers().get("Content-Length")));

//            for (Header e : endpointResponse.getAllHeaders()) {
//                //response.headers().set(e.getName(),e.getValue());
//                System.out.println(e.getName() + " => " + e.getValue());
//            }

        } catch (Exception e) {
            e.printStackTrace();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }
}
