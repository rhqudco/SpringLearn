package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // OrderService에서는 Discount에 대한 정보가 없고
        // DiscountPolicy가 알아서 계산해 줘서 나한테 줘라! 라는 설계로 구현 했기 때문에 단일 책임의 원칙을 잘 지킴
        // 단일 책임의 원칙을 지키지 못 하면 할인 정책에 대한 변경이 생겼을 때 OrderService를 수정해야 하는 불상사 발생

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}