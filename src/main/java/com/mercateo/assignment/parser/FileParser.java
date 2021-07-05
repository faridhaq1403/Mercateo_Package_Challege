package com.mercateo.assignment.parser;

import com.mercateo.assignment.model.Package;

import java.util.List;

public interface FileParser {
    List<Package> parseFile(String path);
}
