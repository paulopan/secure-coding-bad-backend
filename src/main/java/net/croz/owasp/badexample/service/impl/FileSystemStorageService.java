package net.croz.owasp.badexample.service.impl;

import net.croz.owasp.badexample.exception.StorageException;
import net.croz.owasp.badexample.exception.StorageFileNotFoundException;
import net.croz.owasp.badexample.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService {

    // OWASP[184]
    private final Path rootLocation = Paths.get("owasp-guidelines-bad-backend" + File.separator + "src" + File.separator + "main" + File.separator + "resources");
    // private final Path rootLocation = Paths.get("src" + File.separator + "main" + File.separator + "resources");

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException(
                    "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
            }

            return file.getOriginalFilename();
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
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

}
