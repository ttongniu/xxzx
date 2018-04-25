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
<SCRIPT language=JavaScript type=text/javascript 
src="js/calendar.js"></SCRIPT>

<SCRIPT language=javascript>init();</SCRIPT>

<SCRIPT language=JavaScript1.2>
function onechange(theform)
{
   if(form1.workflowid_moren.value=="200010381")
    {
        theform.action = "WF_Daiban_fp.jsp";
	}else{
        theform.action = "WF_Daiban.jsp";
	}
	
	 theform.submit();	
}

</SCRIPT>

<META name=GENERATOR content="MSHTML 11.00.9600.18739"></HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 width="98%">
  <TBODY>
  <TR>
    <TD height=5 colSpan=2></TD></TR>
  <TR>
    <TD background=images/sy_text_1_bg.gif width="41%"><IMG 
      src="images/sy_text_1.gif" 	></TD>
    <TD background=images/sy_text_1_bg.gif width="59%">
      <DIV align=right></DIV></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
  <TBODY></TBODY></TABLE>
<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
  <TBODY>
  <TR>
    <TD bgColor=#cccccc><IMG src="" width=5 height=1></TD></TR></TBODY></TABLE>
<TABLE>
  <TBODY>
  <TR bgColor=#ffffff>
    <TD colSpan=7>
      <FORM method=post name=form2 action=WF_Daiban.jsp><INPUT size=30 
      value=请输入流程单据的编号关键字 name=key> <INPUT type=submit value=SEARCH name=but_1> 
      </FORM></TD></TR></TBODY></TABLE>
<TABLE cellSpacing=1 cellPadding=0 width="100%" bgColor=#999999 border=0>
  <TBODY>
  <TR bgColor=#ebebf1>
    <TD>
      <DIV align=center>序号</DIV></TD>
    <TD height=20 width="16%">
      <DIV align=center>收到的时间</DIV></TD>
    <TD height=20 width="23%">
      <DIV align=center>流程名称</DIV></TD>
    <TD bgColor=#ebebf1 width="7%">
      <DIV align=center>提交人</DIV></TD>
    <TD bgColor=#ebebf1 width="17%">
      <DIV align=center>编号</DIV></TD>
    <TD width="20%">
      <DIV align=center>描述</DIV></TD>
    <TD width="17%">
      <DIV align=center>当前状态</DIV></TD>
    <TD width="11%">
      <DIV align=center>操作</DIV></TD></TR>
  <TR>
    <TD bgColor=#ffffff height=20 colSpan=7>
      <MARQUEE onmouseover=this.scrollDelay=1000 onmouseout=this.scrollDelay=200 
      scrollDelay=200><FONT 
      color=#0066cc><STRONG>目前您没有待办文档!</STRONG></FONT></MARQUEE></TD></TR></TBODY></TABLE></BODY>
</html>