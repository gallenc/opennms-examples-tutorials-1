
// see https://github.com/intoolswetrust/simple-syslog-server/blob/master/src/main/java/com/github/kwart/syslog/UDPSyslogServer.java

package org.opennms.test.application.syslog.alternative;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;

import org.graylog2.syslog4j.server.SyslogServer;
import org.graylog2.syslog4j.SyslogConstants;
import org.graylog2.syslog4j.SyslogRuntimeException;
import org.graylog2.syslog4j.server.SyslogServerEventIF;
import org.graylog2.syslog4j.server.impl.net.udp.UDPNetSyslogServer;

/**
 * UDP syslog server implementation for syslog4j.
 *
 * @author Josef Cacek
 */
public class UDPSyslogServer extends UDPNetSyslogServer {

	@Override
	public void shutdown() {
		super.shutdown();
		thread = null;
	}

	@Override
	public void run() {
		this.shutdown = false;
		try {
			this.ds = createDatagramSocket();
		} catch (Exception e) {
			System.err.println("Creating DatagramSocket failed");
			e.printStackTrace();
			throw new SyslogRuntimeException(e);
		}

		byte[] receiveData = new byte[SyslogConstants.SYSLOG_BUFFER_SIZE];

		while (!this.shutdown) {
			try {
				final DatagramPacket dp = new DatagramPacket(receiveData, receiveData.length);
				this.ds.receive(dp);
				final SyslogServerEventIF event = new Rfc5424SyslogEvent(receiveData, dp.getOffset(), dp.getLength());
				System.out.println(">>> Syslog message came: " + event);
			} catch (SocketException se) {
				se.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
