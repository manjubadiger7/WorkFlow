package workFlowProject.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import workFlowProject.core.service.CsvFileReader;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CsvFileReaderModel {

	@OSGiService
	CsvFileReader csvFileReader;

	public String getPagePath() {
		return csvFileReader.getCsvFile();
	}

	public List<String> getPage() {
		return csvFileReader.getPage();
	}

}
