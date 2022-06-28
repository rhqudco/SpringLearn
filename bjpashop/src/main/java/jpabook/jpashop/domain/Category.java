package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM", // 중간 테이블 매핑 작업
                joinColumns = @JoinColumn(name = "CATEGORY_ID"), // Category가 Join하는 컬럼 명시
                inverseJoinColumns = @JoinColumn(name = "ITEM_ID") // Item이 Join하는 컬럼 명시
    )
    private List<Item> items = new ArrayList<>();
}