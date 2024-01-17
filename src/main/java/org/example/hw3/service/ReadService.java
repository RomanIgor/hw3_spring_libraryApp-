package org.example.hw3.service;


import org.example.hw3.entity.Issue;
import org.example.hw3.entity.Reader;
import org.example.hw3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReadService {

    private final ReaderRepository readerRepository;

    private final IssuerService issuerService;

    @Autowired
    public ReadService(ReaderRepository readerRepository, IssuerService issuerService) {

        this.readerRepository=readerRepository;
        this.issuerService=issuerService;
    }

    public Reader getReaderById(long id){
        return readerRepository.getReaderById(id);
    }


    public List<Reader> allReaders(){
        return readerRepository.getAllReaders();
    }

    public List<Reader>deleteReader(long id){
        return readerRepository.deleteReaderById(id);
    }

    public List<Reader>addReader(Reader reader){
        return readerRepository.addReader(reader);
    }

    public List<Issue> getIssuesByReaderId(long readerId) {
        return issuerService.getIssuesByReaderId(readerId);
    }
}
