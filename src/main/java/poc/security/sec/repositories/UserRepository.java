package poc.security.sec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poc.security.sec.models.User;

import javax.transaction.Transactional;

/**
 * Created by 310280812 on 3/28/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(final String username);

    @Transactional
    void removeUserByUsername(String username);

}