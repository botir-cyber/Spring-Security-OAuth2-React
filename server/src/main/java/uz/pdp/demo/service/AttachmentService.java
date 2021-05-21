/* Created by IntelliJ IDEA.
 User: Mirzaahmatov Aziz
Date: 4/3/2021
Time: 8:02 PM
 To change this template use File | Settings | File Templates.
*/
package uz.pdp.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.pdp.demo.entity.Attachment;
import uz.pdp.demo.entity.AttachmentContent;
import uz.pdp.demo.entity.User;
import uz.pdp.demo.payload.ResUploadFile;
import uz.pdp.demo.repository.AttachmentContentRepository;
import uz.pdp.demo.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Transactional
    public List<ResUploadFile> uploadFile(MultipartHttpServletRequest request, User user) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file;
        List<ResUploadFile> resUploadFiles = new ArrayList<>();
        while (itr.hasNext()) {
            file = request.getFile(itr.next());
            Attachment attachment = null;
            try {
                Attachment attachment1 = new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize());
                attachment1.setCreatedBy(user.getId());
                if (Objects.requireNonNull(file.getContentType()).contains("video")) {
                    Path path;
                    String path1 = new File(".").getCanonicalPath();
                    File file1 = new File(path1 + "/videos");
                    boolean created = file1.mkdir();
                    path = Files.write(Paths.get("videos/" + attachment1.getName()), file.getBytes());
                    File video = path.toFile();
                    FileServlet fileServlet = new FileServlet(video);
                    attachment1.setName(path1 + "/videos/" + attachment1.getName() + "///filePath///" + attachment1.getName());
                    attachment1 = attachmentRepository.save(attachment1);

                } else {
                    try {
                        attachment = attachmentRepository.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()));
                        attachmentContentRepository.save(new AttachmentContent(attachment, file.getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                attachment = attachmentRepository.save(attachment1);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            resUploadFiles.add(new ResUploadFile(attachment.getId(),
                    attachment.getName(),
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/file/").path(attachment.getId().toString()).toUriString(),
                    attachment.getContentType(),
                    attachment.getSize()));

        }
        return resUploadFiles;
    }

    public HttpEntity<?> getAttachmentContent(UUID attachmentId, HttpServletResponse response, String download) {

        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(NullPointerException::new);

        try {
            String path1 = attachment.getName().split("///filePath///")[0];
            File video = new File(path1);

//                   path = Files.write(Paths.get("videos/"+file.getAttachment().getName()), file.getContent());
            byte[] bytes = Files.readAllBytes(Paths.get(path1));
            new FileServlet(video);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                    .body(bytes);
        } catch (Exception e) {
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment).orElseThrow(NullPointerException::new);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                    .body(attachmentContent.getContent());
        }
    }

}
