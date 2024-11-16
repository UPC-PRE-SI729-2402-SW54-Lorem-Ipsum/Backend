package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.*;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LawyerQueryService {
    List<Lawyer> handle(GetAllLawyersQuery query);
    Optional<Lawyer> handle(GetLawyerByIdQuery query);
    Set<LawyerType> handle(GetLawyerTypeByIdQuery query);
    Optional<Long> handle(GetLawyerIdByEmailQuery query);
}
