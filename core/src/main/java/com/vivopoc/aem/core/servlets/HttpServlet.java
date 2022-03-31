package com.vivopoc.aem.core.servlets;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;


import javax.servlet.Servlet;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;
import com.vivopoc.aem.core.interfaces.HttpService;


/**
 * @author Anirudh Sharma
 * 
 * This method makes an HTTP call and read the value from the JSON webservice via an OSGi configuration
 *
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/demo/httpcall" })




public class HttpServlet extends SlingSafeMethodsServlet {
	
	

	
	private static final long serialVersionUID = -2014397651676211439L;

	private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);
	
	@Reference
	private HttpService httpService;
	
	@Reference
    private ResourceResolverFactory resolverFactory;
	
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
	     log.error("PASSSOU AQUI PRIMEIRO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			
	   
	   
			
		String jsonResponse = httpService.makeHttpCall();
		
		/**
		 * Printing the json response on the browser
		 */
		response.getWriter().println(jsonResponse);
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("admin", "admin");
		//ResourceResolver resourceResolver = resolverFactory.getResourceResolver(param); 
		ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null); 
		Session session = resourceResolver.adaptTo(Session.class);
		Node root = session.getRootNode().getNode("content");
		
		// Store content
		Node day = root.addNode("day");
		day.setProperty("message", "Adobe CQ is part of the Adobe Digital Marketing Suite!");
		
		session.save();
		session.logout();
		log.error("PASSSOU AQUI DEPOIS DE SALVAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
	
		
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
	}
	
	

}


