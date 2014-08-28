<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7, IE=9" />
<title>业务流程</title>
<style type="text/css">
	html{-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}
	body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,textarea,p,blockquote,th,td,hr,button,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {margin:0;padding:0;}
	article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {display:block;}
	audio,canvas,video {display: inline-block;*display: inline;*zoom: 1;}
	body,button,input,select,textarea{font:12px/1.5 tahoma,arial,\5b8b\4f53;}
	input,select,textarea{font-size:100%;}
	table{border-collapse:collapse;border-spacing:0;}
	th{text-align:inherit;}
	fieldset,img{border:0;}
	iframe{display:block;}
	abbr,acronym{border:0;font-variant:normal;}
	del {text-decoration:line-through;}
	address,caption,cite,code,dfn,em,th,var {font-style:normal;font-weight:500;}
	ol,ul {list-style:none;}
	caption,th {text-align:left;}
	h1,h2,h3,h4,h5,h6 {font-size:100%;font-weight:500;}
	q:before,q:after {content:'';}
	sub, sup {font-size: 75%; line-height: 0; position: relative; vertical-align: baseline;}
	sup {top: -0.5em;}
	sub {bottom: -0.25em;}
	a:hover {text-decoration:underline;}
	ins,a {text-decoration:none;}
	.clear:after {visibility:hidden;display:block;font-size:0;content:" ";clear:both;height:0;}
	.clear {zoom:1;}
	body .none {display:none;}
	.fl {float:left;}
	.fr {float:right;}
	button {cursor:pointer;}
	html,body {background-color:#F2F9FD;}
	.word-break{word-break:break-all;word-wrap:break-word;overflow:hidden}
	.workflow {width:1090px;height:720px;margin:10px auto;}
</style>
</style>


</body>
	<div class="workflow">
	  <img src="${pageContext.request.contextPath}/style/images/workflownew.png" alt="" width="1090" height="720" usemap="#Map" />
	 <!-- 
	  <map name="Map" id="Map">
	    <area shape="rect" coords="891,289,958,351" href="${pageContext.request.contextPath}/bus/outdepot/gotoOutCargoSign.action" alt="客户签收" />
	    <area shape="rect" coords="93,71,165,140" href="${pageContext.request.contextPath}/bus/carry/gotoList.action" alt="承运单管理" />
	    <area shape="rect" coords="314,74,384,141" href="${pageContext.request.contextPath}/bus/load/gotoList.action" alt="装车单列表" />
	    <area shape="rect" coords="309,194,385,260" href="${pageContext.request.contextPath}/bus/load/gotoPullout.action" alt="装车单发车" />
	    <area shape="rect" coords="555,68,624,134" href="${pageContext.request.contextPath}/bus/load/gotoArrivein.action" alt="到货签到" />
	    <area shape="rect" coords="887,75,961,132" href="${pageContext.request.contextPath}/bus/outdepot/gotoAdd.action" alt="出仓送货" />
	    <area shape="rect" coords="671,179,743,246" href="${pageContext.request.contextPath}/bus/outsourceTran/gotoOutSourceTranList.action" alt="中转单管理" />
	    <area shape="rect" coords="776,185,843,247" href="${pageContext.request.contextPath}/bus/outdepot/gotoOutCargoSign.action" alt="客户签收-自动货物签收" />
	    <area shape="rect" coords="189,195,262,258" href="${pageContext.request.contextPath}/bus/outsource/gotoOutSourceList.action" alt="外包单管理" />
	    <area shape="rect" coords="559,410,622,478" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=1" alt="签单回报" />
	    <area shape="rect" coords="312,533,377,608" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=2" alt="签单接收" />
	    <area shape="rect" coords="194,531,259,600" href="${pageContext.request.contextPath}/bus/carry/gotoSignProcess.action?signType=3" alt="签单发放" />
      </map>
	  -->
	</div>
</html>