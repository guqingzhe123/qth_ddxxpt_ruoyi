package com.ruoyi.system.service.BaoBiao;

import com.ruoyi.system.domain.BaoBiao.Book;
import java.util.List;

public interface IBookService {
    Book get(Long id);
    List<Book> list(Book query);
    int add(Book book);
    int edit(Book book);
    int remove(Long id);
    int removeBatch(List<Long> ids);
}
