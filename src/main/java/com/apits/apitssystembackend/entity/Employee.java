package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "phone")
    private String phone;
    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "status")
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Position position;

    @OneToMany(mappedBy = "assigner", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Assign> assigns;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Interview> interviews;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private Collection<Notification> notifications;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Include
    @ToString.Include
    @JsonIgnore
    private Collection<Contract> contracts;
}

