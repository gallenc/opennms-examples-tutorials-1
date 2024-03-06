package org.opennms.test.application.datagram.syslog;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ReadAndParseOtherLogsTest {
	
	@Test
	public void test1() {

		// check that the OtherEventLogFull class can parse the log
		String logEntry = "Feb 28 16:35:13 LT1-blk1-olt-301 - APP_NAME:alarm_logic_app,APP_VERSION:2212.640,MODULE_NAME:alarm,ENTITY_NAME:ALCLFCA45C31,ENTITY_TYPE:rssi-onu,alarm-type-id:onu-upstream-rx-power-exceed-threshold,event-time:2024-02-28T16:35:13+00:00,perceived-severity:minor,alarm-text:low-alarm < onu-upstream-rx-power(-31.5 dBm) < low-warning, OLT xFP operational limits exceeded";
		OtherEventLogFull eventParser = new OtherEventLogFull();
		
		boolean match = eventParser.parseLogEntry(logEntry);
		assertTrue(match);
		
		System.out.println("Event Parser values parsed from log: "+eventParser.toString());
		
		// test that the class outputs the same log as parsed - WITH NO PRI
		String receivedLogEntry = eventParser.toLogEntry(false);
		
		System.out.println("Original logEntry      : "+ logEntry);
		System.out.println("Event parser toLogEntry: "+ receivedLogEntry);
		
		assertTrue(logEntry.equals(receivedLogEntry));
		
	}


	@Test
	public void test2() {
		// check that the OtherEventLogPartial class can parse the FULL log
	   String logEntry = "Feb 28 16:35:13 LT1-blk1-olt-301 - APP_NAME:alarm_logic_app,APP_VERSION:2212.640,MODULE_NAME:alarm,ENTITY_NAME:ALCLFCA45C31,ENTITY_TYPE:rssi-onu,alarm-type-id:onu-upstream-rx-power-exceed-threshold,event-time:2024-02-28T16:35:13+00:00,perceived-severity:minor,alarm-text:low-alarm < onu-upstream-rx-power(-31.5 dBm) < low-warning, OLT xFP operational limits exceeded";

      OtherEventLogPartial eventParser = new OtherEventLogPartial();
      
      boolean match = eventParser.parseLogEntry(logEntry);
      assertTrue(match);
      
      System.out.println("Event Parser values parsed from log: "+eventParser.toString());
      
      // test that the class outputs the same log as parsed - WITH NO PRI
      String receivedLogEntry = eventParser.toLogEntry(false);
      
      System.out.println("Original logEntry      : "+ logEntry);
      System.out.println("Event parser toLogEntry: "+ receivedLogEntry);
      
      assertTrue(logEntry.equals(receivedLogEntry));

	}
	
	@Test
   public void test3() {
      // check that the OtherEventLogPartial class can parse the PARTIAL log
      String logEntry = "Feb 28 16:35:04 LT2-gry1-olt-303 - APP_NAME:ipfix_logic_app,APP_VERSION:2212.640,MODULE_NAME:ipfix,Data set not exported for cache xpon-vani-onu-present-on-channel-termination (ID: 278). No data available for export";
      OtherEventLogPartial eventParser = new OtherEventLogPartial();
      
      boolean match = eventParser.parseLogEntry(logEntry);
      assertTrue(match);
      
      System.out.println("Event Parser values parsed from log: "+eventParser.toString());
      
      // test that the class outputs the same log as parsed - WITH NO PRI
      String receivedLogEntry = eventParser.toLogEntry(false);
      
      System.out.println("Original logEntry      : "+ logEntry);
      System.out.println("Event parser toLogEntry: "+ receivedLogEntry);
      
      assertTrue(logEntry.equals(receivedLogEntry));

   }
	

}
