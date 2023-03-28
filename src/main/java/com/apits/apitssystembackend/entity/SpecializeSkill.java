package com.apits.apitssystembackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "specialize_skill")
public class SpecializeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_id", insertable=false, updatable=false)
    private Integer skillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Skill skill;

    @Column(name = "specialize_id", insertable=false, updatable=false)
    private Integer specializeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialize_id")
    private Specialize specialize;
}