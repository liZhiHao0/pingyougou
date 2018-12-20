package com.pinyougou.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {
    public List<TbBrand> findAll();

    public PageResult findByPage(int pageNum,int pageSize);

    public void add(TbBrand brand);

    //根据id查询
    public TbBrand findOne(Long id);

    public void update(TbBrand brand);

    public void delete(Long[] ids);

    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);

    List<Map> selectOptionList();
}
