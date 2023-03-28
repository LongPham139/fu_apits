package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "candidate")
@Entity
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    
    private String phone;
    
    private String candidateCode;
    
    @Column(columnDefinition = "text")
    private String image;
    
    private String gender;
    
    private Date createAt;
    
    private Date dob;
    
    private String email;
    
    @Nationalized
    private String address;
    
    private String payment;
    
    @Column(columnDefinition = "text")
    private String description;
    
    private String cv;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Account account;
    
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "candidate")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private List<CandidateSpecialty> candidateSpecialtyList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<CandidateSkillLevel> candidateSkillLevels;
    
    private String status;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Assign> assigns;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Interview> interviews;

    @ManyToMany(mappedBy = "candidates")
    @JsonIgnore
    private Collection<Notification> notifications;
}
