package workFlowProject.core.models;

import java.util.List;

public interface ProductDetails {

	public String getTitle();

	public String getFileReference();

	public String getDescription();

	public List<String> getAuthorName();

	public String getLanguage();

	public int getPrice();

	public String getPublisher();

}
