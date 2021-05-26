package workFlowProject.core.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

import workFlowProject.core.models.ReviewCsvModel;
import workFlowProject.core.models.ReviewCsvModel;

@Component(service = ReviewCsvPageCreation.class, immediate = true)
public class ReviewCsvPageCreationImpl implements ReviewCsvPageCreation {

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	private static final Logger LOG = LoggerFactory.getLogger(CsvPageCreateImpl.class);

	public static final String SERVICE_NAME = "variableName";

	public static final String RESOURCE_PATH = "/content/dam/workFlowProject/CSV/NewCsvFile.csv";

	ResourceResolver resourceResolver = null;

	// Activate method
	@Activate
	@Modified
	public void activate() {
		LOG.info("The control is coming inside the ResourceResolver and the bundle is activated!");
		LOG.info("=====================REview Csv page Creation===================================");
		Map<String, Object> map = new HashMap<>();
		map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_NAME);
		try {
			resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
			LOG.info("Resource Resolver registered");
		} catch (LoginException e) {
			LOG.error("Login Failed");
		}
	}

	public List<ReviewCsvModel> getCsvContent() {

		// creating List for page properties
		List<ReviewCsvModel> pageProperties = null;

		// reading from the csv file using bufferReader
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		try {
			// getting resource from resolver
			Resource resource = resourceResolver.getResource(RESOURCE_PATH);
			LOG.info("resource is coming");
			Asset asset = resource.adaptTo(Asset.class);
			Rendition rendition = asset.getOriginal();

			inputStream = rendition.adaptTo(InputStream.class);
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			pageProperties = new LinkedList<>(); // importance of linked list over here to traverse the links

			// setting all page properties to object
			pageProperties = bufferedReader.lines().skip(1).map(singleLine -> {
				String[] arr = singleLine.split(",");
				ReviewCsvModel reviewCsvModel = new ReviewCsvModel();
				reviewCsvModel.setPageName(arr[0].trim());
				reviewCsvModel.setPageTemplate(arr[1].trim());
				reviewCsvModel.setPageTitle(arr[2].trim());
				reviewCsvModel.setPageDescription(arr[3].trim());
				reviewCsvModel.setPageParent(arr[4].trim());
				return reviewCsvModel;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			LOG.error("We failed to get the CSV datas");
		} finally {
			try {
				// closing the resorces
				if (bufferedReader != null)
					bufferedReader.close();
				if (inputStreamReader != null)
					inputStreamReader.close();
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
				LOG.error("Resources could not be released properly");
			}
		}

		return pageProperties;
	}

	// using page properties creat a page
	@Override
	public List<Page> createPage() {

		Session session = resourceResolver.adaptTo(Session.class);

		List<Page> pagesCreated = new LinkedList<>();
		List<ReviewCsvModel> pageProperties = getCsvContent(); // excluded the csv for now

		LOG.info("*+=+++++++=+=++++++++ hi iam from imple try block ++===============***");
		LOG.info("===============================");
		LOG.info(ReviewCsvModel.getPageName());
		LOG.info(ReviewCsvModel.getPageParent());
		LOG.info(ReviewCsvModel.getPageTemplate());
		LOG.info(ReviewCsvModel.getPageTitle());
		LOG.info(ReviewCsvModel.getPageDescription());
		LOG.info("===============================");

		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		Page page;
		try {
			LOG.info("*+=+++++++=+=++++++++ hi iam from imple try block ++===============***");
			for (ReviewCsvModel pageModel : pageProperties) {

				Page page1 = pageManager.create(pageModel.getPageParent(), pageModel.getPageName(),
						pageModel.getPageTemplate(), pageModel.getPageTitle());

				Node node;
				try {
					// selecting the node using pagePath
					node = (Node) session.getItem(page1.getPath() + "/jcr:content");

					if (node != null) {
						LOG.info(node.getName());
						node.setProperty("jcr:description", pageModel.getPageDescription());
						node.setProperty("pageTitle", "CSV page title");
						node.setProperty("navTitle", "csv nav title");
						session.save();
					} else {
						LOG.info("Node is null");
					}

				} catch (RepositoryException e) {
					e.printStackTrace();
				}

				if (page1 != null) {
					pagesCreated.add(page1);
				}
			}
			return pagesCreated;
		} catch (WCMException e) {
			LOG.error("Page not created");
		}

		return Collections.emptyList();
	}

}
