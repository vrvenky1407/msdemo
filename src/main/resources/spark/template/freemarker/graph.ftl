<#include "header.ftl">
<div class="loading" style="position: absolute;top: 50%;left: 50%;margin-top: -50px; margin-left: -50px;">
  <span></span>
  <span></span>
  <span></span>
</div>
<script type="text/javascript" src='/js/jquery.min.js'></script>
<script type="text/javascript" src='/js/jquery.ui.min.js'></script>
<script type="text/javascript" src='/js/html2canvas.js'></script>
<script type="text/javascript" src='/js/Canvas2Image.js'></script>
 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="http://canvg.googlecode.com/svn/trunk/rgbcolor.js"></script> 
<script src="http://canvg.googlecode.com/svn/trunk/canvg.js"></script> 

<link rel="stylesheet" type="text/css" href='/css/main.css'/>
<link rel="stylesheet" type="text/css" href='/css/orgchart.css'/>
<script src="/js/orgChart.js"></script>
<script type="text/javascript" src='/js/save.js'></script>
<script type="text/javascript" src='/js/onLoad.js'></script>

<div id="wrapper" class="main_content hidden">
<div id="image" class="saveImageButton">
<a id="DownLink"  onclick="return check()" download="image.png" class="btn btn-primary export" style="margin-left: 35px;">Save Image</a>
</div>
<div id="chart_div" class="orgChartDiv"></div>
</div>