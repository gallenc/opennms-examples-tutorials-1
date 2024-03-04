package org.opennms.test.application.datagram.syslog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UDPLiveTest {
	private EchoClient client;

	@Before
	public void setup() throws IOException {
		new EchoServer().start();
		String host="localhost";
		int port=4445;
		client = new EchoClient(host, port);
	}

	@Test
	public void whenCanSendAndReceivePacket_thenCorrect1() {
		String echo = client.sendEcho("hello server");
		assertEquals("hello server", echo);
		echo = client.sendEcho("server is working");
		assertFalse(echo.equals("hello server"));
	}

	@After
	public void tearDown() {
		stopEchoServer();
		client.close();
	}

	private void stopEchoServer() {
		client.sendEcho("end");
	}

}