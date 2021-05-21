/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/27/2021
Time: 7:48 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import uz.pdp.demo.entity.enums.AuthProvider;
import uz.pdp.demo.utils.CommonUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, OAuth2User {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private String imageUrl;
    private String providerId;
    private String fullName;
    private String password;
    @Column(unique = true)
    private String username;
//null bo'lishi mumkin

    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private Map<String, String> attributes;

    @ManyToMany
    private List<Attachment> images;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;


    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;

    public User(String password, String username, String phoneNumber, List<Role> roles) {
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // return this.username;
        return this.phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String getName() {
        return username;
    }

    void setName(String name) {
        this.username = name;
    }

    @Override
    public Map<String, Object> getAttributes() {

        Map<String, Object> objectMap = new HashMap<>();
        Map<String, String> obStringStringMap = attributes;
        if (attributes != null) {
            obStringStringMap.keySet().forEach(key -> {
                objectMap.put(key, (Object) CommonUtils.parseStringToObject(obStringStringMap.get(key), Object.class));
            });
        }

        return objectMap;
    }

}
