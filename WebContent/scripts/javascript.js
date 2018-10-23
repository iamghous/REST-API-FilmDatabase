// this method will be called from inserFilm.html file 
function insertFilmData(id){
	  // assigning the address
    var address = "rest/addFilm";
    // getting the id of selectBox
   var selectBox = document.getElementById("selectInsertFilmBox");
   // assigning the value of selectbox to to variable
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    /* 
     * calling the method with data type parameter which will return 
     * the data e.g if we parameter is xml it will return application/xml
     */
    var contentType = getAcceptType(selectedValue);
    // getting data type which we need to send either json or xml or structured
    var data = getDataType(selectedValue);
    // new XMLhttpRequest
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
		  // this will run if the status is 200
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById(id).innerHTML = this.responseText;
	    }
	  };
	  // if the request type is structured
	  if(selectedValue == "structured"){
		  // it will send the data in the open method
	  xhttp.open("POST", address+data , true);
	  xhttp.setRequestHeader("Content-Type",contentType);
	  xhttp.send();
	  }
	  // and otherwise the data will be sent in the send() method
	  else{
		  xhttp.open("POST", address , true);
		  xhttp.setRequestHeader("Content-Type",contentType);
		  xhttp.send(data);
	  }
}

// this method is used to get All films DATA  and it has almost the same structure as the first method
function getAllFilmsData(addres,id){
	  var address = "rest/allFilms";
    var selectBox = document.getElementById("selectAllFilmsBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    /* adding Output1 to the selected value if selectedValue is xml it will become xmlOutput1 
     * and because all my divs are named properly for e.g json will be jsonOutput1 etc
     */
    var outputDiv = selectedValue+"Output1";
    // calling the method getAcceptType which will return, e.g for json it will return application/json
    var accept = getAcceptType(selectedValue);
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById(outputDiv).innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", address , true);
	  // set Accept type to the accept variable which we created earlier in the method which calls the getAcceptType() method
     xhttp.setRequestHeader("Accept", accept);
	  xhttp.send();
	
}
// this method gets the specific film and prints out the result to the user
// it has almost the structure as the first Method and the second
function getSearchedFilmData(id){
   
    var selectBox = document.getElementById("selectSearchFilmBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var outputDiv = selectedValue+"Output1";
    var accept = getAcceptType(selectedValue);
    var search = document.getElementById('searchFilmName').value
     var address = "rest/searchFilm/"+search;
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById(outputDiv).innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", address , true);
    xhttp.setRequestHeader("Accept", accept);
	  xhttp.send();
	
}

// this method is used to convert format type to its application type for e.g json paramter will return application/json
function getAcceptType(format){
	var value = "";
	if(format == "xml" ){
		value = "application/xml";
	}
	else if(format == "json" ){
		value = "application/json";
		
	}
	else if(format == "structured" ){
		value = "text/html";
	
    }
	return value;	
	
}
/*
 * this method is used only for the insertDataMethod() it basically provides the method the type of data it wants
 * so the method is called with the format paramtere for e.g. json, xml etc
 * and it used if else statements to determine which data needs to be sent back
 */
function getDataType(format){
	// creating an empty string which will be returned later
	var data = "";
	// assigning all the input values to variables
    var filmName  = document.getElementById('filmName').value;
    var filmGenre   = document.getElementById('filmGenre').value;
    var filmCredits = document.getElementById('filmCredits').value;
    var filmYear=document.getElementById('filmYear').value;
    var filmGross= document.getElementById('filmGross').value;
    var filmCountry= document.getElementById('filmCountry').value;
    // if format is structured it will add the following to 'data' variable
    if(format == "structured" ){
		data = "/"+filmYear+"/"+filmGross+"/"+filmName+"/"+filmCredits+"/"+filmGenre+"/"+filmCountry;
	}
    // else if it is json it will use a special method JSON.stringify to convert the data into json format
	else if(format == "json" ){
		data += JSON.stringify({filmYear:filmYear, filmGross:filmGross, filmName:filmName,filmCredits:filmCredits,filmGenre:filmGenre,filmCountry:filmCountry});
		
	}
    // and if it is xml then data will append all of the data below and return it back
	else if(format == "xml" ){
		data += "<film>";
        data += "<filmYear>" +filmYear + "</filmYear>";
        data += "<filmGross>" +filmGross + "</filmGross>";
        data += "<filmName>" +filmName + "</filmName>";
        data += "<filmCredits>" +filmCredits + "</filmCredits>";
        data += "<filmGenre>" +filmGenre + "</filmGenre>";
        data += "<filmCountry>" +filmCountry + "</filmCountry>";
        data += "</film>";
	
    }
    // returns the data
    return data;
}