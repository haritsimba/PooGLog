package isstm.glog.poo.controllers.elio;
import isstm.glog.poo.entities.elio.FileEntity;
import isstm.glog.poo.services.elio.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("file")
@AllArgsConstructor

public class FileController {
    FileService fileService;

    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileId){
        ResponseEntity<FileEntity> file= fileService.getFile(fileId);
        FileEntity fileBody = file.getBody();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileBody.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename=\""+fileBody.getFileName())
                .body(new ByteArrayResource(fileBody.getFileData()));
    }

    @GetMapping("view/{fileId}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileId) {
        ResponseEntity<FileEntity> file = fileService.getFile(fileId);
        FileEntity fileBody = file.getBody();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileBody.getFileType()))
                .body(new ByteArrayResource(fileBody.getFileData()));
    }
}
