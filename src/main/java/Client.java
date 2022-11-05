import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        InetSocketAddress socketAddress = new InetSocketAddress(Constant.HOST, Constant.PORT);
        try(final SocketChannel socketChannel = SocketChannel.open();
            Scanner scanner = new Scanner(System.in)) {
            socketChannel.connect(socketAddress);
            final ByteBuffer inBuf = ByteBuffer.allocate(2<<10);
            String msg;
            while (true) {
                System.out.println("Введите строку с пробелами или 'end' для выхода.");
                msg = scanner.nextLine();
                if ("end".equals(msg)) break;
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                int bytes = socketChannel.read(inBuf);
                String str = new String(inBuf.array(), 0, bytes, StandardCharsets.UTF_8);
                System.out.println("Сервер: " + str);
                inBuf.clear();
            }
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
