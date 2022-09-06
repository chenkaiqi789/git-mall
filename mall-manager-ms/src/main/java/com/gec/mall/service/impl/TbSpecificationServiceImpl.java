package com.gec.mall.service.impl;

import com.gec.mall.domain.PageReq;
import com.gec.mall.domain.PageResp;
import com.gec.mall.domain.RespBean;
import com.gec.mall.entiy.Specification;
import com.gec.mall.mapper.TbSpecificationMapper;
import com.gec.mall.mapper.TbSpecificationOptionMapper;
import com.gec.mall.pojo.TbSpecification;
import com.gec.mall.pojo.TbSpecificationExample;
import com.gec.mall.pojo.TbSpecificationOption;
import com.gec.mall.pojo.TbSpecificationOptionExample;
import com.gec.mall.service.TbSpecificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbSpecificationServiceImpl implements TbSpecificationService {

    @Autowired(required = false)
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired(required = false)
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public RespBean findBySpecification(PageReq pageReq) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<TbSpecification> tbSpecifications = tbSpecificationMapper.selectByExample(new TbSpecificationExample());
        PageResp pageResp = new PageResp();
        pageResp.setList(tbSpecifications);
        pageResp.setTotal(new PageInfo(tbSpecifications).getTotal());
        return RespBean.ok("200", pageResp);
    }

    @Override
    public RespBean findBySpecificationOption(Long id) {
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(example);
        return RespBean.ok("200", tbSpecificationOptions);
    }

    @Override
    public RespBean saveSpecification(Specification specification) {
        //保存一方
        tbSpecificationMapper.insert(specification.getTbSpecification());
        for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
            //设置规格的id回填
            tbSpecificationOption.setSpecId(specification.getTbSpecification().getId());
            tbSpecificationOptionMapper.insert(tbSpecificationOption);
        }
        return RespBean.ok("200");
    }

    @Override
    public RespBean updateSpecification(Specification specification) {
        tbSpecificationMapper.updateByPrimaryKey(specification.getTbSpecification());
        //先删除规格
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getTbSpecification().getId());
        tbSpecificationOptionMapper.deleteByExample(example);
        //在保存
        for (TbSpecificationOption tbSpecificationOption : specification.getSpecificationOptionList()) {
            //设置规格的id回填
            tbSpecificationOption.setSpecId(specification.getTbSpecification().getId());
            tbSpecificationOptionMapper.insert(tbSpecificationOption);
        }
        return RespBean.ok("200");
    }

    @Override
    public RespBean delSpecification(Long id) {
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        tbSpecificationMapper.deleteByPrimaryKey(id);
        tbSpecificationOptionMapper.deleteByExample(example);
        return RespBean.ok("200");
    }
}
