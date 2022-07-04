package com.rain.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import static com.rain.util.common.Constants.DEPTTABLE;

import com.rain.domain.Dept;
//部门信息动态插入和动态跟新
public class DeptDynaSqlProvider {


	// 动态插入
		public String insertDept(Dept dept){
			//使用SQL类来构建动态SQL如下所示
			return new SQL(){
				{
					INSERT_INTO(DEPTTABLE);
					if(dept.getName() != null && !dept.getName().equals("")){
						VALUES("name", "#{name}");
					}
					if(dept.getRemark() != null && !dept.getRemark().equals("")){
						VALUES("remark", "#{remark}");
					}
				}
			}.toString();
		}	
		// 动态更新
		public String updateDept(Dept dept){
			
			return new SQL(){
				{
					UPDATE(DEPTTABLE);
					if(dept.getName() != null){
						SET(" name = #{name} ");
					}
					if(dept.getRemark() != null){
						SET(" remark = #{remark} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
