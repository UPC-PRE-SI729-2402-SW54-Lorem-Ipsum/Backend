package com.loremipsum.lawconnectplatform.profiles.interfaces.rest;

import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ProfileCommandService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ProfileQueryService;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.ProfileResource;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile Endpoints")
public class ProfileController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @Operation(summary = "Create A New Profile")
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.ToCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.ToResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Profile By Id")
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.ToResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @Operation(summary = "Get All Profiles")
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::ToResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }


}
