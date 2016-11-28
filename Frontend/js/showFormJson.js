$(document).ready(function(){
    // Shows JSON in nice popup
    function showFormJson(json) {
        var jsonText = JSON.stringify(json, null, "    ");        
        $('#popup')
            .empty()
            .append( "<h3>Result of <code>$(<THE_FORM>).jqDynaForm('get')</code></h3>" )
            .append( $('<pre></pre>').append(jsonText) )
            .dialog({
                title: "JSON representation of the form",
                width: 600,
                height: 500
            });      
    }

    // получение данных из формы и демострация JSON во всплывающем окне
    $('#smallForm').jqDynaForm();
    //$('#smallForm').jqDynaForm('set', smallJson);
    $('#saveSmallForm').click(function(){    
        var json = $('#smallForm').jqDynaForm('get');
        showFormJson(json);          
    });
 });
		 