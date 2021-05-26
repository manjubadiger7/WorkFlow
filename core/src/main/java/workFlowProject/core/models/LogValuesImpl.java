package workFlowProject.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LogValuesImpl  {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Inject

	String firstName;

	@Inject

	String lastName;

	@Inject
	boolean graduate;

	@Inject
	long phone;

	@Inject
	String gender;

	@Inject
	String country;

	@Inject
	private List<String> books;

	@PostConstruct
	public void log() {
		LOG.info("************************************************************************************************");
		LOG.info(firstName);
		LOG.info(lastName);
//		LOG.info(graduate);
//		LOG.info(phone);
		LOG.info(gender);
		LOG.info(country);
		for (String string : books) {
			LOG.info(string);
		}
		LOG.info("************************************************************************************************");
	}

}
