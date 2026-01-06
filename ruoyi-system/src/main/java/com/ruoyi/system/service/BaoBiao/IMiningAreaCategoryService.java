package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.MiningAreaCategory;
import java.util.List;

public interface IMiningAreaCategoryService {
    MiningAreaCategory get(Long id);
    List<MiningAreaCategory> list(MiningAreaCategory query);
    List<MiningAreaCategory> oneList(MiningAreaCategory query);
    List<MiningAreaCategory> QueryTeamName(String areaCode);
    int add(MiningAreaCategory entity);
    int edit(MiningAreaCategory entity);
    int remove(Long id);
    int removeBatch(List<Long> ids);
    MiningAreaCategory getPartentId(Integer partentId);
    MiningAreaCategory getAreaName(String areaName);
    MiningAreaCategory getAreaCode(String areaCode);
}
