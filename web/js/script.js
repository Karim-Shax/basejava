function makeSectionFormAch(name) {
    let input = document.createElement('input');
    input.size = 100;
    input.type = 'text'
    input.name = name;
    document.getElementById(name).appendChild(input);
}

var educount = 0;

var excount = 0;

$(function () {
    $('#sect').click(function () {
        ++excount;
        let content = "<dd>\n" +
            "                                Кампания <input type=\"text\" name=\"EXPERIENCE\"\n" +
            "                                                size=60\"><br/> <br/>\n" +
            "                                Url Адрес <input type=\"text\" name=\"EXPERIENCE\" size=60\"><br/> <br/>\n" +
            "                            </dd>\n" +
            "                            <dd id=\"position\">\n" +
            "                                Начало\n" +
            "                                <input type=\"date\" name=\"EXPERIENCE\"\n" +
            "                                       size=60><br/> <br/>\n" +
            "                                Конец\n" +
            "                                <input type=\"date\"\n" +
            "                                       name=\"EXPERIENCE\" size=60><br/> <br/>\n" +
            "                                Позиция\n" +
            "                                <input type=\"text\"\n" +
            "                                       name=\"EXPERIENCE\"\n" +
            "                                       size=60><br/> <br/>\n" +
            "                                Описание\n" +
            "                                <input type=\"text\"\n" +
            "                                       name=\"EXPERIENCE\"\n" +
            "                                       size=60><br/> <br/><br/> <br/>\n" +
            "                            </dd>" +
            "\n" +
            "                            <button type=\"button\" id=\"expos" + excount + "\"  onclick=\"addPos(this.id)\">\n" +
            "                                Добавить Позицию\n" +
            "                            </button>\n" +
            "\n" +
            "<input type=\"hidden\" name=\"EXPERIENCE\" value=\"end\">"+
            "<hr>";
        let newdiv = $("<div>");
        newdiv.attr('id', 'ex' + excount);
        newdiv.html(content);
        $(this).after(newdiv);
    });
});


$(function () {
    $('#sect1').click(function () {
        ++educount;
        let content = "<dd>\n" +
            "                                Кампания <input type=\"text\" name=\"EDUCATION\"\n" +
            "                                                size=60\"><br/> <br/>\n" +
            "                                Url Адрес <input type=\"text\" name=\"EDUCATION\" size=60\"><br/> <br/>\n" +
            "                            </dd>\n" +
            "                            <dd id=\"position\">\n" +
            "                                Начало\n" +
            "                                <input type=\"date\" name=\"EDUCATION\"\n" +
            "                                       size=60><br/> <br/>\n" +
            "                                Конец\n" +
            "                                <input type=\"date\"\n" +
            "                                       name=\"EDUCATION\" size=60><br/> <br/>\n" +
            "                                Позиция\n" +
            "                                <input type=\"text\"\n" +
            "                                       name=\"EDUCATION\"\n" +
            "                                       size=60><br/> <br/>\n" +
            "                                Описание\n" +
            "                                <input type=\"text\"\n" +
            "                                       name=\"EDUCATION\"\n" +
            "                                       size=60><br/> <br/><br/> <br/>\n" +
            "                            </dd> " +
            "\n" +
            "                            <button type=\"button\" id=\"edpos" + educount + "\"  onclick=\"addPos(this.id)\">\n" +
            "                                Добавить Позицию\n" +
            "                            </button>\n" +
            "\n" +
            "<input type=\"hidden\" name=\"EDUCATION\" value=\"end\">" +
            "<hr>";
        let newdiv = $("<div>");
        newdiv.attr('id', 'edu' + educount);
        newdiv.html(content);
        $(this).after(newdiv);
    });
});

var posEX = '<dd>' +
    '                                Начало\n' +
    '                                <input type="date" name="EXPERIENCE"\n' +
    '                                       size=60><br/> <br/>\n' +
    '                                Конец\n' +
    '                                <input type="date"\n' +
    '                                       name="EXPERIENCE" size=60><br/> <br/>\n' +
    '                                Позиция\n' +
    '                                <input type="text"\n' +
    '                                       name="EXPERIENCE"\n' +
    '                                       size=60><br/> <br/>\n' +
    '                                Описание\n' +
    '                                <input type="text"\n' +
    '                                       name="EXPERIENCE"\n' +
    '                                       size=60><br/> <br/><br/> <br/>\n' +
    '                            </dd>';

var posEdu = '<dd>' +
    '                                Начало\n' +
    '                                <input type="date" name="EDUCATION"\n' +
    '                                       size=60><br/> <br/>\n' +
    '                                Конец\n' +
    '                                <input type="date"\n' +
    '                                       name="EDUCATION" size=60><br/> <br/>\n' +
    '                                Позиция\n' +
    '                                <input type="text"\n' +
    '                                       name="EDUCATION"\n' +
    '                                       size=60><br/> <br/>\n' +
    '                                Описание\n' +
    '                                <input type="text"\n' +
    '                                       name="EDUCATION"\n' +
    '                                       size=60><br/> <br/><br/> <br/>\n' +
    '                            </dd>';

function addPos(name){
    if (name.startsWith('edpos')){
        let buttom=document.getElementById(name);
        buttom.insertAdjacentHTML('beforebegin',posEdu);
    } else {
        let buttom=document.getElementById(name);
        buttom.insertAdjacentHTML('beforebegin',posEX);
    }
}
