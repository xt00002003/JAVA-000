/**
 * @author teng.xue
 * @date 2020/11/2
 * @since
 **/
package week2.netty.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import week2.netty.filter.DefaultHttpRequestFilter;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyUrl;
    public HttpInboundInitializer(String proxyUrl){
        this.proxyUrl=proxyUrl;
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline c=socketChannel.pipeline();
        c.addLast(new HttpServerCodec());
        c.addLast(new HttpObjectAggregator(1024 * 1024));
        //添加过滤条件
        c.addLast(new DefaultHttpRequestFilter());
        c.addLast(new HttpInboundHandler(this.proxyUrl));

    }
}
