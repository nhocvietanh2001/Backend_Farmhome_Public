package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.ProvinceDTO;
import com.ute.farmhome.dto.WardDTO;
import com.ute.farmhome.service.ProvinceService;
import com.ute.farmhome.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ward")
public class WardController {
    @Autowired
    private WardService wardService;
    @PostMapping
    public ResponseEntity<WardDTO> create(@RequestBody WardDTO dto) {
        return new ResponseEntity<WardDTO>(wardService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<WardDTO> update(@RequestBody WardDTO dto) {
        return ResponseEntity.ok(wardService.update(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        wardService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
