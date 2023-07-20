package uao.edu.co.sinapsis_app.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import uao.edu.co.sinapsis_app.dto.ArchivoDTO;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.stream.Stream;

public interface IStorageService {
    void init();

    ArchivoDTO downloadDB(Long id) throws SQLException, IOException;

    Long storeDB(MultipartFile file);

    String store(MultipartFile file);

    String storeAnuncio(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
