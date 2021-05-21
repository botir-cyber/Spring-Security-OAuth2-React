package uz.pdp.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResUploadFile {
    private UUID fileId;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
