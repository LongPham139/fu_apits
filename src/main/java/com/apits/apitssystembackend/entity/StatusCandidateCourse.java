package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "status_candidate_course")
@Entity
@Builder
public class StatusCandidateCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "text")
    private String certificate;
    private String status;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_skill_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private CandidateSkillLevel candidateSkillLevel;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_level_course_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private SkillLevelCourse skillLevelCourse;

}
