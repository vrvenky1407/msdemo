/* Collapsible Tree Global variables */
var tree, diagonal, svg = [];
var test;
var inc = 0,
    duration = 750,
    root = [];
var container = [];

var businessUnits = localStorage.getItem("businessUnits");
var labels = localStorage.getItem("labels");
labels = JSON.parse(labels);
businessUnits = JSON.parse(businessUnits);
labels.forEach(function(val,index) {
	console.log(val,businessUnits[index]);
    collapse_graph(val, businessUnits[index]);
    //container.push({"businessUnit":businessUnits[index], "product":val});
})

collapse_graph2();

function collapse_graph2() {
	container = JSON.stringify(container);
    var data = {};
    $.post("/productsjson/graph",container,function(success){
    	console.log(success);
    	
        success.forEach(function(data){
        	test = data;
        	// d3 business.
        	var width = '100%';
            var height = 800;

            tree = d3.layout.tree().size([height, width]);

            diagonal = d3.svg.diagonal().projection(function(d) {
                return [d.y, d.x];
            });

            svg[type] = d3.select("#graph").append("svg")
                .attr("type", type)
                .attr("width", width)
                .attr("height", height)
                .append("g")
                .attr("transform", "translate(100,0)");

            root[type] = data;
            root[type].x0 = height / 2;
            root[type].y0 = 0;
            
            //root[type].children.forEach(collapse);
            update(root[type], type);
        });
    },"json");
    
}

function collapse_graph(type,bu) {

    var data = {};
    $.getJSON("/productsjson/graph/" + bu+"/"+type, function(data) {
        // d3 business.
        var width = '100%';
        var height = 800;

        tree = d3.layout.tree().size([height, width]);

        diagonal = d3.svg.diagonal().projection(function(d) {
            return [d.y, d.x];
        });

        svg[type] = d3.select("#graph").append("svg")
            .attr("type", type)
            .attr("width", width)
            .attr("height", height)
            .append("g")
            .attr("transform", "translate(100,0)");

        root[type] = data;
        root[type].x0 = height / 2;
        root[type].y0 = 0;
        
        //root[type].children.forEach(collapse);
        update(root[type], type);

        //d3.select(self.frameElement).style("height", "800px");
    });

}

function update(source, type) {

    // Compute the new tree layout.
    var nodes = tree.nodes(root[type]).reverse(),
        links = tree.links(nodes);

    // Normalize for fixed-depth.
    nodes.forEach(function(d) {
        d.y = d.depth * 180;
    });

    // Update the nodes…
    var node = svg[type].selectAll("g.node")
        .data(nodes, function(d) {
            return d.id || (d.id = ++inc);
        });

    // Enter any new nodes at the parent's previous position.
    var nodeEnter = node.enter().append("g")
        .attr("class", "node")
        .attr("transform", function(d) {
            return "translate(" + source.y0 + "," + source.x0 + ")";
        })
        .on('click', click);

    nodeEnter.append("circle")
        .attr("r", 1e-6)
        .style("fill", function(d) {
            return d._children ? "lightsteelblue" : "#fff";
        });

    nodeEnter.append("text")
        .attr("x", function(d) {
            return d.children || d._children ? -10 : 10;
        })
        .attr("dy", ".35em")
        .attr("text-anchor", function(d) {
            return d.children || d._children ? "end" : "start";
        })
        .text(function(d) {
            return d.product.name;
        })
        .style("fill-opacity", 1e-6);

    // Transition nodes to their new position.
    var nodeUpdate = node.transition()
        .duration(duration)
        .attr("transform", function(d) {
            return "translate(" + d.y + "," + d.x + ")";
        });

    nodeUpdate.select("circle")
        .attr("r", 4.5)
        .style("fill", function(d) {
            return d._children ? "lightsteelblue" : "#fff";
        });

    nodeUpdate.select("text")
        .style("fill-opacity", 1);

    // Transition exiting nodes to the parent's new position.
    var nodeExit = node.exit().transition()
        .duration(duration)
        .attr("transform", function(d) {
            return "translate(" + source.y + "," + source.x + ")";
        })
        .remove();

    nodeExit.select("circle")
        .attr("r", 1e-6);

    nodeExit.select("text")
        .style("fill-opacity", 1e-6);

    // Update the links…
    var link = svg[type].selectAll("path.link")
        .data(links, function(d) {
            return d.target.id;
        });

    // Enter any new links at the parent's previous position.
    link.enter().insert("path", "g")
        .attr("class", "link")
        .attr("d", function(d) {
            var o = {
                x: source.x0,
                y: source.y0
            };
            return diagonal({
                source: o,
                target: o
            });
        });

    // Transition links to their new position.
    link.transition()
        .duration(duration)
        .attr("d", diagonal);

    // Transition exiting nodes to the parent's new position.
    link.exit().transition()
        .duration(duration)
        .attr("d", function(d) {
            var o = {
                x: source.x,
                y: source.y
            };
            return diagonal({
                source: o,
                target: o
            });
        })
        .remove();

    // Stash the old positions for transition.
    nodes.forEach(function(d) {
        d.x0 = d.x;
        d.y0 = d.y;
    });
    inc = 0;
}

// Toggle children on click.
function click(d) {
    if (d.children) {
        d._children = d.children;
        d.children = null;
    } else {
        d.children = d._children;
        d._children = null;
    }
    var type = $(this).parents('svg').attr('type');
    update(d, type);
}
