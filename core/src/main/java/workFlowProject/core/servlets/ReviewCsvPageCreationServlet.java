package workFlowProject.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import workFlowProject.core.service.CsvPageCreate;
import workFlowProject.core.service.ReviewCsvPageCreation;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/ReviewCsvPageCreation" })
public class ReviewCsvPageCreationServlet extends SlingSafeMethodsServlet {

	private static final Logger LOG = LoggerFactory.getLogger(CsvPageServlet.class);

	@Reference
	private transient ReviewCsvPageCreation reviewCsvPageCreation;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();

			response.setContentType("text/plain");

			List<Page> pagesCreated = reviewCsvPageCreation.createPage();
			if (pagesCreated != null) {
				
				LOG.info("page is created");
				out.println("The following pages are created");
				
				for (Page p : pagesCreated) {
					
					out.println(p.getName());
				}
			} else {
				LOG.error("Page is not created");
				out.println("page is not created");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

}
