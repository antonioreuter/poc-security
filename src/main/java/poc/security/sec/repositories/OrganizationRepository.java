package poc.security.sec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.security.sec.models.Organization;

/**
 * Created by 310280812 on 3/28/2017.
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);

}