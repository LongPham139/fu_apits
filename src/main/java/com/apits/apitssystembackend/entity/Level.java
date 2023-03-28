package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "level")
@Builder
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "level")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<SkillLevelCourse> skillLevelCourses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "level")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<CandidateSkillLevel> candidateSkillLevels;


}
