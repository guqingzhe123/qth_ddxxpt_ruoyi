//package com.ruoyi.system.service.BaoBiao;
//
//import com.ruoyi.system.vo.dto.wash.WashCoalPlanPageQueryDTO;
//import com.ruoyi.system.vo.dto.wash.WashCoalPlanVO;
//import com.ruoyi.system.dto.WashCoalPlanSaveDTO;
//import com.ruoyi.system.dto.WashCoalPlanUpdateDTO;
//import com.ruoyi.system.dto.WashCoalPlanUnitPatchDTO;
//import com.ruoyi.system.vo.WashCoalPlanPageVO;
//import com.ruoyi.system.vo.WashCoalPlanDetailVO;
//
//import java.util.List;
//
//public interface IWashCoalPlanService {
//    Long save(WashCoalPlanSaveDTO dto);
//    WashCoalPlanDetailVO detail(Long id);
//
//    /** 分页列表（摘要） */
//    List<WashCoalPlanPageVO> pageList(String planMonth, String mineCategory, String userId);
//
//    /** 全量更新（整单替换 data_JSON 或只改头部字段） */
//    int update(WashCoalPlanUpdateDTO dto);
//
//    /** 局部更新（只改某个 unit_code 对应列） */
//    int updateUnit(WashCoalPlanUnitPatchDTO dto);
//
//    /**
//     * 分页列表（Controller 会先 startPage，再调此方法）
//     * 约定：返回 VO 里的 data_JSON 与“创建时的入参结构”一致。
//     */
//    List<WashCoalPlanVO> page(WashCoalPlanPageQueryDTO query);
//}
package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.dto.wash.*;

import java.util.List;

public interface IWashCoalPlanService {
    Long add(WashCoalPlanCreateDTO dto);
    int edit(WashCoalPlanUpdateDTO dto);
    int remove(Long id);
    WashCoalPlanVO get(Long id);
    List<WashCoalPlanVO> page(WashCoalPlanPageQueryDTO query);
    int deleteData(WashCoalPlanV1 dto);

}
