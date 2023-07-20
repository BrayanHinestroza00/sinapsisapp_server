package uao.edu.co.sinapsis_app.services;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uao.edu.co.sinapsis_app.beans.conf.StorageProperties;
import uao.edu.co.sinapsis_app.dao.interfaces.IStorageDAO;
import uao.edu.co.sinapsis_app.dto.ArchivoDTO;
import uao.edu.co.sinapsis_app.exceptions.StorageException;
import uao.edu.co.sinapsis_app.exceptions.StorageFileNotFoundException;
import uao.edu.co.sinapsis_app.model.Archivo;
import uao.edu.co.sinapsis_app.services.interfaces.IStorageService;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.stream.Stream;

import static uao.edu.co.sinapsis_app.util.AppUtil.genFileName;
import static uao.edu.co.sinapsis_app.util.Constants.CONTEXT_PATH_ANUNCIOS;
import static uao.edu.co.sinapsis_app.util.Constants.CONTEXT_PATH_GEN_ANUNCIOS;

@Service
public class StorageService implements IStorageService {
    @Autowired
    private IStorageDAO storageDAO;

    private final Path rootLocation;

    @Autowired
    public StorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public ArchivoDTO downloadDB(Long id) throws SQLException, IOException {
        Archivo file = storageDAO.downloadDB(id);
        ArchivoDTO archivoDTO = new ArchivoDTO();
        archivoDTO.setExtension(file.getExtension());
        archivoDTO.setFilename(file.getFilename());
        archivoDTO.setContentType(file.getContentType());
        archivoDTO.setFile(
                new String(Base64Utils.encode(
                        IOUtils.toByteArray(
                                file.getDocumentFile().getBinaryStream()))));

        return archivoDTO;
    }

    @Override
    public Long storeDB(MultipartFile file) {
        Archivo archivo = new Archivo();

        String filename = StringUtils.getFilename(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String contentType = file.getContentType();

        archivo.setFilename(filename);
        archivo.setExtension(extension);
        archivo.setContentType(contentType);
        archivo.setDocumentFile(convertToBlob(file));

        archivo = storageDAO.store(archivo);

        return archivo.getId();
    }

    private Blob convertToBlob(MultipartFile file) {
        try {
            byte[] byteData = file.getBytes();
            Blob blobs = new SerialBlob(byteData);

            return blobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String fileURL = genFileName(file.getOriginalFilename());
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(fileURL))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return fileURL;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public String storeAnuncio(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String fileURL = genFileName(file.getOriginalFilename());
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(CONTEXT_PATH_GEN_ANUNCIOS + fileURL))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return CONTEXT_PATH_ANUNCIOS + fileURL;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
