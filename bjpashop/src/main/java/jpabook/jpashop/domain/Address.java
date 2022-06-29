package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter @Setter(AccessLevel.PRIVATE) // setter는 private으로
public class Address {
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    public String fullAddress() {
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    // 직접 접근하지 않고 getter를 통해 필드에 접근한다.
    // 필드에 직접 접근하면 프록시일 때는 값을 얻어오지 못 하기 때문에 계산이 되지 않는다.
    // getter를 통해 접근하면 진짜 객체에 접근해서 값을 얻어올 수 있음.
    // JPA에서는 항상 프록시에 대비해서 코드를 작성해야 한다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}