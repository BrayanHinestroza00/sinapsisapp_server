package uao.edu.co.sinapsis_app.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDTO implements Serializable {
    private int code;
    private String message;
    private Object response;


}
