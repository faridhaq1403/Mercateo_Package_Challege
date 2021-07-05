package com.mercateo.assignment.solution;

import com.mercateo.assignment.model.Item;
import com.mercateo.assignment.model.Package;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class DynamicKnapsackSolver implements KnapsackSolver {
    /**
     * @param pack
     * @return
     */
    @Override
    public List<Item> solve(Package pack) {
        int precision = 100;

        Item[] items = getItemArrayWithLongWeights(pack, precision);

        BigDecimal maxWeight = pack.getPackageWeigthLimit().multiply(new BigDecimal(precision));

        // sort items because send a package which weighs less in case there is more than one package with the same price.
        Arrays.sort(items, Comparator.comparing(Item::getItemCost).thenComparing(Item::getItemWeight));

        int[][] matrix = new int[pack.getPackageItems().size() + 1][maxWeight.intValue() + 1];

        //we need to set first row and first column to zero, as with zero capacity or zero item we can't do anything
        for (int weight = 0; weight < maxWeight.intValue(); weight++) {
            matrix[0][weight] = 0;
        }
        //Generate the matrix with dimemsions of max weight and max items
        generateMatrix(pack, items, maxWeight, matrix);

        /*
         *Iterate the matrix , looking for items to include in solution
         * iterate from max cost because items sorted by cost and then weight.
         */
        List<Item> result = traceBackSolution(pack, items, maxWeight, matrix);
        //This sort for pretty representation based of example so can be removed.
        result.sort(Comparator.comparing(Item::getItemNo));
        return result;
    }

    private List<Item> traceBackSolution(Package pack, Item[] items, BigDecimal maxWeight, int[][] matrix) {
        List<Item> result = new ArrayList<>();
        int maxCost = matrix[pack.getPackageItems().size()][maxWeight.intValue()];

        for (int i = pack.getPackageItems().size(); i > 0 && maxCost > 0; i--) {
            if (maxCost != matrix[i][maxWeight.intValue()]) {
                result.add(items[i]);
                maxCost -= items[i].getItemCost();
            }
        }
        return result;
    }

    private void generateMatrix(Package pack, Item[] items, BigDecimal maxWeight, int[][] matrix) {
        for (int currentItemIndex = 1; currentItemIndex <= pack.getPackageItems().size(); currentItemIndex++) {
            //loops through the item weights - finds the optimal solution for the given item and the given weight in the array
            for (int currentCapacity = 0; currentCapacity <= maxWeight.intValue(); currentCapacity++) {

                if (items[currentItemIndex - 1].getItemWeight().intValue() > currentCapacity) {
                    matrix[currentItemIndex][currentCapacity] = matrix[currentItemIndex - 1][currentCapacity];
                } else {
                    matrix[currentItemIndex][currentCapacity] = Math.max(matrix[currentItemIndex - 1][currentCapacity],
                            matrix[currentItemIndex - 1][currentCapacity - items[currentItemIndex - 1].getItemWeight().intValue()] + items[currentItemIndex - 1].getItemCost());
                }
            }
        }
    }

    private Item[] getItemArrayWithLongWeights(Package pack, int precision) {
        Item[] items = pack.getPackageItems().stream()
                .map(i -> new Item(i.getItemNo(), i.getItemWeight().multiply(new BigDecimal(precision)), i.getItemCost())).
                        toArray(Item[]::new);
        return items;
    }
}
