package workFlowProject.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import workFlowProject.core.service.OsgiReview;

@Model(adaptables = Resource.class, adapters = OsgiReviewModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OsgiReviewModelImpl implements OsgiReviewModel {

	@OSGiService
	OsgiReview osgiReview;

	@Override
	public String getFullName() {

		return osgiReview.getFullName();
	}

	@Override
	public boolean getGraduate() {

		return osgiReview.getGraduate();
	}

	@Override
	public String[] getCompanies() {

		return osgiReview.getCompanies();
	}

	@Override
	public String getState() {

		return osgiReview.getState();
	}

}
