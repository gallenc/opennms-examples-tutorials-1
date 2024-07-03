package org.ni2.v01.api.tt.test.manual;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ni2.v01.api.tt.test.Ni2TroubleTicketApiTest;

/**
 * This test is run manually to test against a running production api
 * You should put the credentials in a file called test-properties.properties in test/resources
 * which can be copied from the template test-properties.properties.template
 * Do not check test-properties.properties into the repo !!. 
 */
public class Ni2TroubleTicketApiIntegrationTest {

   public static final String TEST_PROPERTIES_FILE = "test-properties.properties";

   private Ni2TroubleTicketApiTest ni2TroubleTicketApiTest = new Ni2TroubleTicketApiTest();

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

      ni2TroubleTicketApiTest.setTtServerUrl(testProperties.getProperty("ni2.tt.server.url"));
      ni2TroubleTicketApiTest.setTtUsername(testProperties.getProperty("ni2.tt.server.username"));
      ni2TroubleTicketApiTest.setTtPassword(testProperties.getProperty("ni2.tt.server.password"));
      
      ni2TroubleTicketApiTest.setup();

   }

   @Test
   public void authenticationControllerLoginTest() {
      ni2TroubleTicketApiTest.authenticationControllerLoginTest();

   }

   @Test
   public void createAndTestTroubleTicket() {
      ni2TroubleTicketApiTest.createAndTestTroubleTicket();

   }
   
   @Test
   public void createAndTestTroubleTicketUsingEventApi() {
      ni2TroubleTicketApiTest.createAndTestTroubleTicketUsingEventApi();
   }

   @Test
   public void closeUnknownTroubleTicket() {
      ni2TroubleTicketApiTest.closeUnknownTroubleTicket();

   }

   @Test
   public void cancelUnknownTroubleTicket() {
      ni2TroubleTicketApiTest.cancelUnknownTroubleTicket();

   }

   @Test
   public void resolveUnknownTroubleTicket() {
      ni2TroubleTicketApiTest.resolveUnknownTroubleTicket();

   }

   @Test
   public void getUnknownTroubleTicket() {
      ni2TroubleTicketApiTest.getUnknownTroubleTicket();

   }

}
