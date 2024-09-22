package az.babayev.springrest.controller;

import az.babayev.springrest.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class FileRestController {

    private final Path rootLocation;

    public FileRestController() {
        rootLocation = Paths.get(Constants.fileRootLocation);
    }

    @Autowired
    // video yükləmək üçün
    private ResourceLoader resourceLoader;

    // faylı diskə yazmaq üçün metod
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload file", description = "Method: upload")
    public String upload(@RequestParam(name = "file") MultipartFile file) throws Exception {


        // faylı input streama çeviririk
        InputStream stream = file.getInputStream();

        // faylın original adını götürürük
        String originalFileName = file.getOriginalFilename(); //apple.jpg


        // random name düzəldirik
        UUID uuid = UUID.randomUUID();
        String randomName = uuid.toString();


        // faylın adını tipi olmadan götürürük    apple
        String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));

        // diskdə reandom nameni saxlayırıq
        String fileRandomName = originalFileName.replace(fileNameWithoutExtension, randomName);

        // Faylı diskdə saxlayırıq
        Files.copy(stream, Paths.get(Constants.fileRootLocation + "/" + fileRandomName),
                StandardCopyOption.REPLACE_EXISTING);

        // generasiya olunmuş nameni fronta qaytarırıq
        return fileRandomName;

    }

    @GetMapping("download/{filename:.+}")
    @Operation(summary = "Save file", description = "Method: saveFile")
    // files/download/dsafsasadd.jpg
    public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
        Resource file = loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + filename + "\"")
                .body(file);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }


}
