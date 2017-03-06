package com.unicom.fmos.service.sys;

import com.unicom.fmos.dto.SimilarSearchDto;
import com.unicom.fmos.entity.business.Instance;

/**
 * Created by zhaojb on 2017/2/5.
 */
public interface SimilarSearchService {
    String allInstances();

    String instancefeture(Instance instance);

    String search(SimilarSearchDto similarSearchDto);

    String modelByLineId(Instance instance);
}
