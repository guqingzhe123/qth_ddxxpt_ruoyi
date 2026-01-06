package com.ruoyi.system.service.impl.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.Book;
import com.ruoyi.system.mapper.BaoBiao.BookMapper;
import com.ruoyi.system.service.BaoBiao.IBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book get(Long id) { return bookMapper.selectById(id); }

    @Override
    public List<Book> list(Book query) { return bookMapper.selectList(query); }

    @Override
    public int add(Book book) { return bookMapper.insert(book); }

    @Override
    public int edit(Book book) { return bookMapper.update(book); }

    @Override
    public int remove(Long id) { return bookMapper.deleteById(id); }

    @Override
    public int removeBatch(List<Long> ids) { return bookMapper.deleteByIds(ids); }
}
