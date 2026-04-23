package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.mapper.BaoBiao.MiningAreaCategoryMapper;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MiningAreaCategoryServiceImpl implements IMiningAreaCategoryService {

    @Resource
    private MiningAreaCategoryMapper miningAreaCategoryMapper;

    @Override
    public MiningAreaCategory get(Long id) {
        return miningAreaCategoryMapper.selectById(id);
    }


    @Override
    public List<MiningAreaCategory> list(MiningAreaCategory query) {
        return miningAreaCategoryMapper.selectList(query);
    }
    @Override
    public List<MiningAreaCategory> oneList(MiningAreaCategory query) {
        MiningAreaCategory miningAreaCategories = miningAreaCategoryMapper.select(query);
        MiningAreaCategory  areaCategory =new MiningAreaCategory();
        areaCategory.setParentId(Math.toIntExact(miningAreaCategories.getId()));
        List<MiningAreaCategory> miningAreaCategories1 = miningAreaCategoryMapper.selectList(areaCategory);
        List<MiningAreaCategory> list=new ArrayList<>();
        list.add(miningAreaCategories);
        for (MiningAreaCategory mining:miningAreaCategories1) {
            list.add(mining);
            MiningAreaCategory  areaCategory3 =new MiningAreaCategory();
            areaCategory3.setParentId(Math.toIntExact(mining.getId()));
            List<MiningAreaCategory> mining3 = miningAreaCategoryMapper.selectList(areaCategory3);
            for (MiningAreaCategory area2:mining3) {
                list.add(area2);
            }
        }
        return list;
    }

    @Override
    public List<MiningAreaCategory> QueryTeamName(String areaCode) {
        return miningAreaCategoryMapper.QueryTeamName(areaCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(MiningAreaCategory entity) {
        return miningAreaCategoryMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int edit(MiningAreaCategory entity) {
        return miningAreaCategoryMapper.update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Long id) {
        return miningAreaCategoryMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeBatch(List<Long> ids) {
        return miningAreaCategoryMapper.deleteByIds(ids);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MiningAreaCategory getPartentId(Integer parentId) {
        return miningAreaCategoryMapper.getPartentId(parentId);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MiningAreaCategory getAreaName(String areaName) {
        return miningAreaCategoryMapper.getAreaName(areaName);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public MiningAreaCategory getAreaCode(String areaCode) {
        return miningAreaCategoryMapper.getAreaCode(areaCode);
    }
}
