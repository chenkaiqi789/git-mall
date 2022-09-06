package com.gec.mall.controller;

import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.RespBean;
import com.gec.mall.entiy.Specification;
import com.gec.mall.service.TbSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TbSpecificationController {

    @Autowired
    private TbSpecificationService tbSpecificationService;

    @GetMapping("findBySpecification")//查询规格
    public RespBean findBySpecification(PageReq pageReq) {
        return tbSpecificationService.findBySpecification(pageReq);
    }

    @GetMapping("findBySpecificationOption/{id}") //查询规格的分类
    public RespBean findBySpecificationOption(@PathVariable Long id) {
        return tbSpecificationService.findBySpecificationOption(id);
    }

    @PostMapping("saveSpecification") //保存
    public RespBean saveSpecification(@RequestBody Specification specification) {
        return tbSpecificationService.saveSpecification(specification);
    }

    @PostMapping("updateSpecification") //修改
    public RespBean updateSpecification(@RequestBody Specification specification) {
        return tbSpecificationService.updateSpecification(specification);
    }

    @GetMapping("delSpecification/{id}")
    public RespBean delSpecification(@PathVariable Long id) {
        return tbSpecificationService.delSpecification(id);
    }
}
