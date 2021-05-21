/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/27/2021
Time: 8:13 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String userName);
    Boolean existsByUsername(String username);
    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);
}
