package poc.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.security.audit.AuditLogging;
import poc.security.models.DataItem;
import poc.security.repositories.DataItemRepository;

import javax.transaction.Transactional;

/**
 * Created by 310280812 on 3/27/2017.
 */
@Service("dataItemService")
public class DataItemService {

    @Autowired
    private DataItemRepository dataItemRepository;

    @AuditLogging
    @Transactional
    public DataItem save(DataItem dataItem) {
        return dataItemRepository.save(dataItem);
    }

    @AuditLogging
    public DataItem findById(Long id) {
        return dataItemRepository.findOne(id);
    }

}
