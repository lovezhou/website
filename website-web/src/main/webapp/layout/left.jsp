<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9" />

 <title>好运通物流业务管理系统</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/left.css" />
<!--[if IE 6]><script type="text/javascript">try { document.execCommand('BackgroundImageCache', false, true); } catch(e) {}</script><![endif]-->
</head>
</head>
<body>
<div id="left-menu">
	<h1>功能导航</h1>
	<div class="menu">
		<div class="toggleHd "><h2 class="ico-1"><a href="javascript:;" class="toggleHd-a">公司基本信息</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul>
					<li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/company/view.action">公司信息</a></li>
                   	<li class="sider-li"><a target="mainFrame" class="sider-a"href="${pageContext.request.contextPath}/bic/state/gotoList.action">站点信息</a></li> 
					<li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/dept/gotoList.action">部门信息</a></li>
					<li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/staff/gotoList.action">员工信息</a></li>
					<li class="sider-li"><a id="a_carList" target="mainFrame" href="${pageContext.request.contextPath}/bic/line/gotoList.action">线路信息</a></li>
					<li class="sider-li"><a id="a_partnerList" target="mainFrame" href="${pageContext.request.contextPath}/bic/partner/gotoList.action">合作公司信息</a></li>
					<li class="sider-li"><a id="a_customerList" target="mainFrame" href="${pageContext.request.contextPath}/bic/customer/gotoList.action">客户信息</a></li>
					<li class="sider-li"><a id="a_carList" target="mainFrame" href="${pageContext.request.contextPath}/bic/car/gotoList.action">车辆信息</a></li>
					<li class="sider-li"><a id="a_driverList" target="mainFrame" href="${pageContext.request.contextPath}/bic/driver/gotoList.action">驾驶员信息</a></li>
					<li class="sider-li"><a id="a_depotList" target="mainFrame" href="${pageContext.request.contextPath}/bic/depot/gotoList.action">仓库信息</a></li>
				</ul>
			</div>
		</div>
		<div class="toggleHd current"><h2 class="ico-2"><a href="${pageContext.request.contextPath}/layout/home.jsp" target="mainFrame" class="toggleHd-a">业务管理</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul>
					 <li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">承运管理</a>
						</div>
    		            <ul class="sider-item-bd">
                             <li><a id="a_carryList" target="mainFrame" class="sider-item-bd-a" href="${pageContext.request.contextPath}/bus/carry/gotoList.action" >承运单列表</a></li>
                             <li><a id="a_carryEdit" target="mainFrame" class="sider-item-bd-a" href="${pageContext.request.contextPath}/bus/carry/gotoAdd.action">添加承运单</a></li>
                             <li><a id="a_carryAdd" target="mainFrame" class="sider-item-bd-a" href="${pageContext.request.contextPath}/bus/carry/gotoAdds.action">批量添加承运单</a></li>
                        </ul>
				    </li>
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">装车管理</a>
						</div>
						<ul class="sider-item-bd">
                             <li><a target="mainFrame" class="sider-item-bd-a" href="${pageContext.request.contextPath}/bus/load/gotoList.action">装车单列表</a></li>
                             <li><a target="mainFrame" class="sider-item-bd-a" href="${pageContext.request.contextPath}/bus/load/gotoAdd.action">添加装车单</a></li>
							 <li><a id="a_readyGoList" class="sider-item-bd-a" target="mainFrame" href="${pageContext.request.contextPath}/bus/load/gotoPullout.action" >装车单发车</a></li>
                        </ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">外包管理</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_outSourcelist" target="mainFrame" href="${pageContext.request.contextPath}/bus/outsource/gotoOutSourceList.action" >外包查询</a></li>
							<li><a class="sider-item-bd-a" id="a_outSource" target="mainFrame"     href="${pageContext.request.contextPath}/bus/outsource/gotoAdd.action" >添加外包单</a></li>
							<li><a class="sider-item-bd-a" target="mainFrame"     href="${pageContext.request.contextPath}/bus/outsource/gotoOutSourceOverList.action" >完结外包单</a></li>
						</ul>
					</li>
					<li class="sider-li"><a id="a_affirmList" target="mainFrame" href="${pageContext.request.contextPath}/bus/load/gotoArrivein.action">到货签到</a></li>
					<li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">中转管理</a>
						</div>
						<ul class="sider-item-bd">
							<li><a id="a_outsourcelist" class="sider-item-bd-a" target="mainFrame" href="${pageContext.request.contextPath}/bus/outsource/gotoOutSourceTraList.action">中转查询</a></li>
							<li><a id="a_outsourceTran" class="sider-item-bd-a" target="mainFrame" href="${pageContext.request.contextPath}/bus/outsource/gotoTraAdd.action" >添加中转单</a></li>
							<li><a class="sider-item-bd-a" target="mainFrame"     href="${pageContext.request.contextPath}/bus/outsource/gotoOutSourceTraOverList.action" >完结中转单</a></li>
						</ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">出仓送货管理</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" target="mainFrame" href="${pageContext.request.contextPath}/bus/outdepot/gotoOutSignList.action">送货单查询</a></li>
							<li><a class="sider-item-bd-a" target="mainFrame" href="${pageContext.request.contextPath}/bus/outdepot/gotoAdd.action">出仓送货</a></li>
						</ul>
					</li>
					
					<li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">客户签收管理</a>
						</div>
						<ul class="sider-item-bd">
							<li><a id="a_affirmGoods" target="mainFrame" href="${pageContext.request.contextPath}/bus/outdepot/gotoCustSignList.action">客户签收查询</a></li>
							<li><a id="a_affirmGoods" target="mainFrame" href="${pageContext.request.contextPath}/bus/outdepot/gotoOutCargoSign.action">上门货物签收</a></li>
							<li><a id="a_takeTheirList" target="mainFrame" href="${pageContext.request.contextPath}/bus/outdepot/gotoTakeByCust.action">自提货物签收</a></li>
						</ul>
					</li>
					
					<li class="sider-li">
					   <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">签单管理</a>
						</div>
						<ul class="sider-item-bd">
                             <li><a id="a_signQuery" target="mainFrame" href="${pageContext.request.contextPath}/bus/carry/gotoSign.action">签单查询</a></li>
                             <li><a id="a_signReturn" target="mainFrame" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=1">签单回报</a></li>
                             <li><a id="a_signAccept" target="mainFrame" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=2">签单接收</a></li>
                             <li><a id="a_signProvide" target="mainFrame" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=3">签单发放</a></li>
                        </ul>
					</li>
					
					
					<%-- <li class="sider-li">
						 <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">货损货差</a>
						</div>
						<ul class="sider-item-bd">
							<li><a id="a_cargo_list" class="sider-item-bd-a" target="mainFrame"  href="${pageContext.request.contextPath}/bus/cargoloss/gotoList.action">货损货差列表</a></li>
							<li><a id="a_cargo_add" class="sider-item-bd-a" target="mainFrame"  href="${pageContext.request.contextPath}/bus/cargoloss/gotoArCgList.action">货损货差录入</a></li>
						</ul>
					</li> --%>
					
					<li class="sider-li">
					   <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">挂失管理</a>
						</div>
						<ul class="sider-item-bd">
                             <li><a id="a_list" target="mainFrame" href="${pageContext.request.contextPath}/bus/report/gotoList.action">挂失查询</a></li>
                             <li><a id="a_loss" target="mainFrame" href="${pageContext.request.contextPath}/bus/report/gotoLossList.action">承运单挂失</a></li>
                             <li><a id="a_untie" target="mainFrame" href="${pageContext.request.contextPath}/bus/report/gotoUntieList.action">承运单解挂</a></li>
                        </ul>
					</li>
				</ul>
			</div>
		</div>
		<!--  -->
		<div class="toggleHd"><h2 class="ico-2"><a href="javascript:;" class="toggleHd-a">财务管理</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul>
					
						<li class="sider-li">
							 <div class="sider-li-item">
	
								<a class="sider-item-a" target="mainFrame" href="javascript:;">日常费用</a>
							</div>
					    	<ul  class="sider-item-bd">
								<li><a class="sider-item-bd-a" id="a_add" target="mainFrame"  href="${pageContext.request.contextPath}/cost/costflowaccount/gotoAddIncome.action">添加入项</a></li>
								<li><a class="sider-item-bd-a" id="a_pay" target="mainFrame"  href="${pageContext.request.contextPath}/cost/costflowaccount/gotoAddPay.action">添加支出</a></li>
								
								<li><a class="sider-item-bd-a" id="a_Auditing" target="mainFrame"
									href="${pageContext.request.contextPath}/cost/costflowaccount/gotoVerifyList.action">审核</a></li>
								<li><a class="sider-item-bd-a" id="a_AuditingCost" target="mainFrame"
									href="${pageContext.request.contextPath}/cost/costflowaccount/gotoPastVerifyList.action">已审费用</a></li>
								<li><a class="sider-item-bd-a" id="a_dailyCost" target="mainFrame"
									href="${pageContext.request.contextPath}/cost/costflowaccount/gotoSearchList.action">日常费用查询</a></li>
							</ul>
					    </li>
				     
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">发货站点收付款</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_shipments" target="mainFrame"
								href="${pageContext.request.contextPath}/cost/carryNow/gotoList.action">收付款查询</a></li>
							<li><a class="sider-item-bd-a" id="a_now_pay" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryNow/gotoCarryStartNowList.action">现收现付款</a></li>
							<li><a class="sider-item-bd-a" id="a_back_pay" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryReturn/gotoCarryStartReturnList.action">回收回付款</a></li>
							<li><a class="sider-item-bd-a" id="a_search" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryMonth/gotoMonthList.action">月结查询</a></li>
							<li><a class="sider-item-bd-a" id="a_collectfees" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryMonth/gotoMonthGetList.action">月结付款</a></li>
						</ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">收货站点付款</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_search" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAfter/gotoList.action">收货点查询</a></li>
							<li><a class="sider-item-bd-a" id="a_makecollections" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAfter/gotoCarryEndAfterList.action">收货点付款</a></li>
						</ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">代收款管理</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAgent/gotoList.action">代收款查询</a></li>
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAgent/gotoGetList.action">代收款付款</a></li>
							<li><a class="sider-item-bd-a" id="a_applyList" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAgent/gotoApplyList.action">代收款付款申请</a></li>
							<li><a class="sider-item-bd-a" id="a_auditingList" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAgent/gotoCheckList.action">代收款付款审核</a></li>
							<li><a class="sider-item-bd-a" id="a_paymentList" target="mainFrame" 
								href="${pageContext.request.contextPath}/cost/carryAgent/gotoPayList.action">代收款付款</a></li>
						</ul>
					</li>
					<li class="sider-li">
					   <div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">装车费用管理</a>
						</div>
						<ul class="sider-item-bd">
							 <li><a class="sider-item-bd-a" id="li_costLoadPayList" target="mainFrame" 
							 	href="${pageContext.request.contextPath}/cost/load/gotoLoadList.action">装车费用查询</a></li>
                             <li><a class="sider-item-bd-a" id="li_costLoadPayNow" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/loadnow/gotoLoadNow.action">装车费用现付</a></li>
                             <li><a class="sider-item-bd-a" id="li_costLoadPayAfter" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/loadafter/gotoLoadAfter.action">装车费用到付</a></li>
                             <li><a class="sider-item-bd-a" id="li_costLoadPayReturn" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/loadreturn/gotoLoadReturn.action">装车费用回付</a></li>
                             <li><a class="sider-item-bd-a" id="li_costLoadPayMonth" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/loadmonth/gotoLoadMonth.action">装车费用月结</a></li>
                        </ul>
					</li>
					<li class="sider-li">
					   <div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">送货费管理</a>
						</div>
						<ul class="sider-item-bd">
							 <li><a class="sider-item-bd-a" id="li_costLoadPayList" target="mainFrame" 
							 	href="${pageContext.request.contextPath}/cost/outdepot/gotoList.action">送货费查询</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutdepotApply" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outdepot/gotoApplyList.action">送货费申请</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutdepotCheck" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outdepot/gotoCheckList.action">送货费审核</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutdepotPay" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outdepot/gotoPayList.action">送货费付款</a></li>
                        </ul>
					</li>
					<li class="sider-li">
					   <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">中转费用管理</a>
						</div>
						<ul class="sider-item-bd">
                              <li><a class="sider-item-bd-a" id="a_costOutsourceTransPayList" target="mainFrame" 
                              	href="${pageContext.request.contextPath}/cost/outsource/gotoOutSourceTranList.action">中转费用查询</a></li>
                             <li><a class="sider-item-bd-a" id="a_costOutsourceTransPayNow" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcetrannow/costOutsourceTransPayNow.action">中转费用现付</a></li>
                             <li><a class="sider-item-bd-a" id="a_costOutsourceTransPayReturn" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcetranreturn/gotoOutsourceTransPayReturn.action">中转费用回付</a></li>
                             <li><a class="sider-item-bd-a" id="a_costOutsourceTransPayMonth" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcetranmonth/gotoOutsourceTransPayMonth.action">中转费用月结</a></li>

                        </ul>
					</li>
					<li class="sider-li">
					   <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">外包费用管理</a>
						</div>
						<ul class="sider-item-bd">
                             <li><a class="sider-item-bd-a" id="li_costOutsourceList" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsource/gotoOutSourceList.action">外包费用查询</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutsourcePayNow" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcenow/gotoOutsourcePayNow.action">外包费用现付</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutsourcePayReturn" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcereturn/gotoOutsourcePayReturn.action">外包费用回付</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutsourcePayMonth" target="mainFrame" 
                             	href="${pageContext.request.contextPath}/cost/outsourcemonth/gotoOutsourcePayMonth.action">外包费用月结</a></li>

                        </ul>
					</li>
					<!-- 
					<li class="sider-li">
					   <div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">货损货差赔付信息</a>
						</div>
						<ul class="sider-item-bd">
                             <li><a class="sider-item-bd-a" id="li_costOutsourceList" 
                             	href="${pageContext.request.contextPath}/cost/costCargoDeletion/costCargoDeletionQuery.jsp">货损货差信息查询</a></li>
                             <li><a class="sider-item-bd-a" id="li_costOutsourcePayNow" 
                             	href="${pageContext.request.contextPath}/cost/costCargoDeletion/costCargoDeletionCompensate.jsp">货损货差赔付</a></li>
                        </ul>
					</li>
						  -->
				   <li class="sider-li">
				   <a  target="mainFrame" href="${pageContext.request.contextPath}/cost/statebalance/gotoList.action">站点结算</a>
				   </li>
				
				</ul>
			</div>
		</div>
		 
		  <div class="toggleHd"><h2 class="ico-2"><a href="javascript:;" class="toggleHd-a">仓库管理</a></h2></div>
		 <div class="toggleBd">
			<div class="siderNav">
				<ul class="easyui-tree" >
				    <li class="sider-li"><a id="a_depotCagoList" target="mainFrame" href="${pageContext.request.contextPath}/bus/cargoDepot/gotoList.action">库存货物管理</a></li>
					<li class="sider-li"><a id="a_depotCargoDeletionList" target="mainFrame" href="${pageContext.request.contextPath}/bus/cargoloss/gotoList.action">库存货损货差管理</a></li>
					<li class="sider-li"><a id="a_depotLogoList" target="mainFrame" href="${pageContext.request.contextPath}/bus/cargoDepot/gotoLogoList.action">库存货物日志信息</a></li>
				</ul>
			</div>
		</div>  
		 
		<!--  -->
		<!-- c:if test="${userOrgaType < 3}"  -->
		<div class="toggleHd"><h2 class="ico-2"><a href="javascript:;" class="toggleHd-a">系统管理</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul class="easyui-tree" >
				    <li class="sider-li"><a id="a_admin" target="mainFrame" href="${pageContext.request.contextPath}/sysCertify/gotoList.action">用户管理</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/admin/gotoList.action">公司管理</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/admin/gotoAddState.action">站点管理</a></li>
				    <li class="sider-li"><a id="a_lineList" target="mainFrame" href="${pageContext.request.contextPath}/bic/admin/gotoLine.action">线路管理</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/sysconf/gotoConfCarryCost.action">承运单批量录入配置</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/sysconf/gotoConfGrid.action">系统查询列表配置</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/admin/gotoConfCompany.action">公司运行参数配置</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/bic/bankratio/gotoBankRatio.action">银行扣款比例配置</a></li>
				    <li class="sider-li"><a target="mainFrame" class="sider-a" href="${pageContext.request.contextPath}/cost/costflowaccount/gotoCommonFlowAccountOutType.action">公共费用类型管理</a></li>
				</ul>
			</div>
		</div>
		<!-- 回滚管理 -->
		<div class="toggleHd"><h2 class="ico-2"><a href="javascript:;" class="toggleHd-a">回滚管理</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul>
						<li class="sider-li">
							 <div class="sider-li-item">
	
								<a class="sider-item-a" target="mainFrame" href="javascript:;">正常回滚</a>
							</div>
					    	<ul  class="sider-item-bd">
								<li><a class="sider-item-bd-a" id="a_add" target="mainFrame"  href="${pageContext.request.contextPath}/rollback/gotoCostLogList.action">承运单正常回滚</a></li>
								<li><a class="sider-item-bd-a" id="a_pay" target="mainFrame"  href="${pageContext.request.contextPath}/rollback/gotoStartNowList.action">现收现付回滚</a></li>
								
								<li><a class="sider-item-bd-a" id="a_Auditing" target="mainFrame"
									href="${pageContext.request.contextPath}/rollback/gotoStartReturnList.action">回收回付回滚</a></li>
								<li><a class="sider-item-bd-a" id="a_AuditingCost" target="mainFrame"
									href="${pageContext.request.contextPath}/rollback/gotoStartMonthList.action">月结付款回滚</a></li>
								<li><a class="sider-item-bd-a" id="a_dailyCost" target="mainFrame"
									href="${pageContext.request.contextPath}/rollback/gotoEndAfterList.action">收货点付款回滚</a></li>
								<li><a class="sider-item-bd-a" id="a_dailyCost" target="mainFrame"
									href="${pageContext.request.contextPath}/rollback/gotoAgentList.action">代收款回滚</a></li>
							</ul>
					    </li>
				     
					<li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">装车费用回滚</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_shipments" target="mainFrame"
								href="${pageContext.request.contextPath}/rollback/gotoLoadNowList.action">现付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_now_pay" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoLoadAfterList.action">到付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_back_pay" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoLoadReturnList.action">回付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_search" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoLoadMonthList.action">月结回滚</a></li>
						</ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">送货费用回滚</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_search" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoOutDepotList.action">送货费回滚</a></li>
						</ul>
					</li>
					<li class="sider-li">
						<div class="sider-li-item">

							<a class="sider-item-a" target="mainFrame" href="javascript:;">中转费用回滚</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoNowOutSourceTranList.action">现付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoReturnOutSourceTranList.action">回付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_applyList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoMonthOutSourceTranList.action">月结回滚</a></li>
						</ul>
					</li>
					<li class="sider-li">
					   <div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">外包费用回滚</a>
						</div>
						<ul class="sider-item-bd">
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoNowOutSourceList.action">现付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_searchList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoReturnOutSourceList.action">回付回滚</a></li>
							<li><a class="sider-item-bd-a" id="a_applyList" target="mainFrame" 
								href="${pageContext.request.contextPath}/rollback/gotoMonthOutSourceList.action">月结回滚</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		
		
		<div class="toggleHd"><h2 class="ico-2"><a href="javascript:;" class="toggleHd-a">报表管理</a></h2></div>
		<div class="toggleBd">
			<div class="siderNav">
				<ul>
					 <li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">业务报表</a>
						</div>
    		            <ul class="sider-item-bd">
    		            	<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/dayrpt/gotoList.action">日报表</a></li>
    		            	<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/month/gotoList.action">月报表</a></li>
    		            	<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/carryCargo/gotoList.action">货运清单表</a></li>
    		            	<!--<li class="sider-li"><a target="mainFrame" href="#">货物承运单报表</a></li>
    		            	<li class="sider-li"><a target="mainFrame" href="#">往来账户明细表</a></li>-->
    		            	<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/customer/gotoList.action">发货人（量）统计表</a></li>
						</ul>
				    </li>
				    <li class="sider-li">
						<div class="sider-li-item">
							<a class="sider-item-a" target="mainFrame" href="javascript:;">财务报表</a>
						</div>
    		            <ul class="sider-item-bd">
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/balanceSheetRpt/view.action">收支表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/load/gotoList.action">单车利润表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/monthreturn/gotoList.action">月结回结报表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/agentrpt/gotoList.action">代收款统计表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/osSettle/gotoList.action">外包结算报表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/osTranSettle/gotoList.action">中转结算报表</a></li>
    		            		<!-- <li class="sider-li"><a target="mainFrame" href="#">中转未转报表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="#">发货人结算表</a></li> -->
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/stateGetGoingRpt/gotoList.action">站点应付款汇总表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/balanceend/gotoList.action">收货站点结算表</a></li>
    		            		<li class="sider-li"><a target="mainFrame" href="${pageContext.request.contextPath}/report/balancestart/gotoList.action">发货站点结算表</a></li>
    		            		<!-- <li class="sider-li"><a target="mainFrame" href="#">现付款缴款列表</a></li> -->
								<li class="sider-li"><a id="a_admin" target="mainFrame" href="${pageContext.request.contextPath}/report/nowPayCash/gotoList.action">现收现付统计表</a></li>
                        </ul>
				    </li>
				 </ul>
				
			</div>
		</div>
	</div>
</div>
<script src="${pageContext.request.contextPath}/style/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/layout.js"></script>
</body>
</html>


