<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="css/table.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    *{padding:0;margin:0}
    html {overflow-x:hidden;overflow-y:scroll;}
    body {width: 100%;font-size:14px;color:#fff;font-family: "microsoft yahei",
    Tahoma,Verdana, Georgia, Arial; color:#333; background:#333;background:#fff;}
    div, ul, li, p, ol, dl {list-style:none;display: block;}
    table {cellpadding:0;cellspacing:0;}
    a, img {border:0px;}
    a{color:#333;text-decoration: none;}
    a:hover{color:#3c7ad1; text-decoration:none;}
    a:focus {outline:none;}
    .clear {clear: both;}
    .clearfix:after {
        clear: both;
        content: "";
        display: block;
        font-size: 0;
        height: 0;
        overflow: hidden;
        visibility: hidden;
    }
    .clearfix {
        zoom: 1;
    }
    *html .clearfix{
        height:1%;
    }
    *+html .clearfix{
        height:1%;
    }
    *{
        word-wrap: break-word;
        word-break: break-all;
        transition: all 0.3s ;
        -moz-transition: all 0.3s ;
        -webkit-transition: all 0.3s ;
        -o-transition: all 0.3s ;
    }
    *:hover{
        transition: all 0.3s ;
        -moz-transition: all 0.3s ;
        -webkit-transition: all 0.3s ;
        -o-transition: all 0.3s ;
    }

    /*head*/
    .content{margin: 15px;}
    .dialog-table{
        width: 100%;
        border-collapse:collapse;
        text-align: center;
        border: 1px solid #e7e7e7;
        color: #333;
        border-top: none;
        margin: 0 auto;

    }

    .dialog-table th,.dialog-table td{
        padding: 8px;
    }
    .dialog-table th{text-align: right; }
    .dialog-table .heji{text-align: left; }
    .dialog-table td{text-align: left; }
    .dialog-tableth tr{
        border-top: 1px solid #e7e7e7;
    }
    .dialog-tableth  tr:nth-of-type(odd){
        background-color: #f9f9f9;
    }
    .dialog-tableth  tr:nth-of-type(even){
        background-color: #fff;
    }

    .content-title{text-align: center;margin: 15px auto; font-size:16px;}
    .table-data{margin-bottom: 10px;}
    .table-end{margin: 10px 0px;margin-bottom: 20px;}

    .dialog-table2{
        width: 100%;
        border-collapse:collapse;
        text-align: center;
        border: 1px solid #e7e7e7;
        color: #333;
        margin: 0 auto;

    }
    .dialog-table2 th{}
    .dialog-table2 th,.dialog-table2 td{
        padding: 8px;text-align: center;border: 1px solid #e7e7e7;
    }
    .dialog-tableth tr{
        border-top: 1px solid #e7e7e7;
    }
    .dialog-tableth  tr:nth-of-type(odd){
        background-color: #f9f9f9;
    }
    .dialog-tableth  tr:nth-of-type(even){
        background-color: #fff;
    }


</style>
</head>
<body>
<#assign mayang = 0.00 />
<#assign shiyang = 0.00 />
<#assign avgPrice = 0.00 />
<#assign taxTotal = 0.00 />
<#assign publishSum = 0 />
<#assign refundSum = 0 />
<#assign publishMayang = 0.00 />
<#assign refundMayang = 0.00 />
<#assign refundShiyang = 0.00 />
<#assign publishShiyang = 0.00 />
<div class="content" >
    <h1 class="content-title">${(reportBalanceVO.provinceOrg.orgName)!}批销收回执</h1>
    <div class="table-data">日期：${reportBalanceVO.currentDate}</div>
    <table  border="1" class="dialog-table">
        <tr>
            <th scope="row">购货单位：</th>
            <td colspan="2">${(reportBalanceVO.countyOrg.orgName)!}</td>
            <th scope="row">详细地址：</th>
            <td colspan="2">${(reportBalanceVO.countyOrg.address)!}</td>
        </tr>
        <tr>
            <th scope="row"><p>开户行：</p></th>
            <td colspan="2">&nbsp;</td>

            <th scope="row">账号：</th>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>

            <th scope="row">
                纳税登记号：
            </th>
            <td>&nbsp;</td>
            <th scope="row">邮政编码：</th>
            <td>${(reportBalanceVO.countyOrg.zipCode)!}</td>
            <th scope="row">联系电话：</th>
            <td>${(reportBalanceVO.countyOrg.mobile)!}</td>
        </tr>
        <tr>
            <th scope="row">发货总册数：</th>
            <td id="publishSum">${publishSum}</td>
            <th scope="row">发货总码洋：</th>
            <td id="publishMayang">${publishMayang}</td>
            <th scope="row">发货总实洋：</th>
            <td id="publishShiyang">${publishShiyang}</td>
        </tr>
        <tr>
            <th scope="row">退货总册数：</th>
            <td id="refundSum">${refundSum}</td>
            <th scope="row">退货总码洋：</th>
            <td id="refundMayang">${refundMayang}</td>
            <th scope="row">退货总实洋：</th>
            <td id="refundShiyang">${refundMayang}</td>
        </tr>
        <tr>
            <th scope="row">不含税金额：</th>
            <td>0.00</td>
            <th scope="row">税额：</th>
            <td>0.00</td>
            <th scope="row">平均单价：</th>
            <td>0.00</td>
        </tr>
        <tr>
            <th scope="row">邮，运费：</th>
            <td colspan="2">0.000.00</td>
            <th scope="row">实收金额：</th>
            <td colspan="2" id="shiyang">${shiyang}</td>
        </tr>
        <tr>
            <th colspan="6" scope="row"  class="heji">合计：（人？ x ？  <span id="chineseTotal"></span>）</th>
        </tr>
    </table>
    <div class="table-end">以上是回执 复核后寄回，以便根据回执 付款</div>

    <table class="dialog-table2">
        <!--入库单-->
        <#if reportBalanceVO.publishVOS?? && (reportBalanceVO.publishVOS?size > 0) >
            <tr>
                <th colspan="8">发货单</th>
                <!--<th scope="col">书代号</th>
                <th scope="col">单价</th>
                <th scope="col">总册数</th>
                <th scope="col">总码洋</th>
                <th scope="col">实洋</th>
                <th scope="col">折扣</th>
                <th scope="col">邮运费</th>
                <th scope="col">开单日期</th>-->
            </tr>
            <tr>
                <th scope="col">书代号</th>
                <th scope="col">单价</th>
                <th scope="col">总册数</th>
                <th scope="col">总码洋</th>
                <th scope="col">实洋</th>
                <th scope="col">折扣</th>
                <th scope="col">邮运费</th>
                <th scope="col">开单日期</th>
            </tr>
            <#list reportBalanceVO.publishVOS  as  income>
                <tr>
                    <td>${(income.resourceCode)!}</td>
                    <td>${(income.publishPrice)!}</td>
                    <td><#assign publishSum=income.publishNum+publishSum  />${(income.publishNum)!}</td>
                    <td><#assign publishMayang=income.mayng+publishMayang  />${(income.mayng)!}</td>
                    <td><#assign publishShiyang=income.shiyang+publishShiyang  />${(income.shiyang)!}</td>
                    <td>${(income.nitemdiscountrate)!}%</td>
                    <td>0.00</td>
                    <td>${(income.publishCreateTime?string("yyyy-MM-dd"))!}</td>
                </tr>
            </#list>
        </#if>

        <#if reportBalanceVO.refundVOS?? && (reportBalanceVO.refundVOS?size > 0) >
            <tr>
                <th colspan="8">退货单</th>
                <!--<th scope="col">书代号</th>
                <th scope="col">单价</th>
                <th scope="col">总册数</th>
                <th scope="col">总码洋</th>
                <th scope="col">实洋</th>
                <th scope="col">折扣</th>
                <th scope="col">邮运费</th>
                <th scope="col">开单日期</th>-->
            </tr>
            <tr>
                <th scope="col">书代号</th>
                <th scope="col">单价</th>
                <th scope="col">总册数</th>
                <th scope="col">总码洋</th>
                <th scope="col">实洋</th>
                <th scope="col">折扣</th>
                <th scope="col">邮运费</th>
                <th scope="col">开单日期</th>
            </tr>
            <#list reportBalanceVO.refundVOS  as  refund>
                <tr>
                    <td>${(refund.resourceCode)!}</td>
                    <td>${(refund.refundPrice)!}</td>
                    <td><#assign refundSum=refund.refundTotal+refundSum  />${(refund.refundTotal)!}</td>
                    <td><#assign refundMayang=refundMayang+refund.mayangTotal  />${(refund.mayangTotal)!}</td>
                    <td><#assign refundShiyang=refundShiyang+refund.shiyang  />${(refund.shiyang)!}</td>
                    <td>${(refund.nitemdiscountrate)!}%</td>
                    <td>0.00</td>
                    <td>${(refund.refundCreateTime?string("yyyy-MM-dd"))!}</td>
                </tr>
            </#list>
        </#if>

    </table>
    <#assign  shiyang=publishShiyang-refundShiyang />
    <#assign  chineseTotal=convert.toChinese(shiyang) />
    <script type="text/javascript">
        document.getElementById("shiyang").innerHTML='${shiyang}';
        document.getElementById("publishSum").innerHTML='${publishSum}';
        document.getElementById("refundSum").innerHTML='${refundSum}';
        document.getElementById("refundMayang").innerHTML='${refundMayang}';
        document.getElementById("refundShiyang").innerHTML='${refundShiyang}';
        document.getElementById("publishMayang").innerHTML='${publishMayang}';
        document.getElementById("publishShiyang").innerHTML='${publishShiyang}';
        document.getElementById("chineseTotal").innerHTML='${chineseTotal}';
    </script>

</div>
<!--
<table sheetName="工作表2">
    <tr>
        <td colspan="6" style="font: bold 22pt 'Microsoft YaHei';">国家开放大学出版社有限公司批销托收回执</td>
    </tr>
    <tr>
        <td>日期：${currentDate!}</td>
        <td colspan="5" style="font: bold 22pt 'Microsoft YaHei';"></td>
    </tr>
    <tr>
        <td>购货单位：</td>
        <td colspan="2" style="font: bold 22pt 'Microsoft YaHei';"><#assign  name=convert.toChinese(102212452.36) />${name}</td>
        <td>详细地址：</td>
        <td colspan="2" style="font: bold 22pt 'Microsoft YaHei';"></td>
    </tr>
    <tr>
        <td>开户行：</td>
        <td colspan="2" style="font: bold 22pt 'Microsoft YaHei';"></td>
        <td>账号：</td>
        <td colspan="2" style="font: bold 22pt 'Microsoft YaHei';"></td>
    </tr>
    <tr>
        <td>纳税人登记号：</td>
        <td></td>
        <td>邮政编码：</td>
        <td></td>
        <td>联系电话：</td>
        <td colspan="2" style="font: bold 22pt 'Microsoft YaHei';"></td>
    </tr>
    <tr>
        <td freezeCol="true" style="width: 32px;">normal</td>
        <td style="width: 20px; font: bold;">Bold Font</td>
        <td style="width: 20px; color: red;">Red Color</td>
    </tr>
    <tr freezeRow="true">
        <td style="font: 16pt;">Font Size 16pt</td>
        <td style="font: italic; color: brightgreen; background: gray;">Italic</td>
        <td style="text-decoration: underline;">underline</td>
    </tr>
    <tr>
        <td colspan="2">2 col span</td>
        <td style="background: red; color: yellow;">background red; color yellow;</td>
    </tr>
    <tr>
        <td style="text-align: center; background-color: lightgray;">Center Align</td>
        <td style="text-align: right; color: #0ff;">Right Align</td>
        <td rowspan="2" style="height: 24px; vertical-align: top;">Top Align</td>
    </tr>
    <tr>
        <td colspan="2" style="border: #f00; text-align: center">Border #f00</td>
    </tr>
</table>-->
</body>
</html>