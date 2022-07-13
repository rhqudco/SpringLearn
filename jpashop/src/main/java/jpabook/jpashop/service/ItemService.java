package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional // readOnly = false
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
/*
        @Transactional
        public void updateItem(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId); // JPA가 관리하는 영속성 객체
        findItem.setPrice(bookParam.getPrice());
        findItem.setName(bookParam.getName());
        findItem.setStockQuantity(bookParam.getStockQuantity());
        // itemRepository.save(findItem); 을 호출할 필요가 없음
        // 왜냐면 @Transactional으로 인해 JPA가 관리하는 트랜잭션이기 때문에 커밋하면
        // JPA가 flush를 날려서 변경 감지(dirty checking)를 해서 커밋해 업데이트 쿼리를 날려줌
    }
*/
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // JPA가 관리하는 영속성 객체
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
    public List<Item> findItem() {
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
