package workFlowProject.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductDetailsImpl {

	@Inject
	String title;

	@Inject
	String fileReference;

	@Inject
	String description;

	@Inject
	List<String> author;

	@Inject
	String publisher;

	@Inject
	String language;

	@Inject
	int price;

	public String getTitle() {
		return title;
	}

	public String getFileReference() {

		return fileReference;
	}

	public String getDescription() {

		return description;
	}

	public List<String> getAuthorName() {
		if (author != null) {
			return new ArrayList<>(author);
		} else {
			return Collections.emptyList();
		}

	}

	public String getLanguage() {

		return language;
	}

	public int getPrice() {

		return price;
	}

	public String getPublisher() {

		return publisher;
	}

}
