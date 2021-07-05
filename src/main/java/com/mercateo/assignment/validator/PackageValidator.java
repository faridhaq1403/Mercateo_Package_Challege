package com.mercateo.assignment.validator;

import com.mercateo.assignment.exception.item.ItemCostViolationException;
import com.mercateo.assignment.exception.item.ItemWeightViolationException;
import com.mercateo.assignment.exception.pack.PackageItemsViolationException;
import com.mercateo.assignment.exception.pack.PackageWeightViolationException;
import com.mercateo.assignment.model.Item;
import com.mercateo.assignment.model.Package;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PackageValidator {
    /**
     *
     * @param pack
     * This is the single responsibility of this class, to validate the package data,
     * this method will apply the validation on package level and item level by calling
     * the respective functions
     */
    public void validatePackage(Package pack) {
        validatePackageItems(pack);
        validatePakcageMaxWeigth(pack);
        pack.getPackageItems().stream().forEach(item -> {
            this.validateItemCost(item);
            this.validateItemWeigth(item);
        });
    }

    /**
     *
     * @param pack
     */
    private void validatePakcageMaxWeigth(Package pack) {
        if (pack.getPackageWeigthLimit().compareTo(BigDecimal.valueOf(ValidationRules.MAX_PACK_WEIGHT.getMaxValue())) > 0) {
            throw new PackageWeightViolationException(ValidationRules.MAX_PACK_WEIGHT.getErrorMessage());
        }
    }

    /**
     *
     * @param pack
     */
    private void validatePackageItems(Package pack) {
        if (pack.getPackageItems().size() > ValidationRules.MAX_ITEMS_IN_PACK.getMaxValue().intValue()) {
            throw new PackageItemsViolationException(ValidationRules.MAX_ITEMS_IN_PACK.getErrorMessage());
        }
    }

    /**
     *
     * @param item
     */
    private void validateItemWeigth(Item item) {
        if (item.getItemWeight().compareTo(new BigDecimal(ValidationRules.MAX_ITEM_WEIGHT.getMaxValue())) > 0) {
            throw new ItemWeightViolationException(ValidationRules.MAX_ITEM_WEIGHT.getErrorMessage());
        }
    }

    /**
     *
     * @param item
     */
    private void validateItemCost(Item item) {
        if (item.getItemCost() > ValidationRules.MAX_ITEM_COST.getMaxValue()) {
            throw new ItemCostViolationException(ValidationRules.MAX_ITEM_COST.getErrorMessage());
        }
    }

}
