package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.ProvinceDTO;
import com.ute.farmhome.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @PostMapping
    public ResponseEntity<ProvinceDTO> create(@RequestBody ProvinceDTO dto) {
        return new ResponseEntity<ProvinceDTO>(provinceService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ProvinceDTO> update(@RequestBody ProvinceDTO dto) {
        return ResponseEntity.ok(provinceService.update(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        provinceService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
