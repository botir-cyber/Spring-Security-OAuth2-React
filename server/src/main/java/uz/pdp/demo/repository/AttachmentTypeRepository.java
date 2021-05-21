/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 4/3/2021
Time: 8:00 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.entity.AttachmentType;

import java.util.UUID;

public interface AttachmentTypeRepository extends JpaRepository<AttachmentType, UUID> {
}
