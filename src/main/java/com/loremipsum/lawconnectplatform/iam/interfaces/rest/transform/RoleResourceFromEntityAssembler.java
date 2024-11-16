package com.loremipsum.lawconnectplatform.iam.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.iam.domain.model.entities.Role;
import com.loremipsum.lawconnectplatform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
