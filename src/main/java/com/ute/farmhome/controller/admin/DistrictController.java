package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @PostMapping()
    public ResponseEntity<DistrictDTO> create(@RequestBody DistrictDTO dto) {
        return new ResponseEntity<DistrictDTO>(districtService.save(dto), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<DistrictDTO> update(@RequestBody DistrictDTO dto) {
        return ResponseEntity.ok(districtService.update(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        districtService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
