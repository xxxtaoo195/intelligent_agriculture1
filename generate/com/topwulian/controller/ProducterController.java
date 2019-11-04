package com.topwulian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.page.table.PageTableHandler.CountHandler;
import com.topwulian.page.table.PageTableHandler.ListHandler;

import com.topwulian.dao.ProducterDao;
import com.topwulian.model.Producter;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/producters")
public class ProducterController {

    @Autowired
    private ProducterDao producterDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Producter save(@RequestBody Producter producter) {
        producterDao.save(producter);

        return producter;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Producter get(@PathVariable Long id) {
        return producterDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Producter update(@RequestBody Producter producter) {
        producterDao.update(producter);

        return producter;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return producterDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Producter> list(PageTableRequest request) {
                return producterDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        producterDao.delete(id);
    }
}
