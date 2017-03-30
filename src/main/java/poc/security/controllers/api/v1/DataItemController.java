package poc.security.controllers.api.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import poc.security.audit.AuditLogging;
import poc.security.models.DataItem;
import poc.security.services.DataItemService;

import javax.validation.Valid;

/**
 * Created by 310280812 on 3/27/2017.
 */

@Slf4j
@RequestMapping("/api/v1/dataitems")
@RestController
public class DataItemController {

    @Autowired
    private DataItemService dataItemService;

    @AuditLogging(expression = "#{dataItem.getOrganization()}")
    @RequestMapping(method = RequestMethod.POST, value = "/{organization}")
    @ResponseStatus(HttpStatus.CREATED)
    public DataItem save(@Valid @RequestBody DataItem dataItem) {
        return dataItemService.save(dataItem);
    }

    @AuditLogging(expression = "100")
    @RequestMapping(method = RequestMethod.GET, value = "/{organization}/{id}")
    public DataItem find(@PathVariable("id") Long id) {
        return dataItemService.findById(id);
    }
}
