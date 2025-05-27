// …/dtos/elio/request/UpdateStatusRequest.java
package isstm.glog.poo.dtos.elio.request;

import isstm.glog.poo.entities.elio.Status;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private Status status;
}
