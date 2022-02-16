package uploadingfiles.storage;


import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;
import uploadingfiles.storage.FileSystemStorageService;
import uploadingfiles.storage.StorageProperties;


public class FileSystemStorageServiceTests {

	private StorageProperties properties = new StorageProperties();
	private FileSystemStorageService service;

	@BeforeEach
	public void init() {
		properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
		service = new FileSystemStorageService(properties);
		service.init();
	}

	@Test
	public void loadNonExistent() {
		assertThat(service.load("foo.txt")).doesNotExist();
	}

	@Test
	public void saveAndLoad() {
		service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World".getBytes()));
		assertThat(service.load("foo.txt")).exists();
	}



	@Test
	public void savePermitted() {
		service.store(new MockMultipartFile("foo", "bar/../foo.txt",
				MediaType.TEXT_PLAIN_VALUE, "Hello, World".getBytes()));
	}

}
