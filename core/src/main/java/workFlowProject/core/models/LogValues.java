package workFlowProject.core.models;

import java.util.List;

public interface LogValues {

	public String getFirstName();

	public String getLastName();

	public boolean getGraduate();

	public long getPhone();

	public String getGender();

	public String getCountry();

	public List<String> getBooks();

	public void log();

}
