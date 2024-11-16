package com.loremipsum.lawconnectplatform.iam.interfaces.acl;

import org.apache.logging.log4j.util.Strings;
import com.loremipsum.lawconnectplatform.iam.domain.model.commands.SignUpCommand;
import com.loremipsum.lawconnectplatform.iam.domain.model.entities.Role;
import com.loremipsum.lawconnectplatform.iam.domain.model.queries.GetUserByIdQuery;
import com.loremipsum.lawconnectplatform.iam.domain.model.queries.GetUserByUsernameQuery;
import com.loremipsum.lawconnectplatform.iam.domain.services.UserCommandService;
import com.loremipsum.lawconnectplatform.iam.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
@Service
public class IamContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetUserByUsernameQuery(username);
        var result = userQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

}
