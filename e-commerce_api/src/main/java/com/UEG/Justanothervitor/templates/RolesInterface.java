package com.UEG.Justanothervitor.templates;

import com.UEG.Justanothervitor.domain.roles.ERoles;
import com.UEG.Justanothervitor.domain.roles.RolesDomain;

public interface RolesInterface {
	RolesDomain findByRole(ERoles role);
	RolesDomain save(RolesDomain role);
}
