package ru.mgvk.transferTest;

import ru.erus.nettr.Data.DataBundle;
import ru.erus.nettr.net.Reciever;
import ru.erus.nettr.net.Transmitter;

public class Test {

    public static void main(String[] args) {


        new Reciever(9999, (result, dataBundle) -> {
            System.out.println(result);
            System.out.println((dataBundle.getInteger("1", 0)));
            System.out.println(dataBundle.getString("3", ""));
        }).waitForRecieving();


        new Transmitter("127.0.0.1", 9999)
                .transfer(new DataBundle()
                        .putInteger("1", 1)
                        .putInteger("2", 2)
                        .putString("3", "OLOLO"));


    }


}
