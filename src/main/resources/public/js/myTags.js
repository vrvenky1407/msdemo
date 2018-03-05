$('#related').hide();
productData = [];
labels = [];
businessUnits = [];
$(function() {
	var sampleTags = [ 'c++', 'java', 'php', 'coldfusion', 'javascript', 'asp',
			'ruby', 'python', 'c', 'scala', 'groovy', 'haskell', 'perl',
			'erlang', 'apl', 'cobol', 'go', 'lua' ];
	$().tagit({
		tagSource : sampleTags,
		// This will make Tag-it submit a single form value, as a
		// comma-delimited field.
		singleField : true,
		singleFieldNode : $('#mySingleField')
	});
});

products = [ "" ];
$('#singleFieldTags').tagit({
	allowSpaces : true,
	autocomplete : {
		source : function(request, response) {
			$.ajax({
				url : "/productsjson/name/" + request.term,
				dataType : "json",
				success : function(data) {
					response($.map(data, function(item) {
						return {
							label : item.name,
							value : item.name
						}
					}));
				}
			});
		},
		minLength : 2
	},
	afterTagAdded : function(evt, ui) {
		$.ajax({
			url : "/productsjson/related/" + ui.tagLabel,
			dataType : "json",
			success : function(data) {
				productData.push(data);
				show();
			}
		});
	},
	afterTagRemoved : function(evt, ui) {
		productData = $.grep(productData, function(e) {
			return e.tag != ui.tagLabel;
		});
		if (productData.length != 0) {
			show();
		} else {
			$('#related').hide();
		}
	}
});

show = function() {
	var parentProductsList = [];
	var childProductsList = [];
	$('#related').show();
	$('#parent tbody tr').remove();
	$('#child tbody tr').remove();
	productData.forEach(function(product) {
		childProducts = "";
//		parentProducts = "";
		product.product.childProducts != null ? product.product.childProducts
				.forEach(function(child) {
					if (childProducts == "") {
						childProducts = child
					} else {
						childProducts = childProducts + ", " + child;
					}
				}) : "";
//		product.product.parentProducts != null ? product.product.parentProducts
//				.forEach(function(parent) {
//					if (parentProducts == "") {
//						parentProducts = parent
//					} else {
//						parentProducts = parentProducts + ", " + parent;
//					}
//				}) : ""
                console.log(childProducts)
               parentProductsList.push(product.product.name);
                childProductsList.push(childProducts);
		$('#parent').append(
				"<tr><td>" + product.product.name + "</td><td>"
						+ product.product.bu + "</td><td>"
						+ product.product.buOwner + "</td><td>"
						+ product.product.prodOwner + "</td><td>"
						+ product.product.implementationMgr + "</td><td>"
						+ product.product.developmentMgr + "</td><td>"
						+ product.product.inHouseOrOutsourced + "</td><td>"
						+ childProducts + "</td><td>"
						+ product.product.id + "</td><td>"
                        + product.product.processingLocation + "</td></tr>");
	});
	
	if(typeof(Storage) !== "undefined") {
		localStorage.setItem("parentProductsList", JSON.stringify(parentProductsList));
		localStorage.setItem("childProductList", JSON.stringify(childProductsList));
		//$("<a>").attr("href", "/products/graph").attr("target", "_blank")[0].click();
		return true;
	}
	
}

function showgraph(){
	var searchLabels = $('.tagit-label');
	labels = [];
	businessUnits = [];
	searchLabels.each(function(i,val){
		labels.push(val.innerHTML);
	});
	productData.forEach(function(product){
		businessUnits.push(product.product.bu);
	});
	if(typeof(Storage) !== "undefined") {
		localStorage.removeItem("labels");
		localStorage.removeItem("businessUnits");
		localStorage.setItem("businessUnits", JSON.stringify(businessUnits));
		localStorage.setItem("labels", JSON.stringify(labels));
		//$("<a>").attr("href", "/products/graph").attr("target", "_blank")[0].click();
		return true;
	} else {
	}
}

function showgraphOld(){
	businessUnits = [];
	productData.forEach(function(product){
		businessUnits.push(product.product.bu);
	});
	if(typeof(Storage) !== "undefined") {
		localStorage.removeItem("businessUnits");
		localStorage.setItem("businessUnits", JSON.stringify(businessUnits));
		$("<a>").attr("href", "/products/graph").attr("target", "_blank")[0].click();
		return true;
	} else {

	}
}