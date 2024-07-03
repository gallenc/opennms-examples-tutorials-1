package org.ni2.v01.api.tt.opennms.plugin.test.manual;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.ni2.v01.api.tt.opennms.plugin.Ni2TicketerPlugin;
import org.ni2.v01.api.tt.opennms.plugin.test.Ni2TicketerPluginTest;
import org.opennms.integration.api.v1.ticketing.Ticket;
import org.opennms.integration.api.v1.ticketing.Ticket.State;
import org.opennms.integration.api.v1.ticketing.TicketingPlugin;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket;
import org.opennms.integration.api.v1.ticketing.immutables.ImmutableTicket.Builder;

public class Ni2TicketerPluginIntegrationTest {
   public static final String TEST_PROPERTIES_FILE = "test-properties.properties";
   
   private Ni2TicketerPluginTest ni2TicketerPluginTest= new Ni2TicketerPluginTest();
   
   @Before
   public void pretest() {
      System.out.println("loading properties from "+TEST_PROPERTIES_FILE);
      Properties testProperties = new Properties();
      ClassLoader loader = Thread.currentThread().getContextClassLoader();

      try (InputStream stream = loader.getResourceAsStream(TEST_PROPERTIES_FILE)) {
         if (stream != null) {
            testProperties.load(stream);
            System.out.println("testproperties:"+testProperties);
         } else {
            System.out.println("cannot find " + TEST_PROPERTIES_FILE);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      ni2TicketerPluginTest.setTtServerUrl(testProperties.getProperty("ni2.tt.server.url"));
      ni2TicketerPluginTest.setTtUsername(testProperties.getProperty("ni2.tt.server.username"));
      ni2TicketerPluginTest.setTtPassword(testProperties.getProperty("ni2.tt.server.password"));

      ni2TicketerPluginTest.setup();
   }
   
   @Test
   public void testTicket() {
      ni2TicketerPluginTest.testTicket();
     
   }
   

}
