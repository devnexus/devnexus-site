package com.devnexus.ting.web.payment;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PayPalSession {
    public static final PayPalSession DUMMY = new PayPalSession();

    private long expires_on = 0;
    
    private static final ConcurrentHashMap<String, PayPalSession> CLIENT_MAP = new ConcurrentHashMap<>();
    private String accessToken;
    private APIContext apiContext;
    private OAuthTokenCredential oAuthCredential;
    
    private PayPalSession(String clientId, String clientSecret, String mode) {
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);

        try {
            oAuthCredential = new OAuthTokenCredential(clientId, clientSecret, sdkConfig);
            this.accessToken = oAuthCredential.getAccessToken();
            this.apiContext = new APIContext(accessToken);
            apiContext.setConfigurationMap(sdkConfig);
            this.expires_on = new Date().getTime() + oAuthCredential.expiresIn();

        } catch (PayPalRESTException ex) {
            Logger.getLogger(PayPalSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public static PayPalSession getSession(String clientId, String clientSecret) {
//        PayPalSession session = CLIENT_MAP.computeIfAbsent(clientId, (ignore) ->new PayPalSession(clientId, clientSecret, "sandbox"));
//        if (session.isExpired()) {
//            session = CLIENT_MAP.compute(clientId, (ignoreClientId, ignoreCurrentSession) ->new PayPalSession(clientId, clientSecret, "sandbox"));
//        }
//        return session;
        
        return new PayPalSession(clientId, clientSecret, "sandbox");
    }

    public static PayPalSession getLiveSession(String clientId, String clientSecret) {
//        PayPalSession session = CLIENT_MAP.computeIfAbsent(clientId, (ignore) ->new PayPalSession(clientId, clientSecret, "live"));
//        if (session.isExpired()) {
//            session = CLIENT_MAP.compute(clientId, (ignoreClientId, ignoreCurrentSession) ->new PayPalSession(clientId, clientSecret, "live"));
//        }
//        return session;
        return new PayPalSession(clientId, clientSecret, "live");
    }
    
    private PayPalSession() {
        
    }
    
    
    public boolean isExpired() {
        return new Date().getTime() > expires_on;
    }

    public Payment createPayment(Payment payment) {
        try {
            return payment.create(apiContext);
        } catch (PayPalRESTException ex) {
            Logger.getLogger(PayPalSession.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
}
