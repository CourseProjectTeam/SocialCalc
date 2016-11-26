//плагин для jQuery - расширение (новый метод) для объекта jQuery
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray(); // сериализируем данные	
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
