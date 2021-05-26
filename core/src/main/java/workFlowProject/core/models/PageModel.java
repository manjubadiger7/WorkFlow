package workFlowProject.core.models;

public class PageModel {

	private String pageName;
	private String pageTemplate;
	private String pageTitle;
	private String pageParent;
	private String pageDescription;

	public PageModel(String pageName, String pageTemplate, String pageTitle, String pageParent,
			String pageDescription) {
		super();
		this.pageName = pageName;
		this.pageTemplate = pageTemplate;
		this.pageTitle = pageTitle;
		this.pageParent = pageParent;
		this.pageDescription = pageDescription;
	}

	public String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	public PageModel() {
		super();
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageParent() {
		return pageParent;
	}

	public void setPageParent(String pageParent) {
		this.pageParent = pageParent;
	}

}
