package com.tasukarte.common.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class LoginUser implements UserDetails{
	
	//職員ID
	private final Integer staffId;
	
	//職員名(漢字)
	private final String staffName;
	
	//病院ID
	private final Integer hospitalId;
	
	//病院名
	private final String hospitalName;
	
	//役職ID
	private final Integer positionId;
	
	//役職名
	private final String positionName;
	
	//権限ID
	private final Integer roleId;
	
	//権限名
	private final String roleName;
	
	//パスワード
	private final String password;
	
	//コンストラクタ
	public LoginUser(
			Integer staffId,
			String staffName,
			Integer hospitalId,
			String hospitalName,
			Integer positionId,
			String positionName,
			Integer roleId,
			String roleName,
			String password
			) {
		this.staffId = staffId;
        this.staffName = staffName;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.positionId = positionId;
        this.positionName = positionName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.password = password;	
	}
	
	 // 職員ID
    public Integer getStaffId() {
        return staffId;
    }

    // 職員名
    public String getStaffName() {
        return staffName;
    }

    // 病院ID
    public Integer getHospitalId() {
        return hospitalId;
    }

    // 病院名
    public String getHospitalName() {
        return hospitalName;
    }

    // 役職ID
    public Integer getPositionId() {
        return positionId;
    }

    // 役職名
    public String getPositionName() {
        return positionName;
    }

    // 権限ID
    public Integer getRoleId() {
        return roleId;
    }

    // 権限名
    public String getRoleName() {
        return roleName;
    }

    // Spring Securityが使用するパスワード
    @Override
    public String getPassword() {
        return password;
    }

    // Spring Securityが使用するユーザー名
    // 今回は職員IDをユーザー名として扱う
    @Override
    public String getUsername() {
        return staffId.toString();
    }

    // 現段階では全職員に共通の権限を設定
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_STAFF")
        );
    }
    
    //
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //
    @Override
    public boolean isEnabled() {
        return true;
    }
}
