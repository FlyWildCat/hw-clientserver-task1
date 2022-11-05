import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket(Constant.HOST, Constant.PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)) {
            String msg;
            while (true) {
                System.out.println("Введите элемент последовательности Фибоначчи (>=2) или 'end' для выхода.");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) break;
                System.out.println("Сервер: " + in.readLine());
            }
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
