package com.ruoyi.web.controller.system.BaoBiao;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.BaoBiao.Book;
import com.ruoyi.system.service.BaoBiao.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/book") // ✅ 去掉 /dev-api
public class BookController {

    @Resource
    private IBookService bookService;
    @Anonymous
    @Operation(summary = "查询图书列表")
    @GetMapping("/list")
    public AjaxResult list(Book query) {
        return AjaxResult.success(bookService.list(query));
    }
    @Anonymous
    @Operation(summary = "按ID查询图书详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable Long id) {
        return AjaxResult.success(bookService.get(id));
    }
    @Anonymous
    @Operation(summary = "新增图书")
    @PostMapping
    public AjaxResult add(@RequestBody Book book) {
        return AjaxResult.toAjax(bookService.add(book));
    }
    @Anonymous
    @Operation(summary = "修改图书")
    @PutMapping
    public AjaxResult edit(@RequestBody Book book) {
        return AjaxResult.toAjax(bookService.edit(book));
    }
    @Anonymous
    @Operation(summary = "删除图书（单个）")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return AjaxResult.toAjax(bookService.remove(id));
    }
    @Anonymous
    @Operation(summary = "删除图书（批量）")
    @DeleteMapping
    public AjaxResult removeBatch(@RequestBody List<Long> ids) {
        return AjaxResult.toAjax(bookService.removeBatch(ids));
    }
}
