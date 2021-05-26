package workFlowProject.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import workFlowProject.core.service.SiteMapGenerator;


@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "WorkFLow/components/structure/page", selectors = "sitemap", extensions = "xml")
public class SiteMapGeneratorServlet extends SlingSafeMethodsServlet {
	private static final long serialVersionUID = 1L;
	@Reference
	SiteMapGenerator siteMapGenerator;
	private static Logger LOG = LoggerFactory.getLogger(SiteMapGeneratorServlet.class);

	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
		res.setContentType("application/xml");
		siteMapGenerator.generateSiteMap(req.getResource(), res.getWriter());
	}
}