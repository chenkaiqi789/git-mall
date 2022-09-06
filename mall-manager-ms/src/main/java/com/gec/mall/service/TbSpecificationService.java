package com.gec.mall.service;


import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.RespBean;
import com.gec.mall.entiy.Specification;

public interface TbSpecificationService {

    //查询
    RespBean findBySpecification(PageReq pageReq);

    //查询规格对应的类型
    RespBean findBySpecificationOption(Long id);

    //增加
    RespBean saveSpecification(Specification specification);

    //修改
    RespBean updateSpecification(Specification specification);

    //删除
    RespBean delSpecification(Long id);


}
