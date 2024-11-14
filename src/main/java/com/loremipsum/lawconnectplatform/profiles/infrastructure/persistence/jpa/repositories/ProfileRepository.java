package com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByEmail(EmailAddress email);
    Optional<Profile> findByEmail(EmailAddress email);
}
