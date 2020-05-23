package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Log;
import com.ecomflutter.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/Log")
public class LogRest {

    @Autowired
    private LogService logService;

    @GetMapping("/")
    public List<Log> findAll() {
        return this.logService.findAll();
    }

    @GetMapping("/{id}")
    public Log findById(@PathVariable Long id) {
        return this.logService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Log log) {
        return this.logService.save(log);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.logService.deleteById(id);
    }
}
