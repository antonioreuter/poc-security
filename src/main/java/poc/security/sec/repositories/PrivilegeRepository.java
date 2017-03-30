package poc.security.sec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.security.sec.models.Privilege;

/**
 * Created by 310280812 on 3/28/2017.
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    public Privilege findByName(String name);

}