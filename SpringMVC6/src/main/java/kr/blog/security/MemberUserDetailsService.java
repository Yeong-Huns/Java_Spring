package kr.blog.security;

import kr.blog.entity.Member;
import kr.blog.entity.MemberUser;
import kr.blog.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//@userDetailService
public class MemberUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member mvo = mapper.memLogin(username);
        //-->UserDetail implement -> user -> extends -> memberUser
        if(mvo != null) {
            return new MemberUser(mvo); // new MemberUser(mvo); // Member, authVo
        }else{
            throw new UsernameNotFoundException("user with username"+ username + "dose not exist");
        }
    }
}

