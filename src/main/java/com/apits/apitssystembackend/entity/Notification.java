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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    private String subject;
    @Nationalized
    @Column(columnDefinition="TEXT")
    private String content;
    private Date createTime;
    private String status;

    @ManyToMany
    @JoinTable(name = "notified_candidate", joinColumns = @JoinColumn(name = "notifice_id"), inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    @JsonIgnore
    private Collection<Candidate> candidates;

    @ManyToMany
    @JoinTable(name = "notified_enterprise", joinColumns = @JoinColumn(name = "notifice_id"), inverseJoinColumns = @JoinColumn(name = "enterprise_id"))
    @JsonIgnore
    private Collection<Enterprise> enterprises;

    @ManyToMany
    @JoinTable(name = "notified_employee", joinColumns = @JoinColumn(name = "notifice_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private Collection<Employee> employees;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "interviewId")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Interview interview;
}
