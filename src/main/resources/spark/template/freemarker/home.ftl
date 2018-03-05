<#include "header.ftl">

<script>
function testAttribute(element, attribute) {
   var test = document.createElement(element);
   if (attribute in test) {
    return true;
  }
else 
  return false;
}
window.onload = function() {
  if (!testAttribute('input', 'autofocus'))
  document.getElementById('mySingleField').focus(); 
}
</script> 

<div id="wrapper" class="main_content">

 <div class="row">
   <div style="margin-left:13px;" class="col-lg-12 col-12 col-md-12">
<div id="content">
   <h3>Product:</h3>
   <form>
      <p>
         <input name="tags" id="mySingleField" disabled="true" hidden="true" type="text" autofocus>
      </p>
      <ul id="singleFieldTags"></ul>
   </form>
</div>

</div></div>


<div id="related">
   <div class="row">
   <div class="col-lg-12 col-12 col-md-12">
   
      <div class="col-lg-9 col-9 col-md-9">
         <h3>Details</h3>
      </div>
      <div align="right" style="margin-left:0px; margin-right:0px; padding-right:0px;" class="col-lg-3 col-3 col-md-3">
         <a href="/products/graph" target="_blank" class="btn btn-primary export" onclick="return showgraph()">
         Show Graph</a> 
         <a href="#" class="btn btn-primary export" onclick="fnExcelReport()"> Export to Excel</a>
      </div>
      
      <iframe style='display:none' id='txtArea1'></iframe>
   </div>
   
   </div>
   <div class="col-lg-12 col-12 col-md-12">
   
   <table style="width:100%" id="parent" class="table table-striped">
      <thead>
         <th>Product Name</th>
         <th>FIS Business Unit</th>
         <th>BU Owner</th>
         <th>Product Owner</th>
         <th>Implementation Manager</th>
         <th>Development Manager</th>
         <th>In House or Outsourced</th>
         <th>Dependent Ancillary Products</th>
         <th>Solution Central Asset ID</th>
         <th>Processing Location</th>
      </thead>
   </table>
   </div>
   
   
</div>
<script src="/js/myTags.js" type="text/javascript" charset="utf-8"></script> 
</body>
</html>