package com.vivopoc.aem.core.servlets;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.jcr.contentloader.ContentImporter;

import javax.servlet.Servlet;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;

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
	private ContentImporter contentImporter;
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		
		
		
		


	     final Node node = request.getResource().adaptTo(Node.class);
	     log.error("PASSSOU AQUI PRIMEIRO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		try {
			
		//Create a connection to the CQ repository running on local host
	    Repository repository = JcrUtils.getRepository("http://localhost:8080");
	    log.error("PASSSOU AQUI >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

	   //Create a Session
	   javax.jcr.Session session = repository.login( new SimpleCredentials("admin", "admin".toCharArray()));
	   
	   //Create a node that represents the root node
	   Node root = session.getRootNode();

	   // Store content
	   Node adobe = root.addNode("adobe");
	   Node day = adobe.addNode("day");
	   day.setProperty("message", "Adobe CQ is part of the Adobe Digital Marketing Suite!");
			
		String jsonResponse = httpService.makeHttpCall();
		
		/**
		 * Printing the json response on the browser
		 */
		response.getWriter().println(jsonResponse);
		node.addNode(jsonResponse);
		
	
		
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
		}
	}

}
