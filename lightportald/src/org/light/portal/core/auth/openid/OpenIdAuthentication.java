/*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.core.auth.openid;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.light.portal.core.auth.Authentication;
import org.light.portal.logger.Logger;
import org.light.portal.logger.LoggerFactory;
import org.light.portal.util.PropUtil;
import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.InMemoryConsumerAssociationStore;
import org.openid4java.consumer.InMemoryNonceVerifier;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.FetchRequest;

/**
 * 
 * @author Jianmin Liu
 **/
public class OpenIdAuthentication extends Authentication{
	private static OpenIdAuthentication _instance = new OpenIdAuthentication();
	private ConsumerManager manager;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static OpenIdAuthentication getInstance(){
		return _instance;
	}
	private OpenIdAuthentication(){
		try {
			this.manager = new ConsumerManager();
			manager.setAssociations(new InMemoryConsumerAssociationStore());
			manager.setNonceVerifier(new InMemoryNonceVerifier(5000));
		} catch (ConsumerException e) {
			throw new RuntimeException(e);
		}
	}
    
    public String authRequest(String userSuppliedString,
			HttpServletRequest httpReq, HttpServletResponse httpResp)
			throws IOException, ServletException {
		try {
			// configure the return_to URL where your application will receive			
			String returnToUrl = httpReq.getRequestURL().toString();
			int index = returnToUrl.indexOf(PropUtil.getString("portal.openid.url.request"));
			returnToUrl = returnToUrl.substring(0,index);
			returnToUrl += PropUtil.getString("portal.openid.url.return");
			
			// perform discovery on the user-supplied identifier
			List discoveries = manager.discover(userSuppliedString);

			// attempt to associate with the OpenID provider
			// and retrieve one service endpoint for authentication
			DiscoveryInformation discovered = manager.associate(discoveries);

			// store the discovery information in the user's session
			httpReq.getSession().setAttribute("openid-disc", discovered);

			// obtain a AuthRequest message to be sent to the OpenID provider
			AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

			// Attribute Exchange example: fetching the 'email' attribute
			FetchRequest fetch = FetchRequest.createFetchRequest();

			if ("1".equals(httpReq.getParameter("nickname"))) {
				fetch.addAttribute("nickname",
				// attribute alias
						"http://schema.openid.net/contact/nickname", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("email"))) {
				fetch.addAttribute("email",
				// attribute alias
						"http://schema.openid.net/contact/email", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("fullname"))) {
				fetch.addAttribute("fullname",
				// attribute alias
						"http://schema.openid.net/contact/fullname", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("dob"))) {
				fetch.addAttribute("dob",
				// attribute alias
						"http://schema.openid.net/contact/dob", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("gender"))) {
				fetch.addAttribute("gender",
				// attribute alias
						"http://schema.openid.net/contact/gender", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("postcode"))) {
				fetch.addAttribute("postcode",
				// attribute alias
						"http://schema.openid.net/contact/postcode", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("country"))) {
				fetch.addAttribute("country",
				// attribute alias
						"http://schema.openid.net/contact/country", // type URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("language"))) {
				fetch.addAttribute("language",
				// attribute alias
						"http://schema.openid.net/contact/language", // type
						// URI
						true); // required
			}
			if ("1".equals(httpReq.getParameter("timezone"))) {
				fetch.addAttribute("timezone",
				// attribute alias
						"http://schema.openid.net/contact/timezone", // type
						// URI
						true); // required
			}

			// attach the extension to the authentication request
			authReq.addExtension(fetch);
			httpResp.sendRedirect(authReq.getDestinationUrl(true));
		} catch (OpenIDException e) {
			StringBuilder buffer = new StringBuilder();
			Throwable ex = e;
			while(ex != null){
				log.error(String.format("%s: %s",ex.getClass().toString(),ex.getMessage()));
				buffer.append(ex.getMessage()).append("</br>");
				ex = ex.getCause();				
			}
			httpReq.setAttribute("error",buffer.toString());
			httpReq.getRequestDispatcher(PropUtil.getString("portal.openid.url.error")).forward(httpReq,httpResp);			
		}
		return null;
	}

	// --- processing the authentication response ---
	public String verifyResponse(HttpServletRequest httpReq, HttpServletResponse httpResp)
			throws IOException, ServletException {
		String contextPath = httpReq.getContextPath();
		if(contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix"))>=0)
			contextPath=contextPath.substring(0,contextPath.indexOf(PropUtil.getString("portal.openid.url.prefix")));
		try {
			// extract the parameters from the authentication response
			// (which comes in as a HTTP request from the OpenID provider)
			ParameterList response = new ParameterList(httpReq
					.getParameterMap());

			// retrieve the previously stored discovery information
			DiscoveryInformation discovered = (DiscoveryInformation) httpReq
					.getSession().getAttribute("openid-disc");

			// extract the receiving URL from the HTTP request
			StringBuffer receivingURL = httpReq.getRequestURL();
			String queryString = httpReq.getQueryString();
			if (queryString != null && queryString.length() > 0)
				receivingURL.append("?").append(httpReq.getQueryString());

			// verify the response; ConsumerManager needs to be the same
			// (static) instance used to place the authentication request
			VerificationResult verification = manager.verify(receivingURL
					.toString(), response, discovered);

			// examine the verification result and extract the verified
			// identifier
			Identifier verified = verification.getVerifiedId();
			String identifier = httpReq.getParameter("openid.identity");
			if (verified != null && identifier != null){
				setupUser(httpReq, identifier,PropUtil.getString("portal.openid.user.default.name"));
				httpResp.sendRedirect(contextPath+PropUtil.getString("portal.jsp.main.index"));
			}else{
				httpReq.setAttribute("error","Verification error.");
				httpReq.getRequestDispatcher(PropUtil.getString("portal.openid.url.error")).forward(httpReq,httpResp);
			}
		} catch (OpenIDException e) {
			StringBuilder buffer = new StringBuilder();
			Throwable ex = e;
			while(ex != null){
				log.error(String.format("%s: %s",ex.getClass().toString(),ex.getMessage()));
				buffer.append(ex.getMessage()).append("</br>");
				ex = ex.getCause();				
			}
			httpReq.setAttribute("error",buffer.toString());
			httpReq.getRequestDispatcher(PropUtil.getString("portal.openid.url.error")).forward(httpReq,httpResp);
		}
		
		return null;
	}
}

