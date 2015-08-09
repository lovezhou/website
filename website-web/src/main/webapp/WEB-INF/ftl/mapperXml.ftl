<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.dao.${className}Mapper" >
  
  <!-- 实体类映射mapper -->
  <resultMap id="${className}" type="${packageName}.domain.${className}VO" >
  	<#list list as vo>
  	     <result column="${vo.columnName}" property="${vo.propertyName}" jdbcType="<#if vo.dataType=="VARCHAR2" >VARCHAR<#elseif vo.dataType=="DATE" >DATE<#elseif vo.dataType=="NUMBER" >DECIMAL</#if>" />
  	</#list>
  </resultMap>
  
  <!--查询条件-->
  <sql id="where_condition">
  	<#list list as vo>
  	     <#if vo.chkCond=='1'>
	  	     <if test="${vo.propertyName} != null and ${vo.propertyName} != ''">
	  			and ${vo.columnName} = ${r"#{"}${vo.propertyName}}
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
  <insert id="saveObject" parameterType="${packageName}.domain.${className}VO">
		insert into ${tableName} (
			<#list  list as vo >
	  	       ${vo.columnName} <#if  vo_has_next>,</#if>       
	  		</#list>
		)
		values(
			<#list   list as vo >
	  	       #${r"{"}${vo.propertyName},jdbcType=VARCHAR ${r"}"}<#if vo_has_next>,</#if>
	  		</#list>
        )
	</insert>
 
  <!--唯一性校验-->
  <select id="checkDateIsExits" parameterType="java.lang.String"  resultType="java.lang.Integer">
  		select *
		 from ${tableName}  where 1=1 
		 <#list  list as vo >
		 	 <if test="${vo.propertyName} != null">
	  			and ${vo.columnName} = ${r"#{"}${vo.propertyName}}
	  		</if>
	  	</#list>
  </select>
  
   <!--唯一性校验-->
  <select id="isUniqueExist" parameterType="${packageName}.domain.${className}VO"  resultMap="${className}">
  		select *
		 from t_sys_dict where 1=1 
		  <#list  list as vo >
		 	 <if test="${vo.propertyName} != null and ${vo.propertyName} !=''">
	  			and ${vo.columnName} = ${r"#{"}${vo.propertyName}}
	  		</if>
	  	</#list>
  </select>
  
  
   <!--刪除-->
  <delete id="deleteById" parameterType="java.lang.String" flushCache="true" >
  		  delete from  ${tableName}  where FID= ${r"#{"}id,jdbcType=VARCHAR}
  </delete>
  
  <delete id="deleteByIds" >
		 delete from   ${tableName} where FID in
		<foreach item="item" index="index" collection="list"
			open="(" separator="," close=")">
			${r"#{"}item}
		</foreach>
	</delete>
  
  <!--更新单个对象-->
  <update id="updateObject" parameterType="${packageName}.domain.${className}VO">
		update ${tableName}  set
		<#list list as vo>
	  	      ${vo.columnName}=#${r"{"}${vo.propertyName},jdbcType=<#if vo.dataType=="VARCHAR2" >VARCHAR<#elseif vo.dataType=="DATE" >DATE<#elseif vo.dataType=="NUMBER" >DECIMAL</#if>} <#if  vo_has_next>,</#if>       
	  	</#list>
	  	where
	  	<#list list as vo>
	  	    <#if vo.isPk=="1">
	  	    	${vo.columnName}=#${r"{"}${vo.propertyName},jdbcType=VARCHAR}
	  	    	<#break>
	  	    </#if>
	  	</#list>
  </update>
  
  <!--分页查询-->
  <select id="selectListByPage" resultMap="${className}" parameterType="java.util.Map" >
	select 
	  	<#list list as vo>
	  	       ${vo.columnName} <#if  vo_has_next>,</#if>       
	  	</#list>
	   from ${tableName}  where 1=1  <include refid="where_condition"/>
  </select>
</mapper>