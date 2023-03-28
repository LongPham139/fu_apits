package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Nationalized;

import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "interview")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String purpose;
    private Date date;
    private String linkMeeting;
    private String type;
    @Nationalized
    private String round;
    @Column(columnDefinition = "text")
    private String description;
    private String status;
    @Nationalized
    private String address;
    @Column(name = "candidate_confirm", length = 10)
    private String candidateConfirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "candidateId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "assignId")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Assign assign;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Employee employee;

    @OneToOne(mappedBy = "interview")
    @EqualsAndHashCode.Include
    @ToString.Include
    private InterviewDetail interviewDetail;

    @OneToMany(mappedBy = "interview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Notification> notifications;
}
