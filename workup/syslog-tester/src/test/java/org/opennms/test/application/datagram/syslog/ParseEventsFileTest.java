package org.opennms.test.application.datagram.syslog;

import static org.junit.Assert.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ParseEventsFileTest {

	@Test
	public void test() {
		try {
			Scanner scanner = new Scanner(new File("./src/test/resources/sampleLogs1.csv"));

			while (scanner.hasNextLine()) {
				String logEntry = scanner.nextLine();
				System.out.println(logEntry);
				CalexAxosEventLog eventParser = new CalexAxosEventLog();
				boolean parsed = eventParser.parseLogEntry(logEntry);
				if(parsed) {
					String parsedLog = eventParser.toLogEntry(false);
					System.out.println(parsedLog);
					boolean match = logEntry.equals(parsedLog);
					System.out.println("matched");
				}else {
					System.out.println("could not parse with CalexAxosEventLog");
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
