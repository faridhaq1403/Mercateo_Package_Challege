package com.mercateo.assignment.packer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PackerTest {

    @Autowired
    private Packer packer;

    @Test
    @DisplayName("execute packer and create valid pack")
    void pack() {
        String VALID_INPUT = "D://sampleInput.txt";
        String pack = packer.solvePacking(VALID_INPUT);
        assertThat(pack).isEqualTo("4" +
                System.lineSeparator() +
                "-" +
                System.lineSeparator() +
                "2,7" +
                System.lineSeparator() +
                "8,9");
    }

}
