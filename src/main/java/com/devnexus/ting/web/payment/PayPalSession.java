/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devnexus.ting.web.payment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

/**
*
* @author Summers Pittman
*/
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

	public void execute(String paymentId, String payerId) {

		try {
			Payment payment = new Payment();
			payment.setId(paymentId);
			PaymentExecution paymentExecute = new PaymentExecution();
			paymentExecute.setPayerId(payerId);
			payment.execute(apiContext, paymentExecute);
		} catch (PayPalRESTException ex) {
			Logger.getLogger(PayPalSession.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex);
		}
	}

}
