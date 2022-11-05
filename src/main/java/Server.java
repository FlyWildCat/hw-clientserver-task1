import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try (ServerSocket ss = new ServerSocket(Constant.PORT);
                 Socket socket = ss.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("server run");
                String input;
                while ((input = in.readLine()) != null) {
                    if (input.equals("end")) {
                        out.println(input);
                        ss.close();
                    }
                    out.println(fibonacci(Integer.parseInt(input)));
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getLocalizedMessage());
            }

        }
    }

    private static Long fibonacci(int index) {
        Long[] fibo = new Long[index+1];
        fibo[0] = 0L;
        fibo[1] = 1L;
        for (int i=2; i<fibo.length; i++) {fibo[i] = fibo[i - 1] + fibo[i - 2];}
        return fibo[index];
    }
}
