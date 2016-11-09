package work;

import javax.annotation.security.*;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class WorkBean
 */
@DeclareRoles({SecurityRoles.admin, SecurityRoles.manager, SecurityRoles.employee})
@Stateless
public class WorkBean implements WorkBeanLocal {

    /**
     * Default constructor. 
     */
    public WorkBean() {
    }

	@Override
	@RolesAllowed({SecurityRoles.admin})
	public String administrationTask() {
		return "Administration task successful";
	}

	@Override
	@RolesAllowed({SecurityRoles.admin, SecurityRoles.manager})
	public String managingTask() {
		return "Managing task successful";
	}

	@Override
	@PermitAll
	public String basicTask() {
		return "Basic task successful";
	}

}
