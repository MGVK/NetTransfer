package ru.mgvk.transferTest;

import ru.erus.nettr.data.DataBundle;
import ru.erus.nettr.net.NetTransfer;

public class TestServer {

    public static void main(String[] args) throws InterruptedException {

        int port = 5555;

        NetTransfer transfer = new NetTransfer(port)
                .send(new DataBundle().putInteger("0", 0))
                .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("1", -1)))
                .send(new DataBundle().putInteger("123", 101010))
                .receive((result, dataBundle) -> System.out.println(dataBundle.getString("Q", "")))
                .start();

        while (transfer.isWorking()) {
            Thread.sleep(1000);
        }

    }

}
