package com.devnexus.ting.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class RoleAwareSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		final Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();

		if(auths.size() == 1 && auths.contains("ROLE_APP_USER")) {
			super.getRedirectStrategy().sendRedirect(request, response, "/s/cfp/index");
		}

		super.getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

}
