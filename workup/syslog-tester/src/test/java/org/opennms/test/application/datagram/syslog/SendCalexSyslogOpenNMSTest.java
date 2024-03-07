package org.opennms.test.application.datagram.syslog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SendCalexSyslogOpenNMSTest {
	private SimpleLogSender client;
	
	public static final int SYSLOG_SERVER_PORT=4445;
	
	public static final int SYSLOG_OPENNMS_PORT=10162;
	
   public static final boolean USE_SIMPLE_LOG_SERVER=false;

	@Before
	public void setup() throws IOException {
		String host="localhost";
		
		int port;
		
		if (USE_SIMPLE_LOG_SERVER) {
		   port=SYSLOG_SERVER_PORT;
		   new SimpleLogServer(port).start();
		} else {
		   port = SYSLOG_OPENNMS_PORT;
		}

		client = new SimpleLogSender(host, port);
	}

	@Test
	public void sendMessageTest() {
	   
	     String logEntry = "<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL";

	      CalexAxosEventLog eventParser = new CalexAxosEventLog();
	      
	      boolean match = eventParser.parseLogEntry(logEntry);
	      assertTrue(match);
	      
	      System.out.println("Event Parser values parsed from log: "+eventParser.toString());
	      
	      // test that the class outputs the same log as parsed
	      String receivedLogEntry = eventParser.toLogEntry(true);
	      
	      System.out.println("Event parser toLogEntry: "+ receivedLogEntry);

	   
		client.sendMessage(receivedLogEntry);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
	   if (USE_SIMPLE_LOG_SERVER) {
	      stopEchoServer();
	   }
	
		client.close();
	}

	private void stopEchoServer() {
		client.sendMessage("SHUTDOWN");
	}

}