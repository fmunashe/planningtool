package za.co.sabs.planningtool.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sabs.planningtool.entity.ContactPerson;

public interface ContactPersonRepository extends JpaRepository<@NonNull ContactPerson, @NonNull Long> {
}
