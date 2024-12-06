package com.tinqin.academy.persistence.filereaderfactory;

import com.tinqin.academy.persistence.filereaderfactory.base.FileReader;
import com.tinqin.academy.persistence.filereaderfactory.readers.CsvFileReader;
import com.tinqin.academy.persistence.filereaderfactory.readers.JsonFileReader;
import org.springframework.stereotype.Component;

@Component
public class FileReaderFactory {

    public FileReader createCsvFileReader(String path, Integer batchSize) {
        return new CsvFileReader(path, batchSize);
    }

    public FileReader createJsonFileReader(String path, Integer batchSize) {
        return new JsonFileReader(path, batchSize);
    }
}