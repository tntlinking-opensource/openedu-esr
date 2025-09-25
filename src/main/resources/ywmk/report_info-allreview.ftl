<#include "/macro/crud-metro-page3.ftl" >

<@crudmetropage3>

	<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
	<div class="row-fluid cpquery" style="">
		<div class="span2"></div>
	  	<div class="span3">
	  		<label class="control-label">
				<b>版本号：</b> <input type="text" class="" style="margin-bottom:0px !important">
			</label>
			
	  	</div>
	  	<div class="span3">
	  		<label class="control-label">
				<b>表号：</b> 
			</label>
			<input type="text" class="" style="margin-bottom:0px !important">
	  	</div>
	  	<div class="span3">
	  		<label class="control-label">
				<b>表名：</b> 
			</label>
			<input type="text" class="" style="margin-bottom:0px !important">
	  	</div>
	  	<div class="span1"></div>
	</div>
	
	<div class="row-fluid cpmidrow">
		<center>
			&nbsp;&nbsp;
		</center>
	</div>
	<!--顶部查询条件，根据实际情况选是上下，还是左右布局-->
		
	<div class="row-fluid">
		<div class="span12" id="allpage">
			<div class="row-fluid">
				<div class="span2">
					<select id="webNameOpt" name="webName">
			      		
			      	</select>
				</div>
				<div class="span10">
					<div id="showDiv">
						<IFRAME onLoad='iFrameHeight()' name='Main' id='Main' STYLE='WIDTH:100%;HEIGHT:100%' scrolling='auto'  frameborder='0' src='http://www.baidu.com' marginWidth='0' marginHeight='0' frameBorder='0' LEFTMARGIN='0' TOPMARGIN='0'></IFRAME>
					</div>
				</div>
			</div>
		</div>
	</div>
		
<script>
	//var bodyheight=$(document).height();
	function iFrameHeight() 
    {
        var ifm= document.getElementById("Main");
        var subWeb = document.frames ? document.frames["Main"].document :ifm.contentDocument;
        if(ifm != null && subWeb != null) {
        	ifm.height = subWeb.body.scrollHeight + 100;
        }
    }
    
    $(document).ready(function(){
    	//$(".page-content").css("background-color","#f5f7f9");
    })
</script>		
</@crudmetropage3>