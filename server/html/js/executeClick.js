function executeClick(descriptionObject){
    console.log( descriptionObject);
    console.log(descriptionObject.comment);
    var headerDescription = '<div class=label_block>Comment:</div><div class="comment_block"><pre>' + descriptionObject.comment + '</pre></div>';
    var tableParams = createParamsTable(descriptionObject.params);
    var tableFields = createFieldsTable(descriptionObject.fields);
    var tableLogs = createLogsTable(descriptionObject.logs);
    var resultTextDescription ='';
    resultTextDescription = headerDescription + tableParams + tableFields + tableLogs;
	return resultTextDescription ;
}

function createParamsTable(params){
    var headerTableParam = '<div class=label_block>Params:</div><div class="params_block"><table class="table"><tr><th>Name</th><th>Type</th><th>Title</th></tr>';
    var templateTableParam='<tr><td>:NAME</td><td>:TYPE</td><td>:TITLE</td></tr>';
    var endTableParam='</table></div>';
    var paramReplace='';
    var paramConcat='';
    console.log(params.lenght);
    params.forEach(function(item, i, arr){
            console.log(item);
            console.log(item.name);
            paramReplace = templateTableParam.replace(':NAME',item.name);
            console.log(item.type);
            paramReplace = paramReplace.replace(':TYPE',item.type);
            console.log(item.title);
            paramReplace = paramReplace.replace(':TITLE',item.title);
            paramConcat = paramConcat + paramReplace;
    });
    var resultTableParam;
    if (paramConcat == ''){
        resultTableParam='<div class=label_block>Params:</div><div class="params_block"><p>NO PARAMS</p></div>'
    } else {
        resultTableParam = headerTableParam + paramConcat + endTableParam;
    }
    return resultTableParam;
}

function createFieldsTable(fields){
    var headerTableField = '<div class=label_block>Fields:</div><div class="fields_block"><table class="table"><tr><th>Name</th><th>Type</th><th>Title</th></tr>';
    var templateTableField='<tr><td>:NAME</td><td>:TYPE</td><td>:TITLE</td></tr>';
    var endTableField='</table></div>';
    var fieldReplace='';
    var fieldConcat='';
    console.log(fields.lenght);
    fields.forEach(function(item, i, arr){
            console.log(item);
            console.log(item.name);
            fieldReplace = templateTableField.replace(':NAME',item.name);
            console.log(item.type);
            fieldReplace = fieldReplace.replace(':TYPE',item.type);            
            console.log(item.title);
            fieldReplace = fieldReplace.replace(':TITLE',item.title);
            fieldConcat = fieldConcat + fieldReplace;
    });
    var resultTableFields;
    if (fieldConcat == ''){
        resultTableFields='<div class=label_block>Fields:</div><div class="fields_block"><p>NO FIELDS DESCRIPTION</p></div>'
    } else {
        resultTableFields = headerTableField + fieldConcat + endTableField;
    }
    return resultTableFields;
}

function createLogsTable(logs){
    var headerTableLog = '<div class=label_block>Logs:</div><div class="logs_block"><table class="table"><tr><th>Date change</th><th>Who</th><th>Changes</th></tr>';
    var templateTableLog='<tr><td>:DTCHANGE</td><td>:WHO</td><td>:CHANGES</td></tr>';
    var endTableLog='</table></div>';
    var logReplace='';
    var logConcat='';
    console.log(logs.lenght);
    logs.forEach(function(item, i, arr){
            console.log(item);
            console.log(item.dtChange);
            logReplace = templateTableLog.replace(':DTCHANGE',item.dtChange);
            console.log(item.who);
            logReplace = logReplace.replace(':WHO',item.who);
            console.log(item.changes);
            logReplace = logReplace.replace(':CHANGES',item.changes);                
            logConcat = logConcat + logReplace;
    });
    var resultTableLog;
    if (logConcat == ''){
        resultTableLog='<div class=label_block>Logs:</div><div class="logs_block"><p>NO LOGS</p><div>'
    } else {
        resultTableLog = headerTableLog + logConcat + endTableLog;
    }
    return resultTableLog;
}