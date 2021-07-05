package com.mercateo.assignment.parser;

import com.mercateo.assignment.exception.ApiException;
import com.mercateo.assignment.model.Item;
import com.mercateo.assignment.model.Package;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class FileTextParser implements FileParser {

    /**
     *
     * @param path
     * @return
     */
    public List<Package> parseFile(String path) {
        List<Package> packages;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            return br.lines()
                    .filter(line -> !line.isEmpty())
                    .map(this::assemblePackage)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ApiException("Error: could not find the file on the given path " + path);
        }
    }

    /**
     *
     * @param packageLine
     * @return
     */
    private Package assemblePackage(String packageLine) {

        Pattern packageLinePattern = Pattern.compile("^(?<packageWeigthLimit>\\d+) :(?<items>( \\((.*?)\\))*)");
        Matcher packageLineMatcher = packageLinePattern.matcher(packageLine);

        if (packageLineMatcher.matches()) {
            String packageWeigthLimit = packageLineMatcher.group("packageWeigthLimit");
            String items = packageLineMatcher.group("items").trim();
            List<Item> itemsList = assembleItems(items);
            Package pack = buildPackage(new BigDecimal(packageWeigthLimit), itemsList);
            return pack;
        }
        throw new ApiException("This file contains invalid input " + packageLine);
    }


    /**
     *
     * @param itemString
     * @return
     */
    private List<Item> assembleItems(String itemString) {
        List<Item> itemList = new ArrayList<>();
        Matcher m = Pattern.compile("\\((.*?)\\)").matcher(itemString);
        while (m.find()) {
            Pattern itemPattern = Pattern.compile("^(?<index>\\d+),(?<weight>\\d+(\\.\\d+)*?),\\D(?<cost>\\d+(\\.\\d+)*?)");
            Matcher itemMatcher = itemPattern.matcher(m.group(1));
            if (itemMatcher.matches()) {
                Item item = buildItem(itemMatcher);
                itemList.add(item);

            } else {
                throw new ApiException("Item data should be a valid for " + itemString);
            }

        }
        return itemList;
    }

    /**
     *
     * @param itemMatcher
     * @return
     */
    private Item buildItem(Matcher itemMatcher) {
        Item item = Item.builder()
                .itemNo(Integer.parseInt(itemMatcher.group("index")))
                .itemWeight(new BigDecimal(itemMatcher.group("weight")))
                .itemCost(Integer.parseInt(itemMatcher.group("cost")))
                .build();
        return item;
    }

    /**
     *
     * @param packageMaxWeigth
     * @param list
     * @return
     */
    private Package buildPackage(BigDecimal packageMaxWeigth, List<Item> list) {
        Package pack = Package.builder()
                .packageItems(list)
                .packageWeigthLimit(packageMaxWeigth)
                .build();
        return pack;
    }
}
