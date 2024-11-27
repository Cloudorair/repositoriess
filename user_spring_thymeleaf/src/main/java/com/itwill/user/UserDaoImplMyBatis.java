package com.itwill.user;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.user.mapper.UserMapper;
@Repository
public class UserDaoImplMyBatis implements UserDao {
	@Autowired
	private UserMapper userMapper;

	public UserDaoImplMyBatis() throws Exception {
	}

	@Override
	public int update(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : update() 호출  ");
		int rowCount=userMapper.update(user);
		return rowCount;
	}

	@Override
	public User findUser(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUser() 호출  ");
		User user=userMapper.findUser(userId);
		return user;
	}

	@Override
	public List<User> findUserList() throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUserList 호출  ");
		List<User> userList=userMapper.findUserList();
		return userList;
	}

	@Override
	public int insert(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : insert() 호출  ");
		int rowCount=userMapper.insert(user);
		return rowCount;
	}

	@Override
	public int delete(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : delete() 호출  ");
		int rowCount=userMapper.delete(userId);
		return rowCount;
	}

	@Override
	public int countByUserId(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : countByUserId 호출  ");
		int rowCount=userMapper.countByUserId(userId);
		return rowCount;
	}

}