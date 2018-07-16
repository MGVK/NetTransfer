package ru.erus.nettr.net;

import ru.erus.nettr.data.DataBundle;

import java.io.IOException;
import java.net.Socket;

public class Transmitter {

    public static void transfer(Socket socket, String ip, int port, DataBundle dataBundle, boolean close) {

        try {

            System.out.println("send to " + socket.getInetAddress());
            dataBundle.writeToStream(socket.getOutputStream());
            if (close && !socket.isClosed()) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

