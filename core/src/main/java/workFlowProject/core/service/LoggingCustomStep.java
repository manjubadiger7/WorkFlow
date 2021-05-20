package workFlowProject.core.service;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, immediate = true, property = { "precess.label" + "=LoggingCustomStep",
		Constants.SERVICE_DESCRIPTION + "=This step helps to Logg Step details",
		Constants.SERVICE_VENDOR + "=Mindtree" })
public class LoggingCustomStep implements WorkflowProcess {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(WorkItem workItem, WorkflowSession workFlowSession, MetaDataMap processArguments)
			throws WorkflowException {

		try {
			String fullName = processArguments.get("FULL_NAME", String.class);
			String district = processArguments.get("DISTRICT", String.class);
			String state = processArguments.get("STATE", String.class);

			LOG.info("================================Info==============================");
			LOG.info("Full Name:" + fullName);
			LOG.info("District:" + district);
			LOG.info("State:" + state);
			LOG.info("===================================================================");

		} catch (Exception e) {
			LOG.info("Error:" + e.getMessage());
		}

	}

}
