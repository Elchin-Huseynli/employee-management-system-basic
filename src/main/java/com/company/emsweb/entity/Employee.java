package com.company.emsweb.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 45, name = "name")
    String name;

    @Column(nullable = false, length = 45, name = "surname")
    String surname;

    @Column(nullable = false, unique = true, length = 45)
    String email;
}
