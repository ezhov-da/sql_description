var isPrintLog = false;

function printLog(object){
    if(isPrintLog){
        console.log(object);        
    }
}

var textXmlTestDescription = 
    '<description status="work" tool="SPT">\n' + 
    '    <name>TEST.dbo.V_E_test</name>\n' + 
    '    <author>Ежов Д.А.</author>\n' + 
    '    <dtCreate>2016-08-16 13:12:21</dtCreate>\n' + 
    '    <params>\n' + 
    '        <param name="параметр1" type="int" title="просто параметр"/>\n' + 
    '        <param name="параметр1" type="int" title="просто параметр"/>\n' +         
    '    </params>\n' + 
    '    <fields>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' + 
    '        <field name="id" type="int" title="просто параметр"/>\n' +                                         
    '    </fields>\n' + 
    '    <comment>\n' + 
    '        Просто ни к чему не обязывающий комментарий\n' + 
    '    </comment>\n' + 
    '    <logs>\n' + 
    '        <log dtChange="2016-08-16 14:29:47" who="Я">\n' + 
    '            <changes>\n' + 
    '                а что делал не скажу\n' + 
    '            </changes>\n' + 
    '        </log>\n' + 
    '    </logs>\n' + 
    '</description>'; 

function convertorDescriptionFromXml(
        textXml, 
        statusField, 
        toolField, 
        authorField, 
        nameField, 
        listParamsField, 
        listFieldsField, 
        commentField, 
        listLogsField)
{
	textXml = clearText(textXml);
	printLog(textXml);

    var xmlDoc = $.parseXML(textXml.trim());
    printLog(xmlDoc);
    var xml = $( xmlDoc );
    
    fillCommonData(xml, statusField, toolField, authorField, nameField, commentField)
    fillParams(xml, listParamsField);
    fillFields(xml, listFieldsField);
    fillLogs(xml, listLogsField);

}

function clearText(xmlText){
	var xmlReplace = xmlText.replace('/\*', '');
    printLog(xmlReplace); 
	xmlReplace = xmlReplace.replace('\*/', '');
	printLog(xmlReplace); 
	return xmlReplace.trim();
}

function fillCommonData(xmlDoc, statusField, toolField, authorField, nameField, commentField){
    printLog(xmlDoc);
    var status = xmlDoc.find( "description").attr("status");
    statusField.value=status;
    printLog(status);
    var tool = xmlDoc.find( "description").attr("tool");
    toolField.value=tool;
    printLog(tool);
    var name = xmlDoc.find( "name" );
    nameField.value=name.text().trim();
    printLog(name.text());
    var author = xmlDoc.find( "author" );
    authorField.value=author.text().trim();
    printLog(author.text());   
    var comment = xmlDoc.find( "comment" );
    commentField.value=comment.text().trim();
    printLog(comment.text().trim);   
}

function fillParams(xmlDoc, paramsField){
    var params = xmlDoc.find( "params>param" );
    printLog(params);       
    printLog(params.length);  
    var concatParams = '';
    for(var i = 0; i < params.length; i++){
        printLog(params[i]);  
        var xmlParam = $(params[i]);
        var nameAttr = xmlParam.attr("name");
        printLog(nameAttr); 
        var nameType = xmlParam.attr("type");
        printLog(nameType); 
        var nameTitle = xmlParam.attr("title");
        printLog(nameTitle);  
        concatParams =  concatParams + nameAttr + '|' + nameType + '|' + nameTitle + '\n';          
    } 
    paramsField.value = concatParams;
}

function fillFields(xmlDoc, fieldsField){
    var fields = xmlDoc.find( "fields>field" );
    printLog(fields);       
    printLog(fields.length);  
    var concatFields = ''; 
    for(var i = 0; i < fields.length; i++){
        printLog(fields[i]);  
        var xmlParam = $(fields[i]);
        var nameAttr = xmlParam.attr("name");
        printLog(nameAttr); 
        var nameType = xmlParam.attr("type");
        printLog(nameType); 
        var nameTitle = xmlParam.attr("title");
        printLog(nameTitle);                  
        concatFields =  concatFields + nameAttr + '|' + nameType + '|' + nameTitle + '\n';          
    }
    fieldsField.value = concatFields;
}

function fillLogs(xmlDoc, logsField){
    var logs = xmlDoc.find( "logs>log" );
    printLog(logs);       
    printLog(logs.length); 
    var concatLogs = ''; 
    for(var i = 0; i < logs.length; i++){
        printLog(logs[i]);  
        var xmlParam = $(logs[i]);
        var whoAttr = xmlParam.attr("who");
        printLog(whoAttr); 
        var changes = xmlParam.find("changes");
        printLog(changes.text().trim()); 
        concatLogs =  concatLogs + whoAttr + '|' + changes.text().trim()  + '\n';      
    }
    logsField.value = concatLogs;
}