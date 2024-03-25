package hei.school.soratra.endpoint.rest.controller.health;

import hei.school.soratra.PojaGenerated;
import hei.school.soratra.file.BucketComponent;
import hei.school.soratra.repository.DummyRepository;
import hei.school.soratra.repository.DummyUuidRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@PojaGenerated
@RestController
@AllArgsConstructor
public class PingController {

  DummyRepository dummyRepository;
  DummyUuidRepository dummyUuidRepository;
  private BucketComponent bucketComponent;

  public static final ResponseEntity<String> OK = new ResponseEntity<>("OK", HttpStatus.OK);
  public static final ResponseEntity<String> KO =
          new ResponseEntity<>("KO", HttpStatus.INTERNAL_SERVER_ERROR);

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }
  @PutMapping("/soratra/{id}")
  public ResponseEntity<String>  soratra (@RequestParam String soratra,@PathVariable String id) throws IOException {
    File tempFile = File.createTempFile(id, ".txt");
    try (FileWriter writer = new FileWriter(tempFile)) {
      writer.write(soratra);
    }
    bucketComponent.upload(tempFile , id+".txt");
    return OK;

  }
}
