package com.zhukovskiy.task3.reader.impl;

import com.zhukovskiy.task3.reader.FileDataReader;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataReaderImpl implements FileDataReader {
    @Override
    public String readData(String path) throws Exception {
        return Files.readString(Path.of(path));
    }
}
