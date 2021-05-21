/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 3/27/2021
Time: 8:12 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.entity.Role;
import uz.pdp.demo.entity.enums.RoleName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
   List<Role> findAllByName(RoleName name);

    Optional<Role> findByName(RoleName user);
}
