package org.opennms.test.application.syslog.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

// http://www.syslog4j.org/docs/faq/faq.html
// http://www.syslog4j.org/
// http://www.syslog4j.org/docs/javadoc/
// using https://github.com/graylog-labs/syslog4j-graylog2 as newer

public class SyslogServerLog4JTest {
	
	Logger SYSLOG_BSD_LOGGER = LogManager.getLogger("BSD");
	Logger SYSLOG_RFC5424_LOGGER = LogManager.getLogger("RFC5424");
	
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
	public void test() throws ParseException {
	
		SYSLOG_BSD_LOGGER.error("message 1 to syslog");
		
		SYSLOG_RFC5424_LOGGER.error("message 2 to syslog");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
