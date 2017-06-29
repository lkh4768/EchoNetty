import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by WES on 2017-06-29.
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{
    private static final Logger logger = LogManager.getLogger(EchoClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String str = "Netty rocks!";
        logger.info("Client write: " + str);
        ctx.writeAndFlush(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        logger.info("Client receive: " + msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage());
        ctx.close();
    }
}
