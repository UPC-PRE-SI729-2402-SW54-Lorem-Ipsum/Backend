package com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    Optional<Lawyer> findByProfile(Profile profile);
}
