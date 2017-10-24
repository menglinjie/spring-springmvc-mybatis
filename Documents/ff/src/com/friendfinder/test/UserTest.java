package com.friendfinder.test;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import com.friendfinder.dao.UserDao;
import com.friendfinder.model.UserExtension;
import com.friendfinder.model.UserQueryVo;
import com.friendfinder.util.SplitPageUtil;

public class UserTest {

	@Resource
	private UserDao dao;
	
	@Test
	public void test() {
		UserQueryVo vo = new UserQueryVo();
		SplitPageUtil page = new SplitPageUtil();
		page.setPageSize(10);
		page.setPagePos(1);
		vo.setPage(page);
		UserExtension e = new UserExtension();
		e.setAttr1(1);
		e.setAttr2(1);
		e.setAttr3(1);
		e.setAttr4(1);
		vo.setUser(e);
		
		List<UserExtension> list = dao.listOrderUser(vo);
		if (list != null) {
			for (UserExtension s : list) {
				System.out.println(s.getId() + "--" +  s.getIntroduce() + "--" + s.getNickname());
			}	
		}
	}

}
