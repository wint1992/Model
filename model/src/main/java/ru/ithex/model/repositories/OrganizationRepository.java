package ru.ithex.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithex.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
