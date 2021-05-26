package workFlowProject.core.service;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import workFlowProject.core.components.OsgiConfig;

@Component(service = OsgiReview.class, immediate = true)
@Designate(ocd = OsgiConfig.class)
public class OsgiReviewImpl implements OsgiReview {

	String fullName;
	boolean isGraduate;
	String[] company;
	String state;

	@Activate
	@Modified
	protected final void activate(OsgiConfig osgiConfig) {
		fullName = osgiConfig.FullName();
		isGraduate = osgiConfig.isGraduated();
		company = osgiConfig.Companies();
		state = osgiConfig.State();

	}

	@Override
	public String getFullName() {

		return fullName;
	}

	@Override
	public boolean getGraduate() {

		return isGraduate;
	}

	@Override
	public String[] getCompanies() {

		return company;
	}

	@Override
	public String getState() {

		return state;
	}

}
