package com.example.usermanagmentserver.entity;


import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users" )
public class AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , name = "first_name")
    private String firstName;

    @Column(nullable = false , name = "last_name")
    private String lastName;

    @Column(nullable = false , unique = true , name = "user_name")
    private String userName;

    @Column(nullable = false , unique = true)
    private String email;

    public void update(AbstractUser abstractUser){
        if (StringUtils.isNotEmpty(abstractUser.getFirstName())) this.firstName = abstractUser.getFirstName();
        if (StringUtils.isEmpty(abstractUser.getEmail())) this.email = abstractUser.getEmail();
        if (StringUtils.isNotEmpty(abstractUser.getLastName())) this.lastName = abstractUser.getLastName();
    }
}
