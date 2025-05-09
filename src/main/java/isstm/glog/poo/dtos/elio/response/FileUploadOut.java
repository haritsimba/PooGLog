package isstm.glog.poo.dtos.elio.response;

public record FileUploadOut(
        String fileName,
        String downloadUrl,
        String viewUrl,
        String fileType,
        Long fileSize
){
}