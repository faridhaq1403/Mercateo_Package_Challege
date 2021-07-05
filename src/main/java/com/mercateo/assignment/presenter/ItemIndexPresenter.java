package com.mercateo.assignment.presenter;

import com.mercateo.assignment.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This presenter is a implementation of {@link ItemPresenterStrategy} that print {@link Item} itemIndex field and joined by comma.
 */
@Component
public class ItemIndexPresenter implements ItemPresenterStrategy {
    @Override
    public String present(List<Item> items) {
        return items.stream().map(Item::getItemNo).map(String::valueOf).collect(Collectors.joining(","));
    }
}
