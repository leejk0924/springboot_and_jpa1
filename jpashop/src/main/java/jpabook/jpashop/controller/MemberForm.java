package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.AnnotationConfigUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message="회원 이름은 필수 입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    public static void main(String[] args) {
        
    }
}
