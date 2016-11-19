$(function(){
    $('#my_form').on('submit', function(event){
        event.preventDefault();//предотвращаем выполнение действия по умолчанию
        var frm = $(this); //сохраняем ссылку на форму		
	    var fData = JSON.stringify(frm.serializeObject());
		 
        $.ajax({
            url: frm.attr('action'), // путь к обработчику - берем из атрибута action
            type: frm.attr('method'), // метод передачи - берем из атрибута method
            data: {form_data: fData},
            dataType: 'json',
            success: function(data){
                if(data){
                    //form.replaceWith(data); // заменим форму данными, полученными в ответе.					 
					 var obj = $.parseJSON(data);	//делаем возвращенную сервером строку объектом и 
													//обращаемся к нему ниже; 
													//метод depricated, рекомендуется нативный
					 $('#result').html(data);// +'<br>'+ obj.login);//вывод ответа в div
                }
				//if (obj.login == 'Alex'){
				//location = 'test.html';//переход по ссылке на другую страницу,				                      
				//}					  //если логин Alex (будет другая проверка)				
			},
			error:  function(xhr, str){
                     $('#result').html('Критическая ошибка'); 
			}
        });
    });
});

