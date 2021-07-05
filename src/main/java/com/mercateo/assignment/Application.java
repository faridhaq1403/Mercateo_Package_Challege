package com.mercateo.assignment;

import com.mercateo.assignment.packer.Packer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Packer packer;

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        if (args.length > 0) {
            logger.info("\n" + packer.solvePacking(args[0]));
        }
    }


}
