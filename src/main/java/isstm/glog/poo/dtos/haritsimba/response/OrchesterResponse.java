package isstm.glog.poo.dtos.haritsimba.response;

import isstm.glog.poo.enumerations.haritsimba.OrchesterResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrchesterResponse {
    Object entity;
    OrchesterResponseStatus status;
    String message;
}
