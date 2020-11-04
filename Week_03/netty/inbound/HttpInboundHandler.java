/**
 * @author teng.xue
 * @date 2020/11/2
 * @since
 **/
package week2.netty.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import week2.netty.outbound.HttpOutboundHandler;

public class HttpInboundHandler  extends ChannelInboundHandlerAdapter {

    private String proxyUrl;

    private HttpOutboundHandler handler;

    public HttpInboundHandler(String proxyUrl){
        this.proxyUrl=proxyUrl;
        handler=new HttpOutboundHandler(proxyUrl);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
//            String uri = fullRequest.uri();
//            //logger.info("接收到的请求url为{}", uri);
//            if (uri.contains("/test")) {
//                handlerTest(fullRequest, ctx);
//            }
            System.out.println("执行业务处理");

            handler.handle(fullRequest, ctx);

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

}
