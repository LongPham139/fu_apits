package com.apits.apitssystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "specialize")
public class Specialize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}