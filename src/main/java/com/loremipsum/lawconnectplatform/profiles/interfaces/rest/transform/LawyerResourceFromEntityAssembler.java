package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.LawyerResource;

import java.util.Set;
import java.util.stream.Collectors;

public class LawyerResourceFromEntityAssembler {
    public static LawyerResource ToEntityFromResource(Lawyer entity) {
        Set<String> lawyerTypeStrings = entity.getLawyerTypes().stream()
                .map(LawyerType::name)
                .collect(Collectors.toSet());

        Set<String> pricesStrings = entity.getPrices().toStringSet();

        return new LawyerResource(
                entity.getId(),
                entity.getProfile(),
                lawyerTypeStrings,
                pricesStrings
        );
    }
}
