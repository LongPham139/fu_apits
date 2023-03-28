package com.apits.apitssystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request_skill")
public class RequestSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recruitment_request_id", insertable=false, updatable=false)
    private Integer recruitmentRequestId;

    @Column(name = "skill_id", insertable=false, updatable=false)
    private Integer skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_request_id")
    private RecruitmentRequest recruitmentRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public RequestSkill(Integer recruitmentRequestId, Integer skillId) {
        this.recruitmentRequestId = recruitmentRequestId;
        this.skillId = skillId;
    }
}