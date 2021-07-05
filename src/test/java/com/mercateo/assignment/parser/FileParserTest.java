package com.mercateo.assignment.parser;

import com.mercateo.assignment.exception.ApiException;
import com.mercateo.assignment.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileParserTest {

    private static final String INCORRECT_PATH = "incorrect";
    private static final String INVALID_LINE_FORMAT = "invalid_line_format.txt";
    private static final String INVALID_CHARACTER = "invalid_character.txt";
    private static final String INVALID_ITEM_FORMAT = "invalid_item_format.txt";
    private static final String INVALID_COST = "invalid_cost.txt";


    @Test
    @DisplayName("Input file path must be valid")
    void shouldThrowExceptionIfFileNotFound() {
        FileTextParser textPackParser = new FileTextParser();

        assertThatThrownBy(() -> textPackParser.parseFile(INCORRECT_PATH))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Error: could not find the file on the given path");
    }

    @Test
    @DisplayName("Input file must be contain a valid item")
    void shouldThrowExceptionIfItemFormatIsInvalid() {
        FileTextParser textPackParser = new FileTextParser();

        assertThatThrownBy(() -> textPackParser.parseFile(Utils.getAbsolutePath(INVALID_ITEM_FORMAT)))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Item data should be a valid for");

    }

    @Test
    @DisplayName("Input file must be contain valid line")
    void shouldThrowExceptionIfFileContainInvalidLine() {
        FileTextParser textPackParser = new FileTextParser();

        assertThatThrownBy(() -> textPackParser.parseFile(Utils.getAbsolutePath(INVALID_LINE_FORMAT)))
                .isInstanceOf(ApiException.class)
                .hasMessage("This file contains invalid input");
    }

    @Test
    @DisplayName("Input file must be contain valid character")
    void shouldThrowExceptionIfFileContainInvalidChar() {
        FileTextParser textPackParser = new FileTextParser();

        assertThatThrownBy(() -> textPackParser.parseFile(Utils.getAbsolutePath(INVALID_CHARACTER)))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Item data should be a valid for");
    }

    @Test
    @DisplayName("Input file must be contain valid cost")
    void shouldThrowExceptionIfFileContainInvalidCost() {
        FileTextParser textPackParser = new FileTextParser();

        assertThatThrownBy(() -> textPackParser.parseFile(Utils.getAbsolutePath(INVALID_COST)))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Item data should be a valid for ");
    }
}