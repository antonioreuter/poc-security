package poc.security.controllers.api.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import poc.security.models.DataItem;
import poc.security.services.DataItemService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 310280812 on 3/27/2017.
 */

@Slf4j
@RequestMapping("/api/v1/dataitems")
@RestController
public class DataItemController {

    @Autowired
    private DataItemService dataItemService;

    @RequestMapping(method = RequestMethod.POST)
    public DataItem save(HttpServletRequest request, @RequestBody DataItem dataItem) {
        log.info("Request: {}", request);
        return dataItemService.save(dataItem);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public DataItem find(@PathVariable("id") Long id) {
        return dataItemService.findById(id);
    }
}
