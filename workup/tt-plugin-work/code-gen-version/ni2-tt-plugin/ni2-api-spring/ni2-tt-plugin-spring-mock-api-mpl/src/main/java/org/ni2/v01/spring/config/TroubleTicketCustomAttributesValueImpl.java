package org.ni2.v01.spring.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ni2.v01.spring.model.AddressCreateBodyCustomAttributesValue;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;


public class TroubleTicketCustomAttributesValueImpl implements AddressCreateBodyCustomAttributesValue  {

   private String value=null;
   
   public TroubleTicketCustomAttributesValueImpl() {
      super();
   }

   public TroubleTicketCustomAttributesValueImpl(String value) {
      super();
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   @Override
   public String toString() {
      return value;
   }
   
   

   //private Map<String, String> unknownFields = new LinkedHashMap<>();

   // Capture all other fields that Jackson do not match other members
//   @JsonAnyGetter
//   public Map<String, String> otherFields() {
//       return unknownFields;
//   }
//
//   @JsonAnySetter
//   public void setOtherField(String name, String value) {
//       unknownFields.put(name, value);
//   }
   
   

   
}
