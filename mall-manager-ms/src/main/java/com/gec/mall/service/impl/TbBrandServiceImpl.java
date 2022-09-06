package com.gec.mall.service.impl;

import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.PageResp;
import com.gec.mall.domain.RespBean;
import com.gec.mall.mapper.TbBrandMapper;
import com.gec.mall.pojo.TbBrand;
import com.gec.mall.pojo.TbBrandExample;
import com.gec.mall.service.TbBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TbBrandServiceImpl implements TbBrandService {

    @Autowired(required = false)
    private TbBrandMapper tbBrandMapper;

    @Override
    public RespBean findByBrand(PageReq pageReq) {
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<TbBrand> list = tbBrandMapper.selectByExample(example);
        PageResp pageResp = new PageResp();
        pageResp.setList(list);
        pageResp.setTotal(new PageInfo(list).getTotal());
        return RespBean.ok("200", pageResp);
    }

    @Override
    public RespBean saveBrand(TbBrand tbBrand) {
        if (ObjectUtils.isEmpty(tbBrand.getId())) {
            tbBrandMapper.insert(tbBrand);
        } else {
            tbBrandMapper.updateByPrimaryKey(tbBrand);
        }
        return RespBean.ok("200");
    }

    @Override
    public RespBean delBrand(Long id) {
        tbBrandMapper.deleteByPrimaryKey(id);
        return RespBean.ok("200");
    }
}
