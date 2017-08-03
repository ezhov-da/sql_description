//паттерн построения описания
var pattern = 
	'/*\n' +
	'<description status="${status}" tool="${tool}">\n' +
	'	<name>${name}</name>\n' +
	'	<author>${author}</author>\n' +
	'	<dtCreate>${dtCreate}</dtCreate>\n' +		
	'	<params>\n' +
	'		${params}\n' +
	'	</params>\n' +
	'	<fields>\n' +
	'		${fields}\n' +
	'	</fields>\n' +		
	'	<comment>\n' +
	'		${comment}\n' +
	'	</comment>\n' +
	'	<logs>\n' +
	'		${logs}\n' +
	'	</logs>\n' +	
	'</description>\n' +
	'*/';
//значение при неуказаных данных
var defaultValue = '';	
//паттерн построения параметров
var patternParamsReplace = '\t\t<param name="${name}" type="${type}" title="${title}"/>';
//паттерн построения полей
var patternFieldsReplace = '\t\t<field name="${nameField}" type="${typeField}" title="${titleField}"/>';
//паттерн построения логов
var patternLogsReplace = '\t\t<log dtChange="${dtChange}" who="${whoLog}">\n' +
	'\t\t\t<changes>\n' +
	'\t\t\t\t${changesLog}\n' +
	'\t\t\t</changes>\n' +
	'\t\t</log>'

//очистка полей
function clear(){
	document.getElementById('status').value = '';
	document.getElementById('tool').value = '';
	document.getElementById('author').value = '';
	document.getElementById('comment').value = '';
	document.getElementById('name').value = '';
	document.getElementById('listParam').value = '';
	document.getElementById('listFields').value = '';
	document.getElementById('listLogs').value = '';
	document.getElementById('result').value = '';
}

//получение даты
function getDate(){
    var d = new Date();
    var currDdate = getDateWithZero(d.getDate());
    var currMonth = getDateWithZero(d.getMonth() + 1); //Months are zero based
    var currYear = d.getFullYear();
    var currHours = getDateWithZero(d.getHours());
    var currMinutes = getDateWithZero(d.getMinutes());
    var currSeconds = getDateWithZero(d.getSeconds());
    var result = 
    	currYear + '-' + 
		currMonth +'-' +  
		currDdate +' ' +  
		currHours +':' +  
		currMinutes +':' +  
		currSeconds;		    
    return result;
}

function getDateWithZero(data){
	data = '0' + data;
	var l = data.length;
	if (l == 3){
		data = data.substring(1, 3); 
	}
	return data;
}

//заменяем значения не списка
function basicGenerate(status, name, author, tool, comment){
	var resultText;
	resultText = pattern.replace('${status}', status);
	resultText = resultText.replace('${name}', name);
	resultText = resultText.replace('${author}', author);
	resultText = resultText.replace('${tool}', tool);
	resultText = resultText.replace('${comment}', comment);
	resultText = resultText.replace('${dtCreate}', getDate());
	return resultText;
};

//функция по генерации параметров
function generateParams(listParam, resultWithoutParams){
	var result;
	var basicArr = listParam.trim().split('\n');
	var lenMass = basicArr.length;
	//проверяем на то, чтоб был указан список 
	if (lenMass == 1 & basicArr[0] == "" ){
		result = resultWithoutParams.replace('${params}', '');
		return result;					
	}
	var fullParam ='';
	//получаем список 
	basicArr.forEach(function(item, i, arr) {
		var arr = item.split('|');
		var lenMass = arr.length;
		//если список короче трех, говорим что нужно три параметра и
		//возвращащаем значение по умолчанию
		if (lenMass != 3 ){
			alert(languageLocalizationSqlDescription.errorListParams);
			result = resultWithoutParams.replace('${params}', '');
			return result;
		}
		//если все хорошо начинаем замену
		var resultParams = patternParamsReplace;
		arr.forEach(function(item, i, arr) {
			switch(i) {
			  case 0: 
				if (item == ""){
					resultParams = resultParams.replace('${name}', defaultValue);
				} else {
					resultParams = resultParams.replace('${name}', item);
				}
			    break;

			  case 1:  
				if (item == ""){
					resultParams = resultParams.replace('${type}', defaultValue);
				} else {
					resultParams = resultParams.replace('${type}', item);
				}
			    break;

			  case 2:  
				if (item == ""){
					resultParams = resultParams.replace('${title}', defaultValue);
				} else {
					resultParams = resultParams.replace('${title}', item);
				}
			    break;
			}					
		});
		fullParam = fullParam + resultParams + '\n';
	});
	fullParam = resultWithoutParams.replace('${params}', fullParam.trim());
	return fullParam;
}

//функция по генерации полей
function generateFields(listFields, resultWithParams){
	var result;
	var basicArr = listFields.trim().split('\n');
	var lenMass = basicArr.length;
	//проверяем на то, чтоб был указан список
	if (lenMass == 1 & basicArr[0] == "" ){
		result = resultWithParams.replace('${fields}', '');
		return result;					
	}
	var fullFields ='';
	//получаем список 
	basicArr.forEach(function(item, i, arr) {
		var arr = item.split('|');
		var lenMass = arr.length;
		//если список короче трех, говорим что нужно три и
		//возвращащаем значение по умолчанию
		if (lenMass != 3 ){
			alert(languageLocalizationSqlDescription.errorListFields);
			result = resultWithParams.replace('${fields}', '');
			return result;
		}
		//если все хорошо начинаем замену
		var resultFields = patternFieldsReplace;
		arr.forEach(function(item, i, arr) {
			switch(i) {
			  case 0: 							
				if (item == ""){
					resultFields = resultFields.replace('${nameField}', defaultValue);
				} else {
					resultFields = resultFields.replace('${nameField}', item);
				}
			    break;

			  case 1:  
				if (item == ""){
					resultFields = resultFields.replace('${typeField}', defaultValue);
				} else {
					resultFields = resultFields.replace('${typeField}', item);
				}
			    break;

			  case 2:  
				if (item == ""){
					resultFields = resultFields.replace('${titleField}', defaultValue);
				} else {
					resultFields = resultFields.replace('${titleField}', item);
				}
			    break;

			}					
		});
		fullFields = fullFields + resultFields + '\n';
	});
	fullFields = resultWithParams.replace('${fields}', fullFields.trim());
	return fullFields;
};	

//функция по генерации лога
function generateLogs(listLogs, resultWithParams){
	var result;
	var basicArr = listLogs.trim().split('\n');
	var lenMass = basicArr.length;
	//проверяем на то, чтоб был указан список 
	if (lenMass == 1 & basicArr[0] == "" ){
		result = resultWithParams.replace('${logs}', '');
		return result;					
	}
	var fullLogs ='';
	//получаем список 
	basicArr.forEach(function(item, i, arr) {
		var arr = item.split('|');
		var lenMass = arr.length;
		//если список короче 2, говорим что нужно 2 параметра и
		//возвращащаем значение по умолчанию
		if (lenMass != 2 ){
			alert(languageLocalizationSqlDescription.errorListLogs);
			result = resultWithParams.replace('${logs}', '');
			return result;
		}
		//если все хорошо начинаем замену
		var resultLogs = patternLogsReplace;
		arr.forEach(function(item, i, arr) {
			resultLogs = resultLogs.replace('${dtChange}', getDate());
			switch(i) {
			  case 0: 							
				if (item == ""){
					resultLogs = resultLogs.replace('${whoLog}', defaultValue);
				} else {
					resultLogs = resultLogs.replace('${whoLog}', item);
				}
			    break;

			  case 1:  
				if (item == ""){
					resultLogs = resultLogs.replace('${changesLog}', defaultValue);
				} else {
					resultLogs = resultLogs.replace('${changesLog}', item);
				}
			    break;
			}					
		});
		fullLogs = fullLogs + resultLogs + '\n';
	});
	fullLogs = resultWithParams.replace('${logs}', fullLogs.trim());
	return fullLogs;
};	