package work;

import javax.annotation.security.*;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class WorkBean
 */
@DeclareRoles({SecurityRoles.ADMIN, SecurityRoles.MANAGER, SecurityRoles.EMPLOYEE})
@Stateless
public class WorkBean implements WorkBeanLocal {

    /**
     * Default constructor. 
     */
    public WorkBean() {
    }

	@Override
	@RolesAllowed({SecurityRoles.ADMIN})
	public String administrationTask() {
		return "Administration task successful";
	}

	@Override
	@RolesAllowed({SecurityRoles.ADMIN, SecurityRoles.MANAGER})
	public String managingTask() {
		return "Managing task successful";
	}

	@Override
	@PermitAll
	public String basicTask() {
		return "Basic task successful";
	}

}
