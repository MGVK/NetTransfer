package ru.mgvk.transferTest;

import ru.erus.nettr.data.DataBundle;
import ru.erus.nettr.net.NetTransfer;

import java.io.IOException;

public class TestClient {

    public static void main(String[] args) throws IOException, InterruptedException {


        for (int i = 0; i < 1000; i++) {
            new NetTransfer("127.0.0.1", 5555)
                    .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("0", 0)))
                    .send(new DataBundle().putInteger("1", 1).putInteger("2", 2))
                    .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("123", -1)))
                    .send(new DataBundle().putInteger("5", 5).putString("Q", "THIS IS STRING!!!"))
                    .start();
        }

        Thread.sleep(10000);

    }


}
