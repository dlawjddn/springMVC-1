package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(saveItem.getId());

        assertThat(saveItem.getPrice()).isEqualTo(findItem.getPrice());
    }
    @Test
    void findAll(){
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 12000, 11);
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        Assertions.assertThat(2).isEqualTo(items.size());
        Assertions.assertThat(items).contains(item1, item2);
    }
    @Test
    void updateItem(){
        Item item = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item);

        Item updateItem = new Item("changeItem", 20000, 30);

        itemRepository.updateItem(saveItem.getId(), updateItem);
        Item findItem = itemRepository.findById(saveItem.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}