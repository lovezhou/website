<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePath}.dao.${className}" >
  
  <!-- 实体类映射mapper -->
  <resultMap id="${className}" type="${basePath}.domain.${className}" >
  	<#list list as vo>
  	     <result column="${vo.columnName}" property="${vo.propertyName}" jdbcType="<#if vo.dataType=="VARCHAR2" >VARCHAR<#elseif vo.dataType=="DATE" >DATE<#elseif vo.dataType=="NUMBER" >DECIMAL</#if>" />
  	</#list>
  </resultMap>
  
  <!--查询条件-->
  <sql id="where_condition">
  	<#list list as vo>
  	     <#if vo.chkCond=='1'>
	  	     <if test="${vo.propertyName} != null">
	  			and ${vo.columnName} = ${r"${"}${vo.propertyName}}
	  		</if>
		</#if>
  	</#list>
  </sql>
  
 <!--查询单个Object--> 
  <select id="selectById" resultMap="${className}">
  	select 
	  	<#list list as vo>
	  	       ${vo.propertyName} <#if  vo_has_next>,</#if>       
	  	</#list>
	   from ${tableName}  where 1=1  <include refid="where_condition"/>
  </select>
  
   <!--保存单个Object--> 
  <insert id="saveObject" parameterType="${basePath}.domain.${className}">
		insert into TB_CERTIFY_ACCOUNT (
			<#list  list as vo >
	  	       ${vo.columnName} <#if  vo_has_next>,</#if>       
	  		</#list>
		)
		values(
			<#list   list as vo >
	  	       #${r"{"}${vo.propertyName},jdbType=VARCHAR ${r"}"}<#if vo_has_next>,</#if>
	  		</#list>
        )
	</insert>
 
  <!--唯一性校验-->
  <select id="checkDateIsExits" parameterType="java.lang.String"  resultType="java.lang.Integer">
  		select 
		    count(1) result
		 from ${tableName}
		  <where>
	    </where>
  </select>
  
  <!--更新单个对象-->
  <update id="updateObject" parameterType="${basePath}.domain.${className}">
		update ${tableName}  set
		<#list list as vo>
	  	      ${vo.columnName}=#${r"{"}${vo.propertyName},jdbcType=<#if vo.dataType=="VARCHAR2" >VARCHAR<#elseif vo.dataType=="DATE" >DATE<#elseif vo.dataType=="NUMBER" >DECIMAL</#if>} <#if  vo_has_next>,</#if>       
	  	</#list>
	  	where
	  	<#list list as vo>
	  	    <#if vo.isPk=="1">${vo.columnName}=#${r"{"}${vo.propertyName},jdbcType=VARCHAR}</#if>
	  	</#list>
  </update>
  
  <!--分页查询-->
  <select id="selectListByPage" resultMap="${className}" parameterType="java.util.Map" >
	select 
	  	<#list list as vo>
	  	       ${vo.propertyName} <#if  vo_has_next>,</#if>       
	  	</#list>
	   from ${tableName}  where 1=1  <include refid="where_condition"/>
  </select>
  
  
</mapper>