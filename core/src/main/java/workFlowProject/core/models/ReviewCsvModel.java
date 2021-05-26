package workFlowProject.core.models;

public class ReviewCsvModel {

	private static String pageName;
	private static String pageTemplate;
	private static String pageTitle;
	private static String pageParent;
	private static String pageDescription;

	public ReviewCsvModel(String pageName, String pageTemplate, String pageTitle, String pageParent,
			String pageDescription) {
		super();
		this.pageName = pageName;
		this.pageTemplate = pageTemplate;
		this.pageTitle = pageTitle;
		this.pageParent = pageParent;
		this.pageDescription = pageDescription;
	}

	public ReviewCsvModel() {
		super();
	}

	public static String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public static String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public static String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public static String getPageParent() {
		return pageParent;
	}

	public void setPageParent(String pageParent) {
		this.pageParent = pageParent;
	}

	public static String getPageDescription() {
		return pageDescription;
	}

	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

}
