package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "skill")
@Builder
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    @Column(columnDefinition = "TEXT")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<SpecializeSkill> specializeSkills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<SkillLevelCourse> skillLevelCourses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<CandidateSkillLevel> candidateSkillLevels;



}
