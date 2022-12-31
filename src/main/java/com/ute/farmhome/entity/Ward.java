package com.ute.farmhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ward")
public class Ward implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}
