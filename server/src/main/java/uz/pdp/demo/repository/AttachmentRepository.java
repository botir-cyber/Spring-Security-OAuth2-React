/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 4/3/2021
Time: 7:59 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
