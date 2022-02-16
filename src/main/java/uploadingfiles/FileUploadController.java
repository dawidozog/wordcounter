package uploadingfiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uploadingfiles.storage.StorageService;
import counter.count;
import java.io.FileNotFoundException;
import org.springframework.http.HttpStatus;


@Controller
public class FileUploadController {

	private final StorageService storageService;
        


	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}


    @PostMapping("/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file ) throws FileNotFoundException {

        storageService.store(file);
        String response = count.countFrequencies(file.getOriginalFilename());


        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
