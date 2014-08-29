<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePath}.dao.${className}" >
  
  <!-- 实体类映射mapper -->
  <resultMap id="${className}" type="${basePath}.domain.${className}" >
  	<#list list as vo>
  	     <result column="${vo.columnName}" property="${vo.propertyName}" jdbcType="<#if ${vo.dataType}=="VARCAHR">VARCHAR</#if>" />
  	</#list>
  </resultMap>
  
  
  <sql id="where_condition">
 
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
			<#list columnNames as columnName>
	  	       ${columnName} <#if  columnName_has_next>,</#if>       
	  		</#list>
		)
		values(
			<#list propertyNames as propertyName>
	  	       #${r"{"}${propertyName},jdbType=VARCHAR ${r"}"}<#if propertyName_has_next>,</#if>
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
  
  <update id="updateObject" parameterType="${basePath}.domain.${className}">

  </update>
  
  
  <select id="selectListByPage" resultMap="UserInfo" parameterType="java.lang.Integer" >

  </select>
  
  
</mapper>