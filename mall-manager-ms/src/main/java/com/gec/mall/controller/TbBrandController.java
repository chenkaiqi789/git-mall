package com.gec.mall.controller;

import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.PageResp;
import com.gec.mall.domain.RespBean;
import com.gec.mall.pojo.TbBrand;
import com.gec.mall.service.TbBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TbBrandController {
    @Autowired
    private TbBrandService tbBrandService;

    @GetMapping("findByTbBrand")
    public RespBean findByTbBrand(PageReq pageReq) {
        RespBean respBean = tbBrandService.findByBrand(pageReq);
        return respBean;
    }

    @PostMapping("saveBrand")
    public RespBean saveBrand(@RequestBody TbBrand tbBrand) {
        return tbBrandService.saveBrand(tbBrand);
    }

    @GetMapping("delBrand/{id}")
    public RespBean delBrand(@PathVariable Long id) {
        return tbBrandService.delBrand(id);
    }


}
