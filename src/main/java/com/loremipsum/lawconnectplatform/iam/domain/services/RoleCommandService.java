package com.loremipsum.lawconnectplatform.iam.domain.services;

import com.loremipsum.lawconnectplatform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
