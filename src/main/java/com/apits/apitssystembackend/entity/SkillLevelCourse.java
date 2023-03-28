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
@Table(name = "skill_level_course")
@Builder
public class SkillLevelCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "skill_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Skill skill;
    @ManyToOne
    @JoinColumn(name = "level_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Level level;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Course course;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status_candidate_course")
//    @EqualsAndHashCode.Include
//    @ToString.Include
//    @JsonIgnore
//    private Collection<StatusCandidateCourse> statusCandidateCourses;

}
