package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String link;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<SkillLevelCourse> skillLevelCourses;

}
