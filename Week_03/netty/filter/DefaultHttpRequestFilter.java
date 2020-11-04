package week2.netty.filter;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * project_name: learn-architecture
 * package: week2.netty.filter
 * describe: TODO
 *
 * @author : xue.teng
 * creat_date: 2020-11-03
 * creat_time: 18:58
 **/
public class DefaultHttpRequestFilter extends ChannelInboundHandlerAdapter implements HttpRequestFilter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        filter(fullRequest,ctx);
        ReferenceCountUtil.release(msg);
}

    /**
     * 拦截浏览器二次请求。
     * @param fullRequest
     * @param ctx
     */
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri=fullRequest.uri();
        if (null!=uri && uri.contains("favicon.ico")){
            System.out.println("异常uri:"+uri);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();

        }else {
            //不是浏览器二期请求，放行
            ctx.fireChannelRead(fullRequest);
        }

    }
}
