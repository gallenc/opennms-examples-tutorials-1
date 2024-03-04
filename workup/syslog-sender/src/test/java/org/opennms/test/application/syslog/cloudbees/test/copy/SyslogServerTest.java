package org.opennms.test.application.syslog.cloudbees.test.copy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.graylog2.syslog4j.Syslog;
import org.graylog2.syslog4j.SyslogConstants;
import org.graylog2.syslog4j.SyslogIF;
import org.graylog2.syslog4j.server.SyslogServer;
import org.graylog2.syslog4j.server.SyslogServerIF;
import org.graylog2.syslog4j.server.SyslogServerSessionEventHandlerIF;
import org.graylog2.syslog4j.server.impl.event.printstream.SystemOutSyslogServerEventHandler;
import org.graylog2.syslog4j.server.SyslogServerConfigIF;
import org.graylog2.syslog4j.impl.message.structured.StructuredSyslogMessage;
import org.graylog2.syslog4j.SyslogMessageIF;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cloudbees.syslog.Facility;
import com.cloudbees.syslog.MessageFormat;
import com.cloudbees.syslog.Severity;
import com.cloudbees.syslog.sender.UdpSyslogMessageSender;

// http://www.syslog4j.org/docs/faq/faq.html
// http://www.syslog4j.org/
// http://www.syslog4j.org/docs/javadoc/
// using https://github.com/graylog-labs/syslog4j-graylog2 as newer

public class SyslogServerTest {
	public static final int SYSLOG_PORT = 9899;
	public static final String PROTOCOL = "udp";
	SyslogServerIF server = null;
	private Date e;

	@Before
	public void init() {
		server = SyslogServer.getInstance(PROTOCOL);

		SyslogServerConfigIF syslogServerConfig = server.getConfig();
		syslogServerConfig.setHost("0.0.0.0");
		syslogServerConfig.setPort(SYSLOG_PORT);
		
		syslogServerConfig.setUseStructuredData(true);

		SyslogServerSessionEventHandlerIF eventHandler = SystemOutSyslogServerEventHandler.create();
		syslogServerConfig.addEventHandler(eventHandler);

		// start server
		server = SyslogServer.getThreadedInstance(PROTOCOL);

		System.out.println("server started on port " + SYSLOG_PORT);
	}

	@After
	public void shutdown() {
		// shuts down all instances
		SyslogServer.shutdown();
		System.out.println("server shutdown");
	}

	@Test
	public void test() throws ParseException, IOException {
		// Initialise sender
		UdpSyslogMessageSender messageSender = new UdpSyslogMessageSender();
		messageSender.setDefaultMessageHostname("myhostname"); // some syslog cloud services may use this field to
																// transmit a secret key
		// Feb 28 16:36:19 rea282-olt-1 notfmgrd[6213]: [1][1][A][6213] [23] nm_handle_events.c.412: Id:5044, Syslog-Severity:6, Perceived-Severity:CLEAR, Name:ont-eth-down, Category:PON Cause:This alarm is set when the ont reports no signal present on an enabled ethernet interface., Details:NULL, Xpath:/config/system/ont[ont-id='178004']/interface/ont-ethernet[port='g1'] Address:/interfaces/interface[name='178004/g1'], Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL, Secondary-element:NULL
		
		messageSender.setDefaultAppName("myapp");
		messageSender.setDefaultFacility(Facility.LOCAL7);
		messageSender.setDefaultSeverity(Severity.CRITICAL);
		messageSender.setSyslogServerHostname("127.0.0.1");
		// syslog udp usually uses port 514 as per
		// https://tools.ietf.org/html/rfc3164#page-5
		messageSender.setSyslogServerPort(SYSLOG_PORT);
		messageSender.setMessageFormat(MessageFormat.RFC_3164); // optional, default is RFC 3164
		


		// send a Syslog message
		messageSender.sendMessage("This is a test message");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
