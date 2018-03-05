<#include "header.ftl">
<div class="exampleoverlay">
   <div class="whirly-loader">
      Loading...
   </div>
</div>
<div id="wrapper" class="main_content">
   <div id="content">
      <h3>Enter Project Name</h3>
      <select class="js-example-data-array">
      </select>
   </div>
   <div id="related">
      <h4>Details</h4>
      <table id="parent" class="table table-bordered">
         <thead>
            <th>Id</th>
            <th>Name</th>
            <th>Core</th>
            <th>Type</th>
            <th>BU</th>
            <th>BU Owner</th>
            <th>Product Owner</th>
            <th>Implementation Manager</th>
            <th>Delivery Method</th>
            <th>Child Products</th>
            <th>Parent Products</th>
         </thead>
      </table>
   </div>
</div>
<script>
   productData = [];
      var reqxhr = $.getJSON("/selectProducts", function(data){  
   		$(".exampleoverlay").hide();     
         	$(".js-example-data-array").select2({
             tags:true,
             multiple:true,
             data: data
           })
         
       });
   
   
     $(".js-example-data-array").change(function(event){
       $.ajax({
               url: "/products/related/" + $(".js-example-data-array :selected").text(),
               dataType: "json",
               success: function(data) {
                   productData.push(data);
                   show();
               }
           });
     });
    
   
    show = function(){
     $('#related').show();
     $('#parent tbody tr').remove();
     $('#child tbody tr').remove();
     console.log(productData);
     productData.forEach(function(product){
       childProducts = "";
       parentProducts = "";
        product.product.childProducts != null?product.product.childProducts.forEach(function(child){
              if(childProducts == ""){
                 childProducts = child
                }else{
                  childProducts = childProducts+" ,"+child;
                }
            }):"";
            product.product.parentProducts != null?product.product.parentProducts.forEach(function(parent){
              if(parentProducts == ""){
                parentProducts = parent
              }else{
                parentProducts = parentProducts+" ,"+parent;
              }
            }):""
       $('#parent').append(
            "<tr>" +
                    "<td>" + product.product.id + "</td>" +
                    "<td>" + product.product.name + "</td>" +
                    "<td>" + product.product.core + "</td>" +
                    "<td>" + product.product.type + "</td>" +
                    "<td>" + product.product.bu + "</td>" +
                    "<td>" + product.product.buOwner + "</td>" +
                    "<td>" + product.product.prodOwner + "</td>" +
                    "<td>" + product.product.implementationMgr + "</td>" +
                    "<td>" + product.product.inHouseOrOutsourced + "</td>" +
                    "<td>" + childProducts+ "</td>" +
                    "<td>" + parentProducts+ "</td>" +
                    "</tr>"
     );
     });
   }
    
</script>
</body>
</html>