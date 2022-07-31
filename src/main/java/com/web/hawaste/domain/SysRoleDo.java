package com.web.hawaste.domain;

import com.web.hawaste.entity.SysOffice;
import com.web.hawaste.entity.SysResource;
import com.web.hawaste.entity.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class SysRoleDo extends SysRole {

    private String officeName;

    private List<SysResource> resources;

    private List<SysOffice> offices;
}
