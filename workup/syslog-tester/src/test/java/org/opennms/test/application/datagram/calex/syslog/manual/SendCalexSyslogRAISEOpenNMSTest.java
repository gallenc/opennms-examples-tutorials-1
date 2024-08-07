package org.opennms.test.application.datagram.calex.syslog.manual;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opennms.test.application.datagram.syslog.CalexAxosEventLog;
import org.opennms.test.application.datagram.syslog.SimpleLogSender;
import org.opennms.test.application.datagram.syslog.SimpleLogServer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SendCalexSyslogRAISEOpenNMSTest {
   private SimpleLogSender client;

   public static final int SYSLOG_SERVER_PORT = 4445;

   public static final int SYSLOG_OPENNMS_PORT = 10514;

   public static final boolean USE_SIMPLE_LOG_SERVER = false;

   public static final boolean CHANGE_LOG_TIME_TO_TODAY = true;

   public static final boolean GENERATE_TIMESTAMP = true;

   public static final boolean USE_SYSLOG_PRI = false;

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

      String logEntry = "Feb 28 00:36:00 glo204-olt-1 notfmgrd[6203]: [1][1][A][6203] [23] Id:5030, Syslog-Severity:3, Perceived-Severity:MINOR, Name:high-laser-bias, Category:PON Cause:High laser bias., Details:SerialNo=E7D3FA, Xpath:/config/system/ont[ont-id='212064'] Address:NULL, Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL, Secondary-element:NULL";

      CalexAxosEventLog eventParser = new CalexAxosEventLog();

      boolean match = eventParser.parseLogEntry(logEntry);
      assertTrue(match);

      System.out.println("Event Parser values parsed from log: " + eventParser.toString());

      if (CHANGE_LOG_TIME_TO_TODAY) {
         // change the date time to be in same time as local time
         System.out.println("Parsed date time (from example log) : " + eventParser.getDay() + " " + eventParser.getMonth() + " " + eventParser.getTimestampStr());

         LocalDate today = LocalDate.now();
         Month month = today.getMonth();
         String mon = month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
         int day = today.getDayOfMonth();

         eventParser.setMonth(mon);
         eventParser.setDay(Integer.toString(day));

         System.out.println("Log with revised date time : " + eventParser.getDay() + " " + eventParser.getMonth() + " " + eventParser.getTimestampStr());
      }

      String receivedLogEntry = eventParser.toLogEntry(USE_SYSLOG_PRI);

      System.out.println("Event parser toLogEntry: " + receivedLogEntry);
      
      if (GENERATE_TIMESTAMP) {
         SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
         Date date = new Date();
         eventParser.setTimestampStr(df.format(date));
      }

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