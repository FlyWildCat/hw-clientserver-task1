import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        try (final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            serverSocketChannel.bind(new InetSocketAddress(Constant.HOST, Constant.PORT));
            while (true) {
                try (SocketChannel socketChannel = serverSocketChannel.accept();) {
                    final ByteBuffer inBuf = ByteBuffer.allocate(2<<10);
                    while (socketChannel.isConnected()) {
                        int bytes = socketChannel.read(inBuf);
                        if (bytes < 0) break;
                        String str = new String(inBuf.array(), 0, bytes, StandardCharsets.UTF_8);
                        inBuf.clear();
                        System.out.println("Клент: " + str);
                        str = str.replace(" ", "");
                        socketChannel.write(ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8)));
                    }
                } catch (IOException | NumberFormatException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
