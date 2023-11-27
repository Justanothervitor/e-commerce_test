package com.UEG.Justanothervitor.templates;

import com.UEG.Justanothervitor.domain.RolesDomain;

public interface RolesInterface {
	RolesDomain findByRole(String role);
	RolesDomain save(RolesDomain role);
}
