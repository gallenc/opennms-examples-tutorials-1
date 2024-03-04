package org.opennms.test.application.syslog.cloudbees.test.copy;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.opennms.test.application.syslog.alternative.UDPSyslogServerConfig;

import com.cloudbees.syslog.Facility;
import com.cloudbees.syslog.MessageFormat;
import com.cloudbees.syslog.Severity;
import com.cloudbees.syslog.sender.UdpSyslogMessageSender;




// http://www.syslog4j.org/docs/faq/faq.html
// http://www.syslog4j.org/
// http://www.syslog4j.org/docs/javadoc/
// using https://github.com/graylog-labs/syslog4j-graylog2 as newer

public class SyslogAlternativeServerLog4JTest {

	Logger SYSLOG_BSD_LOGGER = LogManager.getLogger("BSD");
	Logger SYSLOG_RFC5424_LOGGER = LogManager.getLogger("RFC5424");
	Logger SYSLOG_SOCKET_LOGGER = LogManager.getLogger("SYSLOG");

	public static final int SYSLOG_PORT = 9899;
	public static final String PROTOCOL = "udp";
	SyslogServerIF server = null;
	private Date e;

	@Before
	public void init() {

		// clear created server instances (TCP/UDP)
		SyslogServer.shutdown();

		SyslogServerConfigIF config = new UDPSyslogServerConfig();

		config.setUseStructuredData(true);
		// config.setHost(InetAddress.getByName(null).getHostAddress());
		config.setHost("0.0.0.0");
		config.setPort(SYSLOG_PORT);

		SyslogServer.createThreadedInstance(PROTOCOL, config);

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
		messageSender.setDefaultMessageHostname("myhostname"); // some syslog cloud services may use this field to transmit a secret key
		messageSender.setDefaultAppName("myapp");
		messageSender.setDefaultFacility(Facility.USER);
		messageSender.setDefaultSeverity(Severity.INFORMATIONAL);
		messageSender.setSyslogServerHostname("127.0.0.1");
		// syslog udp usually uses port 514 as per https://tools.ietf.org/html/rfc3164#page-5
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
