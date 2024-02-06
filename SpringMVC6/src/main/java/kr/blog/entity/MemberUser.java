package kr.blog.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;
//인증 후 사용자 정보를 저장하기
@Getter
public class MemberUser extends User {

    private Member member;
    public MemberUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public MemberUser(Member mvo) {
        super(mvo.getMemId(), mvo.getMemPwd(),
                mvo.getAuthList().stream()
                        .map(auth->new SimpleGrantedAuthority(auth.getAuth()))
                        .collect(Collectors.toList())
        );
        //List<AuthVo> --> Collectino<SimpleGarentedAuthority>
        this.member=mvo;
    }

}
