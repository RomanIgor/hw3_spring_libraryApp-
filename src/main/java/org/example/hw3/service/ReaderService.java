package org.example.hw3.service;

import org.example.hw3.entity.Reader;
import java.util.List;
import java.util.Optional;

public interface ReaderService {

    Reader addReader(Reader reader);

    List<Reader>getAllReaders();

    Optional<Reader> getReaderById(Long id);

    void deleteReader(long id);


}
