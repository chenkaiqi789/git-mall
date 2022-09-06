package com.gec.mall.entiy;

import com.gec.mall.pojo.TbSpecification;
import com.gec.mall.pojo.TbSpecificationOption;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 规格表和规格选项表
 */

@Data
public class Specification implements Serializable {

    private TbSpecification tbSpecification;

    private List<TbSpecificationOption> specificationOptionList;


}

