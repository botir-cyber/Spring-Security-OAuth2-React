/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 4/3/2021
Time: 7:54 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.demo.entity.Attachment;
import uz.pdp.demo.entity.AttachmentContent;
import uz.pdp.demo.entity.User;
import uz.pdp.demo.repository.AttachmentContentRepository;
import uz.pdp.demo.repository.AttachmentRepository;
import uz.pdp.demo.security.CurrentUser;
import uz.pdp.demo.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("api/file")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    @PostMapping
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request, @CurrentUser User user) throws IOException {
        return ResponseEntity.ok(attachmentService.uploadFile(request,user));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getFile(@PathVariable UUID id, HttpServletResponse response, @RequestParam(value = "download", defaultValue ="") String download) {

        return attachmentService.getAttachmentContent(id, response,download);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delAttachment(@PathVariable UUID id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            Optional<AttachmentContent> byAttachment = attachmentContentRepository.findByAttachment(byId.get());
            if (byAttachment.isPresent()){
                attachmentContentRepository.delete(byAttachment.get());
                attachmentRepository.delete(byId.get());
                return ResponseEntity.ok("deleted");

            }
        }
        return ResponseEntity.ok("Error");
    }


}
