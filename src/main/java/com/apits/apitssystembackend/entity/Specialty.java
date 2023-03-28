package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialty")
@Builder
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specialty")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<SpecializeSkill> specializeSkills;
    @OneToMany(mappedBy = "specialty", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "specialty")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private List<CandidateSpecialty> candidateSpecialtyList;
}
