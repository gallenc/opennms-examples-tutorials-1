package org.ni2.v01.api.tt.opennms.plugin;

public class Ni2TicketerException extends RuntimeException {
   
   private static final long serialVersionUID = 1L;

   public Ni2TicketerException(final String message) {
       super(message);
   }

   public Ni2TicketerException(final String message, final Exception e) {
       super(message, e);
   }

}
