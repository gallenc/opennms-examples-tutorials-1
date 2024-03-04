package org.opennms.test.application.syslog.alternative.test;

import static org.junit.Assert.*;

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
	public void test() throws ParseException {

	//	SYSLOG_BSD_LOGGER.error("message 1 to BSD syslog");

	//	SYSLOG_RFC5424_LOGGER.error("message 2 to RFC5424 syslog");
		
//		SYSLOG_SOCKET_LOGGER.error("message 3 to SYSLOG syslog"); 
//		
//		// threadlocal
//		try (final CloseableThreadContext.Instance ctc = CloseableThreadContext.put("loginId", "loginId-craig")) {
//			SYSLOG_RFC5424_LOGGER.error("message 4 to RFC5424 syslog");
//		}
		
		try (final CloseableThreadContext.Instance ctc = CloseableThreadContext.put("loginId", "loginId-craig")) {
			SYSLOG_SOCKET_LOGGER.error("Feb 28 16:36:16 se372-olt-5 notfmgrd[6208]: [1][1][A][6208] [23] nm_handle_events.c.412: Id:5031, Syslog-Severity:6, Perceived-Severity:CLEAR, Name:low-rx-opt-pwr-fe, Category:PON Cause:The ONT reports low received optical power from the OLT., Details:SerialNo=AEEF24, Xpath:/config/system/ont[ont-id='382230'] Address:NULL, Primary-element:NULL, Value:NULL, Verb:NULL, Session:0, Login:NULL, IpAddress:NULL, SrcManager:NULL, Secondary-element:NULL"); 
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
