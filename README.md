# NetTransfer
Small lib, that allow to transfer data throw tcp without coding sockets. All you need - is to init Reciever and Transmitter to transfer data.

Howto:

### Server:

    int port = 5555;
    
    NetTransfer transfer = new NetTransfer(port) //init server
                .send(new DataBundle().putInteger("0", 0))  //send data
                .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("1", -1))) //receive data
                .send(new DataBundle().putInteger("123", 101010))
                .receive((result, dataBundle) -> System.out.println(dataBundle.getString("Q", "")))
                .start(); //method to start server
                
                
                
    while (transfer.isWorking()) { //may be useful for console applications
        Thread.sleep(1000);
    }


### Client:

    String ip="127.0.0.1";
    int port = 5555;

    new NetTransfer(ip, port) // init client 
                    .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("0", 0))) //receive data
                    .send(new DataBundle().putInteger("1", 1).putInteger("2", 2)) //send data
                    .receive((result, dataBundle) -> System.out.println(dataBundle.getInteger("123", -1)))
                    .send(new DataBundle().putInteger("5", 5).putString("Q", "THIS IS STRING!!!"))
                    .start(); //start communication
