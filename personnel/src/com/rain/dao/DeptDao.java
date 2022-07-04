package com.rain.dao;

import static com.rain.util.common.Constants.DEPTTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.rain.dao.provider.DeptDynaSqlProvider;
import com.rain.domain.Dept;
//定义部门dao接口
public interface DeptDao {
	//查询部门
	//MyBatis注解方式是将SQL直接注解写在接口上 。 这种方式的优点是对于需求比较简单的系统，效率较高。
	// 缺点是：当SQL有变化时都需要重新编译代码。
	//mybatis的查询注解 查询所有部门
	@Select("select * from "+DEPTTABLE+" ")
	//在mybatis中不用xml文件的形式，selectAllDept方法执行的就是上面的Sql语句
	List<Dept> selectAllDept();

	//mybatis的查询注解 根据名字查询部门
	@Select("select * from "+DEPTTABLE+" where name like CONCAT('%',#{content},'%')")
	//在mybatis中不用xml文件的形式，selectLikeAllDept方法执行的就是上面的Sql语句
	List<Dept> selectLikeAllDept(String content);
	
	//遇到动态的语句就需要用到下面的形式
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);

	//mybatis的查询注解 根据id查询部门
	@Select("select * from "+DEPTTABLE+" where id = #{id}")
	//
	Dept get_Info(Integer id);

	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update_Info(Dept dept);
	// 根据id删除部门
	@Delete(" delete from "+DEPTTABLE+" where id = #{id} ")
	void delete_Info(Integer id);

	
}
