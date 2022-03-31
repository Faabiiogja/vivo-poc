package com.vivopoc.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
        resourceTypes="vivopoc/components/navproducts",
        methods=HttpConstants.METHOD_GET,
        extensions="txt")
@ServiceDescription("Nav Products Servlet")

public class HybrisServlet extends SlingAllMethodsServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) 
		throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) 
		throws ServletException, IOException {
	}
	
}
