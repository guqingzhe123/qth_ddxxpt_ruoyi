//package com.ruoyi.system.mapper.BaoBiao;
//
//import com.ruoyi.system.domain.BaoBiao.MinePlan;
//import com.ruoyi.system.vo.dto.mine.MinePlanPageQueryDTO;
//import com.ruoyi.system.domain.BaoBiao.entity.MinePlanRow;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//@Mapper
//public interface MinePlanMapper {
//    MinePlan selectById(@Param("id") Long id);
//    List<MinePlan> selectList(MinePlan query);
//    int insert(MinePlan entity);
//    int update(MinePlan entity);
//    int deleteById(@Param("id") Long id);
//    int deleteByIds(@Param("ids") List<Long> ids);
//    List<MinePlanRow> selectPage(@Param("q") MinePlanPageQueryDTO q);
//}
//package com.ruoyi.system.mapper.BaoBiao;
//
//import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface MinePlanMapper {
//    int insert(MinePlanPO po);
//    int update(MinePlanPO po);
//    MinePlanPO selectById(Long id);
//    int softDeleteById(Long id);
//    List<MinePlanPO> selectList(MinePlanPO criteria); // PageHelper 分页
//}
package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.po.MinePlanPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MinePlanMapper {
    int insert(MinePlanPO po);
    int update(MinePlanPO po);
    int softDeleteById(Long id);
    MinePlanPO selectById(Long id);
    List<MinePlanPO> selectList(MinePlanPO criteria);
    MinePlanPO selectMine(MinePlanPO id);
}
