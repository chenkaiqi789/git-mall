package com.gec.mall.service;

import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.RespBean;
import com.gec.mall.pojo.TbBrand;
import org.springframework.stereotype.Service;

/**
 * 品牌是实现成
 */


public interface TbBrandService {

    //查询所有
    RespBean findByBrand(PageReq pageReq);

    RespBean saveBrand(TbBrand tbBrand);

    RespBean delBrand(Long id);

}
