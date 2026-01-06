package com.ruoyi.system.mapper.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper // 若工程已全局 @MapperScan，可去掉
public interface BookMapper {
    Book selectById(@Param("id") Long id);
    List<Book> selectList(Book query);
    int insert(Book book);
    int update(Book book);
    int deleteById(@Param("id") Long id);
    int deleteByIds(@Param("ids") List<Long> ids);
}
