package poc.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import poc.security.models.DataItem;

/**
 * Created by 310280812 on 3/27/2017.
 */
@Repository
public interface DataItemRepository extends CrudRepository<DataItem, Long> {
}
