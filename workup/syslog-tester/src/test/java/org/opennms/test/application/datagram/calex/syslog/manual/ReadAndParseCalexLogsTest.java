package org.opennms.test.application.datagram.calex.syslog.manual;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.opennms.test.application.datagram.syslog.CalexAxosEventLog;

public class ReadAndParseCalexLogsTest {
	
	@Test
	public void test1() {

		// check that the class can parse the log
		String logEntry = "<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL";

		CalexAxosEventLog eventParser = new CalexAxosEventLog();
		
		boolean match = eventParser.parseLogEntry(logEntry);
		assertTrue(match);
		
		System.out.println("Event Parser values parsed from log: "+eventParser.toString());
		
		// test that the class outputs the same log as parsed
		String receivedLogEntry = eventParser.toLogEntry(true);
		
		System.out.println("Original logEntry      : "+ logEntry);
		System.out.println("Event parser toLogEntry: "+ receivedLogEntry);
		
		assertTrue(logEntry.equals(receivedLogEntry));
		
		// test we can parse string without a pri
		CalexAxosEventLog eventParser2 = new CalexAxosEventLog();
		String noPriEntry = "Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL";
		match = eventParser2.parseLogEntry(noPriEntry);
		assertTrue(match);
		assertTrue(eventParser2.getPri().isEmpty());

		receivedLogEntry = eventParser2.toLogEntry(false);
		System.out.println("Original noPriEntry      : "+ noPriEntry);
		System.out.println("Event parser no pri      : "+ receivedLogEntry);
		assertTrue(noPriEntry.equals(receivedLogEntry));
		
		// test we can create a pri entry
		receivedLogEntry = eventParser2.toLogEntry(true);
		System.out.println("Original logEntry      : "+ logEntry);
		System.out.println("Event parser with pri  : "+ receivedLogEntry);
		assertTrue(logEntry.equals(receivedLogEntry));
		
	
	}
	
	@Test
	public void test2() {
	   // test we can change severity
	     // check that the class can parse the log
      String logEntry = "<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL";
      
      CalexAxosEventLog eventParser = new CalexAxosEventLog();
      
      boolean match = eventParser.parseLogEntry(logEntry);
      assertTrue(match);
      
      System.out.println("Event Parser values parsed from log: "+eventParser.toString());
      
      eventParser.adjustPerceivedSeverity("Clear");
      
      boolean withPri = true;
      System.out.println("Event Parser values parsed from log: "+eventParser.toLogEntry(withPri));
	   
	}



}
