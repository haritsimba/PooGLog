package isstm.glog.poo.enumerations.haritsimba;

public enum OrchesterResponseStatus
{
    SUCCESS,UNAUTHORIZED,FAILED,ERROR;
    public boolean isSuccessFull(){
        return this == SUCCESS;
    }
}
