package com.hamakou

import java.nio.charset.Charset

import com.hamakou.*

/**
 * MemcachedServerTask provides a task to create response.
 */
class MemcachedServerTask implements Runnable {
    Socket socket;

    /**
     * Constructs a task with a socket.
     */
    def MemcachedServerTask(Socket socket) {
        this.socket = socket
    }

    /**
     * Processes datas and Sends a response dependent on a request.
     *
     * @throws IOException when the input or output stream has a trouble
     */
    @Override
    void run() {
        try {
            // open up IO streams
            new BufferedReader(
                    new InputStreamReader(socket.getInputStream())).withCloseable { reader ->
                socket.getOutputStream().withCloseable { writer ->
                    // receives a request from client and convert to a String object
                    String line;
                    String msg = "";
                    while ((line = reader.readLine()) != null) {
                        if (line.isEmpty()) {
                            break;
                        }
                        msg += line + "\n";
                    }

                    // parses a request message and outputs a response as a byte array
                    Request request = new MemcachedRequest(msg)
                    Response response = new MemcachedResponse(request)
                    writer.write(response.generateMsg())
                    writer.flush()

                    // close IO streams, then socket
                    System.out.println("Closing connection with client");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
