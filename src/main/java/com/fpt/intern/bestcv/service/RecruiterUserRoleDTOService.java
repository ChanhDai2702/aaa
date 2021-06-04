package com.fpt.intern.bestcv.service;

import java.util.List;


import com.fpt.intern.bestcv.dto.RecruiterUserRoleDTO;

public interface RecruiterUserRoleDTOService {
	 List<RecruiterUserRoleDTO> getAllAcountRecruiterUser();
	 public List<RecruiterUserRoleDTO>searchRecruiter(String name);

}
