package org.ni2.v01.api.utils;

import javax.net.ssl.TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.transport.http.HTTPConduit;

// see https://stackoverflow.com/questions/7881122/cxf-restful-client-how-to-do-trust-all-certs
// see https://stackoverflow.com/questions/22569866/in-cxf-is-there-a-way-to-program-the-configuration-of-a-client-programmatically
// TODO add configuration to not trust all certificates
public class TLSUtils {
   
   public static void addX509TrustManager(ClientConfiguration config) {
      HTTPConduit conduit = config.getHttpConduit();
      TLSClientParameters params = conduit.getTlsClientParameters();

      if (params == null) {
          params = new TLSClientParameters();
          conduit.setTlsClientParameters(params);
      }

      params.setTrustManagers(new TrustManager[] { new BlindTrustManager() });
      params.setDisableCNCheck(true);
      
   }

}
