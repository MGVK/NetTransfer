package ru.erus.nettr.net;

import ru.erus.nettr.data.DataBundle;

import java.io.IOException;
import java.net.Socket;

public class Transmitter {

    private String ip   = "";
    private int    port = 0;

    public Transmitter(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void transfer(DataBundle dataBundle) {

        try {
            Socket socket = new Socket(ip, port);

            dataBundle.writeToStream(socket.getOutputStream());
            if (!socket.isClosed()) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

