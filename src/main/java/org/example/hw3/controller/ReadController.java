package org.example.hw3.controller;



import org.example.hw3.entity.Issue;
import org.example.hw3.entity.Reader;
import org.example.hw3.service.IssuerService;
import org.example.hw3.service.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReadController {

    private final ReadService readService;
    private final IssuerService issuerService;

    @Autowired
    public ReadController(ReadService readService, IssuerService issuerService) {
        this.readService = readService;
        this.issuerService = issuerService;
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable("id") long id) {
        return readService.getReaderById(id);
    }

    @GetMapping("/all")
    public List<Reader> allReaders() {
        return readService.allReaders();
    }

    @DeleteMapping("/{id}")
    public List<Reader> deleteById(@PathVariable("id") long id) {
        return readService.deleteReader(id);
    }


    @PostMapping
    public List<Reader> addReader(@RequestBody Reader reader) {
        return readService.addReader(reader);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getReaderIssueById(@PathVariable long id) {
      Reader reader=  readService.getReaderById(id);

        if (reader == null) {
            return ResponseEntity.notFound().build();
        }

        List<Issue> issues = issuerService.getIssuesByReaderId(id);
        return ResponseEntity.ok(issues);
    }

}
