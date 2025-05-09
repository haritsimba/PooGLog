package isstm.glog.poo.services.elio;

import isstm.glog.poo.dtos.elio.response.FileUploadOut;
import isstm.glog.poo.entities.elio.FileEntity;
import isstm.glog.poo.repositories.elio.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FileService {
    FileRepository fileRepository;

    public FileUploadOut saveFile(MultipartFile file){

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(fileName.contains("..")){
                throw new Exception("Filename invalid");
            }
            FileEntity fileToSave = new FileEntity();
            fileToSave.setFileName(fileName);
            fileToSave.setFileType(file.getContentType());
            fileToSave.setFileData(file.getBytes());
            FileEntity savedFile = fileRepository.save(fileToSave);

            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/").path(savedFile.getId()).toUriString();
            String viewUrl =  ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/view/").path(savedFile.getId()).toUriString();

            return new FileUploadOut(savedFile.getId(),downloadUrl,viewUrl,savedFile.getFileType(),file.getSize());
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<FileEntity> getFile(String fileId){
        return ResponseEntity.ok().body(fileRepository.findById(fileId).orElseThrow());
    }
}