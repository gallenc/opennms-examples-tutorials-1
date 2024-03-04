package org.opennms.test.application.syslog.test;

import static org.junit.Assert.*;

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
	public void test() throws ParseException {
		SyslogIF syslog = Syslog.getInstance("udp");
		syslog.getConfig().setHost("127.0.0.1");
		syslog.getConfig().setPort(SYSLOG_PORT);
		
		syslog.getConfig().setFacility(SyslogConstants.FACILITY_SYSLOG);
		

		
		int facility=SyslogConstants.FACILITY_SYSLOG;
		int level=SyslogConstants.LEVEL_DEBUG;
		String localname="localname";
		boolean useLocalname=true;
		
		//Date dateTime = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH );
		Date dateTime = df.parse("2021/01/01 16:01:23");
		System.out.println("date translated: " + df.format(dateTime));
		
		//syslog.getMessageProcessor().createSyslogHeader(facility, level, localname, useLocalname, dateTime);
		syslog.critical("not structuredCritical Log Message sent");
		syslog.log(SyslogConstants.LEVEL_ALERT, "not Structured alert message sent", dateTime);
		
		syslog.getConfig().setUseStructuredData(true);
		//syslog.getStructuredMessageProcessor().createSyslogHeader(facility, level, localname, useLocalname, dateTime);
		
		syslog.critical("Structured Critical Log Message sent");
		syslog.alert("Structured Alert Log Message sent");

		syslog.log(SyslogConstants.LEVEL_ALERT, "Structured alert message sent", dateTime);
		String messageId = "messageid1000";
		String procId = "procid2000";
		Map<String, Map<String, String>> structuredData = new HashMap<String, Map<String, String>>();

		Map<String, String> m = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("RC", "T1");
				put("AC", "T1");
			}
		};

		structuredData.put("data1", m);
		String message = "big new message";
		

		SyslogMessageIF syslogMessage = new StructuredSyslogMessage(messageId, procId, structuredData, message);
		System.out.println("syslogmessage: "+syslogMessage.toString());

		syslog.log(SyslogConstants.LEVEL_ALERT, syslogMessage,dateTime);
		syslog.critical( syslogMessage);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// more relevant for TCP
		syslog.shutdown();

	}

}
