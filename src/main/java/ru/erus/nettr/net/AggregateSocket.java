package ru.erus.nettr.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AggregateSocket {

    private boolean      server = false;
    private Socket       socket;
    private ServerSocket serverSocket;
    private String       ip;
    private int          port;

    public AggregateSocket(String ip, int port) {
        this.ip = ip;
        this.port = port;
        server = false;
    }

    public AggregateSocket(int port) {
        this.port = port;
        server = true;
    }

    public void init() {
        try {
            if (server) {
                serverSocket = new ServerSocket(port);
            } else {
                socket = new Socket(ip, port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Socket accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public void close() {
        try {
            if (server) {
                serverSocket.close();
            } else {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServer() {
        return serverSocket;
    }

    public boolean isServer() {
        return server;
    }

    public Socket getClient() {
        return socket;
    }

    public String getInetAddress() {
//        return server?serverSocket.getInetAddress():;
        return "";
    }

}
