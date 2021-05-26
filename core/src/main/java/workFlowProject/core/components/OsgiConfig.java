package workFlowProject.core.components;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "OsgiReview", description = "Osgi Config For review")
public @interface OsgiConfig {

	@AttributeDefinition(name = "Full Name", description = "Name of the mind", type = AttributeType.STRING)
	String FullName();

	@AttributeDefinition(name = "Graduate", description = "Select if Graduated", type = AttributeType.BOOLEAN)
	boolean isGraduated();

	@AttributeDefinition(name = "Companies", description = "Add Companies", type = AttributeType.STRING)
	String[] Companies();

	@AttributeDefinition(name = "Country", description = "Select Your  State", options = {
			@Option(label = "Karnataka", value = "karnataka"), @Option(label = "Tamil Nadu", value = "tamilnadu"),
			@Option(label = "Kerala", value = "kerala"), }, type = AttributeType.STRING)
	String State();

}
