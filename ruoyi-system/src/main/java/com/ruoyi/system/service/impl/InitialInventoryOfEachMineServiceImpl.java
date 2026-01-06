package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BaoBiao.FactoryArchive;
import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import com.ruoyi.system.domain.InitialInventoryOfEachMine;
import com.ruoyi.system.domain.InitialInventoryOfEachMineOutput;
import com.ruoyi.system.domain.SubInitialInventoryOfEachMine;
import com.ruoyi.system.mapper.BaoBiao.FactoryArchiveMapper;
import com.ruoyi.system.mapper.InitialInventoryOfEachMineMapper;
import com.ruoyi.system.mapper.SubInitialInventoryOfEachMineMapper;
import com.ruoyi.system.service.BaoBiao.IMiningAreaCategoryService;
import com.ruoyi.system.service.IInitialInventoryOfEachMineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 各矿期初库存录入Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-11-06
 */
@Slf4j
@Service
public class InitialInventoryOfEachMineServiceImpl implements IInitialInventoryOfEachMineService {
    @Autowired
    private InitialInventoryOfEachMineMapper initialInventoryOfEachMineMapper;
    @Autowired
    private SubInitialInventoryOfEachMineMapper subInitial;
    @Resource
    private IMiningAreaCategoryService miningAreaCategoryService;//煤矿主结构表
    @Resource
    private FactoryArchiveMapper factoryArchiveMapper;//所属厂和发电厂档案
    /**
     * 查询各矿期初库存录入
     * 
     * @param id 各矿期初库存录入主键
     * @return 各矿期初库存录入
     */
    @Override
    public InitialInventoryOfEachMine getInitialInventoryOfEachMineById(Long id) {
        return initialInventoryOfEachMineMapper.selectInitialInventoryOfEachMineById(id);
    }

    /**
     * 查询各矿期初库存录入列表
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 各矿期初库存录入
     */
    @Override
    public List<InitialInventoryOfEachMine> listInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine) {
        return initialInventoryOfEachMineMapper.selectInitialInventoryOfEachMineList(initialInventoryOfEachMine);
    }
    /**
     * 查询各矿期初库存录入列表带子查询
     *
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 各矿期初库存录入
     */
    @Override
    public List<InitialInventoryOfEachMineOutput> listInitialInventoryOfEachMineList(InitialInventoryOfEachMine initialInventoryOfEachMine) {
        List<InitialInventoryOfEachMine> init = initialInventoryOfEachMineMapper.selectInitialInventoryOfEachMineList(initialInventoryOfEachMine);
        List<InitialInventoryOfEachMineOutput> list =new ArrayList<>();

        MiningAreaCategory fac=new MiningAreaCategory();
        fac.setIsSealed(0);
        fac.setLevel(1);
        List<MiningAreaCategory> miningAreaCategories = miningAreaCategoryService.list(fac);//公司名
        FactoryArchive factoryArchive=new FactoryArchive();
        factoryArchive.setIsSealed(0);
        List<FactoryArchive> factoryArchives = factoryArchiveMapper.selectList(factoryArchive);
        if(init.size()>0){
            for (InitialInventoryOfEachMine initial:init) {
                SubInitialInventoryOfEachMine sub=new SubInitialInventoryOfEachMine();
                sub.setInitialInventoryId(initial.getId());
                List<SubInitialInventoryOfEachMine> subin = subInitial.selectSubInitialInventoryOfEachMineList(sub);
                for (MiningAreaCategory  mining:miningAreaCategories) {
                    SubInitialInventoryOfEachMine subMine = subin.stream().filter(item -> mining.getAreaName().equals(item.getUnitName())).findFirst().orElse(new SubInitialInventoryOfEachMine());
                    if(subMine.getUnitName() == null){
                        InitialInventoryOfEachMineOutput output = new InitialInventoryOfEachMineOutput();
                        output.setUnitType("各矿期初库存录入表");
                        output.setUnitCode(mining.getAreaCode());
                        output.setUnitName(mining.getAreaName());
                        list.add(output);
                    }else {
                        if(subMine.getIsReject() == 2 ){
                            InitialInventoryOfEachMineOutput output = new InitialInventoryOfEachMineOutput();
                            output.setUnitType(initial.getUnitType());
                            output.setUnitCode(mining.getAreaCode());
                            output.setUnitName(mining.getAreaName());
                            output.setIsseparate(mining.getIsseparate());
                            output.setInitialInventoryOfThisMonth(BigDecimal.valueOf(0));
                            output.setIsreject(subMine.getIsReject());
                            list.add(output);
                        }else {
                            InitialInventoryOfEachMineOutput output = new InitialInventoryOfEachMineOutput();
                            output.setUnitType(initial.getUnitType());
                            output.setUnitCode(mining.getAreaCode());
                            output.setUnitName(mining.getAreaName());
                            output.setIsseparate(mining.getIsseparate());
                            output.setInitialInventoryOfThisMonth(subMine.getInitialInventoryOfThisMonth());
                            output.setIsreject(subMine.getIsReject());
                            list.add(output);
                        }
                    }
                }

                for (FactoryArchive factory:factoryArchives) {
                    SubInitialInventoryOfEachMine subMine = subin.stream().filter(item -> factory.getFactoryName().equals(item.getUnitName())).findFirst().orElse(new SubInitialInventoryOfEachMine());
                    if(subMine.getUnitName() == null){
                        InitialInventoryOfEachMineOutput output=new InitialInventoryOfEachMineOutput();
                        output.setUnitType("各矿期初库存录入表");
                        output.setIsseparate(0);
                        output.setUnitCode(factory.getFactoryCode());
                        output.setUnitName(factory.getFactoryName());
                        list.add(output);
                    }else {
                        if(subMine.getIsReject()==2){
                            InitialInventoryOfEachMineOutput output=new InitialInventoryOfEachMineOutput();
                            output.setUnitType(initial.getUnitType());
                            output.setUnitCode(factory.getFactoryCode());
                            output.setUnitName(factory.getFactoryName());
                            output.setIsseparate(0);
                            output.setInitialInventoryOfThisMonth(BigDecimal.valueOf(0));
                            output.setIsreject(subMine.getIsReject());
                            list.add(output);
                        }else {
                            InitialInventoryOfEachMineOutput output=new InitialInventoryOfEachMineOutput();
                            output.setUnitType(initial.getUnitType());
                            output.setUnitCode(factory.getFactoryCode());
                            output.setUnitName(factory.getFactoryName());
                            output.setIsseparate(0);
                            output.setInitialInventoryOfThisMonth(subMine.getInitialInventoryOfThisMonth());
                            output.setIsreject(subMine.getIsReject());
                            list.add(output);
                        }
                    }
                }
            }
        }
        else {
            for (MiningAreaCategory  mining:miningAreaCategories) {
                InitialInventoryOfEachMineOutput output=new InitialInventoryOfEachMineOutput();
                output.setUnitType("各矿期初库存录入表");
                output.setUnitCode(mining.getAreaCode());
                output.setUnitName(mining.getAreaName());
                list.add(output);
            }
            for (FactoryArchive factory:factoryArchives) {
                InitialInventoryOfEachMineOutput output=new InitialInventoryOfEachMineOutput();
                output.setUnitType("各矿期初库存录入表");
                output.setIsseparate(0);
                output.setUnitCode(factory.getFactoryCode());
                output.setUnitName(factory.getFactoryName());
                list.add(output);
            }
        }


        return list;
    }
    /**
     * 新增各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    @Override
    public int saveInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine) {
        initialInventoryOfEachMine.setCreateTime(DateUtils.getNowDate());
        return initialInventoryOfEachMineMapper.insertInitialInventoryOfEachMine(initialInventoryOfEachMine);
    }

    /**
     * 修改各矿期初库存录入
     * 
     * @param initialInventoryOfEachMine 各矿期初库存录入
     * @return 结果
     */
    @Override
    public int updateInitialInventoryOfEachMine(InitialInventoryOfEachMine initialInventoryOfEachMine) {
        initialInventoryOfEachMine.setUpdateTime(DateUtils.getNowDate());
        return initialInventoryOfEachMineMapper.updateInitialInventoryOfEachMine(initialInventoryOfEachMine);
    }

    /**
     * 批量删除各矿期初库存录入
     * 
     * @param ids 需要删除的各矿期初库存录入主键
     * @return 结果
     */
    @Override
    public int deleteInitialInventoryOfEachMineByIds(Long[] ids) {
        return initialInventoryOfEachMineMapper.deleteInitialInventoryOfEachMineByIds(ids);
    }

    /**
     * 删除各矿期初库存录入信息
     * 
     * @param id 各矿期初库存录入主键
     * @return 结果
     */
    @Override
    public int deleteInitialInventoryOfEachMineById(Long id) {
        return initialInventoryOfEachMineMapper.deleteInitialInventoryOfEachMineById(id);
    }
}
