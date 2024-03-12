package org.opennms.test.application.datagram.syslog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SendCalexSyslogTranslatorOpenNMSTest {
   private SimpleLogSender client;

   public static final int SYSLOG_SERVER_PORT = 4445;

   public static final int SYSLOG_OPENNMS_PORT = 10514;

   public static final boolean USE_SIMPLE_LOG_SERVER = false;

   @Before
   public void setup() throws IOException {
      String host = "localhost";

      int port;

      if (USE_SIMPLE_LOG_SERVER) {
         port = SYSLOG_SERVER_PORT;
         new SimpleLogServer(port).start();
      } else {
         port = SYSLOG_OPENNMS_PORT;
      }

      client = new SimpleLogSender(host, port);
   }

   @Test
   public void sendMessageTest() {

      String logEntry = "<187>Feb 28 16:35:08 mk901-olt-3 notfmgrd[6215]: [1][1][A][6215] [23] Id:5029, Syslog-Severity:3, Perceived-Severity:MINOR, Name:ont-dying-gasp, Category:PON Cause:ONT is out of service due to loss of power event detected by the ONT., Details:SerialNo=D83F0D, Xpath:/config/system/ont[ont-id='7586'] Address:NULL, Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL, Secondary-element:NULL";

      CalexAxosEventLog eventParser = new CalexAxosEventLog();

      boolean match = eventParser.parseLogEntry(logEntry);
      assertTrue(match);

      System.out.println("Event Parser values parsed from log: " + eventParser.toString());

      // test that the class outputs the same log as parsed
      String receivedLogEntry = eventParser.toLogEntry(true);

      System.out.println("Event parser toLogEntry: " + receivedLogEntry);

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