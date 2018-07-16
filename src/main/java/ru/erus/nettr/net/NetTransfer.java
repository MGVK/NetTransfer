package ru.erus.nettr.net;

import ru.erus.nettr.data.DataBundle;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class NetTransfer {

    private static int count = 0;
    String                    ip      = "";
    int                       port    = 0;
    AggregateSocket           socket;
    ArrayList<SocketRunnable> actions = new ArrayList<>();
    private boolean works;

    public NetTransfer(int port) {
        this.port = port;
        socket = new AggregateSocket(port);
    }

    public NetTransfer(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        socket = new AggregateSocket(ip, port);
    }

    public NetTransfer receive(OnRecievingCompleted onRecievingCompleted) {
        actions.add((s) -> Reciever.recieve(s, port, onRecievingCompleted));
        return this;
    }

    public NetTransfer send(DataBundle dataBundle) {
        actions.add((s) -> Transmitter.transfer(s, ip, port, dataBundle, false));
        return this;
    }

    public void finish() {

    }

    public NetTransfer start() {
        if (!socket.isServer()) {
            actions.add((s) -> {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        new NetWorker(socket.isServer(), actions, count++).start();
        works = true;
        return this;
    }

    void stop() {
        works = false;
    }

    public boolean isWorking() {
        return works;
    }

    private void callOnFinish() {
        if (!socket.isServer()) {
            works = false;
        }
    }

    private class NetWorker extends Thread {

        ArrayList<SocketRunnable> actions;
        private int     index  = 0;
        private boolean server = false;

        public NetWorker(boolean server, ArrayList<SocketRunnable> actions, int index) {
            this.actions = actions;
            this.index = index;
            this.server = server;
        }

        @Override
        public void run() {
            System.out.println("Start " + index + " thread");

            if (actions != null && !actions.isEmpty()) {

                if (socket.isServer()) {
                    while (works) {
                        try {
                            if (socket.getServer() == null) socket.init();
                            Socket client = socket.getServer().accept();
                            if (client != null) {
                                new Thread(() -> work(client)).start();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (socket.getClient() == null) socket.init();
                    work(socket.getClient());
                }

            }
        }

        void work(Socket socket) {
            int i = 0;


            for (SocketRunnable action : actions) {

                System.out.println("Thread" + index + ": starting " + (++i) + " action");
                action.run(socket);
            }

            callOnFinish();

        }
    }


}
