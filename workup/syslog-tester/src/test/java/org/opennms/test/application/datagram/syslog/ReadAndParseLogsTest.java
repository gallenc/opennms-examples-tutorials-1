package org.opennms.test.application.datagram.syslog;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ReadAndParseLogsTest {
	
	String rawMatch=null;
	String pri = null;
	String priValue = null;
	String month = null;
	String day = null;
	String timestampStr = null;
	String nodename = null;
	String slot = null;
	String id = null;
	String syslogSeverity = null;
	String perceivedSeverity = null;
	String logName = null;
	String logCategory = null;
	String logCause = null;
	String details = null;
	String xpath = null;
	String address = null;
	String additionalInfo = null;

	@Test
	public void test() {


		// (?s)(<(.*?)>|)(\D{3})\s(\d|\d{2})\s(.{8})\s(.*?)(?=\snotfmgrd)\s(.*?)\sId:(.*?),\sSyslog-Severity:([0-9]+),\sPerceived-Severity:(.*?),\sName:(.*?),\sCategory:(.*?)\sCause:(.*?),\sDetails:(.*?),\sXpath:(.*?)\sAddress:(.*?),\s(.*)

		String pattern = "(?s)(<(.*?)>|)(\\D{3})\\s(\\d|\\d{2})\\s(.{8})\\s(.*?)(?=\\snotfmgrd)\\s(.*?)\\sId:(.*?),\\sSyslog-Severity:([0-9]+),\\sPerceived-Severity:(.*?),\\sName:(.*?),\\sCategory:(.*?)\\sCause:(.*?),\\sDetails:(.*?),\\sXpath:(.*?)\\sAddress:(.*?),\\s(.*)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		String logEntry = "<187>Feb 12 11:12:08 Hikari notfmgrd[5345]: [1][1][A][5345] [23] Id:1201, Syslog-Severity:3, Perceived-Severity:Major, Name:loss-of-signal, Category:PORT Cause:This alarm is set when there is no signal present on an enabled ethernet interface, Details:Interface operationally down, Xpath:/config/shelf[shelf-id='1']/slot[slot-id='1']/interface/ethernet[port='x2'] Address:/interfaces/interface[name='1/1/x2'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL";

		// Now create matcher object.
		Matcher m = r.matcher(logEntry);

		if (m.find()) {
			rawMatch = m.group(0);
			pri = m.group(1);
			priValue = m.group(2);
			month = m.group(3);
			day = m.group(4);
			timestampStr = m.group(5);
			nodename = m.group(6);
			slot = m.group(7);
			id = m.group(8);
			syslogSeverity = m.group(9);
			perceivedSeverity = m.group(10);
			logName = m.group(11);
			logCategory = m.group(12);
			logCause = m.group(13);
			details = m.group(14);
			xpath = m.group(15);
			address = m.group(16);
			additionalInfo = m.group(17);

			System.out.println(toString());
		} else {
			System.out.println("NO MATCH");
		}
	}

	@Override
	public String toString() {
		return "ReadAndParseLogsTest [\n    "
				+ "rawMatch=" + rawMatch 
				+ ",\n    pri="	+ pri 
				+ ",\n    priValue=" + priValue
				+ ",\n    month="+ month 
				+ ",\n    day=" + day 
				+ ",\n    timestampStr=" + timestampStr 
				+ ",\n    nodename=" + nodename 
				+ ",\n    slot="+ slot 
				+ ",\n    id=" + id 
				+ ",\n    syslogSeverity=" + syslogSeverity 
				+ ",\n    perceivedSeverity="+ perceivedSeverity 
				+ ",\n    logName=" + logName 
				+ ",\n    logCategory=" + logCategory 
				+ ",\n    logCause=" + logCause
				+ ",\n    details=" + details 
				+ ",\n    xpath=" + xpath 
				+ ",\n    address=" + address 
				+ ",\n    additionalInfo=" + additionalInfo
				+ "]";
	}


	
	

}
