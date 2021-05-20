package workFlowProject.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.servlets.HttpConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(methods = {
		HttpConstants.METHOD_POST }, resourceTypes = "WorkFLow/components/structure/page")
public class FormServlet extends SlingAllMethodsServlet {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		try {
			LOG.info("\n ------------------------STARTED POST-------------------------");
			List<RequestParameter> requestParameterList = request.getRequestParameterList();
			for (RequestParameter requestParameter : requestParameterList) {
				LOG.info("\n ==PARAMETERS===>  {} : {} ", requestParameter.getName(), requestParameter.getString());
			}
		} catch (Exception e) {
			LOG.info("\n ERROR IN REQUEST {} ", e.getMessage());
		}
		response.getWriter().write("======FORM SUBMITTED========");
	}

}
