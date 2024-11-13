package com.loremipsum.lawconnectplatform.profiles.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllLawyersQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerTypeByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LawyerQueryServiceImpl implements LawyerQueryService {

    private final LawyerRepository lawyerRepository;

    public LawyerQueryServiceImpl(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public List<Lawyer> handle(GetAllLawyersQuery query) {
        return lawyerRepository.findAll();
    }

    @Override
    public Optional<Lawyer> handle(GetLawyerByIdQuery query) {
        return lawyerRepository.findById(query.lawyerId());
    }

    @Override
    public Set<LawyerType> handle(GetLawyerTypeByIdQuery query) {
        Optional<Lawyer> lawyer = lawyerRepository.findById(query.lawyerId());
        if (lawyer.isPresent()) {
            return lawyer.get().getLawyerTypes();
        } else {
            throw new IllegalArgumentException("Lawyer not found with ID: " + query.lawyerId());
        }
    }
}
