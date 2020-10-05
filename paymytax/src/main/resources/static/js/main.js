$(document).ready(function(){
		$("#totalTax").focus(function(){
			
			if($('#form').valid())
				{
				 var data = $('#form').serialize();
				$.post('calculateTax',data,function(res){
					if(res.status=="SUCCESS")
						$('#totalTax').val(res.result);
					else
						window.location = "errorPage/"+res.errorMessage;
				}); 
				}
			else
				console.log("Not valid form")
			
		});
		var date = new Date();
		var currentYear = date.getFullYear();
		$('#yearOfAssessment').prop('min',1900);
		$('#yearOfAssessment').prop('max',currentYear);
		$('#constructedYear').prop('min',1900);
		$('#constructedYear').prop('max',currentYear);
		
		
		
	});
