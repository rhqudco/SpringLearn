package hellojpa;

import javax.persistence.*;

@Entity
public class MemberProduct {
    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne
//    @JoinColumn("MEMBER_ID")
//    private Member member;
//
//    @ManyToOne
//    @JoinColumn("PRODUCT_ID")
//    private Product product;

}
