package org.opennms.test.application.datagram.syslog;

import static org.junit.Assert.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ParseEventsFileTest {

   @Test
   public void test() {
      Scanner scanner = null;
      try {
         scanner = new Scanner(new File("./src/test/resources/sampleLogs1.csv"));
         int logCount=0;
         int calexLogSuccess=0;
         int otherLogFullSuccess=0;
         int otherLogPartialSuccess=0;
         int failedParse=0;
         
         while (scanner.hasNextLine()) {
            String logEntry = scanner.nextLine();
            logCount++;

            // try calexEventLog parser
            CalexAxosEventLog calexAxosEventLog = new CalexAxosEventLog();
            boolean parsed = calexAxosEventLog.parseLogEntry(logEntry);
            if (parsed) {
               String parsedLog = calexAxosEventLog.toLogEntry(false);
               //
               boolean match = logEntry.equals(parsedLog);
               if (!match) {
                  System.out.println("CalexAxosEventLog no match:");
                  System.out.println("   " + logEntry);
                  System.out.println("   " + parsedLog);
               } else {
                  calexLogSuccess++;

//                  System.out.println("CalexAxosEventLog match:");
//                  System.out.println("   "+logEntry);
               }

            } else {
               // try OtherEventLogFull parser
               OtherEventLogFull otherEventLogFull = new OtherEventLogFull();
               parsed = otherEventLogFull.parseLogEntry(logEntry);
               if (parsed) {
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
                  }
               } else {
               // try OtherEventLogPartial parser
                  OtherEventLogPartial otherEventLogPartial = new OtherEventLogPartial();
                  parsed = otherEventLogPartial.parseLogEntry(logEntry);
                  if (parsed) {
                     String parsedLog = otherEventLogPartial.toLogEntry(false);
                     //
                     boolean match = logEntry.equals(parsedLog);
                     if (!match) {
                        System.out.println("OtherEventLogPartial no match:");
                        System.out.println("   " + logEntry);
                        System.out.println("   " + parsedLog);
                     } else {
                         otherLogPartialSuccess++;
//                        System.out.println("OtherEventLog match:");
//                        System.out.println("   "+logEntry);
                     }
                  } else {
                     failedParse++;
                     System.out.println("OtherEventLogPartial not parsed: ");
                     System.out.println("   " + logEntry);
                  }
               }
            }
         }

         scanner.close();
         System.out.println("file test finished"
                  + "\n  logCount="+logCount
                  + "\n  calexLogSuccess="+calexLogSuccess
                  + "\n  otherLogFullSuccess="+otherLogFullSuccess
                  + "\n  otherLogPartialSuccess="+otherLogPartialSuccess
                  + "\n  failedParse="+failedParse
                  );
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } finally {
         if (scanner != null)
            scanner.close();
      }
   }
}
