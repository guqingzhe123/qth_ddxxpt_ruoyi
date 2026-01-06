//package com.ruoyi.system.service.BaoBiao;
//
//import com.ruoyi.system.domain.BaoBiao.MinePlan;
//import com.ruoyi.system.vo.dto.mine.MinePlanPageQueryDTO;
//import com.ruoyi.system.domain.vo.plan.MinePlanVO;
//import java.util.List;
//
//public interface IMinePlanService {
//    MinePlan get(Long id);
//    List<MinePlan> list(MinePlan query);
//    int add(MinePlan entity);
//    int edit(MinePlan entity);
//    int remove(Long id);
//    int removeBatch(List<Long> ids);
//    List<MinePlanVO> page(MinePlanPageQueryDTO q);
//}
//package com.ruoyi.system.service.BaoBiao;
//
//import com.ruoyi.system.domain.BaoBiao.dto.mine.*;
//import com.ruoyi.system.domain.BaoBiao.vo.mine.MinePlanJsonVO;
//
//import java.util.List;
//
//public interface IMinePlanService {
//    Long add(MinePlanCreateDTO dto);
//    int edit(MinePlanUpdateDTO dto);
//    int remove(Long id);
//    MinePlanJsonVO get(Long id);
//    List<MinePlanJsonVO> page(MinePlanQueryDTO query);
//}

package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanCreateDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanPageQueryDTO;
import com.ruoyi.system.domain.BaoBiao.dto.mine.MinePlanUpdateDTO;
import com.ruoyi.system.domain.BaoBiao.po.SubMinePlanPO;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanJu;
import com.ruoyi.system.domain.BaoBiao.vo.plan.MinePlanVO;

import java.util.List;

public interface IMinePlanService {
    Long add(MinePlanCreateDTO dto);
    int edit(MinePlanUpdateDTO dto);
    int subEdit(SubMinePlanPO dto);
    int remove(Long id);
    MinePlanVO get(Long id);
    List<MinePlanVO> page(MinePlanPageQueryDTO query);
    List<MinePlanVO> allPage(MinePlanPageQueryDTO query);
    List<MinePlanJu> productionData(MinePlanJu query);
    String getState(MinePlanPageQueryDTO query);
    int deleteData(MinePlanJu query);


}
