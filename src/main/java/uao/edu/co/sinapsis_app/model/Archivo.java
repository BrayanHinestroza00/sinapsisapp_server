package uao.edu.co.sinapsis_app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Blob;

@Data
@Entity
@Table(name = "T_SINAPSIS_ARCHIVOS")
public class Archivo {
    @Id
    @SequenceGenerator(name = "SEC_T_SINAPSIS_ARCHIVOS", sequenceName = "SEC_T_SINAPSIS_ARCHIVOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_T_SINAPSIS_ARCHIVOS")
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILENAME")
    private String filename;

    @Column(name = "EXTENSION")
    private String extension;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "DOCUMENT_FILE")
    private Blob documentFile;
}
