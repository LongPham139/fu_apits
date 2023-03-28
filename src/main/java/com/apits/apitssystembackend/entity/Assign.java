package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Nationalized;

import java.sql.Date;
import java.util.Collection;

@Entity
@Getter
@Setter
@ToString
@Table(name = "assign")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private String status;
    @Nationalized
    private String experience;
    private String screeningStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "recruitmentRequestId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private RecruitmentRequest recruitmentRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "assignerId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Employee assigner;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "candidateId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Candidate candidate;

}

