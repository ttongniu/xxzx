<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<LINK 
rel=stylesheet type=text/css href="css/css.css">
<SCRIPT language=javascript>
function tick() {
var hours, minutes, seconds, ap;
var intHours, intMinutes, intSeconds;
var today;
today = new Date();
intHours = today.getHours();
intMinutes = today.getMinutes();
intSeconds = today.getSeconds();
if (intHours == 0) {
hours = "0:";
ap = "午夜";
} else if ((intHours >0)&&(intHours < 8)){
hours = intHours+":";
ap = "清晨";
}  else if ((intHours >=8)&&(intHours < 12)){
hours = intHours+":";
ap = "上午";}else if (intHours == 12) {
hours = "12:";
ap = "中午";
} else if((intHours >12)&&(intHours <19)) {
hours =  intHours-12 + ":";
ap = "下午";}else{
hours = intHours-12 + ":";
ap = "晚上";
}
if (intMinutes < 10) {
minutes = "0"+intMinutes+":";
} else {
minutes = intMinutes+":";
}
if (intSeconds < 10) {
seconds = "0"+intSeconds+" ";
} else {
seconds = intSeconds+" ";
}
timeString = "<font size=2>"+hours+minutes+seconds+ap+"</font>";
Clock.innerHTML = timeString;
window.setTimeout("tick();", 1000);
}
window.onload = tick;
</SCRIPT>

<SCRIPT language=javascript>
function switchSysBar(){
	if (switchPoint.innerText==3){
		switchPoint.innerText=4
		document.all("frmMenu").style.display="none"
	}
	else{
		switchPoint.innerText=3
		document.all("frmMenu").style.display=""
	}
}
</SCRIPT>

<STYLE type=text/css>.navPoint {
	CURSOR: hand; FONT-SIZE: 15pt; FONT-FAMILY: Webdings; COLOR: #ffffff
}
</STYLE>
<LINK rel=stylesheet type=text/css href="css/dtree.css">
<LINK rel=stylesheet type=text/css href="css/css.css">
<BODY style="OVERFLOW-Y: hidden" leftMargin=3 topMargin=0>
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD height=115 background=images/05logo.gif width=20>&nbsp; 
    </TD></TR>
  <TR>
    <TD height=25 background=images/index_banner_bg.gif width="100%">
      <TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#006fc3 
        border=0>
        <TBODY>
        <TR>
          <TD height=20 width="12%">
            <DIV align=center><FONT color=#ffffff>******：您好！</FONT></DIV></TD>
          <TD width="8%">
            <DIV align=left><SPAN onclick=switchSysBar() id=switchPoint 
            title=关闭/开启工具栏 class=navPoint>3</SPAN> </DIV></TD>
          <TD width="67%">
            <DIV align=right><FONT color=white size=2>
            <FONT color=white size=2><SPAN 
          id=Clock></SPAN></FONT></FONT></DIV></TD>
          <TD width="13%">
            <DIV align=right>| <A class=title 
            href="#"><FONT 
            color=#ffffff>重新登录</FONT></A> |&nbsp; 
  </DIV></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD height="100%" vAlign=top width="100%">
      <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD id=frmMenu vAlign=top width=190>
            <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" 
            border=0>
              <TBODY>
              <TR>
                <TD bgColor=#ffffff height=1><IMG 
                  src="images/spacer.gif" width=1 height=1></TD></TR>
              <TR>
                <TD><IFRAME 
                  style="HEIGHT: 100%; WIDTH: 190px; Z-INDEX: 2; VISIBILITY: inherit" 
                  src="jsp/toolbar_all.jsp" frameBorder=0 
                  name=left scrolling=yes></IFRAME></TD></TR></TBODY></TABLE></TD>
          <TD bgColor=#006fc3 width=1><IMG src="" width=1 height=1></TD>
          <TD bgColor=#e0e0e0 vAlign=top width=1><IMG 
            src="images/index_banner_bg1.gif" width=6 height=3><IMG 
            src="" width=5 height=1></TD>
          <TD width="100%">
          <Iframe src="jsp/WF_Daiban.jsp" onload="this.style.height=document.body.clientHeight" style="HEIGHT: 100%; VISIBILITY: inherit; WIDTH: 100%; Z-INDEX: 2" scrolling="auto" frameborder="0" name="content"></iframe>
          </TD></TR></TBODY></TABLE></TD></TR>
	  <TR>
	    <TD bgColor=#cccccc height=20 width="100%">
	      <DIV class=font-bottom align=center>Copyright 2003 亿阳信通股份有限公司版权所有 
	      浏览分辨率为1024*768</DIV></TD></TR></TBODY></TABLE></BODY>
</html>