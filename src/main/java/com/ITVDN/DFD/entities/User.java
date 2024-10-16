package com.ITVDN.DFD.entities;

import lombok.*;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "_USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "_PASSWORD", nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roles=" + "[" + roles.stream().map(r -> r.name() + " ").collect(Collectors.joining()) + "]" +
                '}';
    }
}
