package org.opennms.test.application.datagram.syslog;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;
    private int port;

    public EchoClient(String host, int port) {
        try {
        	this.port=port;
            socket = new DatagramSocket();
            address = InetAddress.getByName(host);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sendEcho(String msg) {
        DatagramPacket packet = null;
        try {
            buf = msg.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}
