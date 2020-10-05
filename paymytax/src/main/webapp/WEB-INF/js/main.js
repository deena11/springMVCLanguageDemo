/**
 * 
 */
function validation()
{
	alert("hello");
 	var yearOfAssessment = Document.getElementByName("yearOfAssessment").value;
 	var constructedYear = Document.getElementByName("constructedYear").value;
 	
 	console.log(yearOfAssessment+"   "+constructedYear);
 	return false;

}