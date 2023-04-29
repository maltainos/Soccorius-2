function todayCharts(label,chartType, id ,labelAndData, width){
	const ctx = $(id);
	
	  new Chart(ctx, {
	    type: chartType,
	    data: {
	      labels: labelAndData.keys,
	      datasets: [{
	        label: label,
	        data: labelAndData.values,
	        borderWidth: width,
	        //backgroundColor: ['red','green','blue','ping','yellow','orange','cyan','greenyellow','gray']
	      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
	  });
}
