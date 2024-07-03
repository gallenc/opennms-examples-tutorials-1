package org.ni2.v01.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * this is a replacement class to avoid problems with oneOff annotation
 */
public class AddressCreateBodyCustomAttributesValue  {

   private String value=null;
   
   public AddressCreateBodyCustomAttributesValue() {
      super();
   }

   //TODO these are needed to unmarshal custom attribute values 
   public AddressCreateBodyCustomAttributesValue(String value) {
      super();
      this.value = value;
   }
   
   //TODO these are needed to unmarshal custom attribute values 
   public AddressCreateBodyCustomAttributesValue(Boolean value) {
      super();
      this.value = value.toString();
   }
   
   //TODO these are needed to unmarshal custom attribute values 
   public AddressCreateBodyCustomAttributesValue(Long value) {
      super();
      this.value = value.toString();
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
}

