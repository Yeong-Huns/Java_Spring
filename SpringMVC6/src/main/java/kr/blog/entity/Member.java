package kr.blog.entity;

import lombok.Data;

import java.util.List;

@Data
public class Member {
	private int memIdx;
	private String memId;
	private String memPwd;
	private String memName;
	private int memAge;
	private String memGender;
	private String memEmail;
	private String memProfile;
	private List<AuthVO> authList;
	//authList[0].auth authList[1].auth authList[2].auth
}
