package com.mercateo.assignment.packer;

import com.mercateo.assignment.model.Package;
import com.mercateo.assignment.parser.FileParser;
import com.mercateo.assignment.presenter.ItemPresenterStrategy;
import com.mercateo.assignment.solution.KnapsackSolver;
import com.mercateo.assignment.validator.PackageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Packer {

    @Autowired
    private FileParser fileParser;
    @Autowired
    private PackageValidator packageValidator;
    @Autowired
    private KnapsackSolver solver;
    @Autowired
    private ItemPresenterStrategy itemPresenter;

    /**
     * @param path
     * @return Single responsibility of this class to solve the packing
     */
    public String solvePacking(String path) {
        // Build packages with the help from File Parser
        List<Package> packs = fileParser.parseFile(path);
        // Now validate all the Packages
        packs.stream().forEach(pack -> packageValidator.validatePackage(pack));

        // Now solve each package and print the solution
        return packs.stream()
                .map(pack -> solver.solve(pack))
                .map(itemPresenter::print).
                collect(Collectors.joining(System.lineSeparator()));

    }

}
