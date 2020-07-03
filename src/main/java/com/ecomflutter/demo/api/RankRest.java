package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Rank;
import com.ecomflutter.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ecomflutter/rank")
public class RankRest {

    @Autowired
    private RankService rankService;

    @GetMapping("/")
    public List<Rank> findAll() {
        return this.rankService.findAll();
    }

    @GetMapping("/{id}")
    public Rank findById(@PathVariable Long id) {
        return this.rankService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Rank rank) {
        return this.rankService.save(rank);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.rankService.deleteById(id);
    }
}
