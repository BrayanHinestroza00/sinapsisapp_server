package uao.edu.co.sinapsis_app.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenthicatedUser implements Serializable {
    private Long id;
    private int[] roles;
    private String username;
}
