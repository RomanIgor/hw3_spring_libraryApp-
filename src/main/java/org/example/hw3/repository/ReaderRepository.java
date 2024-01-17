package org.example.hw3.repository;


import org.example.hw3.entity.Reader;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
    readers.add(new Reader("Igor"));
    readers.add(new Reader("Roman"));
    readers.add(new Reader("Christina"));

  }


  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
            .findFirst()
            .orElse(null);
  }

  public List<Reader> getAllReaders(){
    return List.copyOf(readers);
  }

  public List<Reader> deleteReaderById(long id){

    readers.removeIf(reader -> reader.getId()==id);
    return readers;

  }

  public List<Reader>addReader(Reader reader){
    readers.add(reader);
    return readers;
  }

}
