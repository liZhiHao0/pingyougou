package com.pinyougou.shop;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的认证类
 * Created by crowndint on 2018/10/15.
 */
public class UserDetailServiceImpl implements UserDetailsService {

    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //提交表单进行登录的时候，根据提交的username-parameter指定的参数值,从数据库查询TbSeller对象
        TbSeller seller = sellerService.findOne(username);
        //只有审核通过才去校验密码是否正确
        if ("1".equals(seller.getStatus())) {
            //这里返回的User对象的密码会和表单提交的密码进行比对
            return new User(seller.getSellerId(), seller.getPassword(), authorities);
        }
        return null;
    }

}
