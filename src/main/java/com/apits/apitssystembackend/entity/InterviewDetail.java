package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.sql.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interviewDetail")
@Builder
public class InterviewDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date startAt;
    private Date endAt;
    @Nationalized
    private String result;
    private String recordMeeting;
    @Column(columnDefinition = "text")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interviewId", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Interview interview;
}
