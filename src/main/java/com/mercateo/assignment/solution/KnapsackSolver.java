package com.mercateo.assignment.solution;

import com.mercateo.assignment.model.Item;
import com.mercateo.assignment.model.Package;

import java.util.List;

public interface KnapsackSolver {

    List<Item> solve(Package pack);
}
