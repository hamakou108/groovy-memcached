package com.hamakou

import java.nio.channels.ServerSocketChannel

/**
 * MemcachedServer is used to run memcached server.
 */
class MemcachedServer {
    /**
     * The address to connects from a client.
     */
    String address

    /**
     * The port to connects from a client.
     */
    Integer port

    /**
     * Constructs a server object with the address and the port to connect.
     *
     * @param address the address to connects from a client; must not be {@code null}
     * @param port the port to connects from a client; must not be {@code null}
     */
    def MemcachedServer(String address, Integer port) {
        this.address = address
        this.port = port
    }

    /**
     * Starts to await a request from a client.
     *
     * <p> This method binds the address and the port to the socket, and then await a request from a
     * client. When the socket accepts a request, it calls a non-blocking task to process datas and
     * create response.
     */
    def start() {
        ServerSocketChannel.open().withCloseable { ssc ->
            ssc.socket().bind(new InetSocketAddress(address, port))
            System.out.println("Started server on port " + port)

            while (true) {
                // a "non-blocking" call which waits until a connection is requested
                Socket socket = ssc.socket().accept()
                new Thread(new MemcachedServerTask(socket)).start();
            }
        }
    }

    /**
     * Creates an own object with the specified address and port.
     *
     * @param args command line arguments; not to used
     */
    static void main(String... args) {
        def m = new MemcachedServer("127.0.0.1", 12345)
        m.start()
    }
}
