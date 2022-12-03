package com.ute.farmhome.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"fruit"})
@Entity
@Table(name = "fruit_image")
public class FruitImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String url;
    @ManyToOne
    @JoinColumn(name = "fruit_id")
    @JsonIgnore
    Fruit fruit;
}
