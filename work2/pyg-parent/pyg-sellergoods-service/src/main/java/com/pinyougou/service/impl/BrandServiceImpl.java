package com.pinyougou.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(new TbBrandExample());
    }

    @Override
    public PageResult findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TbBrand> list = brandMapper.selectByExample(new TbBrandExample());
        PageInfo<TbBrand> info = new PageInfo<>(list);
        return new PageResult(info.getTotal(),info.getList());
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        TbBrandExample brandExample = new TbBrandExample();
        TbBrandExample.Criteria criteria = brandExample.createCriteria();
        if(brand!=null){
            if(brand.getName()!=null && brand.getName().length()>0){
                criteria.andNameLike("%"+brand.getName()+"%");
            }
            if(brand.getFirstChar()!=null&&brand.getFirstChar().length()>0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page=(Page<TbBrand>) brandMapper.selectByExample(brandExample);
        return new PageResult(page.getTotal(),page.getResult());
    }


    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
