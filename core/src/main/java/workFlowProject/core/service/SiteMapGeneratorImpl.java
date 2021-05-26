package workFlowProject.core.service;

import java.awt.FontFormatException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.commons.lang3.time.FastDateFormat;
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

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = SiteMapGenerator.class, immediate = true)

public class SiteMapGeneratorImpl implements SiteMapGenerator {

	private static final Logger Log = LoggerFactory.getLogger(SiteMapGeneratorImpl.class);
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	ResourceResolver resourceResolver = null;
	private static final String SITEMAP_LOCATION = "http://localhost:4503/";
	private static final String SITEMAP_NAMESPACE = "http://www.sitemaps.org/schemas/sitemap/0.9";
	private static final FastDateFormat DATE = FastDateFormat.getInstance("YYYY-MM-DD");

	@Activate
	@Modified
	public void activate() {

		try {
			final String SERVICE_USER = "variableName";
			Map<String, Object> mapping = new HashMap();
			mapping.put(ResourceResolverFactory.SUBSERVICE, SERVICE_USER);

			resourceResolver = resourceResolverFactory.getServiceResourceResolver(mapping);
			Log.info("Inside getResourceResolver");
		} catch (LoginException e) {

			Log.info("Inside getResourceResolver failed" + e.getMessage());

		}

	}

	@Override
	public void generateSiteMap(Resource siteResource, Writer writer) {
		try {
			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page mainPage = pageManager.getContainingPage(siteResource);

			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();

			XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(writer);

			// creating url
			streamWriter.writeStartDocument("1.0");
			streamWriter.writeStartElement("", "urlset", SITEMAP_NAMESPACE);
			streamWriter.writeNamespace("", SITEMAP_NAMESPACE);

			innerPage(mainPage, streamWriter);
			// ending
			streamWriter.writeEndElement();
			streamWriter.writeEndDocument();
		} catch (XMLStreamException e) {

			e.printStackTrace();
		}

	}

	private void innerPage(Page mainPage, XMLStreamWriter streamWriter) throws XMLStreamException {
		xmlWriter(mainPage, streamWriter);
		for (Iterator<Page> children = mainPage.listChildren(); children.hasNext();) {
			Page childPage = children.next();
			if (childPage.listChildren().hasNext()) {
				innerPage(childPage, streamWriter);
			} else {
				xmlWriter(childPage, streamWriter);
			}

		}

	}

	private void xmlWriter(Page mainPage, XMLStreamWriter streamWriter) throws XMLStreamException {
		// TODO Auto-generated method stub

		streamWriter.writeStartElement(SITEMAP_NAMESPACE, "url");
		XMLElementWriter(streamWriter, "location", SITEMAP_LOCATION + mainPage.getName());

		XMLElementWriter(streamWriter, "last_modified", DATE.format(mainPage.getLastModified()));

		XMLElementWriter(streamWriter, "change_frequency", "weekly");
		streamWriter.writeEndElement();
	}

	private void XMLElementWriter(XMLStreamWriter streamWriter, String element, String value)
			throws XMLStreamException {
		streamWriter.writeStartElement(SITEMAP_NAMESPACE, element);// creating xml element or starting xml element
		streamWriter.writeCharacters(value);// writing value inside xmltag
		streamWriter.writeEndElement();// creating ending tag
	}

}