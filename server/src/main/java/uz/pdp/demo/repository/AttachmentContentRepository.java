/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 4/3/2021
Time: 8:00 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.entity.Attachment;
import uz.pdp.demo.entity.AttachmentContent;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    Optional<AttachmentContent> findByAttachment(Attachment attachment);
}
