package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "candidate_skill_level")
@Entity
@Builder
public class CandidateSkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Skill skill;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Level level;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Candidate candidate;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status_candidate_course")
//    @EqualsAndHashCode.Include
//    @ToString.Include
//    @JsonIgnore
//    private Collection<StatusCandidateCourse> statusCandidateCourses;

}
