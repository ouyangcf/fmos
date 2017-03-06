package com.unicom.fmos.web;

import com.unicom.fmos.dto.SimilarSearchDto;
import com.unicom.fmos.entity.business.Instance;
import com.unicom.fmos.service.sys.SimilarSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhaojb on 2017/2/5.
 */
@Controller
@RequestMapping(value = "/user/businiess/similarSearch")
public class SimilarSearchController {

    @Autowired
    private SimilarSearchService similarSearchService;

    @RequestMapping(value = "/allinstances", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllInstances() {
        return similarSearchService.allInstances();
    }

    @RequestMapping(value = "/instancefeture", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String instancefeture(@RequestBody List<Instance> instances) {
        return similarSearchService.instancefeture(instances.get(0));
    }

    @RequestMapping(value = "/modelByLineId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modelByLineId(@RequestBody List<Instance> instances) {
        return similarSearchService.modelByLineId(instances.get(0));
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestBody List<SimilarSearchDto> similarSearchDtos) {
        SimilarSearchDto similarSearchDto = similarSearchDtos.get(0);
        String result = similarSearchService.search(similarSearchDto);
        return result;
    }
}
