package org.ni2.v01.spring.config;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.ni2.v01.spring.config.AuthTokens.Token;
import org.springframework.stereotype.Component;

@Component
public class AuthTokens {
   
   long DEFAULT_TOKEN_LIFE  = 60 * 1000; // 60 SECONDS
   
   private Map<String,Token> tokenMap = new ConcurrentHashMap<String, Token>();
   
   public class Token {
      final String token;
      final Long expiryTime;

      public Token(String token, Long expiryTime) {    
         Objects.requireNonNull(token); 
         Objects.requireNonNull(expiryTime); 
         this.token = token;
         this.expiryTime = expiryTime;
      }
      
      public String getToken() {
         return token;
      }
      
      public long getExpiryTime() {
         return expiryTime;
      }

      @Override
      public String toString() {
         return "Token [token=" + token + ", expiryTime=" + expiryTime + "]";
      }
      
      

   }
   

   
   public String createAuthToken() {
      long expiryTime = new Date().getTime() + DEFAULT_TOKEN_LIFE ;
      
      String token = UUID.randomUUID().toString();
      
      Token t = new Token(token, expiryTime);
      
      tokenMap.put(token, t);
      
      return token;
   }
   
   public boolean checkAuthToken(String authToken) {
      
      Token t = tokenMap.get(authToken);
      
      if(t==null) return false;
      
      if (t.expiryTime > new Date().getTime()) return true;
      
      tokenMap.remove(authToken);
      
      return false;
      
   }
   
   public void voidAuthToken(String authToken) {
      Objects.requireNonNull(authToken);
      tokenMap.remove((authToken));
   }
   
   public void voidAllTokens() {
      tokenMap.clear();
   }

}
