package org.javaside.cms.core;

import java.util.HashSet;
import java.util.Set;

import org.javaside.cms.entity.Authority;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.Role;
import org.javaside.cms.service.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author zhouxinghua
 */
public class MemberDetailService implements UserDetailsService {
	@Autowired
	private MemberManager memberManager;

	/**
	 * 获取用户Detail信息的回调函数.
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {

		Member member = memberManager.getMemberByLoginName(userName);
		if (member == null)
			throw new UsernameNotFoundException("用户" + userName + " 不存在");

		GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(member);

		boolean enabled = false;
		if (member.getState() != null && 1 == member.getState())
			enabled = true;
		// ooowo中无以下属性,暂时全部设为true.
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		org.springframework.security.userdetails.User userdetail = new org.springframework.security.userdetails.User(
				member.getLoginName(), member.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);

		return userdetail;
	}

	/**
	 * 获得用户所有角色的权限.
	 */
	private GrantedAuthority[] obtainGrantedAuthorities(Member member) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Role role : member.getRoles()) {
			for (Authority authority : role.getAuths()) {
				authSet.add(new GrantedAuthorityImpl(authority.getName()));
			}
		}
		return authSet.toArray(new GrantedAuthority[authSet.size()]);
	}
}
