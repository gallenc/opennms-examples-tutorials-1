package org.opennms.test.application.datagram.syslog;

import static org.junit.Assert.*;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParseEventsFileTest {

   public boolean SEND_EVENT_TO_OPENNMS = true;

   private SimpleLogSender client;

   public static final int SYSLOG_OPENNMS_PORT = 10514;

   @Before
   public void setup() throws IOException {
      String host = "localhost";

      int port;

      if (SEND_EVENT_TO_OPENNMS) {
         port = SYSLOG_OPENNMS_PORT;
         client = new SimpleLogSender(host, port);
      }

   }

   @After
   public void tearDown() {
      if (client != null)
         client.close();
   }

   @Test
   public void test() {
      Scanner scanner = null;

      PrintWriter hostsFileWriter = null;
      PrintWriter lteMappingFileWriter = null;

      SortedMap<String, String> hostMap = new TreeMap<>();

      // lteSerialNo, lteHostname
      SortedMap<String, String> oltLteMapping = new TreeMap<>();

      IncrimentingIpAddress ipAddress = new IncrimentingIpAddress("172.20.0.150");
      try {
         // file for discovered hostnames
         File hostNamesFile = new File("./target/hostnames.txt");
         hostNamesFile.delete();
         hostNamesFile.createNewFile();
         hostsFileWriter = new PrintWriter(hostNamesFile);

         // file for discovered lte mappings
         File lteMappingFile = new File("./target/lteMappings.txt");
         lteMappingFile.delete();
         lteMappingFile.createNewFile();
         lteMappingFileWriter = new PrintWriter(lteMappingFile);

         scanner = new Scanner(new File("./src/test/resources/sampleLogs1.csv"));

         int logCount = 0;
         int calexLogSuccess = 0;
         int otherLogFullSuccess = 0;
         int otherLogPartialSuccess = 0;
         int failedParse = 0;

         while (scanner.hasNextLine()) {
            String logEntry = scanner.nextLine();
            logCount++;
            String nodeName = null;

            // try calexEventLog parser
            CalexAxosEventLog calexAxosEventLog = new CalexAxosEventLog();
            boolean parsed = calexAxosEventLog.parseLogEntry(logEntry);

            if (parsed) {
               nodeName = calexAxosEventLog.getNodename();

               // get mapping between lte and ont from serial number
               String details = calexAxosEventLog.getDetails();
               String pattern = "(?s)SerialNo=(.*?),";
               Pattern r = Pattern.compile(pattern);
               Matcher m = r.matcher(details);
               if (m.find()) {
                  String serialNo = m.group(1);
                  oltLteMapping.put(serialNo, nodeName);
               }

               String parsedLog = calexAxosEventLog.toLogEntry(false);
               //
               boolean match = logEntry.equals(parsedLog);
               if (!match) {
                  System.out.println("CalexAxosEventLog no match:");
                  System.out.println("   " + logEntry);
                  System.out.println("   " + parsedLog);
               } else {
                  calexLogSuccess++;
                  if (SEND_EVENT_TO_OPENNMS) {
                     String receivedLogEntry = calexAxosEventLog.toLogEntry(true);
                     System.out.println("sending log: " + receivedLogEntry);
                     client.sendMessage(receivedLogEntry);
                  }

                  //                  System.out.println("CalexAxosEventLog match:");
                  //                  System.out.println("   "+logEntry);
               }

            } else {
               // try OtherEventLogFull parser
               OtherEventLogFull otherEventLogFull = new OtherEventLogFull();
               parsed = otherEventLogFull.parseLogEntry(logEntry);
               if (parsed) {
                  nodeName = otherEventLogFull.getNodename();
                  
                  // get mapping between lte and ont from serial number
                  String alarmText = otherEventLogFull.getAlarmText();
                  String pattern = "(?s)Serial-Number=(.*?),";
                  Pattern r = Pattern.compile(pattern);
                  Matcher m = r.matcher(alarmText);
                  if (m.find()) {
                     String serialNo = m.group(1);
                     oltLteMapping.put(serialNo, nodeName);
                  }

                  String parsedLog = otherEventLogFull.toLogEntry(false);
                  //
                  boolean match = logEntry.equals(parsedLog);
                  if (!match) {
                     System.out.println("OtherEventLogFull no match:");
                     System.out.println("   " + logEntry);
                     System.out.println("   " + parsedLog);
                  } else {
                     otherLogFullSuccess++;
                     //                     System.out.println("OtherEventLog match:");
                     //                     System.out.println("   "+logEntry);
                     if (SEND_EVENT_TO_OPENNMS) {
                        String receivedLogEntry = otherEventLogFull.toLogEntry(true);
                        System.out.println("sending log: " + receivedLogEntry);
                        client.sendMessage(receivedLogEntry);
                     }
                  }
               } else {
                  // try OtherEventLogPartial parser
                  OtherEventLogPartial otherEventLogPartial = new OtherEventLogPartial();
                  parsed = otherEventLogPartial.parseLogEntry(logEntry);
                  if (parsed) {

                     nodeName = otherEventLogPartial.getNodename();
                     
                     // get mapping between lte and ont from serial number
                     String alarmText = otherEventLogPartial.getAlarmText();
                     String pattern = "(?s)Serial-Number=(.*?),";
                     Pattern r = Pattern.compile(pattern);
                     Matcher m = r.matcher(alarmText);
                     if (m.find()) {
                        String serialNo = m.group(1);
                        oltLteMapping.put(serialNo, nodeName);
                     }

                     String parsedLog = otherEventLogPartial.toLogEntry(false);
                     //
                     boolean match = logEntry.equals(parsedLog);
                     if (!match) {
                        System.out.println("OtherEventLogPartial no match:");
                        System.out.println("   " + logEntry);
                        System.out.println("   " + parsedLog);
                     } else {
                        otherLogPartialSuccess++;
                        //                        System.out.println("OtherEventLogPartial match:");
                        //                        System.out.println("   "+logEntry);
                        if (SEND_EVENT_TO_OPENNMS) {
                           String receivedLogEntry = otherEventLogPartial.toLogEntry(true);
                           System.out.println("sending log: " + receivedLogEntry);
                           client.sendMessage(receivedLogEntry);
                        }
                     }
                  } else {
                     failedParse++;
                     System.out.println("OtherEventLogPartial not parsed: ");
                     System.out.println("   " + logEntry);
                  }
               }
            }

            hostMap.put(nodeName, null);

         }

         scanner.close();
         System.out.println("file test finished"
                  + "\n  logCount=" + logCount
                  + "\n  calexLogSuccess=" + calexLogSuccess
                  + "\n  otherLogFullSuccess=" + otherLogFullSuccess
                  + "\n  otherLogPartialSuccess=" + otherLogPartialSuccess
                  + "\n  failedParse=" + failedParse
                  + "\n  unique hosts=" + hostMap.size());

         // print out unique nodenames
         for (String nodeNameKey : hostMap.keySet()) {
            hostsFileWriter.println(ipAddress.toString() + " " + nodeNameKey);
            ipAddress = ipAddress.next();
         }

         // print out lte ont mapping
         for (String lteSerialNo : oltLteMapping.keySet()) {
            lteMappingFileWriter.println(lteSerialNo + "," + oltLteMapping.get(lteSerialNo));
         }

      } catch (

      FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         if (scanner != null)
            scanner.close();
         if (hostsFileWriter != null)
            hostsFileWriter.close();
         if (lteMappingFileWriter != null)
            lteMappingFileWriter.close();
      }
   }
}
