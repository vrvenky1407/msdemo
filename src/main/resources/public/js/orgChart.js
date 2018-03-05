        google.charts.load('current', {
        packages: ["orgchart"]
      });
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Child');
        data.addColumn('string', 'Parent');               
        var parentProductsList = localStorage.getItem("parentProductsList");
        var childProductList = localStorage.getItem("childProductList");
        var businessUnits = localStorage.getItem("businessUnits");
        parentProductsList = JSON.parse(parentProductsList);
        childProductList = JSON.parse(childProductList);
        businessUnits = JSON.parse(businessUnits);
        var j = 0;
        function countInArray(array, what) {
        	var arr = array.filter(Boolean);
            var count = 0;
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] === what) {
                    count++;
                }
            }
            return count;
        }
        
           	parentProductsList.forEach(function(val,index) {
           	var count = countInArray(childProductList,childProductList[index]);
           	var childInPrduct = childProductList.indexOf(val);
           	var PrductInChild = parentProductsList.indexOf(childProductList[index]);
        	if(count > 1){
        			data.addRow([val,businessUnits[index]]);
        			if(childProductList[index]!=""){
        				data.addRow([{v:'child_node_'+j,f:childProductList[index]}, val]);	
        			}else{
        				data.addRow([{v:null,f:childProductList[index]}, val]);
        			}
        			
        			j++;        		       		
        	}else if(childInPrduct >= 0){
        		data.addRow([{v:'child_node_'+j,f:val}, businessUnits[index]]);        		
        		data.addRow([childProductList[index],val]);
        		j++;
              	}
        	else{
        		data.addRow([val,businessUnits[index]]);
        		data.addRow([childProductList[index],val]);
        	}
        });

        // Create the chart.
           	var chart_div = document.getElementById('chart_div');
        var chart = new google.visualization.OrgChart(chart_div);
        
        // Draw the chart, setting the allowHtml option to true for the tooltips.
        chart.draw(data, {
          allowHtml: true
        });    
        
        
      }
       